package edu.ynu.se.xiecheng.achitectureclass.utils;
import lombok.Data;

import java.io.Serializable;
/**
 * @功能描述: 响应报文，统一封装类
 */
@Data
public class Response<T> implements Serializable {
    private int code;
    private String msg;
    private T data;
    private String trace;
    private long timestamp;
    public Response() {
        this.timestamp = System.currentTimeMillis();
    }
    public Response(ResponeCode responseCode) {
        this.timestamp = System.currentTimeMillis();
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }
    public Response(ResponeCode responseCode, String msg) {
        this(responseCode);
        this.msg = msg;
    }
    public Response(ResponeCode responseCode, T data) {
        this(responseCode);
        this.data = data;
    }
    public Response(ResponeCode responseCode, String msg, T data) {
        this(responseCode);
        this.trace = msg;
        this.data = data;
    }
    @Override
    public String toString() {
        return "APIResponse{" +
                "timestamp=" + timestamp +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", trace='" + trace + '\'' +
                ", data=" + data +
                //", count=" + count +
                '}';
    }
}
