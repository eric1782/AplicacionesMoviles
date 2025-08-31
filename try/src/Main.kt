//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //try catch
    println("bienvenido a PuertoGames Movil")
    println("Ingresa el monto a recargar en la tarjeta: ")

    try{
        //solicitar al user ingresar un dato
        //!!no admite valores nulos, ? admite valores nulos
        val entrada = readLine()

        //convertimos a int o lanzamos una excepcion
        val monto = entrada?.toInt() ?: throw Exception("Entrada vacia, agrega un valor")

        //que no ingrese = 0

        if (monto<=0){
           //lanzar una excepcion
            throw IllegalArgumentException("El valor debe ser mayor a 0")
        }

        //Si el usuario ingresa el dato ok
        println("Recarga exitosa $$monto CLP")

    }catch(e: NumberFormatException){
        println("Error debes ingresar un dato numerico")
    }catch(e: IllegalArgumentException){
        println("Error inesperado: ${e.message}")
    }//finally se ejecuta siosi
    finally{
        println("Gracias por recargar con PuertoGames Movil!!!")
    }

}