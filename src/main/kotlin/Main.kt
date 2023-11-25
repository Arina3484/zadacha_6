import kotlin.math.pow
import kotlin.math.sqrt

// Класс Точка
data class Point(val x: Double, val y: Double)

// Класс Треугольник
class Triangle(val vertexA: Point, val vertexB: Point, val vertexC: Point) {
    // Метод для вычисления центра и радиуса описанной окружности
    fun circumCircle(): Pair<Point, Double> {
        // Вычисляем середины сторон треугольника
        val midAB = Point((vertexA.x + vertexB.x) / 2, (vertexA.y + vertexB.y) / 2)
        val midBC = Point((vertexB.x + vertexC.x) / 2, (vertexB.y + vertexC.y) / 2)

        // Вычисляем коэффициенты уравнения прямой, проходящей через середины сторон AB и BC
        val slopeAB = -(vertexB.x - vertexA.x) / (vertexB.y - vertexA.y)
        val slopeBC = -(vertexC.x - vertexB.x) / (vertexC.y - vertexB.y)

        // Вычисляем координаты центра окружности
        val centerX = (slopeAB * midAB.x - slopeBC * midBC.x + midBC.y - midAB.y) / (slopeAB - slopeBC)
        val centerY = slopeAB * (centerX - midAB.x) + midAB.y

        // Вычисляем радиус окружности
        val radius = sqrt((centerX - vertexA.x).pow(2) + (centerY - vertexA.y).pow(2))

        return Pair(Point(centerX, centerY), radius)
    }
}

fun main() {
    // Пример использования
    val pointA = Point(0.0, 0.0)
    val pointB = Point(4.0, 0.0)
    val pointC = Point(2.0, 3.0)

    val triangle = Triangle(pointA, pointB, pointC)
    val (center, radius) = triangle.circumCircle()

    println("Центр описанной окружности: (${center.x}, ${center.y})")
    println("Радиус описанной окружности: $radius")
}