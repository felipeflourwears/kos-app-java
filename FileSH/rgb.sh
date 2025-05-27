#!/bin/bash

# Número de LEDs a controlar
COUNT=5

# GPIO utilizado
GPIO=18

# Tipo de strip (ajusta si tu tira usa otro orden)
STRIP=grb

# Loop infinito: rojo -> verde -> azul
while true; do
    # Rojo
    ledstriptool --gpio $GPIO --strip $STRIP --count $COUNT --color ff0000
    sleep 1

    # Verde
    ledstriptool --gpio $GPIO --strip $STRIP --count $COUNT --color 00ff00
    sleep 1

    # Azul
    ledstriptool --gpio $GPIO --strip $STRIP --count $COUNT --color 0000ff
    sleep 1
done
