package Frutas

class FrutaLocal (
    nombre : String,
    precioPorKilo : Double,
    stockKilos : Double,
) : Fruta(nombre, precioPorKilo, stockKilos){
    override fun descripcion(): String{
        return "esta fruta es local y muy jugosa y rica"
    }

}