/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
//Find out why we can't use this import
//import edu.wpi.first.wpilibj2.PIDController;

public class NavX extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    AHRS ahrs;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
    }
    
    public double heading() {
        return Robot.map.ahrs.getAngle();
    }
}
