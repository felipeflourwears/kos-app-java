#!/bin/bash

# Número de LEDs a controlar
COUNT=50

# GPIO utilizado
GPIO=18

# Tipo de strip (ajusta si tu tira usa otro orden)
STRIP=grb

# Ruta completa al binario ledstriptool
LEDTOOL="/mnt/datafs/ledtool/bin/ledstriptool"

# RED
for i in {1..10}; do
    $LEDTOOL --gpio $GPIO --strip $STRIP --count $COUNT --color ff0000
    sleep 1
done
