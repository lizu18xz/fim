package com.fayayo.fim.connector.handler;

import com.fayayo.fim.common.exception.FimServiceException;
import com.fayayo.fim.common.result.ResultEnum;
import com.fayayo.fim.common.result.ResultVO;
import com.fayayo.fim.common.result.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dalizu on 2019/1/14.
 * @version v1.0
 * @desc
 */
@ControllerAdvice
public class ConnectorExceptionHandler {


    /**

     *@描述 处理项目中自定义的异常CommonException

     *@创建人  dalizu

     *@创建时间  2018/8/5

     */
    @ExceptionHandler(value = FimServiceException.class)
    @ResponseBody
    public ResultVO handlerCommonException(FimServiceException e){
        e.printStackTrace();
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handlerCommonException(Exception e){

        e.printStackTrace();

        return ResultVOUtil.error(ResultEnum.UNKNOWN_ERROR);
    }



}
