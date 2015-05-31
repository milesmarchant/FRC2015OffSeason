package org.bitbuckets.frc2015OffSeason.triggers;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.buttons.InternalButton;

public class RapidTrigger extends InternalButton implements Runnable{
	
	Thread checker;
	
	private volatile boolean active = false;
	private BooleanSupplier getActive;
	private long iterationTime;
	
	public RapidTrigger(BooleanSupplier getActive, long iterationTime){
		this.getActive = getActive;
		this.iterationTime = iterationTime;
	}
	
	@Override
	public boolean get(){
		if(checker == null || !checker.isAlive()){
			checker = new Thread(this);
			checker.start();
		}
		return active;
	}

	@Override
	public void run() {
		active = getActive.getAsBoolean();
		try {
			Thread.sleep(iterationTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
