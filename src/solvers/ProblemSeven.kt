package solvers

object ProblemSeven : Solver {
    override fun solve() {
        for (file in solverFiles) {
            val inputs = file.readLines()

            val size = inputs[0].toInt()
            val board: ArrayList<ArrayList<String>> =
                ArrayList(inputs.drop(1).map { ArrayList(it.map { it.toString() }) })

            while (board.flatten().any { it == "O" }) {
                for (row in board) {
                    replaceInRow(row, "OWW")
                    replaceInRow(row, "WOW")
                    replaceInRow(row, "WWO")
                }

                for ((i, column) in board.columns().withIndex()) {
                    replaceInColumn(column, "OWW", board, i)
                    replaceInColumn(column, "WOW", board, i)
                    replaceInColumn(column, "WWO", board, i)
                }

                for (row in board) {
                    if (row.count { it == "W" } == size / 2)
                        row.replaceAll { if (it == "O") "B" else it }
                    if (row.count { it == "B" } == size / 2)
                        row.replaceAll { if (it == "O") "W" else it }
                }

                for ((j, column) in board.columns().withIndex()) {
                    if (column.count { it == "W" } == size / 2)
                        for ((i, color) in column.withIndex())
                            if (color == "O")
                                board[i][j] = "B"

                    if (column.count { it == "B" } == size / 2)
                        for ((i, color) in column.withIndex())
                            if (color == "O")
                                board[i][j] = "W"
                }

                board.forEach(::println).also { end() }
            }

            board.forEach { println(it.joinToString("")) }
        }

        end()
    }

    private fun replaceInColumn(
        column: java.util.ArrayList<String>,
        pattern: String,
        board: ArrayList<ArrayList<String>>,
        i: Int
    ) {
        require(("W" in pattern || "B" in pattern) && !("W" in pattern && "B" in pattern)) { "bad pattern " }
        val replaceWith = if ("W" in pattern) "B" else "W"

        val i1 = column.joinToString("").indexOf(pattern)
        if (i1 != -1)
            board[i1 + pattern.indexOf("O")][i] = replaceWith

        val i2 = column.joinToString("").indexOf(-pattern)
        if (i2 != -1)
            board[i2 + pattern.indexOf("O")][i] = -replaceWith
    }

    private fun replaceInRow(row: ArrayList<String>, pattern: String) {
        require(("W" in pattern || "B" in pattern) && !("W" in pattern && "B" in pattern)) { "bad pattern " }
        val replaceWith = if ("W" in pattern) "B" else "W"

        val i1 = row.joinToString("").indexOf(pattern)
        if (i1 != -1)
            row[i1 + pattern.indexOf("O")] = replaceWith

        val i2 = row.joinToString("").indexOf(-pattern)
        if (i2 != -1)
            row[i2 + pattern.indexOf("O")] = -replaceWith
    }

    operator fun String.unaryMinus(): String {
        return if (length == 1) {
            if (this == "B") "W" else if (this == "W") "B" else this
        } else {
            map { it.toString().unaryMinus() }.joinToString(separator = "")
        }
    }

    private fun ArrayList<ArrayList<String>>.columns(): ArrayList<ArrayList<String>> {
        val columns = arrayListOf<ArrayList<String>>()
        for (i in get(0).indices) {
            columns.add(arrayListOf())
            for (row in this) {
                columns[i].add(row[i])
            }
        }
        return columns
    }
}