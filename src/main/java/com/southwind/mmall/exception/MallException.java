package com.southwind.mmall.exception;

import com.southwind.mmall.enums.ResultEnum;

public class MallException extends RuntimeException {
    public MallException(String error) {
        super(error);
    }

    public MallException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
