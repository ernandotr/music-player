package br.com.devcompleto;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleMp3Player {

    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            String pathname = "Enoque_001.mp3";
            long audioSize = Files.size(Path.of(pathname));

            System.out.printf("Size: %.2f Kb. %n",  audioSize / 1024.0 );
            fis = new FileInputStream(pathname);

            System.out.println("Playing: " + pathname);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player = new Player(bis);
            player.play();

        } catch (IOException | JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }
}
