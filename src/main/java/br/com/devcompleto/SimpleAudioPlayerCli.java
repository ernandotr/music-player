package br.com.devcompleto;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class SimpleAudioPlayerCli {

    Long currentFrame;
    String status;
    Clip clip;
    AudioInputStream audioInputStream;
    static String filePath;

    public SimpleAudioPlayerCli() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("My Music");
        filePath = "Enoque_002.wav";
        long audioSize = Files.size(Path.of(filePath));

        System.out.printf("Size: %.2f Mb. %n",  audioSize / 1024.0 / 1024 );

        SimpleAudioPlayerCli audioPlayer = new SimpleAudioPlayerCli();
        double duration = audioPlayer.clip.getMicrosecondLength() / 1000000.0;
        System.out.printf("Size: %.3f Seconds. %n",  duration );

        audioPlayer.play();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. pause");
            System.out.println("2. resume");
            System.out.println("3. restart");
            System.out.println("4. stop");
            System.out.println("5. Jump to specific time");
            int c = scanner.nextInt();
            audioPlayer.gotoChoice(c);
            if (c == 4)
                break;
        }

    }

    private void gotoChoice(int c) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
            case 5:
                System.out.println("Enter time (" + 0 +
                        ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1);
                break;

        }
    }

    private void jump(long c) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    private void restart() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    private void resumeAudio() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(status.equals("play")) {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    private void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        this.stop();
        status = "paused";
    }

    private void stop() {
        clip.stop();
        clip.close();
    }

    private void play() {
        clip.start();

        status = "paly";
    }
}