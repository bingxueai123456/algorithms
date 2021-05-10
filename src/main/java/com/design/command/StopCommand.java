package com.design.command;

/**
 * @Auther: eclair
 * @Date: 2019/9/6 18:47
 * @Description:
 */
public class StopCommand implements Command {
	AudioPlayer audioPlayer;

	public StopCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	@Override
	public void execute() {
		audioPlayer.stop();
	}
}
