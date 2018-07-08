package com.ralap.blog.util;

import com.github.pagehelper.PageInfo;
import com.qiniu.util.StringMap;
import com.ralap.blog.bussiness.consts.CommonConst;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: ralap
 * @date: created at 2018/5/19 11:54
 */
public class ResultUtil {

    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, StringMap model) {
        return new ModelAndView(view, model.map());
    }

    public static ResponseVO error(String msg) {
        return result(CommonConst.DEFAULT_ERROR_CODE, msg, null);
    }


    public static ResponseVO success(String msg, Object obj) {
        return result(CommonConst.DEFAULT_SUCCESS_CODE, msg, obj);
    }

    public static ResponseVO success(ResponseStatus status) {
        return result(status.getCode(), status.getMsg(), null);
    }

    public static ResponseVO result(int code, String msg, Object object) {
        return new ResponseVO(code, msg, object);
    }

    public static PageResult tablePage(Long total, List<?> rows) {
        return new PageResult(total, rows);
    }

    public static PageResult tablePage(PageInfo pageInfo) {
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

}
