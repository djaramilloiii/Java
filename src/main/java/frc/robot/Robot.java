/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriverControls;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static boolean jankMode = false;
  public static Elevator elevator = new Elevator();
  public static DriveTrain driveTrain = new DriveTrain();
  public static Turret turret = new Turret();
  public static Grabber grabber = new Grabber();
  public static Shooter shooter = new Shooter();
  
  //public static Climber climber = new Climber();
  //public static Elevator elevator = new Elevator();
  //public static Sensors sensors = new Sensors();
  //public static Claw claw;
  //private static final Compressor comp = new Compressor(0);
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    //Robot.comp.setClosedLoopControl(true);
    m_chooser.setDefaultOption("Default Auto", new DriverControls());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    
    //CameraServer.getInstance().startAutomaticCapture();
    Thread c = new CameraThread();
    c.setDaemon(true);
    c.start();

    this.initSmartDashboard();
  }

  private void initSmartDashboard() {
    SmartDashboard.putNumber("selSenPos", Robot.driveTrain.selSenPos);
  }

  public void updateSmartDashboard(){
    SmartDashboard.putNumber("selSenPos", Robot.driveTrain.selSenPos);
  }
  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    this.updateSmartDashboard();

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}
class CameraThread extends Thread {

  @Override
  public void run() {

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setVideoMode(new VideoMode(VideoMode.PixelFormat.kMJPEG, 360, 240, 20));

    CvSink cvSink = CameraServer.getInstance().getVideo();
    CvSource outputStream = CameraServer.getInstance().putVideo("the working one", 360, 240);

    Mat source = new Mat();
    Mat output = new Mat();

    int height = 3;
    Point a = new Point(0, 239 - height);
    Point b = new Point(359, 239 - height);

    camera.close();
    while(!Thread.interrupted()){
      cvSink.grabFrame(source);
      Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
      Imgproc.line(output, a, b, new Scalar(0), 1);
      outputStream.putFrame(output);
    }

  }
}

