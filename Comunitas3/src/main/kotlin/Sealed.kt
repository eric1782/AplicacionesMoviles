//Sealed class= es una clase sellada y se usa cuando quieres controlar todos los tipos de datos
//que puede tener una variable

//es decir solo se puede utilizar o heredar dentro del mismo archivo

//se declara e indicamos que aqui parte la jerarquia cerrada
//todo debe estar declarado dentro del mismo archivo
sealed class Resultado

data class Exito(val mensaje: String): Resultado() //positivo

data class Error(val mensaje: String): Resultado() //negativo

//Funcion que valide el resultado y muestre un mensaje de error o positivo
fun mostrarMensaje(resultado: Resultado){
    //utilizar when para evaluar el tipo de mensaje
    when(resultado){
        //en kotlin -> = lambda = anonimas = no tiene nombre y automaticas
        is Exito -> println("${resultado.mensaje}") //exito
        is Error -> println("${resultado.mensaje}") //error
    }
}

fun main(){
    val rl : resultado = Exito("Acceso correcto, puedes pasar al dashboard")
    val rl2 : resultado = Error("Acceso incorrecto, vuelve a intentarlo")

    println(rl)
    println(rl2)
}