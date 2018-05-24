package com.ralap.blog.bussiness.vo;

import com.ralap.blog.framework.objecct.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ralap
 * @date: created at 2018/5/19 22:17
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class FileConditionVO extends BaseConditionVO {

    private String fileName;
    private String filePath;

}
