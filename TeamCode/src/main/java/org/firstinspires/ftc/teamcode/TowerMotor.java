package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Basic:Tower OpMode", group="Linear Opmode")

public class TowerMotor extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor tdrive = null;
    // private DcMotor cDrive = null;
   // private DcMotor rDrive = null;

    public void runOpMode()
    {
    HardWarePushBotCustom         robot   = new HardWarePushBotCustom();

        robot.init(hardwareMap);



        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        robot.tDrive.setDirection(DcMotor.Direction.FORWARD);
        robot.pDrive.setDirection(DcMotor.Direction.FORWARD);
        //robot.rDrive.setDirection(DcMotor.Direction.REVERSE);
        //  cDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            //double drive = gamepad1.
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            robot.tDrive.setPower(leftPower);
            robot.pDrive.setPower(rightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f)", leftPower);
            telemetry.addData("Motors", "right (%.2f)", rightPower);
            telemetry.update();
        }
    }
}