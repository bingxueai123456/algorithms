package com.reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MyEventSource {

	private List<MyEventListener> listeners;

	public MyEventSource() {
		this.listeners = new ArrayList<>();
	}

	public void register(MyEventListener listener) {    // 1
		listeners.add(listener);
	}

	public void newEvent(MyEvent event) {
		for (MyEventListener listener :
				listeners) {
			listener.onNewEvent(event);     // 2
		}
	}

	public void eventStopped() {
		for (MyEventListener listener :
				listeners) {
			listener.onEventStopped();      // 3
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MyEvent {   // 4
		private Date timeStemp;
		private String message;
	}

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		LinkedList<Integer> list =new LinkedList<>();
//		list.offer(1);
//		list.offer(2);
//		list.offer(3);
//		System.out.println(list);
//		list.poll();
//		System.out.println(list);
//		list.poll();
//		System.out.println(list);
//		list.poll();

//		list.push(1);
//		list.push(2);
//		list.push(3);
//		System.out.println(list);
//		list.poll();
//		System.out.println(list);
//		list.getFirst();
//		System.out.println(list);
//		list.peek();
//		LinkedList list1 = new LinkedList();
//		list1.toString();
		CodeSource codeSource = MyEventSource.class.getProtectionDomain().getCodeSource();
		System.out.println(codeSource.getLocation().getFile());
	}
}