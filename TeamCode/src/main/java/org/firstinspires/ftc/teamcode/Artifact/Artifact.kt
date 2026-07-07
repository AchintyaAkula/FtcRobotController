package org.firstinspires.ftc.teamcode.Artifact

import dev.nextftc.linalg.N3
import dev.nextftc.linalg.Vector
import dev.nextftc.units.Grams
import dev.nextftc.units.Inches
import dev.nextftc.units.Pounds
import org.psilynx.psikit.core.Logger
import org.psilynx.psikit.core.wpi.math.Pose3d
import org.psilynx.psikit.core.wpi.math.Rotation3d
import kotlin.time.ComparableTimeMark
import kotlin.time.Duration
import kotlin.time.TimeSource

class Artifact(val color: ArtifactColor, initialPos: Vector<N3>, val id: Int) {
    var pos: Vector<N3> = initialPos
    var vel: Vector<N3> = Vector.of(N3, 0.0,0.0,0.0)
    var accel: Vector<N3> = Vector.of(N3, 0.0,0.0,0.0)

    var prevTStamp: ComparableTimeMark = TimeSource.Monotonic.markNow()

    val currForces: MutableMap<ComparableTimeMark, Pair<Force, Duration>> = mutableMapOf()

    companion object {
        val RADIUS = Inches.of(2.5)
        val MASS = Pounds.of(0.165)
    }

    fun update() {
        move()
        when (color) {
            ArtifactColor.GREEN -> {
                Logger.recordOutput("artifacts/green/ballPos$id", Pose3d(pos.get(0), pos.get(1), pos.get(2), Rotation3d(0.0,0.0,0.0)))
            }
            ArtifactColor.PURPLE -> {
                Logger.recordOutput("artifacts/purple/ballPos$id", Pose3d(pos.get(0), pos.get(1), pos.get(2), Rotation3d(0.0,0.0,0.0)))
            }
        }
    }

    fun move() {
        val currTStamp: ComparableTimeMark = TimeSource.Monotonic.markNow()
        val tElapsed: Double = ((currTStamp - prevTStamp).inWholeNanoseconds).toDouble() / 1000000000.0

        adjustSystem(currTStamp)
        println(accel)
        println(vel)

        vel += (accel * tElapsed)
        pos += (vel * tElapsed)
    }

    fun adjustSystem(currTStamp: ComparableTimeMark) {
        val toRemove: MutableList<ComparableTimeMark> = mutableListOf()
        for (force in currForces.entries) {
            val t = force.key
            val f = force.value
            if ((currTStamp - t) >= f.second) {
                toRemove.add(t)
            } else {
                accel += Vector(f.first.magnitude / MASS.into(Grams))
            }
        }
        println(currForces)
        for (e in toRemove) {
            currForces.remove(e)
        }
    }

    fun applyForce(force: Force, time: Duration) =
        currForces.put(TimeSource.Monotonic.markNow(), force to time)
}

enum class ArtifactColor {
    GREEN,
    PURPLE
}