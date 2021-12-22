package Thievery.src.aioThievery.tasks;

import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;


//Everything was done inside of Start.java, this file is only here to satisfy
//the needs of the task array.

public class StallOne extends Task {
	
	public StallOne(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean condition() {
		return 1<0;
		
	}
	
	@Override
	public void run() {
		System.out.println("THIS SHOULD NOT APPEAR.");
		
	}
	
	@Override
	public String status() {
		return "SOMETHING IS WRONG.";
	}
}