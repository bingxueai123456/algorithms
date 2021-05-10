package com.design.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: eclair
 * @Date: 2019/9/6 18:54
 * @Description:
 */
public class ConcreteMacroCommand implements MacroCommand {
	private List<Command> list = new ArrayList<>();

	@Override
	public void add(Command command) {
		list.add(command);
	}

	@Override
	public void remove(Command command) {
		list.remove(command);
	}

	@Override
	public void execute() {
		for (Command command : list) {
			command.execute();
		}
	}
}
