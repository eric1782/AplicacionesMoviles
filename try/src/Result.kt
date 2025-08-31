//Ingreso de un correo correctamente
fun validarEmail(email: String): Result<String> {
    //verificar si tiene un @ y .
    return if(email.contains("@") && email.contains(".")) {
        //si ambas condiciones se cumplen es exitoso
        Result.success(email)
    }else{
        Result.failure(Exception("Email invalido"))
    }
}

//funcion principal
fun main(){
    println("ingresa tu correo electronico")

    val correo = readLine() ?: ""
    validarEmail(correo)
        .onSuccess { println("Email validado $it") }
        .onFailure { println("Error inesperado: ${it.message}") }
}