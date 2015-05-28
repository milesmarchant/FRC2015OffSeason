package org.bitbuckets.frc2015OffSeason.subsystems;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.bitbuckets.frc2015OffSeason.RobotConstants;
import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;
import org.bitbuckets.frc2015OffSeason.subsystems.state.ValueTracker;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

public class Grabby extends StateSubsystem{
	
	private Talon grabberController;
	
	private DigitalInput open;
	
	public Grabby(String name, long iterationTime) {
		super(name, iterationTime);
	}


	@Override
	protected void setupComponents() {
        grabberController = new Talon(RobotMap.GRABBY_GRABBER);
        open = new DigitalInput(RobotMap.GRABBY_OPEN);
	}

	@Override
	protected void setupTriggers() {
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
	
	public static class GrabberClose extends State<Grabby>{
		
		
		public GrabberClose(){
			name = "GrabberClose";
		}
		
		@Override
		public void enter() {
			context.grabberController.set(RobotConstants.GRAB_SPEED);
		}

		//TODO check for current limit
		@Override
		public void execute() {
		}

		@Override
		public void leave() {
		}
		
	}
	
	public static class GrabberOpen extends State<Grabby>{
		
		public GrabberOpen(){
			name = "GrabberOpen";
		}

		@Override
		public void enter() {
			context.grabberController.set(-RobotConstants.GRAB_SPEED);
		}
		
		//TODO check for open
		@Override
		public void execute() {
		}

		@Override
		public void leave() {
			
		}
		
	}
	
	public static class GrabbyStop extends State<Grabby>{
		
		public GrabbyStop(){
			name = "GrabberOpen";
		}
		
		@Override
		public void enter(){
			context.grabberController.set(0);
		}
		
		@Override
		public void execute() {
		}

		@Override
		public void leave() {
			
		}
	}


}
