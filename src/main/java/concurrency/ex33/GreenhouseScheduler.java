package concurrency.ex33;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GreenhouseScheduler {
	private volatile boolean light = false;
	private volatile boolean water = false;
	private String thermostat = "Day";
	public synchronized String getThermostat() {
		return thermostat;
	}
	public synchronized void setThermostat(String value) {
		this.thermostat = value;
	}
	private DelayQueue<DelayedTask> dalayedTasks = new DelayQueue<DelayedTask>();
	public void schedule(DelayedTask t) {
		dalayedTasks.add(t);
	}
	class DelayedTask implements Runnable, Delayed {
		private final int delay;
		private final long trigger;
		private final boolean repeatable;
		private final long period;
		public DelayedTask(int delayInMilliseconds, boolean repeatable, long period) {
			delay = delayInMilliseconds;
			trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delay, TimeUnit.MILLISECONDS);
			this.repeatable = repeatable;
			this.period = period;
		}
		public long getDelay(TimeUnit unit) {
			return unit.convert(trigger - System.nanoTime(), unit.NANOSECONDS);
		}
		public int getDelay() {
			return delay;
		}
		public int compareTo(Delayed arg) {
			DelayedTask that = (DelayedTask) arg;
			if (trigger < that.trigger) return -1;
			if (trigger > that.trigger) return 1;
			return 0;
		}
		public void run() {
			//System.out.println(this + " ");
		}
		public long getTrigger() {
			return trigger;
		}
		public boolean repeatable() {
			return repeatable;
		}
		public long getPeriod() {
			return this.period;
		}
	}
	class LightOn extends DelayedTask {
		public LightOn(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Turning on lights");
			light = true;
			if (this.repeatable()) {
				dalayedTasks.add(new LightOn(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	class LightOff extends DelayedTask {
		public LightOff(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Turning off lights");
			light = false;
			if (this.repeatable()) {
				dalayedTasks.add(new LightOff(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	class WaterOn extends DelayedTask {
		public WaterOn(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Turning on water");
			water = true;
			if (this.repeatable()) {
				dalayedTasks.add(new WaterOn(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	class WaterOff extends DelayedTask {
		public WaterOff(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Turning off water");
			water = false;
			if (this.repeatable()) {
				dalayedTasks.add(new WaterOff(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	class ThermostatNight extends DelayedTask {
		public ThermostatNight(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Thermostat to night setting");
			GreenhouseScheduler.this.setThermostat("Night");
			//thermostat = "Night";
			if (this.repeatable()) {
				dalayedTasks.add(new ThermostatNight(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	class ThermostatDay extends DelayedTask {
		public ThermostatDay(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Thermostat to day setting");
			GreenhouseScheduler.this.setThermostat("Day");
			//thermostat = "Day";
			if (this.repeatable()) {
				dalayedTasks.add(new ThermostatDay(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	class Bell extends DelayedTask {
		public Bell(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Bing!");
			if (this.repeatable()) {
				dalayedTasks.add(new Bell(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	class Terminate extends DelayedTask {
		private ExecutorService exec;
		public Terminate(int delayInMilliseconds, boolean repeatable, long period, ExecutorService exec) {
			super(delayInMilliseconds, repeatable, period);
			this.exec = exec;
		}

		public void run() {
			// Must start a separate task to do this job,
			// since the scheduler has been shut down
			exec.shutdownNow();
			new Thread() {
				public void run() {
					for(DataPoint d : data) {
						System.out.println(d);
					}
				}
			}.start();
		}
	}
	static class DataPoint {
		final Calendar time;
		final float temperature;
		final float humidity;
		public DataPoint(Calendar d, float temp, float hum) {
			time = d;
			temperature = temp;
			humidity = hum;
		}
		public String toString() {
			return time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
		}
	}
	class DelayTaskConsumer implements Runnable {
		@Override
		public void run() {
			try {
				while (!Thread.interrupted()) {
					DelayedTask delayedTask = dalayedTasks.take();
					delayedTask.run();
				}
			} catch (InterruptedException e) {
				
			}
			System.out.println("Finished DelayedTaskConsumer");
		}
	}
	private Calendar lastTime = Calendar.getInstance();
	{
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}
	private float lastTemp = 65.0f;
	private int tempDirection = +1;
	private float lastHumidity = 50.0f;
	private int humidityDirection = +1;
	private Random rand = new Random(47);
	List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());
	class CollectData extends DelayedTask {
		public CollectData(int delayInMilliseconds, boolean repeatable, long period) {
			super(delayInMilliseconds, repeatable, period);
		}

		public void run() {
			System.out.println("Collecting data");
			synchronized(GreenhouseScheduler.this) {
				// Pretend the interval is longer than it is:
				lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
				// One in 5 chances of reversing the direction:
				if (rand.nextInt(5) == 4) 
					tempDirection = -tempDirection;
				lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
				if (rand.nextInt(5) == 4)
					humidityDirection = -humidityDirection;
				lastHumidity = lastHumidity + humidityDirection * rand.nextFloat();
				// Calendar must be cloned, otherwise all 
				// DataPoints hold references to the same lastTime
				// for a basic object like Calendar, clone() is OK
				data.add(new DataPoint((Calendar)lastTime.clone(), lastTemp, lastHumidity));
			}
			if (this.repeatable()) {
				dalayedTasks.add(new CollectData(getDelay(), repeatable(), getPeriod()));
			}
		}
	}
	
	public static void main(String[] args) {
		GreenhouseScheduler gh = new GreenhouseScheduler();
		ExecutorService exec = Executors.newCachedThreadPool();
		gh.schedule(gh.new Terminate(5000, false, 0, exec));
		gh.schedule(gh.new Bell(100, true, 0));
		gh.schedule(gh.new ThermostatNight(2000, true, 0));
		gh.schedule(gh.new LightOn(200, true, 0));
		gh.schedule(gh.new LightOff(400, true, 0));
		gh.schedule(gh.new WaterOn(600, true, 0));
		gh.schedule(gh.new WaterOff(800, true, 0));
		gh.schedule(gh.new ThermostatDay(1400, true, 0));
		gh.schedule(gh.new CollectData(500, true, 500));
		exec.execute(gh.new DelayTaskConsumer());
	}
}
