package com.logistica.Transporte

class Drone(
    patente: String, // no hay patente, pero pa q sea igual q el resto maoma
    pesoCarga: Double,
    capacidadMaxima: Double,
    val autonomiaKm: Double
) : TransporteCarga(patente, pesoCarga, capacidadMaxima) {

    override fun calcularCostoEnvio(): Double {
        return (pesoCarga * 2.0) + (100 / autonomiaKm)
    }

    override fun obtenerInfo(): String {
        return "${super.obtenerInfo()}, Tipo: Drone, Autonomía: $autonomiaKm km"
    }
}