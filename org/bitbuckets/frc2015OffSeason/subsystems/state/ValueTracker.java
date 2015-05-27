package org.bitbuckets.frc2015OffSeason.subsystems.state;

import java.util.function.Supplier;

public class ValueTracker<T> {
	
	T oldValue;
	T newValue;
	Supplier<T> fetcher;
	
	public ValueTracker(Supplier<T> fetchValue){
		fetcher = fetchValue;
	}
	
	public T getValue(){
		newValue = fetcher.get();
		if(newValue != null){
			oldValue = newValue;
		}
		return oldValue;
	}

}
