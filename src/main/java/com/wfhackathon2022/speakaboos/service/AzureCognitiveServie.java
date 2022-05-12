package com.wfhackathon2022.speakaboos.service;


import java.util.Scanner;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisCancellationDetails;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;

@Service
public class AzureCognitiveServie {
	
	
    // Replace below with your own subscription key
    String speechSubscriptionKey = "8bbe96c8331e4f31896a8cc435bbe355";
    // Replace below with your own service region (e.g., "westus").
    String serviceRegion = "eastus";
    
    
	public SpeechSynthesisResult retrieveSpeach(String text) {
		
		try (SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
	             SpeechSynthesizer synth = new SpeechSynthesizer(config)) {

            Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(text);

            SpeechSynthesisResult result = task.get();

            if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
            	//CODE_DEBT: Success
            }
            else if (result.getReason() == ResultReason.Canceled) {
            	//CODE_DEBT: Error Handle it
                SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(result);
                System.out.println("CANCELED: Reason=" + cancellation.getReason());

                if (cancellation.getReason() == CancellationReason.Error) {
                    System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                    System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                    System.out.println("CANCELED: Did you update the subscription info?");
                }
            }

        } catch (Exception ex) {
            //CODE_DEBT: Log and handle exception

        }
		//CODE_DEBT: Fix it
		return null;
	}

}
