package com.morgan.project1.servicebookingsystem.payload;

import com.morgan.project1.servicebookingsystem.enums.Status;
import lombok.Data;

@Data
public class Response {
    private int statusCode;
    private Status response;

    public Response(int statusCode,Status response){
        this.statusCode=statusCode;
        this.response=response;
    }
}
