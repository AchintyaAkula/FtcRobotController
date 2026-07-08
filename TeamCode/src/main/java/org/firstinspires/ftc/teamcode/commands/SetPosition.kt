package org.firstinspires.ftc.teamcode.commands

import dev.nextftc.hardware.actuators.NextServo

class SetPosition @JvmOverloads constructor(
    servo: NextServo,
    position: Double = 0.0
) : InstantCommand( { servo.position = position } ) {  }