//package br.com.devcompleto;
//
//import javazoom.jl.encoder.LameEncoder;
//import javazoom.jl.decoder.JavaLayerException;
//
//import javax.sound.sampled.*;
//import java.io.*;
//
//public class WavToMp3Converter {
//
//    public static void main(String[] args) {
//        String wavFilePath = "audio.wav";
//        String mp3FilePath = "audio.mp3";
//
//        try {
//            convertWavToMp3(wavFilePath, mp3FilePath);
//            System.out.println("Conversão concluída: " + mp3FilePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void convertWavToMp3(String wavFilePath, String mp3FilePath) throws Exception {
//        File wavFile = new File(wavFilePath);
//        File mp3File = new File(mp3FilePath);
//
//        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
//             FileOutputStream mp3OutputStream = new FileOutputStream(mp3File)) {
//
//            AudioFormat format = audioInputStream.getFormat();
//            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
//                throw new UnsupportedAudioFileException("Apenas PCM é suportado.");
//            }
//
//            LameEncoder encoder = new LameEncoder(
//                    format,
//                    128, // Taxa de bits (kbps)
//                    LameEncoder.CHANNEL_MODE_STEREO,
//                    LameEncoder.QUALITY_HIGHEST,
//                    false
//            );
//
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
//                byte[] encoded = new byte[encoder.getOutputBufferSize(bytesRead)];
//                int encodedBytes = encoder.encodeBuffer(buffer, 0, bytesRead, encoded);
//                mp3OutputStream.write(encoded, 0, encodedBytes);
//            }
//
//            encoder.close();
//        }
//    }
//}
