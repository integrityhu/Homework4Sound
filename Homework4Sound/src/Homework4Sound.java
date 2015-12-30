import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.spi.FormatConversionProvider;

import com.sun.media.sound.JDK13Services;

import audio.ConvertGSMTask;
import audio.ConvertWavTask;
import audio.Microphone;
import audio.PlaybackTask;
import audio.RecordTask;

/**
 * 
 */

/**
 * @author pzoli
 *         http://docs.oracle.com/javase/tutorial/sound/sampled-overview.html
 *         http://www.jsresources.org/examples/AudioDecoder.html
 *         http://tritonus.org/plugins.html
 *         
 */
public class Homework4Sound {

    /**
     * Entry to run the program
     */
    public static void main(String[] args) {
    	//List codecs = getFormatConversionProviders();
    	List providers = JDK13Services.getProviders(FormatConversionProvider.class);
    	for(Object provider: providers) {
    		System.out.println(provider);
    	}
        //PlaybackTask.playBackAudioFile();
        //RecordTask.record();
        ConvertGSMTask.main(args);
        //ConvertWavTask.main(args);
    }

}
