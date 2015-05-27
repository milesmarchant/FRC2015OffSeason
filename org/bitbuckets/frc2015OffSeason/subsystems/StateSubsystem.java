package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.subsystems.state.DisabledState;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class StateSubsystem extends Subsystem implements Runnable {
    
    private Thread t;
    protected State currState;
    protected volatile boolean stopRequested = false;
    protected volatile boolean stopped = false;
    protected long iterationTime;
    
    public StateSubsystem(String name, long iterationTime){
    	t = new Thread(this, name);
    	if(iterationTime == 0){
    		this.iterationTime = 10; //TODO find a better way
    	} else{
    		this.iterationTime = iterationTime;
    	}
    }
    
    public void setState(State newState) {
    	if(currState.checkNewState(newState)){
    		currState = newState;
    	}
	}
    
    public final void start(){
    	stopRequested = false;
    	stopped = false;
    	init();
    	if(t != null && t.isAlive() == false){
    		t = new Thread(this, t.getName());
    	}
    	t.start();
    }
    
    public final void stop(){
    	stopRequested = true;
    }
    
    //TODO make it a correct time loop
    @Override
    public final void run(){
    	while(stopRequested){
    		currState.execute(this);
    		try {
				Thread.sleep(iterationTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	stopped = true;
    }

    protected abstract void init();
    
    //TODO what's the inverse of init()?
    
    protected abstract void interrupted();
    
    protected void setDefaultState(){
    	setState(new DisabledState());
    }
    
}

