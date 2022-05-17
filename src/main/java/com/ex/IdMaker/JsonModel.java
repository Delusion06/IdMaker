package com.ex.IdMaker;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Exception
 * @create 2021-07-17-15:22
 * @content
 */
@Data
//使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@AllArgsConstructor
public class JsonModel {
    private Integer code;
    private String msg;
    private Object result;
}
