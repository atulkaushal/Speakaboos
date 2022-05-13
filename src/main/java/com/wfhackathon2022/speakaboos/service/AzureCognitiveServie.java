package com.wfhackathon2022.speakaboos.service;


import java.util.concurrent.Future;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisCancellationDetails;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisOutputFormat;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import com.wfhackathon2022.speakaboos.exception.PronunciationException;

@Service
public class AzureCognitiveServie {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AzureCognitiveServie.class);

    // Replace below with your own subscription key
    String speechSubscriptionKey = "8bbe96c8331e4f31896a8cc435bbe355";
    // Replace below with your own service region (e.g., "westus").
    String serviceRegion = "eastus";
    
    
	public byte[] retrieveSpeech(String text, String language, Integer speed) {
		byte[] audio = null;        
        SpeechConfig config = null;
        SpeechSynthesizer synth = null;
        AudioConfig fileOutput = null;
        
		try {
			config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
			
	        // Sets the synthesis output format.
	        config.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Audio16Khz32KBitRateMonoMp3);
	       
	        // Creates a speech synthesizer using file as audio output.
	        // Replace with your own audio file name.
	        String fileName = "nameaudio.mp3";
	        fileOutput = AudioConfig.fromWavFileOutput(fileName);
	        
			synth = new SpeechSynthesizer(config, fileOutput);
			
			//Set the language
			config.setSpeechSynthesisLanguage(language);
				        
			Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(text);

            SpeechSynthesisResult result = task.get();

            if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
            	LOG.info("AzureCognitiveServie::retrieveSpeech::completed successfully for "+ text + " in "+language);
            	audio = result.getAudioData();
            }
            else if (result.getReason() == ResultReason.Canceled) {
            	//CODE_DEBT: Error Handle it
                SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(result);
                LOG.error("AzureCognitiveServie::retrieveSpeech:: "+ text +" CANCELED: Reason=" + cancellation.getReason());
                
                if (cancellation.getReason() == CancellationReason.Error) {
                	LOG.error("AzureCognitiveServie::retrieveSpeech:: "+ text +"CANCELED: ErrorCode=" + cancellation.getErrorCode());
                	LOG.error("AzureCognitiveServie::retrieveSpeech:: "+ text +"CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                }
                throw new PronunciationException(cancellation.getReason().toString(), "WFH9002");
            }

        } catch (Exception ex) {
            LOG.error("AzureCognitiveServie::retrieveSpeech::exception occured: "+ex.getMessage(), ex);
            throw new PronunciationException(ex.getMessage(), "WFH9002", ex);

        }
		finally {
			if(synth != null) {
				synth.close();
			}
			if(fileOutput != null) {
				fileOutput.close();
			}
		}
		return audio;
	}

}
