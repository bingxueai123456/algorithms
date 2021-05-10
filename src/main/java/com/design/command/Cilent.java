package com.design.command;

/**
 * @Auther: eclair
 * @Date: 2019/9/6 18:48
 * @Description:
 */
public class Cilent {
	public static void main(String[] args) {
		Invoker invoker = new Invoker();
		AudioPlayer au = new AudioPlayer();
		Command play = new PlayCommand(au);
		Command stop = new StopCommand(au);
		invoker.setPlayCom(play);
		invoker.setStopCom(stop);
		invoker.play();
		invoker.stop();
		MacroCommand macroCommand = new ConcreteMacroCommand();
		macroCommand.add(play);
		macroCommand.add(stop);
		macroCommand.execute();
	}
}
