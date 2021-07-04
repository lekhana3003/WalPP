package com.example.WalPP.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

enum Status {
    OK, BAD_REQUEST, EXCEPTION, NOT_FOUND, DUPLICATE_ENTITY,LOW_BALANCE
}
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private Status status;
    private T payload;
    private String errors;

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }


    public void setErrors(String errors) {
        this.errors = errors;
    }

 public static <T> Response<T> ValidResponse(T payload){
     Response<T> response = new Response<>();
     response.setPayload(payload);
     response.setStatus(Status.OK);
     return response;
 }
    public static <T> Response<T> NotFound(String message){
        Response<T> response = new Response<>();
        response.setErrors(message);
        response.setStatus(Status.NOT_FOUND);
        return response;
    }
    public static <T> Response<T> DuplicateEntity(String message){
        Response<T> response = new Response<>();
        response.setErrors(message);
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }
    public static <T> Response<T> Exception(String message){
        Response<T> response = new Response<>();
        response.setErrors(message);
        response.setStatus(Status.EXCEPTION);
        return response;
    }
    public static <T> Response<T> BadRequest(String message){
        Response<T> response = new Response<>();
        response.setErrors(message);
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }
    public static <T> Response<T> LowBalance(String message){
        Response<T> response = new Response<>();
        response.setErrors(message);
        response.setStatus(Status.LOW_BALANCE);
        return response;
    }
}
