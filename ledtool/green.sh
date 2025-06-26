#!/bin/bash

# Número de LEDs a controlar
COUNT=50

# GPIO utilizado
GPIO=18

# Tipo de strip (ajusta si tu tira usa otro orden)
STRIP=grb

# Ruta completa al binario ledstriptool
LEDTOOL="/mnt/datafs/ledtool/bin/ledstriptool"

# GREEN
for i in {1..10}; do
    $LEDTOOL --gpio $GPIO --strip $STRIP --count $COUNT --color 00ff00
    sleep 1
done
