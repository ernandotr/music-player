package br.com.devcompleto;

import javazoom.jl.decoder.*;

import javax.sound.sampled.*;
import java.io.*;
import java.util.logging.Logger;

public class Mp3ToWavConverterCli {
    private static final Logger logger =  Logger.getLogger("Mp3ToWavConverterCli");

    public static void main(String[] args) {
        String mp3FilePath = "Enoque_002.mp3";
        String wavFilePath = "Enoque_002.wav";

        try {
            convertMp3ToWav(mp3FilePath, wavFilePath);
            logger.info("Converted successful: " + wavFilePath);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    public static void convertMp3ToWav(String mp3FilePath, String wavFilePath) throws Exception {
        try (InputStream mp3Stream = new BufferedInputStream(new FileInputStream(mp3FilePath));
             OutputStream wavStream = new BufferedOutputStream(new FileOutputStream(wavFilePath))) {

            Bitstream bitstream = new Bitstream(mp3Stream);
            javazoom.jl.decoder.Decoder decoder = new javazoom.jl.decoder.Decoder();

            SampleBuffer sampleBuffer;
            AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
            DataOutputStream dataOutputStream = new DataOutputStream(wavStream);

            writeWavHeader(dataOutputStream, format, mp3Stream.available());

            Header header;
            while ((header = bitstream.readFrame()) != null) {
                sampleBuffer = (SampleBuffer) decoder.decodeFrame(header, bitstream);
                short[] samples = sampleBuffer.getBuffer();
                for (short sample : samples) {
                    dataOutputStream.writeShort(Short.reverseBytes(sample));
                }
                bitstream.closeFrame();
            }

            updateWavHeader(wavStream);
        }
    }

    private static void writeWavHeader(DataOutputStream out, AudioFormat format, int dataLength) throws IOException {
        out.writeBytes("RIFF");
        out.writeInt(36 + dataLength);
        out.writeBytes("WAVE");

        out.writeBytes("fmt ");
        out.writeInt(16);
        out.writeShort((short) 1);
        out.writeShort((short) format.getChannels());
        out.writeInt((int) format.getSampleRate());
        out.writeInt((int) (format.getSampleRate() * format.getChannels() * format.getSampleSizeInBits() / 8));
        out.writeShort((short) (format.getChannels() * format.getSampleSizeInBits() / 8));
        out.writeShort((short) format.getSampleSizeInBits());

        out.writeBytes("data");
        out.writeInt(dataLength);
    }

    private static void updateWavHeader(OutputStream wavStream) throws IOException {
        RandomAccessFile wavFile = new RandomAccessFile("Enoque_002.wav", "rw");
        wavFile.seek(4);
        wavFile.writeInt((int) (wavFile.length() - 8));

        wavFile.seek(40);
        wavFile.writeInt((int) (wavFile.length() - 44));
        wavFile.close();
    }
}

