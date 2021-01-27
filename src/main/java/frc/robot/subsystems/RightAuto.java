/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
//import frc.robot.RobotMap;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.LimeLightAim;
import frc.robot.commands.LoadBallTimed;
import frc.robot.commands.RevShooter;
import frc.robot.commands.Space;
import frc.robot.commands.TimedShoot;

public class RightAuto extends CommandGroup {
   
  public RightAuto() {
    double timeout;
    timeout = .125;
    addSequential(new LimeLightAim(), timeout);
    addSequential(new LimeLightAim(), timeout);
    addSequential(new RevShooter());
    addParallel(new TimedShoot());
    addSequential(new LoadBallTimed());
    addSequential(new Space(3));
    addSequential(new AutoDrive(.5, .5, 2.5));
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
