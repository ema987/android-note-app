package com.w3ma.noteapp.network.clients;

import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.datasource.local.db.Note;
import com.w3ma.noteapp.network.api.MyServerService;
import com.w3ma.noteapp.network.model.user.UserInfo;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Emanuele on 30/09/2016.
 */

public abstract class MyServerClient {

    protected final MyServerService myServerService;

    public MyServerClient(final String baseUrl, final GsonUtil gsonUtil, final Interceptor... interceptors) {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        for (final Interceptor interceptor : interceptors) {
            clientBuilder.addInterceptor(interceptor);
        }

        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gsonUtil.getGson()))
                .client(clientBuilder.build())
                .baseUrl(baseUrl)
                .build();

        myServerService = retrofit.create(MyServerService.class);
    }

    public void getNoteList(final MyServerClientListener<List<Note>> myServerClientListener) {
        myServerClientListener.onStart();
        myServerService.getNoteList().enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(final Call<List<Note>> call, final Response<List<Note>> response) {
                myServerClientListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(final Call<List<Note>> call, final Throwable t) {
                myServerClientListener.onFailure(t.getMessage());
            }
        });
    }

    public void getUserInfo(final MyServerClientListener<UserInfo> myServerClientListener) {
        myServerClientListener.onStart();
        myServerService.getUserInfo().enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(final Call<UserInfo> call, final Response<UserInfo> response) {
                myServerClientListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(final Call<UserInfo> call, final Throwable t) {
                myServerClientListener.onFailure(t.getMessage());
            }
        });
    }

    public void getNote(final Long id, final MyServerClientListener<Note> myServerClientListener) {
        myServerClientListener.onStart();
        myServerService.getNote(id).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(final Call<Note> call, final Response<Note> response) {
                myServerClientListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(final Call<Note> call, final Throwable t) {
                myServerClientListener.onFailure(t.getMessage());
            }
        });
    }

    public interface MyServerClientListener<T extends Object> {
        void onSuccess(T t);

        void onFailure(String message);

        void onStart();
    }

}
