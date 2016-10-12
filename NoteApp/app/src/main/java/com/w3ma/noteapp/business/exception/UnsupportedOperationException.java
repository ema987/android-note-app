package com.w3ma.noteapp.business.exception;

/**
 * Created by Emanuele on 30/09/2016.
 */

public class UnsupportedOperationException extends RuntimeException {

    public UnsupportedOperationException() {
        super("This operation is not supported");
    }
}
