package br.com.devcompleto;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SimpleMp3Player {

    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            String pathname = "Enoque_001.mp3";
            fis = new FileInputStream(new File(pathname));
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player = new Player(bis);
            player.play();
            System.out.println("Playing: " + pathname);
        } catch (IOException | JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }
}
