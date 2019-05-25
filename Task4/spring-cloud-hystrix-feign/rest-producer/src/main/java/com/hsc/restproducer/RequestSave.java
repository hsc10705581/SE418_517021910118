package com.hsc.restproducer;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;

public class RequestSave {

    private HttpServletResponse response;
    private Instant time;

    public RequestSave(){}
    public RequestSave(HttpServletResponse response, Instant time){
        this.response = response;
        this.time = time;
    }

    public Instant getTime() {
        return time;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
