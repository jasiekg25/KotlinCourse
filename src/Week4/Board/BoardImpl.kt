package Week4.Board

import java.lang.IllegalArgumentException

data class ImplementedCell<T>(

    override val i: Int,
    override val j: Int,
    var value: T? = null

) : Cell {
    override fun toString(): String {
        return "($i, $j)"
    }
}


open class SquareBoardImplementation<T>(final override val width: Int) : SquareBoard {
    protected val cells: List<ImplementedCell<T>>

    fun ImplementedCell<T>.toString() = "($i, $j)"

    init {
        if (width <= 0) throw IllegalArgumentException("Width must be greater than 0, now width = $width")
        cells = IntRange(1, width)
            .flatMap { i ->
                IntRange(1, width)
                    .map { j -> ImplementedCell<T>(i, j) }
            }
    }

    protected fun hashCode(row: Int, column: Int): Int {
        require(row >= 1) { "row must be greater than 0" }
        require(column >= 1) { "column must be greater than 0" }
        return (row - 1) * width + (column - 1)
    }

    private fun getRange(fixedCoord: Int, range: IntProgression, indexer: (Int, Int) -> Int): List<Cell> {
        val (start, end) = restrictToBoardBoundaries(range)
        return IntProgression.fromClosedRange(start, end, range.step).map { fluentCoord ->
            val index = indexer(fixedCoord, fluentCoord)
            cells[index]
        }
    }

    private fun restrictToBoardBoundaries(range: IntProgression): Pair<Int, Int> {
        return if (range.step > 0)
            Pair(maxOf(range.first, 1), minOf(range.last, width))
        else
            Pair(minOf(range.first, width), maxOf(range.last, 1))
    }


    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i <= width && j <= width) cells[hashCode(i, j)] else null
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j)
            ?: throw IllegalArgumentException("This cell is not exist, Your value is: cell($i, $j)")
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return getRange(i, jRange) { row, column -> hashCode(row, column) }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return getRange(j, iRange) { column, row -> hashCode(row, column) }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            Direction.DOWN ->
                if (i < width) cells[hashCode(i + 1, j)] else null
            Direction.LEFT ->
                if (i > 1) cells[hashCode(i, j - 1)] else null
            Direction.UP ->
                if (i > 1) cells[hashCode(i - 1, j)] else null
            Direction.RIGHT ->
                if (i < width) cells[hashCode(i, j + 1)] else null

        }
    }
}

class GameBoardImplementation<T>(width: Int) : SquareBoardImplementation<T>(width), GameBoard<T> {
    override fun get(cell: Cell): T? {
        return cells[hashCode(cell.i, cell.j)].value
    }

    override fun set(cell: Cell, value: T?) {
        cells[hashCode(cell.i, cell.j)].value = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return cells.filter(cellMatcher(predicate))
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return cells.find(cellMatcher(predicate))
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return cells.any(cellMatcher(predicate))
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return cells.all(cellMatcher(predicate))
    }


    private fun cellMatcher(predicate: (T?) -> Boolean): (ImplementedCell<T>) -> Boolean {
        return { cell -> predicate(cell.value) }
    }
}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImplementation<Nothing>(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImplementation(width)

