#!/bin/sh

GPIO=17

# Exportar GPIO si no está exportado
if [ ! -d /sys/class/gpio/gpio${GPIO} ]; then
  echo ${GPIO} > /sys/class/gpio/export
  sleep 0.1
fi

# Configurar como salida
echo out > /sys/class/gpio/gpio${GPIO}/direction

# Loop infinito para parpadear
while true; do
  echo 1 > /sys/class/gpio/gpio${GPIO}/value
  sleep 1
  echo 0 > /sys/class/gpio/gpio${GPIO}/value
  sleep 1
done