package util;

import org.bitbuckets.frc2015OffSeason.RobotConstants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Adds the ability to use a d-pad on a controller as separate buttons.
 * <p/>
 * Created by James on 1/25/2015.
 */
public class JoystickDirButton extends Button {
    private Direction joyDir;
    private Joystick stick;
    private int xAxis;
    private int yAxis;

    public JoystickDirButton(Joystick joystick, Direction dir, int horizontalAxis, int verticalAxis) {
        joyDir = dir;
        stick = joystick;
        xAxis = horizontalAxis;
        yAxis = verticalAxis;
    }

    @Override
    public boolean get() {
        switch (joyDir) {
            case UP:
                if (aboveDeadbandInDirection(yAxis, -1) && !aboveDeadband(xAxis)) {
                    return true;
                }
                break;
            case UP_RIGHT:
                if (aboveDeadbandInDirection(yAxis, -1) && aboveDeadbandInDirection(xAxis, 1)) {
                    return true;
                }
                break;
            case RIGHT:
                if (aboveDeadbandInDirection(xAxis, 1) && !aboveDeadband(yAxis)) {
                    return true;
                }
                break;
            case DOWN_RIGHT:
                if (aboveDeadbandInDirection(yAxis, 1) && aboveDeadbandInDirection(xAxis, 1)) {
                    return true;
                }
                break;
            case DOWN:
                if (aboveDeadbandInDirection(yAxis, 1) && !aboveDeadband(xAxis)) {
                    return true;
                }
                break;
            case DOWN_LEFT:
                if (aboveDeadbandInDirection(yAxis, 1) && aboveDeadbandInDirection(xAxis, -1)) {
                    return true;
                }
                break;
            case LEFT:
                if (aboveDeadbandInDirection(xAxis, -1) && !aboveDeadband(yAxis)) {
                    return true;
                }
                break;
            case UP_LEFT:
                if (aboveDeadbandInDirection(yAxis, -1) && aboveDeadbandInDirection(xAxis, -1)) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    private boolean aboveDeadbandInDirection(int axis, int direction) {
        return Math.abs(stick.getRawAxis(axis)) > RobotConstants.STICK_TO_BUTTON_DEADBAND && (int) (Math.abs(stick.getRawAxis(axis)) / stick.getRawAxis(axis)) == direction;
    }

    private boolean aboveDeadband(int axis){
        return Math.abs(stick.getRawAxis(axis)) > RobotConstants.STICK_TO_BUTTON_DEADBAND;
    }
}
