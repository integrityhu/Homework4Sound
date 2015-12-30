/**
 * 
 */
package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 * @author pzoli
 *
 */
public class ConvertWavTask {

    public static void main(String[] args) {
        File gsmIFile = new File("/tmp/ConvertedAudio.gsm");
        File decodedFile = new File("/tmp/ConvertedAudio-decoded.wav");
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(gsmIFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ais == null) {
            System.out.println("cannot open audio file");
            System.exit(1);
        }

        /**
         * We check if the input file has a format that can be converted to GSM.
         */
        AudioFormat sourceFormat = ais.getFormat();
        AudioFormat.Encoding targetEncoding = AudioFormat.Encoding.PCM_SIGNED;
        AudioInputStream gsmAIS = AudioSystem.getAudioInputStream(targetEncoding, ais);
        AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
        int nWrittenFrames = 0;
        try {
            nWrittenFrames = AudioSystem.write(gsmAIS, fileType, decodedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
