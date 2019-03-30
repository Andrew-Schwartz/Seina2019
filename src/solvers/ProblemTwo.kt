package solvers

import kotlin.math.pow

object ProblemTwo : Solver {
    override fun solve() {
        for (file in solverFiles) {
            var n = file.readLines()[0].toInt()
            var count = 0

            while (n != 1 && n != 89) {
                n = n.squareDigits()
                count++
            }

            println("$n $count")
        }

        end()
    }

    private fun Int.squareDigits() = toString().map { Character.getNumericValue(it).sqr() }.sum()

    private fun Int.sqr() = this.toDouble().pow(2).toInt()
}