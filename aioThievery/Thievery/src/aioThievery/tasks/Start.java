package Thievery.src.aioThievery.tasks;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.filters.SimpleSkills;
import simple.hooks.scripts.task.Task;
//import simple.hooks.simplebot.teleporter.Teleporter;
import simple.hooks.wrappers.SimpleLocalPlayer;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class Start extends Task {
	//NEED TO EDIT THIS TO BE THE TP LOCATION FOR EDGEVILLE
	public static WorldPoint startPoint = new WorldPoint(2730, 3488, 0);

	public Start(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean condition() {
		final SimpleLocalPlayer player = (SimpleLocalPlayer) ctx.players.getLocal();
		return (
				player.getLocation().getPlane() == 0
		);
	}
	
	@Override
	public void run() {
		//Navigate to Edgeville
		/*
		this.ctx.keyboard.sendKeys("::home");
		ctx.sleep(1000);
		*/
		System.out.println(ctx.players.getLocal().getLocation());
		final SimpleObject craftingStall = (SimpleObject) ctx.objects.populate().filter(37978).filter(new WorldPoint(3098, 3500, 0)).nearest().next();
		final SimpleObject foodStall = (SimpleObject) ctx.objects.populate().filter(37979).filter(new WorldPoint(3097, 3500, 0)).nearest().next();
		final SimpleObject generalStall = (SimpleObject) ctx.objects.populate().filter(37980).filter(new WorldPoint(3096, 3500, 0)).nearest().next();
		final SimpleObject magicStall = (SimpleObject) ctx.objects.populate().filter(37981).filter(new WorldPoint(3095, 3500, 0)).nearest().next();
		final SimpleObject scimitarStall = (SimpleObject) ctx.objects.populate().filter(37982).filter(new WorldPoint(3094, 3500, 0)).nearest().next();
		final SimpleNpc guard = (SimpleNpc) ctx.npcs.populate().filter(380).nearest().next();
		
		if(guard != null) {
			guard.click("Dismiss");
			System.out.println("Dismissing guard...?");
		}
		
		int thievingLvl = Integer.valueOf(this.ctx.skills.realLevel(SimpleSkills.Skills.THIEVING)).intValue();
		
		//The extra stalls and clicks throughout the following if statements
		//are put there to ensure increased efficiency. It will steal from 
		//the appropriate stall for your level, as well as the stall previous.
		//There's a number of inefficiencies in this script, but it was slapped
		//together sloppily.
		
		if((thievingLvl < 20) && (craftingStall.click(0))) {
			ctx.sleep(3000);
		}
		if((thievingLvl > 19 && thievingLvl < 40) && (foodStall.click(0))) {
			ctx.sleep(2000);
			craftingStall.click(0);
			ctx.sleep(2000);
			this.ctx.pathing.clickSceneTile(new WorldPoint(3097, 3501, 0), false, false);
			ctx.sleep(1000);
		}
		
		if((thievingLvl >= 40 && thievingLvl < 60) && (generalStall.click(0))) {
			ctx.sleep(2000);
			foodStall.click(0);
			ctx.sleep(2000);
			this.ctx.pathing.clickSceneTile(new WorldPoint(3096, 3501, 0), false, false);
			ctx.sleep(1000);
		}
		if((thievingLvl >= 60 && thievingLvl < 80) && (magicStall.click(0))) {
			ctx.sleep(2000);
			generalStall.click(0);
			ctx.sleep(2000);
			this.ctx.pathing.clickSceneTile(new WorldPoint(3095, 3501, 0), false, false);
			ctx.sleep(1000);
		}
		if((thievingLvl >= 80 && thievingLvl < 100) && (scimitarStall.click(0))) {
			ctx.sleep(2000);
			magicStall.click(0);
			ctx.sleep(2000);
			this.ctx.pathing.clickSceneTile(new WorldPoint(3094, 3501, 0), false, false);
			ctx.sleep(1000);
		}
		
	}
	
	
	@Override
	public String status() {
		return "Stealing......";
	}
	
	
}

