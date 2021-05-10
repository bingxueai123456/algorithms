package com.design.response.simple;

/**
 * @Auther: eclair
 * @Date: 2019/8/31 20:36
 * @Description:
 */
public abstract class AbstractHandler {
	public Handler success;

	public Handler getSuccess() {
		return success;
	}

	public void setSuccess(Handler success) {
		this.success = success;
	}

	public abstract void handleExecute();

}
