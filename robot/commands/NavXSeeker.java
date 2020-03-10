/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



/**
 * An example command.  You can replace me with your own command.
 */
public class NavXSeeker extends Command {

    //This value will hold the value for the angle that was last recorded before the limelight was not within distance of the target
    public double lastRecorded0;

    //This value will hold the current recorded angle from the navx
    public double current0;

    //default speed for the motors
    public final double kdrivespeed = 0.2;
    
    //Turning speed for the robot when aligning itself for the last recorded 0 
	double turnSpeed = 0.25;

    //This value will have the power adjustment for the motors
    double speedAdjust;
    double steeringAdjust;

	//speeds to give for the drive motors(after adjustments)
	double lspeed;
    double rspeed;
    
    //max value of steering adjust
    public final double steeringcap = 0.3;

    //sets the difference in angle to 0 if the absolute value of the angle is less than 3 degrees away from the target angle
    public final double Angletolerancethreshold = 3;
    
    public static SeekVisionTarget kSeekVisionTarget
    = new SeekVisionTarget();

    boolean recordtarget0;

	public NavXSeeker() {
    //requires(Robot.kExampleSubsystem);
		requires(Robot.kNavX);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
       steeringAdjust = 0;
       current0 = Robot.kNavX.getIMUPitch();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//This sets the last recorded Pitch(left to right turns) that the robot was at before it lost track of the target when it was using the lime light 
		if(Robot.kSeekVisionTarget.reachedTargetDistance == true){
            lastRecorded0 = Robot.kNavX.getIMUPitch();
            recordtarget0 = false;    
        }    
        //adjusting the robot to be at the correct angle when moving towards the target
        steeringAdjust = Math.abs(current0) - Math.abs(lastRecorded0);
        if(steeringAdjust > steeringcap){
            steeringAdjust = steeringcap;
        }
        else if(steeringAdjust < -steeringcap){
            steeringAdjust = -steeringcap;
        }
        lspeed = steeringAdjust - kdrivespeed;
        rspeed = steeringAdjust + kdrivespeed;
        //speed of the robot
        Robot.Drive.driveTank(lspeed, rspeed);
        if(steeringAdjust > 10){
            Robot.Drive.driveTank(turnSpeed, -turnSpeed);
        }
        else if(steeringAdjust <-10){
            Robot.Drive.driveTank(-turnSpeed, turnSpeed);

        }
        
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