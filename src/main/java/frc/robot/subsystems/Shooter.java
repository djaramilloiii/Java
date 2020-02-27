/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Utilities;
import frc.robot.commands.ShootBall;


public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final VictorSPX shootMotor1 = new VictorSPX(RobotMap.MOTORS.SHOOT_MOTOR_1.ordinal());
  

  public Shooter() {


  }

  public VictorSPX getGrabMotor1() {
    return shootMotor1;
  }

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    super.setDefaultCommand(new ShootBall());
  }

  public void setShootMotor1(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_SHOOT);
    this.shootMotor1.set(ControlMode.PercentOutput, motorSetting); //
  }

  public void runShooter(){
    setShootMotor1(RobotMap.MAX_SPEED_SHOOT);
  }

  public void stop(){
    setShootMotor1(0);
  }
}
