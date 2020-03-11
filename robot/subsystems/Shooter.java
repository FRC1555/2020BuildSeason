/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

//import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Shooter extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// TODO: edit this reference to the SparkMAXs
	// public CANSparkMax shoot1;
	// public CANSparkMax shoot2;
	private boolean movingshooterLift=false;
	boolean armPositionUp = false;
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void init() {
		// shoot1 = Robot.map.shooter1;
		// shoot2 = Robot.map.shooter2;
		Robot.map.shooterLift.set(0);
		Robot.map.lswitchBottom.get();
	}

	//It is assumed that the motors will spin in opposite directions
	//Therefore, we will always activate them using this method
	public void shooterPower(double power) {
		// shoot1.set(power);
		// shoot2.set(-power);
		Robot.map.Shooter.set(power);
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

	// public void toggleShooterLift(){
	// 	if(!lswitchBottom.get() && lswitchTop.get() && !movingshooterLift){
	// 			shooterLift.set(0.3);
	// 			movingshooterLift=true;
	// 	}else if(lswitchBottom.get() && !lswitchTop.get() && !movingshooterLift){
	// 			shooterLift.set(-0.1);
	// 			movingshooterLift=true;
	// 	}else if(movingshooterLift && (!lswitchBottom.get() || !lswitchTop.get())){
	// 		shooterLift.set(0);
	// 		movingshooterLift=false;
	// 	}
	// }

	public void moveToPosition() {
		DigitalInput lswitch;
		double armSpeed;
		//Determines the limit switch and speed based off if we are going up or down
		if (armPositionUp) {
			lswitch = Robot.map.lswitchTop;
			armSpeed = 0.3;
		}
		else {
			lswitch = Robot.map.lswitchBottom;
			armSpeed = -0.1;
		}

		//Moves to the appropriate position
		if (!lswitch.get()) {
			Robot.map.shooterLift.set(armSpeed);
		}
		else {
			Robot.map.shooterLift.set(0);
		}
	}
}
