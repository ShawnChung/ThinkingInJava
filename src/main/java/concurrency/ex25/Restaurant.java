package concurrency.ex25;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Meal {
	private final int orderNum;
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable {
	private Restaurant restaurant;
	public WaitPerson(Restaurant r) {
		restaurant = r;
	}
	
	public void run() {
		try {
			while (!Thread.interrupted()) {
				restaurant.lock.lock();
				try {
					while (restaurant.meal == null)
						restaurant.condition.await();
				} finally {
					restaurant.lock.unlock();
				}
				System.out.println("Waitperson got " + restaurant.meal);
				restaurant.lock.lock();
				try {
					restaurant.meal = null;
					restaurant.condition.signalAll();
				} finally {
					restaurant.lock.unlock();
				}
				/*System.out.println("Waitperson deliver meal");
				synchronized(restaurant.busBoy) {
					restaurant.busBoy.notifyAll();
				}*/
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;
	public Chef(Restaurant r) {
		restaurant = r;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				restaurant.lock.lock();
				try {
					while (restaurant.meal != null)
						restaurant.condition.await();
				} finally {
					restaurant.lock.unlock();
				}
				if (++count == 10) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
					//return; // The following codes will not be executed after return
				}
				
				System.out.println("Order Up!");
				restaurant.lock.lock();
				try {
					restaurant.meal = new Meal(count);
					restaurant.condition.signalAll();
				}  finally {
					restaurant.lock.unlock();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
			
		} catch (InterruptedException e) {
			
		}
	}
}

class BusBoy implements Runnable {
	private Restaurant restaurant;
	public BusBoy(Restaurant r) {
		restaurant = r;
	}
	public void run() {
		try {
			synchronized(this) {
				while (true) {
					wait();
					System.out.println("Cleaning up");
				}
			}
		} catch(InterruptedException e) {
			
		}
	}
}

public class Restaurant {
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	//BusBoy busBoy = new BusBoy(this);
	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
		//exec.execute(busBoy);
	}
	public static void main(String[] args) {
		new Restaurant();
	}
}
