package com.w3ma.noteapp.network.api;

import com.w3ma.noteapp.datasource.local.db.Note;
import com.w3ma.noteapp.network.model.user.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Emanuele on 21/07/2016.
 */
public interface MyServerService {

    @GET("/notes")
    Call<List<Note>> getNoteList();

    @GET("/userInfo")
    Call<UserInfo> getUserInfo();

    @GET("/notes/{id}")
    Call<Note> getNote(@Path("id") Long id);

}
