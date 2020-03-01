/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Utilities;
//import frc.robot.commands.LimeLightAim;
import oi.limelightvision.limelight.frc.LimeLight;

/**
 * An example subsystem. You can replace with me with your own subsystem.
 */
public class DriveTrainSlow extends Subsystem {

  private final TalonFX leftMotor1 = new TalonFX(RobotMap.MOTORS.LEFT_MOTOR_1.ordinal()); // drive train motors
  private TalonFX leftMotor2 = null;
  //private PWMSparkMax leftMotor3 = null;
  private final TalonFX rightMotor1 = new TalonFX(RobotMap.MOTORS.RIGHT_MOTOR_1.ordinal());
  private  TalonFX rightMotor2 = null;
  //private  PWMSparkMax rightMotor3 = null;
  public int selSenPos = leftMotor1.getSelectedSensorPosition();
  private final LimeLight _limelight;
 

  // private final double distancePerPulse = (2.0 * RobotMap.WHEEL_DIAMETER *
  // RobotMap.ENCODER_GEAR_RATIO) /
  // (RobotMap.ENCODER_PULSES_PER_REVOLUTION);

  // private int leftOffset = 0;
  // private int rightOffset = 0;

  public DriveTrainSlow() {
    _limelight = new LimeLight();

    if (Robot.jankMode) {

      this.leftMotor2 = new TalonFX(RobotMap.MOTORS.LEFT_MOTOR_2.ordinal());
      this.rightMotor2 = new TalonFX(RobotMap.MOTORS.RIGHT_MOTOR_2.ordinal());
      this.leftMotor2.follow(this.leftMotor1);
      this.rightMotor2.follow(this.rightMotor1);
      this.rightMotor2.setInverted(false);
      this.leftMotor2.setInverted(false);
    } else {
      this.leftMotor2 = new TalonFX(RobotMap.MOTORS.LEFT_MOTOR_2.ordinal());
      this.rightMotor2 = new TalonFX(RobotMap.MOTORS.RIGHT_MOTOR_2.ordinal());
      this.leftMotor2.follow(this.leftMotor1);
      this.rightMotor2.follow(this.rightMotor1);
      this.rightMotor2.setInverted(false);
      this.leftMotor2.setInverted(false);
    }

    this.leftMotor1.configOpenloopRamp(RobotMap.RAMP_RATE, 0);
    this.rightMotor1.configOpenloopRamp(RobotMap.RAMP_RATE, 0);

    this.rightMotor1.configNeutralDeadband(0.07);
    this.rightMotor2.configNeutralDeadband(0.07);
    this.leftMotor1.configNeutralDeadband(0.07);
    this.leftMotor2.configNeutralDeadband(0.07);

  
  }

  @Override
  protected void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }

  public void setLeftMotor(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_SLOW);
    this.leftMotor1.set(ControlMode.PercentOutput, motorSetting); // 2 is following 1

    // check current and ensure safe limit //if (this.leftMotor1.getOutputCurrent()
    // > RobotMap.CURRENT_LIMIT) {
    // this.leftMotor1.set(ControlMode.Current, RobotMap.CURRENT_LIMIT);
    // }
  }

  public void setRightMotor(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_SLOW);
    this.rightMotor1.set(ControlMode.PercentOutput, motorSetting); // 2 is following 1

  }

  public void stop() {
    this.leftMotor1.set(ControlMode.PercentOutput, 0);
    this.rightMotor2.set(ControlMode.PercentOutput, 0);
  }

  public void backwards() {
    setLeftMotor(RobotMap.MAX_SPEED_ADRIVE);
    setRightMotor(-RobotMap.MAX_SPEED_ADRIVE);
  }

  public LimeLight gLimeLight() {
    return _limelight;
  }


}

