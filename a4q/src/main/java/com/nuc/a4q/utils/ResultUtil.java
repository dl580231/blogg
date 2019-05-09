package com.nuc.a4q.utils;

import com.nuc.a4q.entity.Result;
import com.nuc.a4q.enums.ResultEnum;

public class ResultUtil {
	public static Result success(Object data) {
		Result result = new Result();
		result.setState(ResultEnum.SUCCESS.getState());
		result.setStateInfo(ResultEnum.SUCCESS.getStateInfo());
		result.setData(data);
		return result;
	}

	public static Result success() {
		return success(null);
	}

	public static Result error(Integer state, String stateInfo) {
		Result result = new Result();
		result.setState(state);
		result.setStateInfo(stateInfo);
		return result;
	}
	
	public static Result error(String stateInfo) {
		Result result = new Result();
		result.setState(400);
		result.setStateInfo(stateInfo);
		return result;
	}
}
