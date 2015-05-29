package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.Winchy.Holding;
import org.bitbuckets.frc2015OffSeason.subsystems.state.DisabledState;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Tilty extends StateSubsystem{
	
    private DoubleSolenoid tiltSolenoid;
    private boolean upTilt = false;

	public Tilty(String name, long iterationTime) {
		super(name, iterationTime);
	}
	
	@Override
	protected void setupComponents() {
        tiltSolenoid = new DoubleSolenoid(RobotMap.TILTY_SOLENOID_1, RobotMap.TILTY_SOLENOID_2);
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
		defaultTeleopState = new WaitForInput();
		defaultAutoState = new WaitForInput();
		defaultTestState = new WaitForInput();
	}
	
    /**
     * Returns a boolean indicating the intended position of the stacky elevator. The pneumatic cylinder takes time to operate,
     *  however this boolean changes instantly, so do not use to test the absolute, current position.
     * 
     * @return - a boolean indicating the intended position of stacky.
     */
    public boolean getUp(){
        return upTilt;
    }
    
    public static class WaitForInput extends State<Tilty>{

		@Override
		public void enter() {
		}

		@Override
		public void execute() {
			if(context.robot.oi.operatorTiltDown.get()){
				context.setState(new TiltDown());
			} else if(context.robot.oi.operatorTiltUp.get()){
				context.setState(new TiltUp());
			}
		}

		@Override
		public void leave() {
		}
    	
    }
    
    public static class TiltUp extends State<Tilty>{
    	
    	@Override
    	public void enter(){
			context.tiltSolenoid.set(DoubleSolenoid.Value.kReverse);
    	}

		@Override
		public void execute() {
			context.setState(new WaitForInput());
		}

		@Override
		public void leave() {
		}
    	
    }
    
    public static class TiltDown extends State<Tilty>{
    	
    	@Override
    	public void enter(){
			context.tiltSolenoid.set(DoubleSolenoid.Value.kForward);
    	}

		@Override
		public void execute() {
			context.setState(new WaitForInput());
		}

		@Override
		public void leave() {
		}
    	
    }

}
