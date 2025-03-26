package br.com.devcompleto;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class SimpleMp3PlayerCli {
    private static final Logger logger = Logger.getLogger("SimpleMp3PlayerCli");

    public static void main(String[] args) {
        FileInputStream fis;
        try {
            String pathname = "Enoque_001.mp3";
            long audioSize = Files.size(Path.of(pathname));

            System.out.printf("Size: %.2f Kb. %n",  audioSize / 1024.0 );
            fis = new FileInputStream(pathname);

            System.out.println();
            logger.info("Playing: " + pathname);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player = new Player(bis);
            player.play();

        } catch (IOException | JavaLayerException e) {
            logger.severe(e.getMessage());
        }

    }
}
