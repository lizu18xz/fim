package com.fayayo.fim.common.exception;

import com.fayayo.fim.common.result.ResultEnum;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc
 */
public class FimServiceException extends RuntimeException {


    private Integer code;

    public FimServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public FimServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public FimServiceException(FimErrorMsg fimErrorMsg) {
        super(fimErrorMsg.getMessage());
        this.code=fimErrorMsg.getErrorCode();
    }
}
