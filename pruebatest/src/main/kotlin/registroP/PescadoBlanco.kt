package registroP

class PescadoBlanco(
    nombre: String,
    precioPorKilo: Double,
    stockKilos: Double
) : Pescado(nombre, precioPorKilo, stockKilos){
    override fun descripcion(): String{
        return "este pescado es blanco, mas saludable! (segun algunos)"
    }

    override fun obtenerInfo(): String{
        return "${super.obtenerInfo()}"
    }
}