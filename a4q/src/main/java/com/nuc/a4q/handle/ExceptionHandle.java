package com.nuc.a4q.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.entity.Result;
import com.nuc.a4q.enums.ResultEnum;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.utils.ResultUtil;

@ControllerAdvice
public class ExceptionHandle {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	/**
	 * 未知异常处理
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result unknowEexceptionHandle(RuntimeException e) {
		logger.error("-----系统内部错误-----{}", e);
		return ResultUtil.error(ResultEnum.INNER_ERROR.getState(), ResultEnum.INNER_ERROR.getStateInfo());
	}

	/**
	 * 对验证时的一些异常处理
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(LogicException.class)
	@ResponseBody
	public Result logicExceptionHandle(LogicException e) {
		return ResultUtil.error(e.getState(), e.getMessage());
	}
}
