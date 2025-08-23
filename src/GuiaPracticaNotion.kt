fun main(){

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