package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.RobotConstants;
import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.Talon;

public class Shooty extends StateSubsystem{
	
	private Talon shooter;
	private Talon winder;

	public Shooty(String name, long iterationTime) {
		super(name, iterationTime);
	}

	@Override
	protected void setupComponents() {
		shooter = new Talon(RobotMap.SHOOTY_SHOOTER);
		winder = new Talon(RobotMap.SHOOTY_WINDER);
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
	protected void setDefaultStates() {		
		
	}
	
	static interface Active{
	}
	
	public abstract static class ShootyState extends State<Shooty>{
		
		protected ShootyInput getInput(){
			if(context.robot.oi.operatorTapeShoot.get()){
				return ShootyInput.SHOOT;
			} else if(context.robot.oi.operatorTapeRetract.get()){
				return ShootyInput.RETRACT;
			} else{
				return ShootyInput.WAIT;
			}
			
		}
		
		ShootyInput currentInput;
		
		protected enum ShootyInput{
			SHOOT, RETRACT, WAIT;
		}
	}
	
	public static class Wait extends ShootyState{

		@Override
		public void enter() {
			context.shooter.set(0);
			context.winder.set(0);
		}

		@Override
		public void execute() {
			currentInput = getInput();
			if(currentInput == ShootyInput.SHOOT){
				context.setState(new Unwind());
			} else if(currentInput == ShootyInput.RETRACT){
				context.setState(new Retract());
			}
			
		}

		@Override
		public void leave() {
			
		}
		
	}
	
	public static class Unwind extends ShootyState implements Active{
		
		long timeInit;

		@Override
		public void enter() {
			timeInit = System.nanoTime();
			context.shooter.set(RobotConstants.SHOOT_SPEED);
			context.winder.set(RobotConstants.SHOOT_SPEED * RobotConstants.WIND_MULTIPLIER);
		}

		@Override
		public void execute() {
			if(System.nanoTime()-timeInit > RobotConstants.WIND_TIME*1000000){
				context.setState(new Shoot());
			}
		}

		@Override
		public void leave() {
			context.winder.set(0);
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof Active){
				return false;
			}
			return true;
		}
		
	}
	
	public static class Shoot extends ShootyState implements Active{
		
		long timeInit;

		@Override
		public void enter() {
			timeInit = System.nanoTime();
			context.shooter.set(RobotConstants.SHOOT_SPEED);
			context.winder.set(0);
		}

		@Override
		public void execute() {
			if(System.nanoTime()-timeInit > (RobotConstants.WIND_TIME-RobotConstants.SHOOT_TIME)*1000000){
				context.setState(new Wait());
			}
		}

		@Override
		public void leave() {
			context.shooter.set(0);
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof Active){
				return false;
			}
			return true;
		}
		
	}
	
	public static class Retract extends ShootyState implements Active{

		@Override
		public void enter() {
			context.winder.set(-1 * RobotConstants.SHOOT_SPEED * RobotConstants.WIND_MULTIPLIER);
		}

		@Override
		public void execute() {
			currentInput = getInput();
			if(currentInput == ShootyInput.WAIT){
				context.setState(new Wait());
			}
		}

		@Override
		public void leave() {
			context.winder.set(0);
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof Active){
				return false;
			}
			return true;
		}
		
	}

}
