package org.bitbuckets.frc2015OffSeason;

import org.bitbuckets.frc2015OffSeason.util.Direction;
import org.bitbuckets.frc2015OffSeason.util.JoystickDirButton;
import org.bitbuckets.frc2015OffSeason.util.POVHatDirButton;
import org.bitbuckets.frc2015OffSeason.util.TwoWayButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * <p>
 * PLAYSTATION BUTTONS:
 * <ul>
 *   <li>1  - SQUARE
 *   <li>2  - X
 *   <li>3  - CIRCLE
 *   <li>4  - TRIANGLE
 *   <li>5  - LEFT BUMPER (L1)
 *   <li>6  - RIGHT BUMPER (R1)
 *   <li>7  - LEFT TRIGGER (L2)
 *   <li>8  - RIGHT TRIGGER (R2)
 *   <li>9  - SHARE
 *   <li>10 - OPTIONS
 *   <li>11 - LEFT IN (L3)
 *   <li>12 - RIGHT IN (R3)
 *   <li>13 - PLAYSTATION BUTTON
 *   <li>14 - BIG MIDDLE BUTTON
 * </ul>
 * 
 * CURRENTLY USED:
 * <ul>
 *   <li>Driver:
 *     <ul>
 *       <li>7  - L2
 *       <li>8  - R2
 *     </ul>
 *   <li>Operator:
 *     <ul>
 *       <li>2  - X
 *       <li>3  - CIRCLE
 *       <li>4  - TRIANGLE
 *       <li>5  - L1
 *       <li>6  - R1
 *       <li>10 - OPTIONS
 *       <li>11 - L3
 *       <li>Left Stick Vertical Axis
 *     </ul>
 * </ul>
 * 
 */
public class OI {
    ////////////////////JOYSTICKS///////////////////////
    public Joystick driver = new Joystick(1);
    public Joystick operator = new Joystick(2);

    ///////////////////BUTTONS//////////////////////////
    //Driver
    
    //Operator
    //public Button cancelCommands = new JoystickButton(operator, 4);

    //Tilty
    public POVHatDirButton operatorTiltUp = new POVHatDirButton(operator, Direction.UP);
    public POVHatDirButton operatorTiltDown = new POVHatDirButton(operator, Direction.DOWN);

    //Grabby
    public TwoWayButton operatorGrabOpen = new TwoWayButton(operator, 5);
    public TwoWayButton operatorGrabClose = new TwoWayButton(operator, 6);
    
    //Winchy
    

    //Stacky
    public Button operatorToteUp = new JoystickButton(operator, 2);//JoystickDirButton(operator, Direction.UP, 0, 1);
    public Button operatorToteUpBlind = new JoystickDirButton(operator, Direction.UP, 0, 1);
    public Button operatorToteDown = new JoystickDirButton(operator, Direction.DOWN, 0, 1);
    public Button operatorToteDownAll = new JoystickButton(operator, 11);
    public Button operatorToteDownBit = new JoystickButton(operator, 3);
    
    //Shooty
    public Button operatorTapeShoot = new JoystickButton(operator, 1);
    public Button operatorTapeRetract = new JoystickButton(operator, 4);


    
    
    

    public Button changeControl = new JoystickButton(driver, 10);

    //////////////////AXIS STUFF///////////////////////////
    //Driving (driver)
    public static int GO = 1;
    public static int STRAFE = 0;
    public static int TURN = 2;
    //Grabber (operator)
    //Do you even
    public static final int LIFT = 5;
    //bro?

    public static void changeControls() {
        if (GO == 1) {
            GO = 5;
            STRAFE = 2;
            TURN = 0;
        } else {
            GO = 1;
            STRAFE = 0;
            TURN = 2;
        }
    }

}
