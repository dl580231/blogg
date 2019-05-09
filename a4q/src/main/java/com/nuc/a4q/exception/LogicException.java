package com.nuc.a4q.exception;

public class LogicException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5107764401210482770L;

	private Integer state;

	public LogicException(Integer state, String stateInfo) {
		super(stateInfo);
		this.state = state;
	}

	public LogicException(String stateInfo) {
		super(stateInfo);
		this.state = 400;
	}

	public Integer getState() {
		return state;
	}

}
