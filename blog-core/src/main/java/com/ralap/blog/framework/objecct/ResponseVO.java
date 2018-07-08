package com.ralap.blog.framework.objecct;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import java.util.Collection;
import java.util.List;
import lombok.Data;

/**
 * @author: ralap
 * @date: created at 2018/5/19 11:24
 */
@Data
public class ResponseVO<T> {

    private Integer status;
    private String msg;
    private T data;

    public ResponseVO(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVO(ResponseStatus status, T data) {
        this.status = status.getCode();
        this.msg = status.getMsg();
        this.data = data;
    }

    public String toJson() {
        T t = this.getData();
        if (t instanceof List || t instanceof Collection) {
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty);
        } else {
            return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
        }
    }


}
