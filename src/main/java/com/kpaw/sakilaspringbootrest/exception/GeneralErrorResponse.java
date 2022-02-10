package com.kpaw.sakilaspringbootrest.exception;

public class GeneralErrorResponse {
    private final int status;
    private final String message;
    private final long timeStamp;


    public GeneralErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}

