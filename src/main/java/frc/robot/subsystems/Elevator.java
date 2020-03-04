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
import frc.robot.commands.Loader;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final VictorSPX liftMotor1 = new VictorSPX(RobotMap.MOTORS.ELEV_LIFT_1.ordinal());
  private final VictorSPX liftMotor2 = new VictorSPX(RobotMap.MOTORS.ELEV_LIFT_2.ordinal()); 
  

  public Elevator() {
    this.liftMotor1.configNeutralDeadband(0.1);
    this.liftMotor2.configNeutralDeadband(0.1);
      
  }

  public VictorSPX getLiftMotor2() {
    return liftMotor2;
  }

  public VictorSPX getLiftMotor1() {
    return liftMotor1;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    super.setDefaultCommand(new Loader());
  }

  public void setLiftMotor1(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_LIFT1);
    this.liftMotor1.set(ControlMode.PercentOutput, motorSetting); // 2 is following 1
  }
  
  public void setLiftMotor2(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_LIFT2);
    this.liftMotor2.set(ControlMode.PercentOutput, motorSetting); // 2 is following 1
  }

  public void stop(){
    this.liftMotor1.set(ControlMode.PercentOutput, 0);
    this.liftMotor2.set(ControlMode.PercentOutput, 0);
  }

  public void run(){
    setLiftMotor1(-RobotMap.MAX_SPEED_LIFT1);
    setLiftMotor2(RobotMap.MAX_SPEED_LIFT2);
  }
}
