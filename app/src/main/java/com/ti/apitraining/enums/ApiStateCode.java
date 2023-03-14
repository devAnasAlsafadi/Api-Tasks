package com.ti.apitraining.enums;

public enum ApiStateCode {
    Ok(200),
    Created(201),
    Accepted(202),
    NoContent(204),
    BadRequest(400),
    UnAuthenticated(401),
    ForBidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    ServerError(500);


    public final int code;

    ApiStateCode(int code){
        this.code = code;
    }
}
