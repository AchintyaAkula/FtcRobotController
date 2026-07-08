package org.firstinspires.ftc.teamcode.mechanisms

import com.pedropathing.ivy.Command
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.Mechanism
import org.firstinspires.ftc.teamcode.commands.SetPosition

/**
 * Mechanism to control transfer process with kicker and shooter gate
 */
class TransferMechanism : Mechanism {
    val blockerServo: NextServo = NextServo("blocker_servo")
    val kickerServo: NextServo = NextServo("kicker_servo")

    val blockShooter: Command = SetPosition(blockerServo, SHOOTER_BLOCKED_POSITION)
    val releaseShooter: Command = SetPosition(blockerServo, SHOOTER_RELEASED_POSITION)

    val engageKicker: Command = SetPosition(kickerServo, KICKER_ENGAGED_POSITION)
    val disengageKicker: Command = SetPosition(kickerServo, KICKER_DISENGAGED_POSITION)

    companion object {
        const val KICKER_ENGAGED_POSITION = 0.2
        const val KICKER_DISENGAGED_POSITION = 0.02
        const val SHOOTER_BLOCKED_POSITION = 0.2
        const val SHOOTER_RELEASED_POSITION = 0.3
    }
}