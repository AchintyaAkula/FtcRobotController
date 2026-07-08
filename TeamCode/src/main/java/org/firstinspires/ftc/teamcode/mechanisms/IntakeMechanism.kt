package org.firstinspires.ftc.teamcode.mechanisms

import com.pedropathing.ivy.Command
import com.pedropathing.ivy.groups.Groups.parallel
import com.pedropathing.ivy.groups.Groups.sequential
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.Mechanism
import org.firstinspires.ftc.teamcode.commands.SetPosition
import org.firstinspires.ftc.teamcode.commands.SetThrottle

/**
 * Mechanism to control intake motor, and intake gates to hold balls.
 */
class IntakeMechanism : Mechanism {
    // Hardware Declarations
    val intakeMotor: NextMotor = NextMotor("intake_motor")
    val leftGateServo: NextServo = NextServo("left_gate_servo")
    val rightGateServo: NextServo = NextServo("right_gate_servo")

    // Gate close/open commands
    val closeGateCommand: Command = parallel(
        SetPosition(leftGateServo, GATE_CLOSE_POSITION_LEFT),
        SetPosition(rightGateServo, GATE_CLOSE_POSITION_RIGHT)
    )
    val openGateCommand: Command = parallel(
        SetPosition(leftGateServo, GATE_OPEN_POSITION_LEFT),
        SetPosition(rightGateServo, GATE_OPEN_POSITION_RIGHT)
    )
    val intakeMaxPowerCommand: Command = intakeCommand(1.0)
    val intakeHalfPowerCommand: Command = intakeCommand(0.5)
    val stopIntakeCommand: Command = sequential(
        closeGateCommand,
        SetThrottle(intakeMotor, 0.0)
    )

    fun intakeCommand(throttle: Double): Command {
        require(throttle != 0.0) { "intakeCommand(0.0) is invalid, use stopIntakeCommand instead" }
        return sequential(
            SetThrottle(intakeMotor, throttle),
            openGateCommand
        )
    }

    companion object {
        // Gate Servo Positions
        const val GATE_CLOSE_POSITION_LEFT: Double = 0.725
        const val GATE_CLOSE_POSITION_RIGHT: Double = 0.425
        const val GATE_OPEN_POSITION_LEFT: Double = 0.5
        const val GATE_OPEN_POSITION_RIGHT: Double = 0.86
    }
}