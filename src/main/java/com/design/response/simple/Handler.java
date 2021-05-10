package com.design.response.simple;

import java.util.Objects;

/**
 * @Auther: eclair
 * @Date: 2019/8/31 20:36
 * @Description:
 */
public class Handler extends AbstractHandler {
	@Override
	public void handleExecute() {
		if (Objects.nonNull(getSuccess())) {
			System.out.println("我不处理。。。");
			getSuccess().handleExecute();
		} else {
			System.out.println("自行处理");
		}
	}

	public static void main(String[] args) {
		Handler handler = new Handler();
		Handler handler1 = new Handler();
		handler.setSuccess(handler1);
		handler.handleExecute();
	}
}
