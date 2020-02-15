/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * An example command.  You can replace me with your own command.
 */
public class RotationControl extends Command {
    //
	String startColor; 
	//
	String Color2;

	Victor spinner = Robot.map.spinner;
	
	//String[] col = ("Green", "Blue", "Yellow", "Red");
	
	//
	boolean Flag;
	//
	int Ticks;
    
    public RotationControl() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kExampleSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startColor = "Unknown";
		Flag = false;
		Ticks = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Spin for 4 rotations
        // Store the current color
		if (startColor == "Unknown") {
			startColor = Robot.eyeball.RobotColorDetector();
			return;
		}
		spinner.set(1);

		// Check for a color change
		
		Color2 = Robot.eyeball.RobotColorDetector();
		
		if (Color2 == startColor) {
			if (!Flag) {
				Ticks += 1;
			}
			Flag = true;
		}
		else {
			Flag = false;
		}
		// Stop after 8 ticks have been accumulated
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (Ticks == 8) {
			return true;
		}
		else {
			return false;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		spinner.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
