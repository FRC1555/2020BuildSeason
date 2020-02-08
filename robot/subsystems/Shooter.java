/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Shooter extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static Victor shoot = Robot.map.shooter;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
    }

	public static void FullPower() {
        shoot.set(1);
    }
    public static void Stop() {
        shoot.set(0);
    }
    public static void Reverse() {
        shoot.set(-1);
	}
}
