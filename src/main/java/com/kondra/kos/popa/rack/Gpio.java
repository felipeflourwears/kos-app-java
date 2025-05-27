package com.kondra.kos.popa.rack;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class Gpio {

    public static void startBlink() {
        String scriptPath = "/mnt/datafs/test/rgb.sh";

        try {
            log.info("-------------------------------------------------------------------------------------------");
            log.info("🔥 Blink LED script execution starting 🔥");
            log.info("-------------------------------------------------------------------------------------------");

            // Ejecutar el script con sudo para aprovechar la configuración sin contraseña
            ProcessBuilder pb = new ProcessBuilder("sudo", scriptPath);
            pb.redirectErrorStream(true); // Une stdout y stderr

            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info(line);
                }
            }

            int exitCode = process.waitFor(); // opcional: esperar a que termine el script
            log.info("Blink script finished with exit code: " + exitCode);

        } catch (Exception e) {
            log.error("Error running blink script", e);
        }
    }
}
