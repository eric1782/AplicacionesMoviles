package com.logistica.Transporte

abstract class TransporteCarga(
    val patente: String,
    val pesoCarga: Double, // peso actual de la carga
    val capacidadMaxima: Double // capacidad max del vehículo
) {
    abstract fun calcularCostoEnvio(): Double

    open fun obtenerInfo(): String {
        return "Patente: $patente, Peso Carga: $pesoCarga kg, Capacidad Máxima: $capacidadMaxima kg"
    }
}