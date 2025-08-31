package com.logistica.Transporte

class Camion(
    patente: String,
    pesoCarga: Double,
    capacidadMaxima: Double,
    val numeroEjes: Int
) : TransporteCarga(patente, pesoCarga, capacidadMaxima) {

    override fun calcularCostoEnvio(): Double {
        return (pesoCarga * 0.5) + (numeroEjes * 10)
    }

    override fun obtenerInfo(): String {
        return "${super.obtenerInfo()}, Tipo: Camión, Número de Ejes: $numeroEjes"
    }
}