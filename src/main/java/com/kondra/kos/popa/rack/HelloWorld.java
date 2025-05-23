package com.kondra.kos.popa.rack;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorld {

    public static void printArt() {
        String art =
            "        .-.\n" +
            "       |_:_|\n" +
            "      /(_Y_)\\\n" +
            "     ( \\/M\\/ )\n" +
            "      \\ \\|/ /\n" +
            "       '---'\n" +
            "      /|   |\\\n" +
            "     /_|___|_\\\n" +
            "    /__|   |__\\\n" +
            "       || ||\n" +
            "       || ||\n" +
            "       || ||\n" +
            "       || ||\n" +
            "      /_| |_\\\n" +
            "     /__| |__\\\n" +
            "       || ||\n" +
            "       || ||\n" +
            "      ==' '==\n" +
            "   DARTH VADER\n";

        log.info("\n" + art);
    }

    public static void main(String[] args) {
        printArt();
    }
}