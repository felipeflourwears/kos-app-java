package com.kondra.kos.popa.rack;

import com.tccc.kos.ext.freestyle.hardware.can.subnode.GPIOState;
import com.tccc.kos.commons.core.service.handle.Handle;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GpioExample {

    public static void main(String[] args) {
        // Crear un handle, normalmente te lo provee el framework
        Handle myHandle = obtenerHandle(); // Método ficticio, depende de tu app

        // Crear instancia GPIOState con el handle
        GPIOState gpioState = new GPIOState(myHandle);

        // Inicialmente, los estados son null (desconocidos)
        System.out.println("Estado inicial input0: " + gpioState.getInput0());

        // Setear estado de input0 a true (alto)
        gpioState.setInput0(true);
        System.out.println("Estado modificado input0: " + gpioState.getInput0());

        // Setear otros pines
        gpioState.setInput1(false);
        gpioState.setInput2(true);
        gpioState.setInput3(false);

        // Leer y mostrar todos
        System.out.println("GPIO States:");
        System.out.println("input0 = " + gpioState.getInput0());
        System.out.println("input1 = " + gpioState.getInput1());
        System.out.println("input2 = " + gpioState.getInput2());
        System.out.println("input3 = " + gpioState.getInput3());

        // Para imprimir todo con toString
        System.out.println("GPIOState completo: " + gpioState.toString());
    }

    private static Handle obtenerHandle() {
        // Aquí debes obtener o crear un Handle válido según la lógica del framework
        // Este es solo un placeholder
        return null;
    }
}
