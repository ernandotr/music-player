package br.com.devcompleto;

import java.io.IOException;
import java.util.logging.Logger;

public class WavToMp3FFmpeg {
    private static Logger logger = Logger.getLogger("WavToMp3FFmpeg");
    public static void main(String[] args) {
        String wavFile = "Enoque_001.wav";
        String mp3File = "audio.mp3";
        try {
            ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", wavFile, mp3File);
            Process process = pb.inheritIO().start();
            process.waitFor();
            System.out.println("Conversão concluída: " + mp3File);
        } catch (IOException | InterruptedException e) {
            logger.severe(e.getMessage());
        }
    }
}

