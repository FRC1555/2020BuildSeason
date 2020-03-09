/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


/**
 * An example command.  You can replace me with your own command.
 */
public class NavXSeeker extends Command {

	public double lastRecorded0;

	double Angle;
	//speed for the Driving motors before adjustments
	public final double kLDriveSpeed = 0.2;

	//speeds to give for the drive motors(after adjustments)
	double lspeed;
	double rspeed;
	
	//steering adjustment speed for the robot
	double turnSpeed = 0.25;

	public NavXSeeker() {

		//requires(Robot.kExampleSubsystem);
		requires(Robot.kNavX);
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//This sets the last recorded Pitch(left to right turns) that the robot was at before it lost track of the target when it was using the lime light 
		if(Robot.kSeekVisionTarget.isFinished() == true){
			lastRecorded0 = Robot.kNavX.getIMUPitch();
			Robot.Drive.driveTank(lspeed, rspeed);
		}

		//This portion of the code is used for keeping the robot on track towards the target


		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
