package com.kondra.kos.popa.rack;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;



import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldSensor extends WebSocketServer {

    private Set<WebSocket> connections = Collections.synchronizedSet(new HashSet<>());

    public HelloWorldSensor(int port) {
        super(new InetSocketAddress("0.0.0.0", port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connections.add(conn);
        System.out.println("Cliente conectado: " + conn.getRemoteSocketAddress());
        conn.send("Welcome to SENSOR WebSocket!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
        System.out.println("Cliente desconectado: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Mensaje recibido del cliente: " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Error en conexión WebSocket:");
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("✅ Servidor WebSocket iniciado en puerto " + getPort());

        // Iniciar hilo que simula lecturas de sensor
        new Thread(() -> {
            try {
                Random random = new Random();
                while (true) {
                    Thread.sleep(5000);
                    int sensorValue = random.nextInt(101);
                    broadcastMessage("Sensor reading: " + sensorValue);
                }
            } catch (InterruptedException e) {
                System.err.println("📛 Hilo de lecturas interrumpido.");
            }
        }).start();
    }

    public void broadcastHelloWorldSensor() {
        synchronized (connections) {
            for (WebSocket conn : connections) {
                conn.send("Hello World SENSOR");
            }
        }
    }

    public void broadcastMessage(String message) {
        synchronized (connections) {
            for (WebSocket conn : connections) {
                conn.send(message);
            }
        }
    }


    public static void main(String[] args) {
        int port = 8887;
        HelloWorldSensor server = new HelloWorldSensor(port);
        server.start();

        System.out.println("⌛ Esperando conexiones en ws://localhost:" + port);

        // Bucle para enviar mensaje cada 5 segundos
        try {
            Random random = new Random();
            while (true) {
                Thread.sleep(5000);
                int sensorValue = random.nextInt(101);
                server.broadcastMessage("Sensor reading: " + sensorValue);
            }
        } catch (InterruptedException e) {
            System.err.println("Servidor interrumpido.");
        }
    }
}