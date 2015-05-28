package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.RobotConstants;
import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;
import org.bitbuckets.frc2015OffSeason.triggers.SubsystemTrigger;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;

public class Stacky extends StateSubsystem{
	
	private CANTalon winch;
	
	private DigitalInput reedAbove;
	private DigitalInput reedBelow;
	private DigitalInput bumperLeft;
	private DigitalInput bumperRight;

	public Stacky(String name, long iterationTime) {
		super(name, iterationTime);
	}

	@Override
	protected void setupComponents() {
		winch = new CANTalon(RobotMap.WINCH_MOTOR);
		winch.enableLimitSwitch(true, true);
		winch.setPID(RobotConstants.STACKY_KP, RobotConstants.STACKY_KI, RobotConstants.STACKY_KD, 0.0, RobotConstants.STACKY_IZONE, 0, 0);
		
		reedAbove = new DigitalInput(RobotMap.HALL_ABOVE);
		reedBelow = new DigitalInput(RobotMap.HALL_BELOW);
		bumperLeft = new DigitalInput(RobotMap.BUMP_SENSE_LEFT);
		bumperRight = new DigitalInput(RobotMap.BUMP_SENSE_RIGHT);
	}

	@Override
	protected void setupTriggers() {
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getBumpers(){
		return bumperLeft.get() && bumperRight.get();
	}
	
	public static class Holding extends State<Stacky>{

		@Override
		public void enter() {
			context.winch.changeControlMode(ControlMode.Position);
			context.winch.set(context.winch.get());
		}

		@Override
		public void execute() {
		}

		@Override
		public void leave() {
			
		}
		
	}
	
	public static class HoldingAutoMagic extends State<Stacky>{

		@Override
		public void enter() {
			context.winch.changeControlMode(ControlMode.Position);
			context.winch.set(context.winch.get());
		}

		@Override
		public void execute() {
			if(context.getBumpers() == false){
				context.setState(new MoveAutoMagic(true));
			}
		}

		@Override
		public void leave() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class MoveAutoMagic extends State<Stacky>{
		
		private boolean direction;
		
		public MoveAutoMagic(boolean up){
			this.direction = direction;
		}

		@Override
		public void enter() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void leave() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class MoveManual extends State<Stacky>{

		@Override
		public void enter() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void leave() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class WaitReed extends State<Stacky>{

		@Override
		public void enter() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void leave() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
