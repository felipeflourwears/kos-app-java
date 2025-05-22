package com.kondra.kos.popa.rack;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GpioLedController {

    private final String gpioPin;
    private final String gpioPath;

    public GpioLedController(String gpioPin) {
        this.gpioPin = gpioPin;
        this.gpioPath = "/sys/class/gpio/gpio" + gpioPin;
    }

    private void runCommand(List<String> command) throws Exception {
        log.info("🔧 Ejecutando comando: {}", String.join(" ", command));

        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = pb.start();

        try (BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
             BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

            String line;
            StringBuilder output = new StringBuilder();
            while ((line = stdOut.readLine()) != null) {
                output.append(line).append("\n");
            }

            StringBuilder errors = new StringBuilder();
            while ((line = stdErr.readLine()) != null) {
                errors.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            log.info("✅ Código de salida: {}", exitCode);
            if (output.length() > 0) {
                log.info("📤 Salida estándar:\n{}", output);
            }
            if (errors.length() > 0) {
                log.warn("⚠️ Salida de error:\n{}", errors);
            }

            if (exitCode != 0) {
                throw new RuntimeException("❌ El comando falló: " + String.join(" ", command));
            }
        }
    }

    private boolean isExported() {
        return Files.exists(Paths.get(gpioPath));
    }

    public void export() throws Exception {
        if (!isExported()) {
            // Exportar GPIO
            runCommand(List.of("bash", "-c", "echo " + gpioPin + " > /sys/class/gpio/export"));
            // Configurar como salida
            runCommand(List.of("bash", "-c", "echo out > " + gpioPath + "/direction"));
            log.info("📌 GPIO {} exportado y configurado como salida", gpioPin);
        } else {
            log.info("ℹ️ GPIO {} ya está exportado", gpioPin);
        }
    }

    public void setLed(boolean on) throws Exception {
        if (!isExported()) {
            export();
        }
        String value = on ? "1" : "0";
        runCommand(List.of("bash", "-c", "echo " + value + " > " + gpioPath + "/value"));
        log.info("💡 GPIO {} configurado a {}", gpioPin, value);
    }

    public void unexport() throws Exception {
        if (isExported()) {
            runCommand(List.of("bash", "-c", "echo " + gpioPin + " > /sys/class/gpio/unexport"));
            log.info("🔌 GPIO {} desexportado", gpioPin);
        } else {
            log.info("ℹ️ GPIO {} no está exportado, no es necesario desexportar", gpioPin);
        }
    }

    public void blink(long onMillis, long offMillis, int times) {
        try {
            export();
            for (int i = 0; i < times; i++) {
                setLed(true);
                Thread.sleep(onMillis);
                setLed(false);
                Thread.sleep(offMillis);
            }
            unexport();
            log.info("✅ Parpadeo completado para GPIO {}", gpioPin);
        } catch (Exception e) {
            log.error("🔥 Error al controlar GPIO {}", gpioPin, e);
        }
    }
}
