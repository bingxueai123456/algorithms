package com.design.command;

/**
 * @Auther: eclair
 * @Date: 2019/9/6 18:49
 * @Description:
 */
public class Invoker {
	private Command playCom;
	private Command stopCom;

	public void play() {
		playCom.execute();
	}

	public void stop() {
		stopCom.execute();
	}

	public Command getPlayCom() {
		return playCom;
	}

	public void setPlayCom(Command playCom) {
		this.playCom = playCom;
	}

	public Command getStopCom() {
		return stopCom;
	}

	public void setStopCom(Command stopCom) {
		this.stopCom = stopCom;
	}
}
