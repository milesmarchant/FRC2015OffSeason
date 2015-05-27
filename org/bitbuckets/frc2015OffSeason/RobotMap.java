package org.bitbuckets.frc2015OffSeason;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----SOLENOIDS----/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * The first port for the double solenoid controlling the tilty pistons.
     */
    public static final int TILTY_SOLENOID_1 = 0;
    /**
     * The second port for the double solenoid controlling the tilty pistons.
     */
    public static final int TILTY_SOLENOID_2 = 1;

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----PWM----/////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Motors driving the winch for the elevator on {@link org.bitbuckets.frc2015.subsystems.Stacky}.
     */
    public static final int GRABBY_GRABBER = 4;
    
    /**
     * The motor for driving the {@link org.bitbuckets.frc2015.subsystems.Shooty}.
     */
    public static final int SHOOTY_SHOOTER = 1;

    /**
     * The motor for driving the {@link org.bitbuckets.frc2015.subsystems.Shooty} winder.
     */
	public static final int SHOOTY_WINDER = 0;


    /////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////----PDP----///////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    public static final int GRABBER_MOTOR_CHANNEL = 3;

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----CAN----/////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    /**
     * The CAN index for the front right motor on the drivetrain.
     */
    public static final int WHEEL_FR_MOTOR = 2;
    /**
     * The CAN index for the rear right motor on the drivetrain.
     */
    public static final int WHEEL_RR_MOTOR = 4;
    /**
     * The CAN index for the front left motor on the drivetrain.
     */
    public static final int WHEEL_FL_MOTOR = 1;
    /**
     * The CAN index for the rear left motor on the drivetrain.
     */
    public static final int WHEEL_RL_MOTOR = 3;

    /**
     * The CAN index for the motor that lifts the carriages
     */
    public static final int WINCH_MOTOR = 7;

    /**
     * The CAN index for the motor that lifts grabby
     */
    public static final int GRABBY_LIFTER = 10;

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----DIO----/////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    /**
     * The reed switch below the tote pickup point.
     */
    public static final int HALL_BELOW = 2;
    /**
     * The reed switch above the tote pickup point.
     */
    public static final int HALL_ABOVE = 3;

    /**
     * The right side sensor to detect collision with a tote
     */
    public static final int BUMP_SENSE_RIGHT = 4;
    /**
     * The left side sensor to detect collision with a tote
     */
    public static final int BUMP_SENSE_LEFT = 5;

    /**
     * The limit switch that gets pressed when grabby is open
     */
    public static final int GRABBY_OPEN = 6;

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----WHEELS----//////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    /**
     * The X coordinate of the front left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FL_X = -9.5 / 12.0;
    /**
     * The Y coordinate of the front left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FL_Y = 12.5 / 12.0;
    /**
     * The angle of the front left wheel from top down positive X.
     */
    public static final double WHEEL_FL_THETA = Math.PI / 4;

    /**
     * The X coordinate of the front right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FR_X = 9.5 / 12.0;
    /**
     * The Y coordinate of the front right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FR_Y = 12.5 / 12.0;
    /**
     * The angle of the front right wheel from top down positive X.
     */
    public static final double WHEEL_FR_THETA = 7 * Math.PI / 4;

    /**
     * The X coordinate of the rear left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RL_X = -9.5 / 12.0;
    /**
     * The Y coordinate of the rear left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RL_Y = -12.5 / 12.0;
    /**
     * The angle of the rear left wheel from top down positive X.
     */
    public static final double WHEEL_RL_THETA = 3 * Math.PI / 4;

    /**
     * The X coordinate of the rear right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RR_X = 9.5 / 12.0;
    /**
     * The Y coordinate of the rear right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RR_Y = -12.5 / 12.0;
    /**
     * The angle of the rear right wheel from top down positive X.
     */
    public static final double WHEEL_RR_THETA = 5 * Math.PI / 4;

    /**
     * The X coordinate of the center of rotation.
     */
    public static final double CENTER_X = 0 / 12;
    /**
     * The Y coordinate of the center of rotation.
     */
    public static final double CENTER_Y = 0 / 12;
    
    /////////////////////////////////////////////////////////////////////////////
    ////////////////////////////----AnalogInputs----/////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    /**
     * Port number for the analog gyroscope
     */
    public static final int ANALOG_GYRO = 0;
    
}
