package com.w3ma.noteapp.network.clients;

import com.w3ma.noteapp.business.util.GsonUtil;

/**
 * Created by Emanuele on 21/07/2016.
 */
public class MyServerClientImpl extends MyServerClient {

    private static final String SERVER_URL = "http://myserver";
    public static final String NAME = "MyServerClientImpl";

    public MyServerClientImpl(final GsonUtil gsonUtil) {
        super(SERVER_URL, gsonUtil, null);
    }

}
