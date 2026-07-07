package org.firstinspires.ftc.teamcode.Artifact

import dev.nextftc.linalg.N3
import dev.nextftc.linalg.Vector
import dev.nextftc.units.Grams
import dev.nextftc.units.measuretypes.Mass

data class Force(val mass: Mass, val accel: Vector<N3>) {
    val magnitude: Vector<N3>
        get() = accel * mass.into(Grams)
}