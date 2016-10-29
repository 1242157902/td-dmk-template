package com.talkingdata.domain;

/**
 * User£º    ysl
 * Date:   2016/10/18
 * Time:   17:29
 */
public class AuthResponse {

    private int code ;
    private String status ;
    private String message ;
    private String time ;
    private String data ;
    private String trace ;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", data='" + data + '\'' +
                ", trace='" + trace + '\'' +
                '}';
    }
}
