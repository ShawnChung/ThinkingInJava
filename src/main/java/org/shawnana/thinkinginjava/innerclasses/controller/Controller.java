package org.shawnana.thinkinginjava.innerclasses.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Controller {
	private List<Event> eventList = new LinkedList<Event>();
	public void addEvent(Event e) {
		eventList.add(e);
	}
	public void run() {
		while (eventList.size() > 0) {
			Iterator<Event> iterator = new LinkedList<Event>(eventList).iterator();
			while (iterator.hasNext()) {
				Event e = iterator.next();
				if (e.ready()) {
					System.out.println(e);
					e.action();
					eventList.remove(e);
				}
			}
		}
	}
}
