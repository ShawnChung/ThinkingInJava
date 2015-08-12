package org.shawnana.thinkinginjava.innerclasses.controller;

public class GreenhouseControls extends Controller {
	private boolean light = false;
	public class LightOn extends Event {
		public LightOn(long delayTime) {
			super(delayTime);
		}
		public void action() {
			light = true;
		}
		public String toString() {
			return "Light is on";
		}
	}
	public class LightOff extends Event {
		public LightOff(long delayTime) {
			super(delayTime);
		}
		public void action() {
			light = false;
		}
		public String toString() {
			return "Light is false";
		}
	}
	
	private boolean water = false;
	public class WaterOn extends Event {
		public WaterOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = true;
		}
		
		public  String toString() {
			return "Water is on";
		}
	}
	public class WaterOff extends Event {
		public WaterOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = false;
		}
		
		public  String toString() {
			return "Water is off";
		}
	}
	
	private String thermostat = "Day";
	public class ThermostatNight extends Event {

		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Night";
		}
		
		public String toString() {
			return "Thermostat on night setting";
		}
	}
	public class ThermostatDay extends Event {

		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Day";
		}
		
		public String toString() {
			return "Thermostat on day setting";
		}
	}
	
	private boolean fans = false;
	public class FansOn extends Event {

		public FansOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			fans = true;
		}
		
		public String toString() {
			return "Fans is on";
		}
	}
	
	public class FansOff extends Event {

		public FansOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			fans = false;
		}
		
		public String toString() {
			return "Fans is off";
		}
	}
	
	public class Bell extends Event {
		public Bell(long delayTime) {
			super(delayTime);
		}
		public void action() {
			addEvent(new Bell(delayTime));
		}
		public String toString() {
			return "Bing!";
		}
	}
	
	public class Restart extends Event {
		private Event[] eventList;
		public Restart(long delayTime, Event[] eventList) {
			super(delayTime);
			this.eventList = eventList;
			for (Event e : eventList) {
				addEvent(e);
			}
		}
		@Override
		public void action() {
			for (Event e : eventList) {
				e.start();
				addEvent(e);
			}
			start();
			addEvent(this);
		}
		
		public String toString() {
			return "Restarting system";
		}
	}
	
	public static class Terminate extends Event {

		public Terminate(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			System.exit(0);
		}
		
		public String toString() {
			return "System terminate";
		}
	}
}

class CustomGreenhouseControls extends GreenhouseControls {
	private boolean mist = false;
	public class MistOn extends Event {

		public MistOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			mist = true;
		}
		
		public String toString() {
			return "Water mist is on";
		}
		
	}
	
	public class MistOff extends Event {

		public MistOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			mist = false;
		}
		
		public String toString() {
			return "Water mist is off";
		}
		
	}
}
