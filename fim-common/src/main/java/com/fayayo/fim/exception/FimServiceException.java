package com.fayayo.fim.exception;

import com.fayayo.fim.result.ResultEnum;
import lombok.Getter;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc
 */
@Getter
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
