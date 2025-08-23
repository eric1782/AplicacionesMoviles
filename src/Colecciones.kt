fun main(){

    //Contenido Guia 2 ava

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


    //Contenido Guia practica de Notion


    //CICLOS BASICOS

    val edades = listOf(15, 18, 20, 22, 17)

    for(e in edades){
        if (e >= 18){
            println(e)
        }
    }

    var i = 0
    while (i < edades.size){
        if (edades[i] >= 18){
            println(edades[i])
        }
        ++i
    }

    edades.forEachIndexed { index, edad ->
        if (index != edades.lastIndex) {
            print("$edad, ")
        } else {
            print("$edad")
        }
    }

    //COLECCIONES

    val nombres: List<String> = listOf("Ericsito","Diegin","Marcelino","Camilita","Andysito","Ericsito")

    val cursos: Map<String, String> = mapOf(

        "Ericsito" to "Ingles",
        "Diegin" to "Matematicas",
        "Marcelino" to "Informatica",
        "Camilita" to "Estadistica descriptiva",
        "Andysito" to "Ciencia",
        "Ericsito" to "Ciencias"

    )
    println("Map: $cursos")

    println("Curso de Ericsito: ${cursos["Ericsito"]}")
    println("Curso de noExisto: ${cursos["Noexisto"]}")

    //OPERACION CON COLECCIONES

    val primerMayorFind = edades.find { it > 18 }
    println(primerMayorFind)

    val hayMenoresAny = edades.any { it < 18 }
    println(hayMenoresAny)

    val AdultosAll = edades.all { it >= 18 }
    println(AdultosAll)

    val edadesx2 = edades.map { it * 2 }
    println(edadesx2)

    val MayoresFilter = edades.filter { it >= 18 }
    println(MayoresFilter)

    //promedio
    val suma2 = edades.sum()
    val promedio2 = edades.average()

    println("Suma: $suma2")
    println("Promedio: $promedio2")




}