package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Dispenser;
import org.firstinspires.ftc.teamcode.subsystems.Extension;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

public class MainTeleOpMode {
    @TeleOp(name="TeleopOp_Roadrunner")

    public class TeleopOpRoadrunner extends LinearOpMode {
        private ElapsedTime runtime = new ElapsedTime();
        Pose2d startPose = new Pose2d(0,0, 0);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Extension extension = new Extension(hardwareMap);
        Pivot pivot = new Pivot(hardwareMap);
        Dispenser dispenser = new Dispenser(hardwareMap);

        @Override
        public void runOpMode(){
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            waitForStart();
            runtime.reset();

            while (opModeIsActive()){
                float extensionSpeed = 0.8f;
                double rotateSpeed = 0.8;
                float pivotSpeed = 0.8f;
                drive.drive(-gamepad2.left_stick_x, -gamepad2.left_stick_y,
                        -gamepad2.right_stick_x * rotateSpeed, gamepad2.right_trigger, gamepad2.left_trigger);
                extension.extension(gamepad1.right_stick_y, gamepad1.right_stick_y, extensionSpeed);
                pivot.pivot(gamepad1.left_trigger, gamepad1.right_trigger, pivotSpeed);
                dispenser.dispenser(gamepad1.left_bumper, gamepad1.right_bumper);

                // Show the elapsed game time and wheel power.
                //telemetry.addData("Left Trigger", -intake);
                //telemetry.addData("Right Trigger", dispense);
                //telemetry.addData("Arm Langth", Extension.getCurrentPosition());
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                //telemetry.addData("Pivot Angle", Pivot.getCurrentPosition());
                //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
                telemetry.update();
            }

        }

    }
}
