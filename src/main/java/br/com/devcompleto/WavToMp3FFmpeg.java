package br.com.devcompleto;

import java.io.IOException;

public class WavToMp3FFmpeg {
    public static void main(String[] args) {
        String wavFile = "Enoque_001.wav";
        String mp3File = "audio.mp3";
        try {
            ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", wavFile, mp3File);
            Process process = pb.inheritIO().start();
            process.waitFor();
            System.out.println("Conversão concluída: " + mp3File);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

