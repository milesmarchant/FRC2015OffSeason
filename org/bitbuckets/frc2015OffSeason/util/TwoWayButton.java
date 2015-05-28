package org.bitbuckets.frc2015OffSeason.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class TwoWayButton extends Button{

    GenericHID m_joystick;
    int m_buttonNumber;

    /**
     * Create a joystick button which activates one command when pressed and another when released.
     * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
     * @param buttonNumber The button number (see {@link GenericHID#getRawButton(int) }
     */
    public TwoWayButton(GenericHID joystick, int buttonNumber) {
        m_joystick = joystick;
        m_buttonNumber = buttonNumber;
    }
	
    public void setAction(final Command onPress, final Command onRelease){
    	whenPressed(onPress);
    	whenReleased(onRelease);
    }
	
    /**
     * Gets the value of the joystick button
     * @return The value of the joystick button
     */
    public boolean get() {
        return m_joystick.getRawButton(m_buttonNumber);
    }

}
