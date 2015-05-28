package org.bitbuckets.frc2015OffSeason.util;

import java.util.function.Consumer;
import java.util.function.Function;

public class AxisSender extends ValueSender<Integer, Double>{
	
	private int sourceAxis;
	
	public AxisSender(Function<Integer, Double> source, Consumer<Double> target, long iterationTime, int sourceAxis){
		super(null, target, iterationTime);
		this.sourceAxis = sourceAxis;
	}

	@Override
	public void run() {
		long time;
		while(!Thread.interrupted()){
			time = System.currentTimeMillis();
			target.accept(source.apply(sourceAxis));
			try {
				Thread.sleep(System.currentTimeMillis()-time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
