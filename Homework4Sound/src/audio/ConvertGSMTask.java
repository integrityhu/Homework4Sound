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
public class ConvertGSMTask {

    public static void main(String[] args)
    {
        File    pcmFile = new File("/tmp/RecordAudio-toGSM.wav");
        File    gsmFile = new File("/tmp/ConvertedAudio.gsm");
        AudioInputStream    ais = null;
        try
        {
            ais = AudioSystem.getAudioInputStream(pcmFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (ais == null)
        {
            System.out.println("cannot open audio file");
            System.exit(1);
        }

        /** We check if the input file has a format that can be converted
            to GSM.
        */
        AudioFormat sourceFormat = ais.getFormat();
        if (! sourceFormat.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) ||
            sourceFormat.getSampleRate() != 8000.0F ||
            sourceFormat.getSampleSizeInBits() != 16 ||
            sourceFormat.getChannels() != 1)
        {
            System.out.println("The format of the input data has to be PCM 8 kHz 16 bit mono");
            System.exit(1);
        }
        AudioFormat.Encoding    targetEncoding = new AudioFormat.Encoding("GSM0610");        
        AudioInputStream    gsmAIS = AudioSystem.getAudioInputStream(targetEncoding, ais);
        AudioFileFormat.Type    fileType = new AudioFileFormat.Type("GSM", ".gsm");
        int nWrittenFrames = 0;
        try
        {
            nWrittenFrames = AudioSystem.write(gsmAIS, fileType, gsmFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
