package cn.l.x.common.bean;

import java.io.Serializable;

import org.springframework.util.StringUtils;

/**
 * HTTP请求返回消息类型。
 * 
 * @author 马岳
 *
 */
public class Result<T> implements Serializable {
    /**
     * 序列化版本号。
     */
    private static final long serialVersionUID = -6421279427859768728L;

    /**
     * 状态码。
     */
    private String code = "000";

    /**
     * HTTP 状态值(暂不使用，直接使用 HTTP RESPONSE 的返回状态)。
     */
    private int httpStatusCode;

    /**
     * 错误信息。
     */
    private String msg;

    /**
     * 返回对象(具体类型参照具体的HTTP请求)。
     */
    private T t;

    /**
     * 默认构造函数。
     */
    public Result() {
    }

    /**
     * 构造函数。
     * 
     * @param code 状态码。
     */
    public Result(String code) {
        this.code = code;
    }

    /**
     * 构造函数。
     * 
     * @param code 状态码。
     * @param msg 返回消息。
     */
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造函数。
     * 
     * @param code 状态码。
     * @param msg 返回消息。
     * @param obj 返回对象。
     */
    public Result(String code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.t = t;
    }

    /**
     * 从目标 Result 中设置返回值和消息。
     * 
     * @param result 目标 Result
     * @param args 消息的格式化参数(仅当目标 Result 的 msg 字段为格式化字符串时可用)
     * @return 本实例
     */
    public Result setCodeMsg(Result result, Object... args) {
        if (result != this) {
            setCode(result.getCode());
            String resultMsg = result.getMsg();
            if (args == null || args.length < 1) { // 未指定参数
                // 消息内容保持不变 (result.msg)
            } else if (StringUtils.isEmpty(resultMsg)) { // 未指定消息格式
                // 使用第一个参数作为消息
                resultMsg = "" + args[0];
            } else { // 指定了消息格式和参数
                resultMsg = String.format(resultMsg, args);
            }
            setMsg(resultMsg);
        }
        return this;
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return t;
    }

    public void setObj(T t) {
        this.t = t;
    }

}
