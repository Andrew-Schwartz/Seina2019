package solvers

object ProblemOne : Solver{
    enum class Size(val teaspoonsInA: Int) {
        TEASPOONS(1),
        TABLESPOONS(3),
        CUPS(48),
        PINTS(96),
        QUARTS(192),
        GALLONS(768)
    }

    override fun solve() {
        for (file in solverFiles) {
            val values = file.readLines()[0].split(" ")
            val initTsps = values[0].toInt() * Size.valueOf(values[1]).teaspoonsInA
            val finalTsps = initTsps / Size.valueOf(values[2]).teaspoonsInA
            println(finalTsps)
        }

        end()
    }
}