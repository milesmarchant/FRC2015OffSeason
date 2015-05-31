package org.bitbuckets.frc2015OffSeason.util;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ValueSender<T, R> implements Runnable {
	
	protected Function<T, R> source;
	protected Consumer<R> target;
	long iterationTime;
		
	public ValueSender(Function<T, R> source, Consumer<R> target, long iterationTime){
		this.source = source;
		this.target = target;
		this.iterationTime = iterationTime;
	}
	
	public abstract void run();

}
