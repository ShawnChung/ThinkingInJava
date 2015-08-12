package org.shawnana.thinkinginjava.innerclasses.controller;

public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	public Event(long delayTime) {
		this.delayTime = delayTime;
		start();
	}
	
	public void start() { // Allow restarting
		eventTime = System.nanoTime() + delayTime;
	}
	
	public boolean ready() {
		return System.nanoTime() - eventTime >= 0;
	}
	
	public abstract void action();
}
