import java.io.File
import java.io.FileNotFoundException

fun main(){
    println("Ingresa el nombre o la ruta del archivo a leer: ")
    val nombreArchivo = readLine() ?: ""

    try{
        //intentar leer el archivo si existe
        val contenido = File(nombreArchivo).readText()
        println("Contenido del archivo: \n$contenido")
    }catch(e: FileNotFoundException){
        //se va a ejecutar si el archivo no existe
        println("Error el archivo: $nombreArchivo no existe, mentirosillo")
    }catch(e:Exception){
        //cuando da un error no esperado
        println("Error inesperado: ${e.message}")
    }


}