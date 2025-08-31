package com.logistica.Transporte

class Camioneta(
    patente: String,
    pesoCarga: Double,
    capacidadMaxima: Double,
    val es4x4: Boolean
) : TransporteCarga(patente, pesoCarga, capacidadMaxima) {

    override fun calcularCostoEnvio(): Double {
        return pesoCarga * 0.8 + (if (es4x4) 20.0 else 0.0)
    }

    override fun obtenerInfo(): String {
        return "${super.obtenerInfo()}, Tipo: Camioneta, 4x4: ${if (es4x4) "Sí" else "No"}"
    }
}