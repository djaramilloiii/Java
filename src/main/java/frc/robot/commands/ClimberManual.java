/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberManual extends Command {
  //final DigitalInput forwardLimitSwitch, reverseLimitSwitch;
  final DigitalInput forwardLimitSwitch = new DigitalInput(8);
  final DigitalInput reverseLimitSwitch = new DigitalInput(9);
  private static final int kUltrasonicPort = 0;
  private static final double kValueToInches = 1;

  private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);


  public ClimberManual() {
    super.requires(Robot.climber);
    // Use requires() here to declare subsystem dependencies
    
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  
    //OI.operatorController.setRumble(RumbleType.kLeftRumble, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rightStick = Robot.m_oi.getOperatorRawAxis(RobotMap.RIGHT_STICK_Y);
    double currentDistance = m_ultrasonic.getValue() * kValueToInches;
       if (forwardLimitSwitch.get()) // If the forward limit switch is pressed, we want to keep the values between -1 and 0
            rightStick = Math.min(rightStick, 1);
           //OI.operatorController.setRumble(RumbleType.kLeftRumble, 1);
        else if(reverseLimitSwitch.get()) // If the reversed limit switch is pressed, we want to keep the values between 0 and 1
            rightStick = Math.max(rightStick, 0);
       // else
       // OI.operatorController.setRumble(RumbleType.kLeftRumble, 0);
        if (currentDistance > 12)
          rightStick = rightStick/42;
        
      Robot.climber.setClimbMotor1(+rightStick);
      Robot.climber.setClimbMotor2(-rightStick);
      System.out.println("distance");
      System.out.println(currentDistance);
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
