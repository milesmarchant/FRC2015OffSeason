package org.bitbuckets.frc2015OffSeason.subsystems.state;

import org.bitbuckets.frc2015OffSeason.subsystems.StateSubsystem;

public abstract class State<S extends StateSubsystem> {
	
	protected String name;
	protected S context;
	
	public State(){
	}
	
	public final void setContext(S context){
		this.context = context;
		createValueTrackers();
	}
	
	protected void createValueTrackers(){
	}
	
	public abstract void enter();
	
	public abstract void execute();
	
	public abstract void leave();
	
	public boolean checkNewState(State<?> newState){
		return true;
	}
	
	public String getName(){
		return name;
	}
}
