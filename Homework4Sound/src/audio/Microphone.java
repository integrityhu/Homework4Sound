/*
 * Copyright 1999-2004 Carnegie Mellon University.  
 * Portions Copyright 2004 Sun Microsystems, Inc.  
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL 
 * WARRANTIES.
 *
 */

package audio;

import java.io.InputStream;

import javax.sound.sampled.*;

/**
 * InputStream adapter
 */
public class Microphone {

    private final TargetDataLine line;
    private final InputStream inputStream;

    public Microphone(AudioFormat format) {
        try {
            line = AudioSystem.getTargetDataLine(format);
            line.open();
        } catch (LineUnavailableException e) {
            throw new IllegalStateException(e);
        }
        inputStream = new AudioInputStream(line);
    }

    public void startRecording() {
        line.start();
    }

    public void stopRecording() {
        line.stop();
    }

    public InputStream getStream() {
        return inputStream;
    }
    
    public int getBufferSize() {
        return line.getBufferSize();
    }
    
}
