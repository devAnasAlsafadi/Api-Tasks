package com.ti.apitraining.enums;

public enum ApiUri {
    Login("/api/students/auth/login"),
    Logout("/api/ "),
        Register("/api/students/auth/register");

    public final String urn;

     ApiUri(String urn){
        String url = "http://demo-api.mr-dev.tech";
        this.urn = url+urn;
    }

}
