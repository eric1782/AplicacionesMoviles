//data class . solo lo declaramos
data class Producto(var nombre: String, var precio: Int)


fun main(){
    //crear un objeto de Producto
    val producto = Producto("Mouse Xtitan",10000)

    //apply= poder modificar un objeto en un bloque de codigo especifico

    //lamar objeto.aplicar apply...lo que quiero modificar
    producto.apply{
        precio -=2000
    }
    producto.apply{
        nombre = "Red Dragon"
    }

    //transformo a String y muestro el resultado
    //let = Tomar un objeto, hacer algo con el y devolver
    val productoFinal = producto.let{"Producto final ${it.nombre} ahora con descuento ${it.precio}"}
    println(productoFinal)
}