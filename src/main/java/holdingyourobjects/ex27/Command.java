package holdingyourobjects.ex27;

import java.util.LinkedList;
import java.util.Queue;

public class Command {
	private String command;
	
	public Command(String command) {
		this.command = command;
	}
	
	public void operation() { 
		System.out.println(command);
	}
	
	public static void main(String[] args) {
		QueueExecutor.execute(QueueFillor.getQueue());
	}
}

class QueueFillor {
	public static Queue<Command> getQueue() {
		Queue<Command> queue = new LinkedList<Command>();
		queue.offer(new Command("Ctrl+C"));
		queue.offer(new Command("Ctrl+V"));
		queue.offer(new Command("Ctrl+S"));
		queue.offer(new Command("Ctrl+A"));
		queue.offer(new Command("Ctrl+Z"));
		queue.offer(new Command("Ctrl+Y"));
		return queue;
	}
}

class QueueExecutor {
	public static void execute(Queue<Command> queue) {
		while(!queue.isEmpty()) {
			queue.remove().operation();
		}
	}
}
