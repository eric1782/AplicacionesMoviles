package registroP

abstract class Pescado(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double
) {

    abstract fun descripcion(): String;

    open fun obtenerInfo(): String {
        return "Nombre: $nombre, Precio por Kilo: $precioPorKilo kg, Stock Kilos: $stockKilos kg"
    }

    // AGREGADO: Validaciones requeridas en el init
    init {
        require(precioPorKilo > 0) { "El precio debe ser mayor que 0" }
        require(stockKilos >= 0) { "El stock no puede ser negativo" }
    }
    // AGREGADO: Metodo para calcular valor total del stock
    fun valorTotalStock(): Double {
        return precioPorKilo * stockKilos
    }
}