package org.shawnana.thinkinginjava.innerclasses.controller;

public class GreenHouseController {
	public static void main(String[] args) {
		CustomGreenhouseControls gc = new CustomGreenhouseControls();
		gc.addEvent(gc.new Bell(900));
		Event[] eventList = {
				gc.new ThermostatNight(0),
				gc.new LightOn(200),
				gc.new LightOff(400),
				gc.new WaterOn(600),
				gc.new WaterOff(800),
				gc.new FansOn(1000),
				gc.new FansOff(1200),
				gc.new MistOn(1400),
				gc.new MistOff(1600),
				gc.new ThermostatDay(2000)
		};
		gc.addEvent(gc.new Restart(2400, eventList));
		if (args.length == 1) {
			gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0])));
		}
		gc.run();
			
	}
}
