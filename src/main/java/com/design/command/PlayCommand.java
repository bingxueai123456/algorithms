package com.design.command;

/**
 * @Auther: eclair
 * @Date: 2019/9/6 18:47
 * @Description:
 */
public class PlayCommand implements Command {
	AudioPlayer audioPlayer;

	public PlayCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	@Override
	public void execute() {
		audioPlayer.play();
	}
}
