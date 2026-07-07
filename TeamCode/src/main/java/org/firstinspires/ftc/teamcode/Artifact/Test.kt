package org.firstinspires.ftc.teamcode.Artifact

import dev.nextftc.linalg.N3
import dev.nextftc.linalg.Vector
import org.psilynx.psikit.core.Logger
import org.psilynx.psikit.core.rlog.RLOGServer
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun main() {
    val server = RLOGServer()
    Logger.addDataReceiver(server)
    periodic()
}

fun periodic() {
    val am = ArtifactManager()
    am.setupInitialConfig()
    Logger.start()
    Thread.sleep(10)
    Logger.periodicBeforeUser()
    val testBall = am.artifacts[0]
    testBall.applyForce(Force(Artifact.MASS, Vector.of(N3, 0.1, 0.0,0.0)), 50.0.toDuration(DurationUnit.MILLISECONDS))
    testBall.applyForce(Force(Artifact.MASS, Vector.of(N3, -0.01, 0.0,0.0)), 500.0.toDuration(DurationUnit.MILLISECONDS))
    am.update()
    Logger.periodicAfterUser(0.0,0.0)
    while (true) {
        Logger.periodicBeforeUser()
        am.update()
        Thread.sleep(10)
        Logger.periodicAfterUser(0.0, 0.0)
    }
}