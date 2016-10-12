package com.w3ma.noteapp.network;

import android.content.Context;

import com.tientun.mockresponse.FakeInterceptor;

import java.io.IOException;
import java.util.Random;

import okhttp3.Interceptor;

/**
 * Created by Emanuele on 06/10/2016.
 */

public class DelayedFakeInterceptor extends FakeInterceptor {

    public DelayedFakeInterceptor(final Context context) {
        super(context);
    }

    @Override
    public okhttp3.Response intercept(final Interceptor.Chain chain) throws IOException {
        final okhttp3.Response response = super.intercept(chain);
        //this is just to add some delay to the responses to simulate network delays
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (final InterruptedException e) {
            //nothing
        }
        return response;
    }
}
