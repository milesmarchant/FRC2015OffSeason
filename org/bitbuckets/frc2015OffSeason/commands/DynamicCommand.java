package org.bitbuckets.frc2015OffSeason.commands;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @param <V>
 *
 */
public class DynamicCommand<T> extends Command {
	
	Consumer<T> action;
	T value;

    public DynamicCommand(Consumer<T> action, T value) {
    	this.action = action;
    	this.value = value;
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	action.accept(value);
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
