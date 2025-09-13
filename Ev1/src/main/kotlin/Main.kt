import Frutas.*
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

//sealed class para control de calidad
sealed class ResultadoCalidad{
    data class Exitoso(val frutaval: Fruta,val mensaje: String) : ResultadoCalidad()
    data class Error(val frutaval: Fruta,val mensaje: String) : ResultadoCalidad()
}

//menu principal
fun main() = runBlocking {
    println("--------------------------------------")
    println("Bienvenido a Puerto Frutal SPA!")
    println("--------------------------------------")

    val frutas = mutableListOf<Fruta>()

    //menu main
    while(true){
        mostrarMenu()
        try{
            println("Ingresa la opcion que quieres realizar: ")
            val opcion = readLine()?.toIntOrNull() ?: 0

            when (opcion){
                1 -> registrarFruta(frutas)
                2 -> mostrarFrutas(frutas)
                3 -> mostrarFrutaCara(frutas)
                4 -> CalcularValorStock(frutas)
                5 -> realizandocontrolCalidad(frutas)


            }

        }catch (e: Exception){
            println("Error inesperado: ${e.message}")
        }


    }


}

//funciones detras del menu principal ese de arriba

fun mostrarMenu(){
    println("Menu Principal:")
    println("1.- Agregar fruta")
    println("2.- Mostrar frutas creadas")
    println("3.- Mostrar frutas premium (>1000)")
    println("4.- Calcular valor total de stock actual")
    println("5.- Control de calidad")
    println("0.- salir")
}

//Metodo agregar fruta
fun registrarFruta(frutas: MutableList<Fruta>){
    println("--------------------------------------")
    try{
        println("Ingresa la opcion de fruta que deseas agregar: ")
        println("1.- Fruta local")
        println("2.- Fruta ropical")
        println("Escoge una de las dos opciones (1 o 2): ")

        val tipo = readLine()?.toIntOrNull()
        if (tipo !in 1..2){
            println("tienes que ingresar 1 o 2, yapo...intenta denuevo")
        }

        println("Ingresa el nombre de la fruta: ")
        val nombre = readLine().toString()
        if (nombre.isNullOrBlank()){
            println("tienes que ingresar el nombre po, intenta denuevo")
            println("--------------------------------------")
            return
        }

        print("Ingresa el precio por kilo de la fruta: $")
        val preciotexto = readLine()?.trim()
        val precio = preciotexto?.toDoubleOrNull()
        if (precio == null){
            println("precio invalido, tiene que ser un numero y tenes que escribirlo bien")
            println("--------------------------------------")
            return
        }

        print("Ingrese stock en kilos para esta fruta (el stock que agregaras): ")
        val stocktexto = readLine()?.trim()
        val stock = stocktexto?.toDoubleOrNull()
        if (stock == null){
            println("Stock invalido, tiene que ser un numero y tenes que escribirlo bien")
            println("--------------------------------------")
            return
        }

        val fruta = when (tipo){
            1 -> FrutaLocal(nombre,precio,stock)
            2 -> FrutaTropical(nombre, precio, stock)
            else -> throw IllegalArgumentException("tipo invalido")
        }

        frutas.add(fruta)
        println("Fruta $nombre registrada exitosamente")
        println("${fruta.obtenerInfo()}")
    }catch (e: Exception){
        println("Error inesperado: ${e.message}")
        println("--------------------------------------")
    }
    println("--------------------------------------")

}

//Metodo mostrar fruta
fun mostrarFrutas(fruta: List<Fruta>){
    println("--------------------------------------")
    println("-----Listado de frutaas-----")

    if (fruta.isEmpty()){
        println("No hay frutas registradas todavia, intenta registrar una")
        println("--------------------------------------")
        return
    }

    fruta.forEachIndexed { index, fruta ->
        println("${index + 1}. ${fruta.obtenerInfo()}")
    }

    println("esas son todas las frutas por ahora...intenta registrar mas!")

    println("--------------------------------------")


}

fun mostrarFrutaCara(fruta: List<Fruta>){
    println("--------------------------------------")
    println("-----fruta cara-----")

    val frutaCara = fruta.filter { it.precioPorKilo > 1000 }

    if (frutaCara.isEmpty()){
        println("No hay frutas registradas, intenta registrar una que sea sobre 1000 en su precio x kilo")
        println("--------------------------------------")
        return
    }
    frutaCara.forEachIndexed { index, fruta ->
        println("${index + 1}. ${fruta.obtenerInfo()}")
    }
    println("Esas son las frutas caras....por el momento")

    println("--------------------------------------")


}

fun CalcularValorStock(fruta: List<Fruta>){
    println("--------------------------------------")

    println("-----Calcular valor total stock-----")

    if (fruta.isEmpty()){
        print("no hay frutas registradas, registra algunas para calcular el valor total del stock actual!")
        println("--------------------------------------")
        return
    }

    val valorTotal = fruta.sumOf{it.valorTotalStock()}
    println("Valor total del inventario: $${String.format("%,.0f", valorTotal)}")

    println("\nDesglose por fruta:")

    fruta.forEachIndexed { index, fruta ->
        val valor = fruta.valorTotalStock()
        println("${index + 1}. ${fruta.nombre}")
        println("   ${fruta.stockKilos} kg × $${fruta.precioPorKilo}/kg = $${String.format("%.2f", valor)}")
    }
    println("--------------------------------------")

}
suspend fun controlarCalidad(fruta: Fruta): ResultadoCalidad {
    println("Controlando calidad de: ${fruta.nombre}")

    // Simular proceso lento con delay
    val tiempoControl = kotlin.random.Random.nextLong(800, 2000)
    delay(tiempoControl)

    // Simular resultado basado en precio
    val probabilidadExito = when {
        fruta.precioPorKilo > 10000 -> 0.95
        fruta.precioPorKilo > 7000 -> 0.85
        fruta.precioPorKilo > 5000 -> 0.75
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
        ResultadoCalidad.Exitoso(fruta, mensajes.random())
    } else {
        val motivos = listOf(
            "Color irregular detectado",
            "Textura no cumple estándares",
            "Aroma raro",
            "Tamaño fuera de rango"
        )
        ResultadoCalidad.Error(fruta, motivos.random())
    }
}
//control de calidad
suspend fun realizandocontrolCalidad(frutas: List<Fruta>) {
    println("--------------------------------------")
    println("-----Control de calidad-----")
    if (frutas.isEmpty()) {
        println("No hay fruta para controlar, intenta crear un par de frutas!")
        return
    }

    val cantidadMaxima = minOf(frutas.size, 5) // Máximo 5 para no demorar mucho
    println("Frutas disponibles: ${frutas.size}")
    print("¿Cuántas frutas quieres controlar? (1-$cantidadMaxima): ")

    val cantidad = try {
        readLine()?.toIntOrNull()?.coerceIn(1, cantidadMaxima) ?: 3
    } catch (e: Exception) {
        println("Error inesperado: ${e.message}")
    }

    val frutasAControlar = frutas.take(cantidad as Int)

    println("\nIniciando control de calidad de ${frutasAControlar.size} frutas...")
    println("Esto puede tomar unos segundos...\n")

    val resultados = coroutineScope {
        frutasAControlar.map { fruta ->
            async { controlarCalidad(fruta) }
        }.awaitAll()
    }

        println("\nresultados:")
        println("=".repeat(50))

        resultados.forEachIndexed { index, resultado ->
            when (resultado) {
                is ResultadoCalidad.Exitoso -> {
                    println("${index + 1}.  ${resultado.frutaval.nombre}")
                    println("   ${resultado.mensaje}")
                    println("   Valor: $${String.format("%.2f", resultado.frutaval.valorTotalStock())}")
                }
                is ResultadoCalidad.Error -> {
                    println("${index + 1}.  ${resultado.frutaval.nombre}")
                    println("   ${resultado}")
                    println("   Valor: $${String.format("%.2f", resultado.frutaval.valorTotalStock())}")
                }
            }
            println()
        }

        // Resumen estadístico
        val aprobados = resultados.count { it is ResultadoCalidad.Exitoso }
        val rechazados = resultados.count { it is ResultadoCalidad.Error }
        val valorAprobado = resultados.filterIsInstance<ResultadoCalidad.Exitoso>()
            .sumOf { it.frutaval.valorTotalStock() }

        println(" resumen:")
        println(" Aprobados: $aprobados (${(aprobados * 100.0 / resultados.size).toInt()}%)")
        println(" Rechazados: $rechazados (${(rechazados * 100.0 / resultados.size).toInt()}%)")
        println(" Valor productos aprobados: $${String.format("%,.2f", valorAprobado)}")
    }

