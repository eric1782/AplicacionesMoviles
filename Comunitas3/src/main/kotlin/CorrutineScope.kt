import kotlinx.corroutines.*;

fun main() = runBlocking{

    //Dispatchers = Despachador
    val scope = CoroutineScope(Dispatchers.Default)
    //llamo a la valor y lo lanzo
    scope.launch {
        delay(2000L)
        println("Postre leche asada en el horno")
    }

    scope.launch {
        delay(3000L)
        println("Plato principal: pollito asado en el horno")
    }

    println("Organizando la cocina encendiendo hornos")

    delay(4000L)
    println("Comida lista para servir al cliente")

}