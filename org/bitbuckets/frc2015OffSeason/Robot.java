
package org.bitbuckets.frc2015OffSeason;

import org.bitbuckets.frc2015OffSeason.subsystems.Drivey;
import org.bitbuckets.frc2015OffSeason.subsystems.Grabby;
import org.bitbuckets.frc2015OffSeason.subsystems.Shooty;
import org.bitbuckets.frc2015OffSeason.subsystems.Stacky;
import org.bitbuckets.frc2015OffSeason.subsystems.Tilty;
import org.bitbuckets.frc2015OffSeason.subsystems.Winchy;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public OI oi;
	public Tilty tilty;
	public Grabby grabby;
	public Winchy winchy;
	public Drivey drivey;
	public Shooty shooty;
	public Stacky stacky;
	
	public PowerDistributionPanel pdp;
	private Compressor compressor;
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
		tilty = new Tilty("Tilty", 50);
		grabby = new Grabby("Grabby", 30);
		winchy = new Winchy("Winchy", 30);
		drivey = new Drivey("Drivey", 10);
		shooty = new Shooty("Shooty", 30);
		stacky = new Stacky("Stacky", 20);
		
		pdp = new PowerDistributionPanel();
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);

		//FIXME these need to be moved
		tilty.start();
		grabby.start();
		winchy.start();
		drivey.start();
		
		
    }
	

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    }
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	

    public void autonomousInit() {
        if (autonomousCommand != null) {
        	autonomousCommand.start();
        }
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }


    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    
    @Override
    public void testInit(){
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
