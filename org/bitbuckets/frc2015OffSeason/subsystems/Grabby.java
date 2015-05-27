package org.bitbuckets.frc2015OffSeason.subsystems;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class Grabby extends StateSubsystem{
	
	private Talon grabberController;
	
	private DigitalInput open;
	
	private ArrayBlockingQueue<Double> speed = new ArrayBlockingQueue<Double>(1);

	public Grabby(String name, long iterationTime) {
		super(name, iterationTime);
        grabberController = new Talon(RobotMap.GRABBY_GRABBER);
        open = new DigitalInput(RobotMap.GRABBY_OPEN);
	}

	@Override
	protected void init() {
		
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
    /**
     * Gets whether the grabber is closed according to the current being drawn by the motor
     * NYI
     *
     * @return If the current draw from the grab motor port is greater than the threshold.
     */
	public boolean getCurrentLimit(){        
		return true;
		//TODO uncomment once the pdp is there
//        return Robot.pdp.getCurrent(RobotMap.GRABBER_MOTOR_CHANNEL) >= RobotConstants.GRABBY_CURRRENT_MAX;
	}
	
    /**
     * Gets the state of the limit switch that closes when the grabber is open.
     *
     * @return If the limit is triggered.
     */
	public boolean getOpen(){
		return open.get();
	}
	
	public void setSpeed(Double speed, long timeout){
		try {
			this.speed.offer(speed, timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Double getSpeed(){
		return speed.poll();
	}
	
	public static class Grab extends State<Grabby>{
		
		double oldSpeed = 0.0;
		Double newSpeed = 0.0;

		@Override
		public void execute(Grabby context) {
			newSpeed = context.getSpeed();
			if(newSpeed == null){
				context.grabberController.set(oldSpeed);
			} else{
				oldSpeed = newSpeed;
				//feed in oldSpeed to reduce unboxing
				context.grabberController.set(oldSpeed);
			}
		}
		
	}

}
