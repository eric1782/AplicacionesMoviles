package Frutas

class FrutaTropical (
    nombre : String,
    precioPorKilo : Double,
    stockKilos : Double,
) : Fruta(nombre, precioPorKilo, stockKilos){
    override fun descripcion(): String{
        return "esta fruta es tropical y de colores, ñam ñam"
    }
}