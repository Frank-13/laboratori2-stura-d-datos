import java.util.*

data class Orden(val cliente: String, val pupusas: List<Pair<String, Int>>)

// Estructuras de datos
val ordenesPendientes: Queue<Orden> = LinkedList() // Cola para órdenes pendientes
val ordenesDespachadas: Stack<Orden> = Stack() // Pila para órdenes despachadas

// Función para registrar una nueva orden
fun registrarOrden() {
    println("Porfavor Ingrese el nombre del cliente:")
    val nombreCliente = readLine()?.trim() ?: return

    val tiposPupusas = mutableListOf<Pair<String, Int>>()

    try {
        println("¿Cuantos diferente tipos de pupusas desea ordenar?:")
        val cantidadTipos = readLine()?.toIntOrNull() ?: return
        for (i in 1..cantidadTipos) {
            println("Ingrese el tipo de pupusa #$i:")
            val tipoPupusa = readLine()?.trim() ?: return
            println("Ingrese la cantidad de pupusas que prefiere de $tipoPupusa:")
            val cantidad = readLine()?.toIntOrNull() ?: return
            tiposPupusas.add(Pair(tipoPupusa, cantidad))
        }
    } catch (e: NumberFormatException) {
        println("Entrada no valida. Por favor, ingrese números validos.")
        return
    }

    val orden = Orden(nombreCliente, tiposPupusas)
    ordenesPendientes.add(orden)
    println("Orden registrada para $nombreCliente: ${tiposPupusas.joinToString(", ") { "${it.second} pupusas de ${it.first}" }}")
}

// Función para ver órdenes pendientes
fun verOrdenesPendientes() {
    if (ordenesPendientes.isEmpty()) {
        println("No hay ordenes pendientes.")
    } else {
        println("Ordenes pendientes:")
        ordenesPendientes.forEachIndexed { index, orden ->
            val pupusas = orden.pupusas.joinToString(", ") { "${it.second} pupusas de ${it.first}" }
            println("${index + 1}. ${orden.cliente}: $pupusas")
        }
    }
}

// Función para despachar una orden
fun despacharOrden() {
    if (ordenesPendientes.isEmpty()) {
        println("No hay ordenes para despachar.")
    } else {
        val ordenDespachada = ordenesPendientes.poll()
        ordenesDespachadas.push(ordenDespachada)
        val pupusas = ordenDespachada.pupusas.joinToString(", ") { "${it.second} pupusas de ${it.first}" }
        println("Despachando la orden de ${ordenDespachada.cliente}: $pupusas")
    }
}

// Función para ver órdenes despachadas
fun verOrdenesDespachadas() {
    if (ordenesDespachadas.isEmpty()) {
        println("No hay ordenes despachadas.")
    } else {
        println("Ordenes despachadas:")
        ordenesDespachadas.forEachIndexed { index, orden ->
            val pupusas = orden.pupusas.joinToString(", ") { "${it.second} pupusas de ${it.first}" }
            println("${index + 1}. ${orden.cliente}: $pupusas")
        }
    }
}

// Función principal para mostrar el menú
fun mostrarMenu() {
    while (true) {
        println("\nBienvenido a la Pupuseria 'El Comalito'")
        println("Seleccione una opcion:")
        println("1. Registrar una nueva orden")
        println("2. Ver ordenes pendientes")
        println("3. Despachar orden")
        println("4. Ver ordenes despachadas")
        println("5. Salir")

        when (readLine()) {
            "1" -> registrarOrden()
            "2" -> verOrdenesPendientes()
            "3" -> despacharOrden()
            "4" -> verOrdenesDespachadas()
            "5" -> {
                println("Saliendo del sistema. ¡Gracias Por Preferir Nuestra Pupuseria 'El Comalito'!")
                return
            }
            else -> println("Opci0n no valida. Intente de nuevo.")
        }
    }
}

// Función principal
fun main() {
    mostrarMenu()
}