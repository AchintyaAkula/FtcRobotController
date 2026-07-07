package org.firstinspires.ftc.teamcode

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import org.opencv.core.Core.magnitude
import org.psilynx.psikit.core.Logger
import org.psilynx.psikit.core.wpi.math.Pose3d
import org.psilynx.psikit.core.wpi.math.Rotation3d
import org.psilynx.psikit.core.wpi.math.Transform3d
import org.psilynx.psikit.core.wpi.math.Translation3d
import kotlin.math.sqrt
import kotlin.time.ComparableTimeMark
import kotlin.time.TimeSource

class Ball(initialPos: Pose3d, bColor: BallColor, val id: Int) {
    val color = bColor
    var currPos = initialPos
    var velocity: Translation3d = Translation3d(0.0, 0.0, 0.0)
    var accel: Translation3d = Translation3d(0.0,0.0,0.0)
    var lastTStamp: ComparableTimeMark = TimeSource.Monotonic.markNow()
    var instant: Boolean = false

    companion object {
        const val R: Double = 0.0635;
    }

    fun update() {
        when (color) {
            BallColor.PURPLE -> { Logger.recordOutput("artifacts/purple/ballPos$id", currPos) }
            BallColor.GREEN -> { Logger.recordOutput("artifacts/green/ballPos$id", currPos) }
        }
        move()
    }

    fun move() {
        val currTStamp = TimeSource.Monotonic.markNow()
        val t: Double = (((currTStamp - lastTStamp).inWholeNanoseconds).toDouble() / 1000000000.0)

        this.velocity += (accel * t)
        this.currPos += Transform3d(velocity, Rotation3d(0.0,0.0,0.0)) * t
        this.lastTStamp = currTStamp
    }

    fun checkCollision(otherBall: Ball): Boolean {
        val c2c: Translation3d = otherBall.currPos.minus(this.currPos).translation
        val magnitude: Double = sqrt(c2c.x * c2c.x + c2c.y * c2c.y + c2c.z * c2c.z)

        return (magnitude < (2 * R))
    }

    fun applyInstantForce(f: Transform3d) {
        accel += (f / 74.0).translation
        instant = true
    }
}

enum class BallColor {
    PURPLE,
    GREEN
}