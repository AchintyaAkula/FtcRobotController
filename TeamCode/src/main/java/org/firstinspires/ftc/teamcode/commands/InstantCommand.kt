package org.firstinspires.ftc.teamcode.commands

import com.pedropathing.ivy.CommandBuilder

open class InstantCommand(
    task: Runnable,
) : CommandBuilder() {
    init {
        setStart(task)
        setDone { true }
    }
}
