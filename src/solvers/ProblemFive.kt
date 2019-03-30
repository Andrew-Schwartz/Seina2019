package solvers

object ProblemFive : Solver {
    override fun solve() {
        for (file in solverFiles) {
            val str = file.readLines()[0]
            val sides = str.substring(0, str.length / 2) to
                    str.substring(str.length / 2 + if (str.length % 2 != 0) 1 else 0)
            println(str)

            val firstFreqs = sides.first.groupBy { it }.mapValues { it.value.size }
            val secondFreqs = sides.second.groupBy { it }.mapValues { it.value.size }
            var balance = 0

            for ((k, v) in firstFreqs)
                if (secondFreqs[k] ?: 0 < v) balance--
            for ((k, v) in secondFreqs)
                if (firstFreqs[k] ?: 0 < v) balance++

            println(
                if (balance == 0) {
                    if (sides.first.toSortedSet() == sides.second.toSortedSet()) {
                        "PERFECTLY BALANCED"
                    } else {
                        "BALANCED"
                    }
                } else if (balance < 0) {
                    "LEFT UNBALANCED"
                } else {
                    "RIGHT UNBALANCED"
                }
            )
        }

        end()
    }
}