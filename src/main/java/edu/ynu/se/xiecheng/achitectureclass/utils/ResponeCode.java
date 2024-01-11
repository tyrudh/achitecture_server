package edu.ynu.se.xiecheng.achitectureclass.utils;
/**
 * @功能描述: 响应报文，统一封装类
 */
public enum ResponeCode {
    SUCCESS(200, "验证成功"),
    FAIL(400, "验证失败"),
    UNAUTHORIZED(401, "缺少认证信息"),
    NOT_FOUND(404, "接口不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");
    private int code;
    private String msg;
    private ResponeCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }
}
