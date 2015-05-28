package org.bitbuckets.frc2015OffSeason.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Adds the ability to use a d-pad on a controller as separate buttons.
 * <p/>
 * Created by James on 1/25/2015.
 */
public class POVHatDirButton extends Trigger {
    private Direction hatDir;
    private Joystick stick;

    public POVHatDirButton(Joystick joystick, Direction dir) {
        hatDir = dir;
        stick = joystick;
    }

    @Override
    public boolean get() {
        switch (hatDir) {
            case UP:
                if (stick.getPOV() == 0) {
                    return true;
                }
                break;
            case UP_RIGHT:
                if (stick.getPOV() == 45) {
                    return true;
                }
                break;
            case RIGHT:
                if (stick.getPOV() == 90) {
                    return true;
                }
                break;
            case DOWN_RIGHT:
                if (stick.getPOV() == 135) {
                    return true;
                }
                break;
            case DOWN:
                if (stick.getPOV() == 180) {
                    return true;
                }
                break;
            case DOWN_LEFT:
                if (stick.getPOV() == 225) {
                    return true;
                }
                break;
            case LEFT:
                if (stick.getPOV() == 270) {
                    return true;
                }
                break;
            case UP_LEFT:
                if (stick.getPOV() == 315) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
