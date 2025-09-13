package registroP

class Salmon(
    nombre: String,
    precioPorKilo: Double,
    stockKilos: Double
) : Pescado(nombre, precioPorKilo, stockKilos){
    override fun descripcion(): String{
        return "este salmon es un salmon, muy rico"
    }

    override fun obtenerInfo(): String{
        return "${super.obtenerInfo()}"
    }
}