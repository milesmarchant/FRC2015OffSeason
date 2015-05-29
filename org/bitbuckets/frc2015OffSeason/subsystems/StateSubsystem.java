package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.OI;
import org.bitbuckets.frc2015OffSeason.subsystems.state.DisabledState;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class StateSubsystem extends Subsystem implements Runnable {
    
    private Thread t;
    protected OI oi = null;
    protected State currState;
    protected volatile boolean stopRequested = false;
    protected volatile boolean stopped = false;
    protected long iterationTime;
    protected State<?> defaultTeleopState;
    protected State<?> defaultAutoState;
    protected State<?> defaultTestState;
    
    public StateSubsystem(String name, long iterationTime){
    	t = new Thread(this, name);
    	if(iterationTime == 0){
    		this.iterationTime = 10; //TODO actually read this value from somewhere?
    	} else{
    		this.iterationTime = iterationTime;
    	}
    	setupComponents();
    	setupTriggers();
    	setDefaultStates();
    }
    
    public final void setState(State newState) {
    	if(currState.checkNewState(newState)){
    		currState.leave();
    		newState.setContext(this);
    		currState = newState;
    		currState.enter();
    	}
	}
    
    public final void setOI(OI oi){
    	this.oi = oi;
    }
    
    public final String getCurrentStateName(){
    	return currState.getName();
    }
    
    public final void start(){
    	stopRequested = false;
    	stopped = false;
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
    		currState.execute();
    		try {
				Thread.sleep(iterationTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	stopped = true;
    }
    
    public final void setDisabledState(){
    	setState(new DisabledState());
    }
    
    public final void setTeleopState(){
    	setState(defaultTeleopState);
    }
    
    public final void setAutoState(){
    	setState(defaultAutoState);
    }
    
    public final void setTestState(){
    	setState(defaultTestState);
    }

    protected abstract void setupComponents();
    protected abstract void setupTriggers();
        
    protected abstract void interrupted();

    protected abstract void setDefaultStates();
}

