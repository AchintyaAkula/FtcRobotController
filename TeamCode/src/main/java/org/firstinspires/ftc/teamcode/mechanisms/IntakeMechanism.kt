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
    /**
     * Intake motor for intaking mechanism
     * Config name: "intake_motor"
     */
    val intakeMotor: NextMotor = NextMotor("intake_motor")

    /**
     * Left-side gate servo for keeping artifacts in/out
     * Config name: "left_gate_servo"
     */
    val leftGateServo: NextServo = NextServo("left_gate_servo")

    /**
     * Right-side gate servo for keeping artifacts in/out
     * Config name: "right_gate_servo"
     */
    val rightGateServo: NextServo = NextServo("right_gate_servo")

    /**
     * Command to close the gate:
     *  Makes both servos go to the designated close position
     */
    val closeGate: Command = parallel(
        SetPosition(leftGateServo, GATE_CLOSE_POSITION_LEFT),
        SetPosition(rightGateServo, GATE_CLOSE_POSITION_RIGHT)
    )

    /**
     * Command to open the gate:
     *  Makes both servos go to the designated open position
     */
    val openGate: Command = parallel(
        SetPosition(leftGateServo, GATE_OPEN_POSITION_LEFT),
        SetPosition(rightGateServo, GATE_OPEN_POSITION_RIGHT)
    )

    /**
     * Command to intake at full throttle -> 1.0
     */
    val intakeMaxThrottle: Command = intake(1.0)

    /**
     * Command to intake at half throttle -> 0.5
     */
    val intakeHalfThrottle: Command = intake(0.5)

    /**
     * Command to stop intaking:
     * Closes gate and then sets motor throttle to 0
     */
    val stopIntake: Command = sequential(
        closeGate,
        SetThrottle(intakeMotor, 0.0)
    )

    /**
     * Command supplier to return a Command that intakes at the specified throttle:
     * Sets throttle, and then opens the gate to allow artifacts to enter
     *
     * @param throttle The magnitude of throttle to intake at:
     * MUST be >0, if stopping is required, then [stopIntake] should be used
     *
     * @return Command that will set the motor throttle when scheduled
     */
    fun intake(throttle: Double): Command {
        require(throttle != 0.0) { "intake(0.0) is invalid, use stopIntake instead" }
        return sequential(
            SetThrottle(intakeMotor, throttle),
            openGate
        )
    }

    /**
     * Stores pre-calculated constants for servos positions:
     * Close/open x Left/Right
     */
    companion object {
        const val GATE_CLOSE_POSITION_LEFT: Double = 0.725
        const val GATE_CLOSE_POSITION_RIGHT: Double = 0.425
        const val GATE_OPEN_POSITION_LEFT: Double = 0.5
        const val GATE_OPEN_POSITION_RIGHT: Double = 0.86
    }
}