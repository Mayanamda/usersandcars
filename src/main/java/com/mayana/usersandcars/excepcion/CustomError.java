package com.mayana.usersandcars.excepcion;

public class CustomError {
    private String message;

    public CustomError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }    

}
