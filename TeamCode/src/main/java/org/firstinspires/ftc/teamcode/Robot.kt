package org.firstinspires.ftc.teamcode

import dev.nextftc.robot.Mechanism
import dev.nextftc.robot.NextRobot
import org.firstinspires.ftc.teamcode.mechanisms.IntakeMechanism
import org.firstinspires.ftc.teamcode.mechanisms.TransferMechanism

class Robot : NextRobot {
    override val mechanisms: Set<Mechanism>
        get() = setOf(IntakeMechanism(), TransferMechanism())
}