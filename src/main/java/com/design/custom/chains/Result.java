package com.design.custom.chains;

/**
 * @Auther: eclair
 * @Date: 2019/9/5 23:06
 * @Description:
 */
public class Result {
	private boolean isPassed;
	private String message;

	public Result(boolean isPassed, String message) {
		this.isPassed = isPassed;
		this.message = message;
	}

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean passed) {
		isPassed = passed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
