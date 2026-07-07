package org.firstinspires.ftc.teamcode

import org.psilynx.psikit.core.Logger
import org.psilynx.psikit.core.rlog.RLOGServer
import org.psilynx.psikit.core.wpi.math.Pose3d
import org.psilynx.psikit.core.wpi.math.Rotation3d
import org.psilynx.psikit.core.wpi.math.Translation3d

class Field {
    private val server: RLOGServer = RLOGServer();
    private val balls: MutableList<Ball> = mutableListOf()
    private val positions: List<Pair<BallColor, Pose3d>> = listOf(
        BallColor.GREEN to Pose3d(0.889564, 1.071361-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(0.889564, 1.200005-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(0.889564, 1.326172-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),

        BallColor.PURPLE to Pose3d(0.292983, 1.071361-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.GREEN to Pose3d(0.292983, 1.200005-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(0.292983, 1.326172-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),

        BallColor.PURPLE to Pose3d(-0.301017, 1.071361-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(-0.301017, 1.200005-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.GREEN to Pose3d(-0.301017, 1.326172-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),

        BallColor.GREEN to Pose3d(0.889564, -1.071361-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(0.889564, -1.200005-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(0.889564, -1.326172-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),

        BallColor.PURPLE to Pose3d(0.292983, -1.071361-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.GREEN to Pose3d(0.292983, -1.200005-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(0.292983, -1.326172-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),

        BallColor.PURPLE to Pose3d(-0.301017, -1.071361-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.PURPLE to Pose3d(-0.301017, -1.200005-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
        BallColor.GREEN to Pose3d(-0.301017, -1.326172-0.0635, 0.0635, Rotation3d(0.0, 0.0, 0.0)),
    )

    init {
        Logger.addDataReceiver(server)
        var i = 0
        for (b in positions) {
            balls.add(Ball(b.second, b.first, i))
            i ++
        }
        balls[0].velocity = Translation3d(0.0,-0.1,0.0)
        balls[9].velocity = Translation3d(0.0,0.1,0.0)
    }

    fun periodic() {
        for (b in balls) {
            b.update()
        }
        Thread.sleep(10)
        Logger.recordOutput("Collision? d   ", balls[0].checkCollision(balls[9]))
    }
}

fun main() {
    val t = Field()
    Logger.start()
    while (true) {
        Logger.periodicBeforeUser()
        t.periodic()
        Logger.periodicAfterUser(0.0,0.0)
    }
}