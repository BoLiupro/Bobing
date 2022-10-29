package com.ee308.bobing.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NieChangan
 * @Description 公共结果返回
 */
@Data
public class ResponseResult {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();

    /**
     * 构造方法私有化,里面的方法都是静态方法
     * 达到保护属性的作用
     */
    private ResponseResult(){

    }

    /**
     * 这里是使用链式编程
     */
    public static ResponseResult ok(){
        ResponseResult result = new ResponseResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static ResponseResult error(){
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }

    public static ResponseResult error(ResultCode resultCode){
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 自定义返回成功与否
     * @param success
     * @return
     */
    public ResponseResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResponseResult message(String message){
        this.setMessage(message);
        return this;
    }

    public ResponseResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResponseResult data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    public ResponseResult data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}