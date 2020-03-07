/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Shooter extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// TODO: edit this reference to the SparkMAXs
	public CANSparkMax shoot1;
	public CANSparkMax shoot2;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void init() {
		shoot1 = Robot.map.shooter1;
		shoot2 = Robot.map.shooter2;
	}

	//It is assumed that the motors will spin in opposite directions
	//Therefore, we will always activate them using this method
	public void shooterPower(double power) {
		shoot1.set(power);
		shoot2.set(-power);
	}

	public void shoot() {
		shooterPower(1);
	}
    public void Stop() {
		shooterPower(0);
	}
    public void intake() {
        shooterPower(-0.3);
	}
}
