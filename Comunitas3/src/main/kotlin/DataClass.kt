//Data class = es un tipo de clase que me permite modelar datos de forma simple y ordenada
data class Contacto(val nombre: String, val telefono: String)

fun main(){
    val cliente1 = Contacto("Pedro", "+56965435443")
    val cliente2 = Contacto("Juan", "+56987573849")
    val cliente3 = Contacto("Juan", "+56987573849")

    //toString muestra todos los datos
    println("toString: $cliente1")

    println("Son iguales? ${cliente2==cliente3}")//true
    println("Son distintos? ${cliente1==cliente3}")//false
    //HashCode asignar un numero a una caja = val or var
    println("HashCode ${cliente1.hashCode()}")

    //uso de copy()= copiar un elemento
    val cliente4 = cliente2.copy(telefono = "+56937463845")
    println("Juan con nuevo numero $cliente4")

}