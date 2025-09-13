// Main.kt
import registroP.*
import kotlinx.coroutines.*

// Sealed class para control de calidad
sealed class ResultadoCalidad {
    data class Exitoso(val pescado: Pescado, val mensaje: String) : ResultadoCalidad()
    data class Error(val pescado: Pescado, val motivo: String) : ResultadoCalidad()
}

fun main() = runBlocking {
    println("===========================================")
    println("    SALMONES DEL SUR SPA - PUERTO MONTT")
    println("      Sistema de Gestión de Inventario")
    println("===========================================\n")

    val pescados = mutableListOf<Pescado>()

    // Menú principal
    while (true) {
        mostrarMenu()

        try {
            print("Ingrese su opción: ")
            val opcion = readLine()?.toIntOrNull() ?: 0

            when (opcion) {
                1 -> registrarPescado(pescados)
                2 -> mostrarPescados(pescados)
                3 -> filtrarPescadosCaros(pescados)
                4 -> calcularValorTotal(pescados)
                5 -> demonstrarPolimorfismo(pescados)
                6 -> realizarControlCalidad(pescados)
                0 -> {
                    println("\n¡Gracias por usar nuestro sistema!")
                    break
                }
                else -> println("Opción inválida. Intente nuevamente.\n")
            }
        } catch (e: Exception) {
            println("Error inesperado: ${e.message}\n")
        }
    }
}

/**
 * Muestra el menú principal del sistema
 */
fun mostrarMenu() {
    println("\n MENÚ PRINCIPAL:")
    println("1. Registrar pescado")
    println("2. Mostrar pescados registrados")
    println("3. Filtrar pescados premium (>$5000)")
    println("4. Calcular valor total del stock")
    println("5. Demostrar herencia y polimorfismo")
    println("6. Control de calidad (corrutinas)")
    println("0. Salir")
    println("-".repeat(45))
}

/**
 * Registra un nuevo pescado en el sistema
 */
fun registrarPescado(pescados: MutableList<Pescado>) {
    println("\n=== REGISTRO DE PESCADO ===")

    try {
        // Solicitar tipo de pescado
        println("Tipos disponibles:")
        println("1. Salmón")
        println("2. Pescado Blanco")
        print("Seleccione tipo (1-2): ")

        val tipo = readLine()?.toIntOrNull()
        if (tipo !in 1..2) {
            println("Tipo inválido. Operación cancelada.")
            return
        }

        // Solicitar nombre
        print("Ingrese nombre del pescado: ")
        val nombre = readLine()?.trim()
        if (nombre.isNullOrBlank()) {
            println("El nombre no puede estar vacío.")
            return
        }

        // Solicitar precio con validación
        print("Ingrese precio por kilo ($): ")
        val precioTexto = readLine()?.trim()
        val precio = precioTexto?.toDoubleOrNull()
        if (precio == null) {
            println("Precio inválido. Debe ser un número.")
            return
        }

        // Solicitar stock con validación
        print("Ingrese stock en kilos: ")
        val stockTexto = readLine()?.trim()
        val stock = stockTexto?.toDoubleOrNull()
        if (stock == null) {
            println("Stock inválido. Debe ser un número.")
            return
        }

        // Crear pescado según tipo seleccionado
        val pescado = when (tipo) {
            1 -> Salmon(nombre, precio, stock)
            2 -> PescadoBlanco(nombre, precio, stock)
            else -> throw IllegalArgumentException("Tipo no válido")
        }

        pescados.add(pescado)
        println("Pescado '$nombre' registrado exitosamente!")
        println("${pescado.obtenerInfo()}")

    } catch (e: IllegalArgumentException) {
        println("Error de validación: ${e.message}")
    } catch (e: Exception) {
        println("Error inesperado: ${e.message}")
    }
}

/**
 * Muestra todos los pescados registrados
 */
fun mostrarPescados(pescados: List<Pescado>) {
    println("\n=== PESCADOS REGISTRADOS ===")

    if (pescados.isEmpty()) {
        println("No hay pescados registrados en el sistema.")
        return
    }

    pescados.forEachIndexed { index, pescado ->
        println("${index + 1}. ${pescado.obtenerInfo()}")
    }

    println("\nResumen:")
    println("Total de pescados: ${pescados.size}")

    val salmones = pescados.count { it is Salmon }
    val blancos = pescados.count { it is PescadoBlanco }
    println("Salmones: $salmones | Pescados Blancos: $blancos")
}

/**
 * Filtra y muestra pescados con precio > $5000
 */
fun filtrarPescadosCaros(pescados: List<Pescado>) {
    println("\n===PESCADOS PREMIUM (>$5000) ===")

    val pescadosCaros = pescados.filter { it.precioPorKilo > 5000 }

    if (pescadosCaros.isEmpty()) {
        println("No hay pescados con precio mayor a $5000")
        return
    }

    pescadosCaros.forEachIndexed { index, pescado ->
        println("${index + 1}. ${pescado.nombre}: $${String.format("%.2f", pescado.precioPorKilo)}/kg")
        println("   Stock: ${pescado.stockKilos} kg")
    }

    println("\nTotal pescados premium: ${pescadosCaros.size}")
}

/**
 * Calcula y muestra el valor total del stock usando sumOf
 */
fun calcularValorTotal(pescados: List<Pescado>) {
    println("\n=== VALOR TOTAL DEL STOCK ===")

    if (pescados.isEmpty()) {
        println("No hay pescados registrados para calcular.")
        return
    }

    val valorTotal = pescados.sumOf { it.valorTotalStock() }

    println("Valor total del inventario: $${String.format("%,.2f", valorTotal)}")
    println("\nDesglose por pescado:")

    pescados.forEachIndexed { index, pescado ->
        val valor = pescado.valorTotalStock()
        println("${index + 1}. ${pescado.nombre}")
        println("   ${pescado.stockKilos} kg × $${pescado.precioPorKilo}/kg = $${String.format("%.2f", valor)}")
    }
}

/**
 * Demuestra herencia y polimorfismo
 */
fun demonstrarPolimorfismo(pescados: List<Pescado>) {
    println("\n=== HERENCIA Y POLIMORFISMO ===")

    if (pescados.isEmpty()) {
        println("No hay pescados registrados.")
        return
    }

    println("Cada tipo implementa descripcion() de forma diferente:\n")

    pescados.forEachIndexed { index, pescado ->
        println("${index + 1}. [${pescado::class.simpleName}] ${pescado.nombre}")
        println("   ${pescado.descripcion()}\n")
    }

    // Clasificación por herencia
    val salmones = pescados.filterIsInstance<Salmon>()
    val pescadosBlancos = pescados.filterIsInstance<PescadoBlanco>()

    println("Clasificación por herencia:")
    println("Salmones: ${salmones.size}")
    println("Pescados Blancos: ${pescadosBlancos.size}")
}

/**
 * Función suspend para simular control de calidad
 */
suspend fun controlarCalidad(pescado: Pescado): ResultadoCalidad {
    println("🔍 Controlando calidad de: ${pescado.nombre}")

    // Simular proceso lento con delay
    val tiempoControl = kotlin.random.Random.nextLong(800, 2000)
    delay(tiempoControl)

    // Simular resultado basado en precio (pescados más caros tienen mejor calidad)
    val probabilidadExito = when {
        pescado.precioPorKilo > 10000 -> 0.95
        pescado.precioPorKilo > 7000 -> 0.85
        pescado.precioPorKilo > 5000 -> 0.75
        else -> 0.60
    }

    val esExitoso = kotlin.random.Random.nextDouble() < probabilidadExito

    return if (esExitoso) {
        val mensajes = listOf(
            "Excelente calidad!!!!",
            "Muy buena condición!",
            "Aprobado para exportación",
            "Cumple estándares basicos de premium noma"
        )
        ResultadoCalidad.Exitoso(pescado, mensajes.random())
    } else {
        val motivos = listOf(
            "Color irregular detectado",
            "Textura no cumple estándares",
            "Aroma atípico encontrado",
            "Tamaño fuera de rango"
        )
        ResultadoCalidad.Error(pescado, motivos.random())
    }
}

/**
 * Realiza control de calidad usando corrutinas y sealed class
 */
suspend fun realizarControlCalidad(pescados: List<Pescado>) {
    println("\n===CONTROL DE CALIDAD CON CORRUTINAS ===")

    if (pescados.isEmpty()) {
        println("No hay pescados para controlar.")
        return
    }

    // Permitir al usuario seleccionar cuántos pescados controlar
    val cantidadMaxima = minOf(pescados.size, 5) // Máximo 5 para no demorar mucho
    println("Pescados disponibles: ${pescados.size}")
    print("¿Cuántos pescados desea controlar? (1-$cantidadMaxima): ")

    val cantidad = try {
        readLine()?.toIntOrNull()?.coerceIn(1, cantidadMaxima) ?: 3
    } catch (e: Exception) {
        3 // Default
    }

    val pescadosAControlar = pescados.take(cantidad)

    println("\nIniciando control de calidad de ${pescadosAControlar.size} pescados...")
    println("Esto puede tomar unos segundos...\n")

    // Procesar con corrutinas de forma concurrente
    val resultados = coroutineScope {
        pescadosAControlar.map { pescado ->
            async { controlarCalidad(pescado) }
        }.awaitAll()
    }

    println("\nRESULTADOS DEL CONTROL DE CALIDAD:")
    println("=".repeat(50))

    // Usar when con sealed class
    resultados.forEachIndexed { index, resultado ->
        when (resultado) {
            is ResultadoCalidad.Exitoso -> {
                println("${index + 1}.  ${resultado.pescado.nombre}")
                println("   ${resultado.mensaje}")
                println("   Valor: $${String.format("%.2f", resultado.pescado.valorTotalStock())}")
            }
            is ResultadoCalidad.Error -> {
                println("${index + 1}.  ${resultado.pescado.nombre}")
                println("   ${resultado.motivo}")
                println("   Valor: $${String.format("%.2f", resultado.pescado.valorTotalStock())}")
            }
        }
        println()
    }

    // Resumen estadístico
    val aprobados = resultados.count { it is ResultadoCalidad.Exitoso }
    val rechazados = resultados.count { it is ResultadoCalidad.Error }
    val valorAprobado = resultados.filterIsInstance<ResultadoCalidad.Exitoso>()
        .sumOf { it.pescado.valorTotalStock() }

    println(" RESUMEN:")
    println(" Aprobados: $aprobados (${(aprobados * 100.0 / resultados.size).toInt()}%)")
    println(" Rechazados: $rechazados (${(rechazados * 100.0 / resultados.size).toInt()}%)")
    println(" Valor productos aprobados: $${String.format("%,.2f", valorAprobado)}")
}