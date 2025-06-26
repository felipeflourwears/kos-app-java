#!/bin/bash

# Número de LEDs a controlar
COUNT=5

# GPIO utilizado
GPIO=18

# Tipo de strip (ajusta si tu tira usa otro orden)
STRIP=grb

# Ruta completa al binario ledstriptool
LEDTOOL="/mnt/datafs/ledtool/bin/ledstriptool"

# Loop infinito: rojo -> verde -> azul
while true; do
    # Rojo
    $LEDTOOL --gpio $GPIO --strip $STRIP --count $COUNT --color ff0000
    sleep 1

    # Verde
    $LEDTOOL --gpio $GPIO --strip $STRIP --count $COUNT --color 00ff00
    sleep 1

    # Azul
    $LEDTOOL --gpio $GPIO --strip $STRIP --count $COUNT --color 0000ff
    sleep 1
done
