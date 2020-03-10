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

        /*
        Matthew's suggested code layout
        Think of this command running as something that runs in stages
        In each stage the robot will behaves differently
        All we need is some sort of tracker that knows what stage we are in,
        and a logic block that can make us run the proper stage
        Each stage would also have an exit condition: something that would trigger the next stage once satisfied
        There's already a great piece of logic we can use for this called a switch statment
        You can find an example of a switch statement in the nextColorClockwise function in the ColorSensor subsystem
        It would look something like this
        
        int stage = 1;
        switch (stage) {
            case 1:
                //Stage one code
                //Activate the SeekVisionTarget command
                //Check to see if SeekVisionTarget finished. If it did, stage++;
                //Finally, we need a break; statememt to end the swtich.
                //Without it, the program will continue to run the code for ALL the following cases, despite the fact that their cases haven't
                //Been triggered, until it encounters a break; statement.
                break;
            case 2:
                //Stage two code
                //Set a heading by adding the current heading from the navX to the angle the limelight shows we are currently at from the target
                //we only want this to happen once, so we can immediately write stage++;
                break;
            case 3:
                //Stage three code
                //Drive at the specified heading
                //Check to see if we've crashed. If we have, stage++;
                break;
            case 4:
                //Stage 4 code
                //Record or reset the timer object
                stage++;
                break;
            case 5:
                //Stage 5 code
                //Run for a set ammount of time to make sure we are flat against the wall
                //We don't want to put a delay() function here, becasue that would delay the entire robot, not just this command
                //Check to see if the neccessary amount of time has passed. If so, stage++;
                break;
            case 6:
                //Stage 6 code
                //Record or reset the timer object
                stage++;
                break;
            case 7:
                //Stage 7 code
                //Deposit fuel cells
                //Again, wait a certain amount of time
                //Check to see if the neccessary amount of time has passed. If so, stage++;
                break;
            default:
                //If none of the previous conditions are true
                //Set all motor powers to 0
                //Make the isFinished function return true when stage == 8
                break;
        }
        */
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
