package org.firstinspires.ftc.teamcode.commands

import dev.nextftc.hardware.actuators.NextMotor

class SetThrottle @JvmOverloads constructor(
    motor: NextMotor,
    throttle: Double = 0.0
) : InstantCommand( { motor.setThrottle(throttle) } ) {  }