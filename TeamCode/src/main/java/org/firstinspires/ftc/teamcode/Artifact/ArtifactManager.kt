package org.firstinspires.ftc.teamcode.Artifact

import dev.nextftc.linalg.N3
import dev.nextftc.linalg.Vector

class ArtifactManager {
    val artifacts: MutableList<Artifact> = mutableListOf()

    fun setupInitialConfig() {
        var i = 0
        for (ball in positions) {
            artifacts.add(Artifact(ball.first, ball.second, i))
            i++
        }
    }

    fun update() {
        artifacts.forEach { it.update() }
    }
}

val positions: List<Pair<ArtifactColor, Vector<N3>>> = listOf(
    ArtifactColor.GREEN to Vector.of(N3, 0.889564, 1.071361-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, 0.889564, 1.200005-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, 0.889564, 1.326172-0.0635, 0.0635 ),

    ArtifactColor.PURPLE to Vector.of(N3, 0.292983, 1.071361-0.0635, 0.0635 ),
    ArtifactColor.GREEN to Vector.of(N3, 0.292983, 1.200005-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, 0.292983, 1.326172-0.0635, 0.0635 ),

    ArtifactColor.PURPLE to Vector.of(N3, -0.301017, 1.071361-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, -0.301017, 1.200005-0.0635, 0.0635 ),
    ArtifactColor.GREEN to Vector.of(N3, -0.301017, 1.326172-0.0635, 0.0635 ),

    ArtifactColor.GREEN to Vector.of(N3, 0.889564, -1.071361-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, 0.889564, -1.200005-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, 0.889564, -1.326172-0.0635, 0.0635 ),

    ArtifactColor.PURPLE to Vector.of(N3, 0.292983, -1.071361-0.0635, 0.0635 ),
    ArtifactColor.GREEN to Vector.of(N3, 0.292983, -1.200005-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, 0.292983, -1.326172-0.0635, 0.0635 ),

    ArtifactColor.PURPLE to Vector.of(N3, -0.301017, -1.071361-0.0635, 0.0635 ),
    ArtifactColor.PURPLE to Vector.of(N3, -0.301017, -1.200005-0.0635, 0.0635 ),
    ArtifactColor.GREEN to Vector.of(N3, -0.301017, -1.326172-0.0635, 0.0635 ),
)