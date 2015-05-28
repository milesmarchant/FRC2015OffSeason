package org.bitbuckets.frc2015OffSeason;


public class RobotConstants {    
	
	
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----MISC----//////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * The deadband after which the stick is seen as "pressed";
     */
    public static final double STICK_TO_BUTTON_DEADBAND = .2;
    /**
     * Default settle time for autonomous driving, in milliseconds.
     */
    public static final int DEFAULT_SETTLE_TIME = 150;

    /**
     * The ratio at which the slow driving happens.
     */
    public static final double slowModeRatio = 0.5;
    /**
     * What is this?
     */
    public static final double FUDGE_FACTOR = 1;
    /**
     * An example deadzone value
     */
    public static final double DEFAULT_DEADZONE = 0.01;
    /**
     * Toggles various debug statements.
     */
    public static final boolean TESTING = false;
    /**
     * Encoder ticks per revolution of a motor shaft.
     */
    public static final double ENC_TICK_PER_REV = 768.0;//256 enc ticks per rev, 4 ticks per enc tick
    
    
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----DRIVEY----////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * Maximum translational speed in ft/s.(needs to be converted to ft/s)
     */
    public static final double MAX_TRANS_SPEED = 10;
    /**
     * Maximum translational acceleration in ft/s^2.(needs to be converted to ft/s^2)
     */
    public static final double MAX_TRANS_ACCEL = 30;
    /**
     * Maximum rotational speed in rad/s.
     */
    public static final double MAX_ROT_SPEED = 3;
    /**
     * Maximum rotational acceleration in rad/s^2.
     */
    public static final double MAX_ROT_ACCEL = 3;
    /**
     * The gear ratio of the gearbox between the encoder and the wheel in a wheel module.
     */
    public static final double WHEEL_GEAR_RATIO = 70. / 24.;
    /**
     * The approximate circumference of a drivetrain wheel.
     */
    public static final double WHEEL_CIRCUMFERENCE = Math.PI / 3;
    /**
     * Encoder ticks for one revolution of a wheel.
     */
    public static final double DRIVEY_ENC_TICK_PER_REV = 256.0 * WHEEL_GEAR_RATIO;
    
	public static final double SMOOTHING_THRESHOLD = 0.2;
	
	public static final double SMOOTH_CHANGE = 0.1;

    
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----STACKY----////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * The speed for the carriage while it is going fast.
     */
    public static final double CARRIAGE_FAST_SPEED = .7;
    /**
     * The speed for the carriage while it is going slowly.
     */
    public static final double CARRIAGE_SLOW_SPEED = .5;
    /**
     * The approximate circumference of the Stacky winch drum.
     */
    public static final double STACKY_WINCH_DRUM_CIRCUMFERENCE = 2 * Math.PI / 12;
    /**
     * Timeout for the StackyDownOne command, in seconds.
     */
    public static final double STACK_MOVE_ONE_TIMEOUT = 1.5;
    /**
     * Timeout for the StackyDownAll command, in seconds.
     */
    public static final double STACK_DOWN_ALL_TIMEOUT = 5;

    
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----GRABBY----////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * Maximum speed of Grabby's lift in rad/s.
     */
    public static final double MAX_GRABBY_LIFTER_SPEED = 1;
    /**
     * Maximum acceleration of Grabby's lift in rad/s^2.
     */
    public static final double MAX_GRABBY_LIFTER_ACCEL = 1;
    /**
     * 
     */
    public static final double GRABBY_CURRRENT_MAX = 8;
    /**
     * 
     */
    public static final double GRAB_TIMEOUT = 3;
    /**
     * 
     */
    public static final double GRAB_SPEED = 1;
    /**
     * The approximate circumference of the Grabby winch drum.
     */
    public static final double GRABBY_WINCH_DRUM_CIRCUMFERENCE = 3 * Math.PI / 24;
    
    
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----SHOOTY----////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * The speed at which the Talons running the tape measure shooter should be set to.
     */
	public static final double SHOOT_SPEED = 1;
	
	public static double WIND_MULTIPLIER = .75;
	
	public static final double RETRACT_SPEED = .75;
	//TODO make final
	/**
	 * The length of time the shooter should run before it stops.
	 */
	public static double SHOOT_TIME = 225;
	public static double UNWIND_TIME = 175;
	public static final long WIND_TIME = 1100;
	public static final long WIND_TIME_SHORT = 50;

    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----PID----///////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Proportional constant for driving.
     */
    public static final double DRIVE_KP = 0.2;
    /**
     * Integral constants for driving.
     */
    public static final double DRIVE_KI = 0.005;
    /**
     * Derivative constant for driving.
     */
    public static final double DRIVE_KD = 0.01;
    /**
     * Feedforward constant for driving.
     */
    public static final double DRIVE_KF = 0.01;
    /**
     * Integral zone for driving.
     */
    public static final int DRIVE_IZONE = 50;
    
    /**
     * Proportional constant for Stacky.
     */
    public static final double STACKY_KP = 0.6;
    /**
     * Integral constant for Stacky.
     */
    public static final double STACKY_KI = 0.0;
    /**
     * Derivative constant for Stacky.
     */
    public static final double STACKY_KD = 0;
    /**
     * Feed Forward constant for Stacky.
     */
    public static final double STACKY_KF = 0;
    /**
     * Integral zone for Stacky.
     */
    public static final int STACKY_IZONE = 500;

    /**
     * Proportional constant for Grabby.
     */
    public static final double GRABBY_KP = 1;
    /**
     * Integral constant for Grabby.
     */
    public static final double GRABBY_KI = 0;
    /**
     * Derivative constant for Grabby.
     */
    public static final double GRABBY_KD = 0;
    /**
     * Feed Forward constant for Grabby.
     */
    public static final double GRABBY_KF = 0;
    /**
     * Integral zone for Grabby.
     */
    public static final int GRABBY_IZONE = 50;
}
