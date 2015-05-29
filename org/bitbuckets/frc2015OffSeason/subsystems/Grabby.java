package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.RobotConstants;
import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.Stacky.Holding;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

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
	
	@Override
	protected void setDefaultStates(){
		defaultTeleopState = new GrabberStop();
		defaultAutoState = new GrabberStop();
		defaultTestState = new GrabberStop();
	}
	
    /**
     * Gets whether the grabber is closed according to the current being drawn by the motor
     * NYI
     *
     * @return If the current draw from the grab motor port is greater than the threshold.
     */
	public boolean isCurrentMaxed(){        
		return false;
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
	
	public static abstract class GrabberState extends State<Grabby>{
		
		protected enum GrabberInputState{
			NEITHER, CLOSE, OPEN, BOTH;
		}
		
		GrabberInputState currentInput;
		
		protected GrabberInputState getInput(){
			if(context.oi.operatorGrabClose.get() == true && context.oi.operatorGrabOpen.get() == true){
				return GrabberInputState.BOTH;
			} else if(context.oi.operatorGrabClose.get() == true){
				return GrabberInputState.CLOSE;
			} else if(context.oi.operatorGrabOpen.get() == true){
				return GrabberInputState.OPEN;
			} else{
				return GrabberInputState.NEITHER;
			}
		}
		
	}
	
	public static class GrabberClose extends GrabberState{
		
		
		public GrabberClose(){
			name = "GrabberClose";
		}
		
		@Override
		public void enter() {
			context.grabberController.set(RobotConstants.GRAB_SPEED);
		}

		@Override
		public void execute() {
			currentInput = getInput();
			if(currentInput == GrabberInputState.BOTH || currentInput == GrabberInputState.NEITHER || context.isCurrentMaxed()){
				context.setState(new GrabberStop());
			} else if(currentInput == GrabberInputState.OPEN){
				context.setState(new GrabberOpen());
			}
		}

		@Override
		public void leave() {
		}
		
	}
	
	public static class GrabberOpen extends GrabberState{
		
		public GrabberOpen(){
			name = "GrabberOpen";
		}

		@Override
		public void enter() {
			context.grabberController.set(-RobotConstants.GRAB_SPEED);
		}
		
		@Override
		public void execute() {
			currentInput = getInput();
			if(currentInput == GrabberInputState.BOTH || currentInput == GrabberInputState.NEITHER || context.getOpen()){
				context.setState(new GrabberStop());
			} else if(currentInput == GrabberInputState.CLOSE){
				context.setState(new GrabberClose());
			}
		}

		@Override
		public void leave() {
			
		}
		
	}
	
	public static class GrabberStop extends GrabberState{
		
		public GrabberStop(){
			name = "GrabberOpen";
		}
		
		@Override
		public void enter(){
			context.grabberController.set(0);
		}
		
		@Override
		public void execute() {
			currentInput = getInput();
			if(currentInput == GrabberInputState.OPEN && context.getOpen() == false){
				context.setState(new GrabberOpen());
			} else if(currentInput == GrabberInputState.CLOSE && context.isCurrentMaxed() == false){
				context.setState(new GrabberClose());
			}
		}

		@Override
		public void leave() {
			
		}
	}


}
