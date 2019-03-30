package solvers

object ProblemThree : Solver {
    override fun solve() {
        for (file in solverFiles) {
            val vals = file.readLines()[0].split(" ")
            var n = vals[0].toInt()
            val base = vals[1].toInt()

            while (n != 0) {
                val rem = n % base
                println("${n / base} $rem")
                n /= base
            }

            println(Integer.toString(vals[0].toInt(), base).toUpperCase())
        }

        end()
    }
}