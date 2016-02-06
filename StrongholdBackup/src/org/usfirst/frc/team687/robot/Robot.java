
package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	DoubleSolenoid shooterPunch;
	CANTalon motorIntakeArtic, motorShooterLift, motorShooterLeft, motorShooterRight;
	Joystick joystickLeft, joystickRight, joystickArtic;
	VictorSP motorFrontRight, motorBackRight, motorFrontLeft, motorBackLeft, motorIntakeRoller;
	double leftSpeed, rightSpeed, intakeRollerPower, intakeArticPower, shooterLiftPower, shooterPower;
	
    public void robotInit() {
    	joystickLeft = new Joystick(0);  //Left Driver Joystick
    	joystickRight = new Joystick(1); //Right Driver Joystick
    	motorFrontRight = new VictorSP(3);
    	motorBackRight = new VictorSP(5);
    	motorFrontLeft = new VictorSP(2);
    	motorBackLeft = new VictorSP(4);
    	motorIntakeRoller = new VictorSP(6);  //Runs intake rollers
    	motorIntakeArtic = new CANTalon(4);  //Moves intakes "in" and "out" of the robot.
    	motorShooterLift = new CANTalon(3);  //Alters shooter angle
    	motorShooterLeft = new CANTalon(1);  //Left outtake on shooter
    	motorShooterRight = new CANTalon(2); //Right outtake on shooter
    	shooterPunch = new DoubleSolenoid(5, 6);  //Solenoid that hits the ball at the shooter outtakes while they spin
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//Gets and Sets speeds for drive motors
       leftSpeed = -joystickLeft.getY();
       rightSpeed = joystickRight.getY(); 
       motorFrontRight.set(rightSpeed);
       motorBackRight.set(rightSpeed);
       motorFrontLeft.set(leftSpeed);
       motorBackLeft.set(leftSpeed);
 
       //Sets the power for the intake rollers
       if (joystickArtic.getRawButton(7)) {  //Intake
    	   intakeRollerPower = 0.75;
       }
       else if (joystickArtic.getRawButton(8)) {  //Outtake
    	   intakeRollerPower = -0.75;
       }
       else {  //Idle
    	   intakeRollerPower = 0.0;
       }
       //Sets the Intake Roller Motor's power.
       motorIntakeRoller.set(intakeRollerPower);
       
       //Sets the power for motorIntakeArtic
       if (joystickArtic.getRawButton(9)) {
    	   intakeArticPower = 0.25;
       }
       else if (joystickArtic.getRawButton(10)) {
    	   intakeArticPower = -0.25;
       }
       else {
    	   intakeArticPower = 0.0;
       }
     //Sets the Intake Artic Motor's power.
       motorIntakeArtic.set(intakeArticPower);
       
       //Sets power for motorShooterLift
       if (joystickArtic.getRawButton(6)) {
    	   shooterLiftPower = 0.25;
       }
       else if (joystickArtic.getRawButton(4)) {
    	   shooterLiftPower = -0.25;
       }
       else {
    	   shooterLiftPower = 0.0;
       }
       //Sets Shooter Lift Motor's power.
       motorShooterLift.set(shooterLiftPower);
       
       //Sets power for the ShooterLeft and ShooterRight motors
       if (joystickArtic.getRawButton(3)) {
    	   shooterPower = 1.0;
       }
       else {
    	   shooterPower = 0.0;
       }
       //Sets Shooter Motors' power.
       motorShooterLeft.set(shooterPower);  //CHECK: IS ONE INVERSED?
       motorShooterRight.set(shooterPower);
       
       //Activates solenoid
       if (joystickArtic.getRawButton(1)) {
    	   shooterPunch.set(DoubleSolenoid.Value.kForward);
       }
       else {
    	   shooterPunch.set(DoubleSolenoid.Value.kReverse);
       }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }

}