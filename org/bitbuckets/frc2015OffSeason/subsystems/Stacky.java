package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.RobotConstants;
import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.Tilty.WaitForInput;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Counter;
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
	
	public boolean getBumpers(){
		return bumperLeft.get() && bumperRight.get();
	}
	
	public static class Holding extends State<Stacky>{

		@Override
		public void enter() {
			//stop the winch
			context.winch.changeControlMode(ControlMode.Position);
			context.winch.set(context.winch.get());
		}

		@Override
		public void execute() {
			//if the auto mode button is pressed, go to HoldingAutoMagic state
			if(context.oi.operatorAutoMode.get() == true){
				context.setState(new HoldingAutoMagic());
			} else if(context.oi.operatorToteUp.get()){
				context.setState(new MoveUpOne());
			} else if(context.oi.operatorToteDown.get()){
				context.setState(new MoveDownOne());
			} else if(context.oi.operatorToteDownAll.get()){
				context.setState(new MoveDownAll());
			} else if(context.oi.operator.getRawAxis(context.oi.operatorStackyWinchAxis) != 0){
				context.setState(new MoveManual());
			}
			
		}

		@Override
		public void leave() {
			
		}
		
	}
	
	public static class HoldingAutoMagic extends State<Stacky>{

		@Override
		public void enter() {
			//stop the winch
			context.winch.changeControlMode(ControlMode.Position);
			context.winch.set(context.winch.get());
		}

		@Override
		public void execute() {
			//if the auto mode button is not pressed, go to Holding state
			if(context.oi.operatorAutoMode.get() == false){
				context.setState(new HoldingAutoMagic());
			} else if(context.getBumpers() == true){ //if the bumpers are pressed, go to MoveAutoMagic state, going upwards
				context.setState(new MoveUpOne());
			} else if(context.oi.operatorToteUp.get()){
				context.setState(new MoveUpOne());
			} else if(context.oi.operatorToteDown.get()){
				context.setState(new MoveDownOne());
			} else if(context.oi.operatorToteDownAll.get()){
				context.setState(new MoveDownAll());
			} else if(context.oi.operator.getRawAxis(context.oi.operatorStackyWinchAxis) != 0){
				context.setState(new MoveManual());
			}
		}

		@Override
		public void leave() {
			
		}
		
	}
	
	public static abstract class MovingState extends State<Stacky>{
		@Override
		public void enter(){
			context.winch.changeControlMode(ControlMode.Speed);
		}
	}
	
	public static class MoveDownOne extends MovingState{

		@Override
		public void execute() {
			context.winch.set(-RobotConstants.CARRIAGE_FAST_SPEED);
			context.setState(new WaitReed());
		}

		@Override
		public void leave() {			
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof MovingState){
				return false;
			}
			return true;
		}
		
	}
	
	public static class MoveUpOne extends MovingState{

		@Override
		public void execute() {
			context.winch.set(RobotConstants.CARRIAGE_FAST_SPEED);
			context.setState(new WaitReed());
		}

		@Override
		public void leave() {			
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof MovingState){
				return false;
			}
			return true;
		}
		
	}
	
	public static class MoveDownAll extends MovingState{

		@Override
		public void execute() {
			context.winch.set(-RobotConstants.CARRIAGE_FAST_SPEED);
			context.setState(new WaitBottom());
		}

		@Override
		public void leave() {			
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof MovingState){
				return false;
			}
			return true;
		}
		
	}
	
	public static class MoveManual extends MovingState{

		@Override
		public void execute() {
			context.winch.set(context.oi.operator.getRawAxis(context.oi.operatorStackyWinchAxis));
			if(context.oi.operatorToteUp.get()){
				context.setState(new MoveUpOne());
			} else if(context.oi.operatorToteDown.get()){
				context.setState(new MoveDownOne());
			} else if(context.oi.operatorToteDownAll.get()){
				context.setState(new MoveDownAll());
			} else if(context.oi.operatorAutoMode.get() && context.getBumpers()){
				context.setState(new MoveUpOne());
			} else if(context.oi.operator.getRawAxis(context.oi.operatorStackyWinchAxis) == 0){
				context.setState(new Holding());
			}
		}

		@Override
		public void leave() {
		}
		
	}
	
	public static class WaitReed extends State<Stacky>{
		
		long timeInit;

		@Override
		public void enter() {
			timeInit = System.currentTimeMillis();
		}

		@Override
		public void execute() {
			
			Counter aboveCounter = new Counter(context.reedAbove);
			Counter belowCounter = new Counter(context.reedBelow);
			
			if(aboveCounter.get() > 0 || belowCounter.get() > 0){
				aboveCounter.reset();
				belowCounter.reset();
				if(System.currentTimeMillis() - timeInit > RobotConstants.CARRAIGE_SAG_COMPENSATION_TIME){
					context.winch.set(0);
					context.setState(new Holding());
				}
			}
			
			if((System.currentTimeMillis() - timeInit) > RobotConstants.STACK_MOVE_ONE_TIMEOUT){
				context.winch.set(0);
				context.setState(new Holding());
			}
		}

		@Override
		public void leave() {
			
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof MovingState){
				return false;
			}
			return true;
		}
		
	}
	
	public static class WaitBottom extends State<Stacky>{
		
		long timeInit;

		@Override
		public void enter() {
			timeInit = System.currentTimeMillis();
		}

		@Override
		public void execute() {
			//TODO check which limit switch it should be
			if(context.winch.isRevLimitSwitchClosed() || (System.currentTimeMillis() - timeInit) > RobotConstants.STACK_DOWN_ALL_TIMEOUT){
				context.winch.set(0);
				context.setState(new Holding());
			}
		}

		@Override
		public void leave() {
			
		}
		
		@Override
		public boolean checkNewState(State<?> newState){
			if(newState instanceof MovingState){
				return false;
			}
			return true;
		}
		
	}

}
