package org.bitbuckets.frc2015OffSeason.commands;

import org.bitbuckets.frc2015OffSeason.subsystems.StateSubsystem;
import org.bitbuckets.frc2015OffSeason.subsystems.state.State;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StateSetter extends Command {

    public StateSetter(StateSubsystem context, State state) {
    	context.setState(state);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
