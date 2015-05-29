package org.bitbuckets.frc2015OffSeason.subsystems;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.bitbuckets.frc2015OffSeason.RobotConstants;
import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.commands.DynamicCommand;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;
import org.bitbuckets.frc2015OffSeason.triggers.SubsystemTrigger;

import edu.wpi.first.wpilibj.CANTalon;

public class Winchy extends StateSubsystem{
	
	private CANTalon winchController;
	private ArrayBlockingQueue<Double> speed = new ArrayBlockingQueue<Double>(1);
    SubsystemTrigger topLimitSwitch;
    SubsystemTrigger bottomLimitSwitch;

	public Winchy(String name, long iterationTime) {
		super(name, iterationTime);
	}
	
	@Override
	protected void setupComponents() {
        winchController = new CANTalon(RobotMap.GRABBY_LIFTER);
        winchController.reverseOutput(true);
        winchController.setPID(RobotConstants.GRABBY_KP, RobotConstants.GRABBY_KI, RobotConstants.GRABBY_KD, 0.0, RobotConstants.GRABBY_IZONE, 0, 0);
        winchController.enableLimitSwitch(true, true);
	}

	@Override
	protected void setupTriggers() {
		topLimitSwitch = new SubsystemTrigger(winchController::isRevLimitSwitchClosed);
		//TODO find the encoder value of the top
        topLimitSwitch.whenPressed(new DynamicCommand<Double>(winchController::setPosition, 1000.0));
		topLimitSwitch = new SubsystemTrigger(winchController::isFwdLimitSwitchClosed);
        topLimitSwitch.whenPressed(new DynamicCommand<Double>(winchController::setPosition, 0.0));
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected void initDefaultCommand() {
	}

	@Override
	protected void setDefaultStates(){
		defaultTeleopState = new Holding();
		defaultAutoState = new Holding();
		defaultTestState = new Holding();
	}
	
	public void setSpeed(Double speed, long timeout){
		try {
			this.speed.offer(speed, timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static class Moving extends State<Winchy>{
				
		public Moving(){
			name = "Moving";
		}
		
		@Override
		public void enter() {
			context.winchController.changeControlMode(CANTalon.ControlMode.PercentVbus);
		}
		
		@Override
		public void execute() {
			context.winchController.set(context.oi.operator.getRawAxis(context.oi.operatorStackyWinchAxis));
			if(context.oi.operator.getRawAxis(context.oi.operatorStackyWinchAxis) == 0){
				context.setState(new Holding());
			}
		}

		@Override
		public void leave() {
		}
		
	}
	
	public static class Holding extends State<Winchy>{
		
		public Holding(){
			name = "Holding";
		}
		
		@Override
		public void enter() {
			context.winchController.changeControlMode(CANTalon.ControlMode.Position);
			context.winchController.set(context.winchController.get());
		}
		
		@Override
		public void execute() {
			if(context.oi.operator.getRawAxis(context.oi.operatorStackyWinchAxis) > 0){
				context.setState(new Moving());
			}
		}

		@Override
		public void leave() {
		}
		
	}
}
