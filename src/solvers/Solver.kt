package solvers

import java.io.File

interface Solver {
    companion object {
        fun end() = println("----------------------------")
    }

    fun end() = Companion.end()

    fun solve()

    val solverFiles: Array<out File>
        get() = File("src/inputs/${this::class.simpleName!!.substringAfter("Problem").toLowerCase()}").listFiles()
}