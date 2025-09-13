//Suspend= marca una funcion para que se pueda pausar su ejecucion, no bloquea el hilo principal.
suspend fun saludarConRetraso(nombre: String){
    //simular una espera de 2s
    delay(2000L)

    //print
    println("Hola $nombre")

}

//runBlocking = suele usarse en main para ejecutar funciones suspendidas, bloquea el hilo principal hasta que la corrutina termine
//se usa solo en medio de pruebas o testing
fun main() = runBlocking{

    //hilo principal
    println("preparando un saludo con retraso")

    saludarConRetraso("Pedro")
    println("Saludo finalizado")
}