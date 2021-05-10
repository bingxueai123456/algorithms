package com.design.command;

/**
 * @Auther: eclair
 * @Date: 2019/9/6 18:54
 * @Description:
 */
public interface MacroCommand extends Command {
	void add(Command command);

	void remove(Command command);
}
