package com.logistica

import com.logistica.Transporte.*
import java.lang.NumberFormatException

fun main() {
    val vehiculosRegistrados = mutableListOf<TransporteCarga>()

    println("--- BIENVENIDO, REGISTRA TU VEHICULO! ---")

    // Registrar camion
    println("\nregistrando tu camion...")
    registrarVehiculo {
        println("ingrese patente del camión:")
        val patente = readLine() ?: ""
        println("ingrese peso de la carga (kg):")
        val pesoStr = readLine() ?: "0.0"
        println("ingrese capacidad máxima (kg):")
        val capacidadStr = readLine() ?: "0.0"
        println("ingrese número de ejes:")
        val ejesStr = readLine() ?: "0"

        println("----------------------------------")

        Result.runCatching {
            val peso = pesoStr.toDouble()
            val capacidad = capacidadStr.toDouble()
            val ejes = ejesStr.toInt()

            if (patente.isBlank()) throw IllegalArgumentException("la patente no puede estar vacía.")
            if (peso > capacidad) throw IllegalArgumentException("el peso de la carga es mayor a la capacidad máxima.")
            if (peso < 0 || capacidad < 0 || ejes < 0) throw IllegalArgumentException("los valores de peso, capacidad y ejes tienen que ser numeros validos.")

            Camion(patente, peso, capacidad, ejes)
        }
    }?.let { vehiculosRegistrados.add(it) }



    // registrar camioneta
    println("\nRegistrando tu Camioneta...")
    registrarVehiculo {
        println("ingrese patente de la camioneta:")
        val patente = readLine() ?: ""
        println("ingrese peso de la carga (kg):")
        val pesoStr = readLine() ?: "0.0"
        println("ingrese capacidad máxima (kg):")
        val capacidadStr = readLine() ?: "0.0"
        println("¿Es 4x4? (s/n):")
        val es4x4Str = readLine() ?: "n"

        println("----------------------------------")

        Result.runCatching {
            val peso = pesoStr.toDouble()
            val capacidad = capacidadStr.toDouble()
            val es4x4 = es4x4Str.lowercase() == "s"

            if (patente.isBlank()) throw IllegalArgumentException("la patente no puede estar vacía.")
            if (peso > capacidad) throw IllegalArgumentException("el peso de la carga es mayor a la capacidad máxima.")
            if (peso < 0 || capacidad < 0) throw IllegalArgumentException("los valores de peso y capacidad tienen que ser numeros validos")

            Camioneta(patente, peso, capacidad, es4x4)
        }
    }?.let { vehiculosRegistrados.add(it) }

    // Registrar dron
    println("\nregistrando tu droncito:")
    registrarVehiculo {
        println("ingrese identificador del drone (ej. DRONE-001):")
        val idDrone = readLine() ?: ""
        println("ingrese peso de la carga (kg):")
        val pesoStr = readLine() ?: "0.0"
        println("ingrese capacidad máxima (kg):")
        val capacidadStr = readLine() ?: "0.0"
        println("ingrese autonomía en km:")
        val autonomiaStr = readLine() ?: "0.0"

        println("----------------------------------")

        Result.runCatching {
            val peso = pesoStr.toDouble()
            val capacidad = capacidadStr.toDouble()
            val autonomia = autonomiaStr.toDouble()

            if (idDrone.isBlank()) throw IllegalArgumentException("El identificador del drone no puede estar vacío.")
            if (peso > capacidad) throw IllegalArgumentException("El peso de la carga excede la capacidad máxima.")
            if (peso < 0 || capacidad < 0 || autonomia <= 0) throw IllegalArgumentException("Los valores de peso, capacidad y autonomía deben ser positivos.")

            Drone(idDrone, peso, capacidad, autonomia)
        }
    }?.let { vehiculosRegistrados.add(it) }

    println("----------------------------------")

    println("\n--- VEHÍCULOS REGISTRADOS ---")
    if (vehiculosRegistrados.isEmpty()) {
        println("no se logro registrar un vehiculo, intente denuevo")
    } else {
        vehiculosRegistrados.forEachIndexed { index, vehiculo ->
            println("\nVehículo ${index + 1}:")
            try {
                println(vehiculo.obtenerInfo())
                val costo = vehiculo.calcularCostoEnvio()
                println("costo de envío estimado: $${"%.2f".format(costo)}")
            } catch (e: Exception) {
                println("error al calcular el costo de envío: ${e.message}")
            }
        }
    }
}

// manejar el registro y la validación con Result
fun <T : TransporteCarga> registrarVehiculo(block: () -> Result<T>): T? {
    return try {
        block()
            .onSuccess {
                println("vehiculo registrado exitosamente.")
                it
            }
            .onFailure { exception ->
                when (exception) {
                    is IllegalArgumentException -> println("error de validación: ${exception.message}")
                    is NumberFormatException -> println("error: por favor, ingrese un número válido.")
                    else -> println("ocurrió un error inesperado: ${exception.message}")
                }
            }
            .getOrNull() // Devuelve el objeto o null si hay fallo
    } catch (e: Exception) {
        println("error fatal durante el registro: ${e.message}")
        null
    }
}