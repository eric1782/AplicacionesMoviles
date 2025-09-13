package Frutas

abstract class Fruta
    (
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double
) {

    abstract fun descripcion(): String;

    open fun obtenerInfo(): String {
        return "Nombre: $nombre, Precio por Kilo: $precioPorKilo kg, Stock Kilos: $stockKilos kg"
    }

    // validaciones para init
    init {
        require(precioPorKilo > 0) { "el precio debe ser mayor a 0" }
        require(stockKilos >= 0) { "tu stock no puede ser negativo" }
    }

    fun valorTotalStock(): Double {
        return precioPorKilo * stockKilos
    }
}