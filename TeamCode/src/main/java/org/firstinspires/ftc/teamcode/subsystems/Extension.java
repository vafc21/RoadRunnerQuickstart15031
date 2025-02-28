package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.PIDController;

public class Extension {
    private DcMotor Extension;
    PIDController extensionPID = new PIDController(0.007);
    private int extensionSetpoint = 0;
    public Extension(HardwareMap hardwareMap){
        Extension = hardwareMap.get(DcMotor.class, "exten");


    }
    public void extension(float extensionOut, float extensionIn, float extensionSpeed){
        extensionOut = extensionOut*extensionSpeed;
        extensionIn = extensionIn*extensionSpeed;
        if (extensionOut > 0.01){
            extensionSetpoint += extensionOut*20;

        } else if (extensionIn < -0.01){
            extensionSetpoint -= extensionIn*-20;

        }
        //extensionSetpoint = MathUtils.clamp(extensionSetpoint,);
        double outputE = extensionPID.calculate(Extension.getCurrentPosition(),extensionSetpoint);
        Extension.setPower(outputE + 0.01);
    }

}
