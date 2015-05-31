package org.bitbuckets.frc2015OffSeason.subsystems;

import org.bitbuckets.frc2015OffSeason.OI;
import org.bitbuckets.frc2015OffSeason.RobotConstants;
import org.bitbuckets.frc2015OffSeason.RobotMap;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;

/**
 * Robot wheel order:
 * FL = 1;
 * RL = 2;
 * RR = 3;
 * FR = 4;
 *   __________  <br>
 *  /          \ <br>
 *  | 1      4 | <br>
 *  |          | <br>
 *  |          | <br>
 *  |          | <br>
 *  |          | <br>
 *  |          | <br>
 *  | 2      3 | <br>
 *  \          / <br>
 *   ----------  <br>
 *  
 * Robot vectors:
 * <ul>
 *   <li>+x = forwards
 *   <li>+y = left
 * </ul>
 * 
 * This means that positive is counter clockwise for rotation. If doing math in a Cartesian coordinate system, simply rotate the robot clockwise 90 degrees
 *  
 *  
 * 
 * @author Miles Marchant
 *
 */
public class Drivey extends StateSubsystem{
	
	private Gyro tempGyro = new Gyro(1);
	
	private CANTalon flController;
	private CANTalon rlController;
	private CANTalon rrController;
	private CANTalon frController;
	
	//TODO change the robotmap values to match new coordinates
	//Because all of the wheels are at a 45/135/225/315 degree angles, a term of sqrt(2)/2 can be factored out.
	private double conversionFactor = Math.sin(Math.PI/4);
	private double[][] conversionMatrix =
			{{conversionFactor, -conversionFactor, -RobotMap.WHEEL_FL_Y*conversionFactor - RobotMap.WHEEL_FL_X*conversionFactor},
			 {conversionFactor, conversionFactor, -RobotMap.WHEEL_RL_Y*conversionFactor + RobotMap.WHEEL_RL_X*conversionFactor},
			 {-conversionFactor, conversionFactor, RobotMap.WHEEL_RR_Y*conversionFactor + RobotMap.WHEEL_RR_X*conversionFactor},
			 {-conversionFactor, -conversionFactor, RobotMap.WHEEL_FR_Y*conversionFactor - RobotMap.WHEEL_FR_X*conversionFactor}};
	
	//Converts from linear speed to motor setpoint. A speed of 10 ft/s will result in an output of 1.0, or max speed.
	private double Kv = 0.1;
	
	//Factor to slow rotation to make control easier. Note that this does not interfere with normalization, as it is the wheel speeds that are normalized, not the 
	//Euclidean vector.
	private double Kr = 0.5;

	public Drivey(String name, long iterationTime) {
		super(name, iterationTime);
	}

	@Override
	protected void setupComponents() {
		flController = new CANTalon(RobotMap.WHEEL_FL_MOTOR);
		rlController = new CANTalon(RobotMap.WHEEL_RL_MOTOR);
		rrController = new CANTalon(RobotMap.WHEEL_RR_MOTOR);
		frController = new CANTalon(RobotMap.WHEEL_FR_MOTOR);
		
        flController.changeControlMode(CANTalon.ControlMode.Speed);
        rlController.changeControlMode(CANTalon.ControlMode.Speed);
        rrController.changeControlMode(CANTalon.ControlMode.Speed);
        frController.changeControlMode(CANTalon.ControlMode.Speed);
        
        flController.setPID(RobotConstants.DRIVE_KP, RobotConstants.DRIVE_KI, RobotConstants.DRIVE_KD, RobotConstants.DRIVE_KF, RobotConstants.DRIVE_IZONE, 0, 0);
        rlController.setPID(RobotConstants.DRIVE_KP, RobotConstants.DRIVE_KI, RobotConstants.DRIVE_KD, RobotConstants.DRIVE_KF, RobotConstants.DRIVE_IZONE, 0, 0);
        rrController.setPID(RobotConstants.DRIVE_KP, RobotConstants.DRIVE_KI, RobotConstants.DRIVE_KD, RobotConstants.DRIVE_KF, RobotConstants.DRIVE_IZONE, 0, 0);
        frController.setPID(RobotConstants.DRIVE_KP, RobotConstants.DRIVE_KI, RobotConstants.DRIVE_KD, RobotConstants.DRIVE_KF, RobotConstants.DRIVE_IZONE, 0, 0);
        
        flController.reverseSensor(true);
        rlController.reverseSensor(true);
        rrController.reverseSensor(true);
        frController.reverseSensor(true);
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
		defaultTeleopState = new Drive();
		defaultAutoState = new Drive();
		defaultTestState = new Drive();
	}
	
	protected void setMotors(double fl, double rl, double rr, double fr){
        flController.set(fl);
        rlController.set(rl);
        rrController.set(rr);
        frController.set(fr);
	}
	
	protected void setMotors(double[] setPoints){
        flController.set(setPoints[0]);
        rlController.set(setPoints[1]);
        rrController.set(setPoints[2]);
        frController.set(setPoints[3]);
	}
	
	protected double[] getInputs(OI oi){
		return new double[]{oi.driver.getRawAxis(oi.GO), oi.driver.getRawAxis(oi.STRAFE), oi.driver.getRawAxis(oi.TURN)};
	}
	
	protected double[] normalize(double[] input){
		double max = 0.95;
		for(int i = 0; i < input.length; i++){
			if(Math.abs(input[i]) > max){
				max = Math.abs(input[i]);
			}
		}
		double[] normalized = new double[input.length];
		for(int i = 0; i < input.length; i++){
			//if all wheels are below maximum, this will not change the values. 
			//If any of the wheels are overdriven, this allows for a small buffer, so that control code will not attempt to run a motor at greater than allowed speed.
			normalized[i] = input[i]/(max+0.5);
		}
		return normalized;
	}
	
	protected double[] transformFromFieldCentric(double[] fieldCentricVector, double angle){
		return new double[]{
				fieldCentricVector[1]*Math.sin(angle) + fieldCentricVector[0]*Math.cos(angle),
				fieldCentricVector[1]*Math.cos(angle) - fieldCentricVector[0]*Math.sin(angle),
				fieldCentricVector[2]-angle};
	}
	
	public static class Drive extends State<Drivey>{
		
		double[] inputs = new double[3];
		double[] wheelSpeeds = new double[4];

		@Override
		public void enter() {
			
		}

		@Override
		public void execute() {
			inputs = context.getInputs(context.robot.oi);
			for(int i = 0; i < wheelSpeeds.length; i++){
				wheelSpeeds[i] = inputs[0]*context.conversionMatrix[i][0] + inputs[1]*context.conversionMatrix[i][1] + inputs[2]*context.conversionMatrix[i][2]*context.Kr;
			}
			context.setMotors(context.normalize(wheelSpeeds));
		}

		@Override
		public void leave() {
			
		}
		
	}
	
	public static class DriveFieldCentric extends State<Drivey>{
		
		double[] inputs = new double[3];
		double[] wheelSpeeds = new double[4];

		@Override
		public void enter() {
			context.tempGyro.reset();
		}

		@Override
		public void execute() {
			//TODO get gyro
			inputs = context.transformFromFieldCentric(context.getInputs(context.robot.oi), context.tempGyro.getAngle());
			for(int i = 0; i < wheelSpeeds.length; i++){
				wheelSpeeds[i] = inputs[0]*context.conversionMatrix[i][0] + inputs[1]*context.conversionMatrix[i][1] + inputs[2]*context.conversionMatrix[i][2]*context.Kr;
			}
			context.setMotors(context.normalize(wheelSpeeds));
		}

		@Override
		public void leave() {
			
		}
		
	}
	

}
