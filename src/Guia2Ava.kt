fun main(){

    //Variables y operadores aritmeticos
    val numeroInt:Int = 10
    val numeroDouble:Double = 3.14

    val suma = numeroDouble + numeroInt
    val resta = numeroDouble - numeroInt
    val multiplicacion = numeroInt * numeroDouble
    val division = numeroInt / numeroDouble

    println("Resultados:")
    println("La suma es: $suma")
    println("La resta es: $resta")
    println("La multiplicacion es: $multiplicacion")
    println("La division es: $division")

    //mi comprension lectora no da para este siguiente ejercicio
    //Seguridad ante Nulos (Null Safety)

    var textoCreo:String? = "valor inicial de texto?"
    println("Longitud: ${textoCreo?.length}")

    textoCreo = null

    println("Longitud ahora q es null: ${textoCreo?.length}")

    //Pregunta de reflexion
    println("La verificacion en java puede hacerse con un if o un try catch")

    //Logica condicional con when (falte a esta clase ojala este bien)
    val numerox:Int = 6

    val dia = when (numerox){
        1-> "Lunes"
        2-> "Martes"
        3-> "Miercoles"
        4-> "Jueves"
        5-> "Viernes"
        6-> "Sabado"
        7-> "Domingo"
        else -> "NO HAY MENOS DE 0 Y MAS DE 7 DIAS DE LA SEMANA CAMBIA TU NUMERO"
    }

    println("HOY ES.....$dia")

    //Preguntas finales de reflexion
    
    //Note bastante la diferencia entre java y kotlin al momento de manejar posibles errores con Nulls,
    //en especial relacionado a como en Kotlin es necesario el pensar en eso al momento de crear las variables,
    //mientras que en Java se hace despues al trabajar con estas variables.

    //Pues podria ser aplicado para evitar errores por especificamente lo aprendido, que seria la presencia de datos nulos donde no se espere






}