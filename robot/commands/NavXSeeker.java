/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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

    //speeds for the motors as they are pushing up against the wall
    double crashspeed;

    //max value of steering adjust
    public final double steeringcap = 0.3;

    //value for the stage when it starts running
    public int stage = 1;

    //timer for the robot to give it time to push up against the wall
    public Timer time
    = new Timer();

    //sets the difference in angle to 0 if the absolute value of the angle is less than 3 degrees away from the target angle
    public final double Angletolerancethreshold = 3;
    
    public static SeekVisionTarget kSeekVisionTarget
    = new SeekVisionTarget();

    double recordtarget0;

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
        if(Robot.map.lswitchTop.get()){
            Robot.map.shooterLift.set(0.3);
        }
        else if(!Robot.map.lswitchTop.get()){
            Robot.map.shooterLift.set(0);
        }
        
        switch(stage){
            case 1 : 
                Scheduler.getInstance().add(kSeekVisionTarget);
                if(kSeekVisionTarget.reachedTargetDistance == true){
                    stage ++;   
                } 
                break; 
            case 2 :
                lastRecorded0 = Robot.kLimelight.getLimeX() + Robot.kNavX.getYaw();
                stage ++;
                break;
            case 3 :
                Robot.Drive.driveTank(lspeed, rspeed);
                if(Robot.kNavX.hasCrashed() == true && !Robot.map.lswitchTop.get()){
                    stage++;
                }
                break;
            case 4 :
                time.reset();
                stage++;
                break;
            case 5 :
                Robot.Drive.driveTank(crashspeed, -crashspeed);
                if(time.get() > 2000){
                    stage++;
                }
                break;
            case 6 :
                time.reset();
                stage++;
                break;
            case 7 :
                    Robot.kShooter.shoot();
                if(time.get() > 1500){
                    Robot.kShooter.Stop();
                    stage++;
                }
                break;
            default:
                Robot.Drive.driveTank(0, 0);
                Robot.kShooter.Stop();
            }
        }
    // Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
        if(stage == 8){
            return true;
        }
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
        Robot.Drive.driveTank(0, 0);
        Robot.kShooter.Stop();
        Robot.map.shooterLift.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}