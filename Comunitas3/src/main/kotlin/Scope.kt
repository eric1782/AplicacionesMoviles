//importar las corrutinas
import kotlinx.coroutines.*;

fun main(){
    println("1- preparar la masa de la pizza")//ejecutar en el hilo principal/default

    //GlobalScope = es un ambito global que permite lanzar corrutinas que viven durante toda la ejecucion del programa
    //se crea un nuevo hilo de ejecuciom de manera paralela
    GlobalScope.launch {

        delay(2000L) //simulando el tiempo que la pizza se cocina en el horno

        println("La pizza esta lista para sacarla del horno")
    }

    //optimizo tiempo mientras la pizza se cocina
    println("Preparando la mesa y una ensaladita")

    //le doy el tiempo para que la pizza se cocina
    Thread.sleep(3000L)

    println("me siento para comer la pizzita")
}