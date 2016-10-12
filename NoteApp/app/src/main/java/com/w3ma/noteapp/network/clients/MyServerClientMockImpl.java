package com.w3ma.noteapp.network.clients;

import android.content.Context;

import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.network.DelayedFakeInterceptor;

/**
 * Created by Emanuele on 30/09/2016.
 */

public class MyServerClientMockImpl extends MyServerClient {

    public static final String NAME = "MyServerClientMockImpl";
    private static final String SERVER_URL = "http://mock.api";

    public MyServerClientMockImpl(final Context context, final GsonUtil gsonUtil) {
        super(SERVER_URL, gsonUtil, new DelayedFakeInterceptor(context));
    }

}
