package solvers

object ProblemFour : Solver {
    override fun solve() {
        for (file in solverFiles) {
            val lines = file.readLines()
            val height = lines[0].split(" ")[0].toInt()
            val board = Array(height) { i ->
                lines[i + 1].map { it.toString() }.toTypedArray()
            }
            val targets = Array(lines[height + 1].toInt()) { lines[it + height + 2] }

            for (target in targets) {
                println(find(target, board))
            }
        }

        end()
    }

    private fun find(target: String, board: Array<Array<String>>): String {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (isAt(target, board, i, j, 1, 0)) {
                    return "$target FOUND"
                }
                if (isAt(target, board, i, j, 1, 1)) {
                    return "$target FOUND"
                }
                if (isAt(target, board, i, j, 0, 1)) {
                    return "$target FOUND"
                }
                if (isAt(target, board, i, j, 1, -1)) {
                    return "$target FOUND"
                }
            }
        }
        return "$target NOT FOUND"
    }

    private fun isAt(key: String, board: Array<Array<String>>, i: Int, j: Int, iDir: Int, jDir: Int): Boolean {
        return try {
            var found = ""
            for (k in key.indices) found += board[i + k * iDir][j + k * jDir]

            found == key || found.reversed() == key
        } catch (e: Exception) {
            false
        }
    }
}