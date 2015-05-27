package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.RobotMap;
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
	
    /**
     * Sets the position of the stacky subsystem.
     *
     * @param up Whether to set the tilty as up or not.
     */
    public void raiseTilty(boolean up) {
        upTilt = !up;
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
    
    public static class Tilt extends State<Tilty>{
    	
    	private boolean up;
    	
    	public Tilt(boolean up){
    		this.up = up;
    		name = "Tilt";
    	}
    	
    	@Override
    	public void enter(){
    	}

		@Override
		public void execute() {
			context.tiltSolenoid.set(up ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
			context.setState(new DisabledState());
		}

		@Override
		public void leave() {
		}
    	
    }

}
