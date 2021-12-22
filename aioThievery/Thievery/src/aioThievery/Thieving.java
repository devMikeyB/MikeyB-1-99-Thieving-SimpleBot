package Thievery.src.aioThievery;

import Thievery.src.aioThievery.tasks.*;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleNpc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ScriptManifest(author = "MikeyB", name = "MikeyB | 1-99 Thieving", category = Category.THIEVING, version = "1.0",
description = "Start in Edgeville bank.", discord = "Mike #8528", servers = { "Novea" })

public class Thieving extends TaskScript {
	private List tasks = new ArrayList();

	private String status;
	private int guardsThwarted;
	private int stallsRobbed;
	private long startTime;
	
	@Override
	public boolean prioritizeTasks() {
		return true;
	}
	
	@Override
	public List<Task> tasks() {
		return tasks;
	}
	
	@Override
	public void onProcess() {
		super.onProcess();
		status = getScriptStatus();
	}
	
	@Override
	public void onExecute() {
		//Everything is run within Start, I added StallOne because of poor
		//planning and rapid deployment. This task format was learned from apwil's
		//SeersAgility script and is properly utilized in my Agility Pyramid script.
		startTime = System.currentTimeMillis();
		tasks.addAll(Arrays.asList(
				new Start(ctx),
				new StallOne(ctx)
		));
	}
	
	@Override
	public void onTerminate() {
		
	}
	
	public void onChatMessage(ChatMessage msg) {
		if (msg.getMessage().contains("attempt to steal")) {
			System.out.println("Sneaky silly guy, you stole the gold!");
			this.stallsRobbed++;
		}
		if (msg.getMessage().contains("sorry for the")) {
			System.out.println("~~~~~~~~~~~~~~~~~~~THE GUARD HAS BEEN THWARTED~~~~~~~~~~~~~~~~~~~");
			this.guardsThwarted++;
			
			}
	}
		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(5, 2, 192, 86);
			
			g.setColor(Color.decode("#303030"));
			g.drawRect(5, 2, 192, 86);
			
			g.setColor(Color.decode("#4d4d4d"));
			g.drawLine(8, 24, 194, 24);
			
			g.setColor(Color.decode("#3150ad"));
			g.drawString("v. " + "1.0", 160, 20);
			
			g.setColor(Color.decode("#787878"));
			g.drawString("MikeyB | 1-99 Thieving", 12, 20);
			g.drawString("Time: " + ctx.paint.formatTime(System.currentTimeMillis() - startTime), 14, 42);
			g.drawString("Status: " + status, 14, 56);
			g.drawString("Guards Thwarted: " + guardsThwarted, 14, 70);
			g.drawString("Stalls Robbed: " + stallsRobbed, 14, 84);
			//g.drawString("Earnings: " + earnings);
		}
	}