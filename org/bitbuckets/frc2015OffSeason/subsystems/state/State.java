package org.bitbuckets.frc2015OffSeason.subsystems.state;

import org.bitbuckets.frc2015OffSeason.subsystems.StateSubsystem;

public abstract class State<S extends StateSubsystem> {
	
	protected State<?>[] disallowedTargets;
	
	public State(){
	}
	
	public abstract void execute(S context);
	
	public final boolean checkNewState(State<?> newState){
		for(State<?> s: disallowedTargets){
			if(s.equals(newState)){
				return false;
			}
		}
		return true;
	}
}
