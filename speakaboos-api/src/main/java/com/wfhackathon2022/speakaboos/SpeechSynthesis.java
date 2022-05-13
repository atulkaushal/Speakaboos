package com.wfhackathon2022.speakaboos;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisCancellationDetails;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;

/** The Class SpeechSynthesis. */
public class SpeechSynthesis {

  /** The Your subscription key. */
  private static String YourSubscriptionKey = "YourSubscriptionKey";

  /** The Your service region. */
  private static String YourServiceRegion = "YourServiceRegion";

  /**
   * The main method.
   *
   * @param args the arguments
   * @throws InterruptedException the interrupted exception
   * @throws ExecutionException the execution exception
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    SpeechConfig speechConfig =
        SpeechConfig.fromSubscription(YourSubscriptionKey, YourServiceRegion);

    speechConfig.setSpeechSynthesisVoiceName("en-US-JennyNeural");

    SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig);

    // Get text from the console and synthesize to the default speaker.
    System.out.println("Enter some text that you want to speak >");
    String text = new Scanner(System.in).nextLine();
    if (text.isEmpty()) {
      return;
    }

    SpeechSynthesisResult speechRecognitionResult = speechSynthesizer.SpeakTextAsync(text).get();

    if (speechRecognitionResult.getReason() == ResultReason.SynthesizingAudioCompleted) {
      System.out.println("Speech synthesized to speaker for text [" + text + "]");
    } else if (speechRecognitionResult.getReason() == ResultReason.Canceled) {
      SpeechSynthesisCancellationDetails cancellation =
          SpeechSynthesisCancellationDetails.fromResult(speechRecognitionResult);
      System.out.println("CANCELED: Reason=" + cancellation.getReason());

      if (cancellation.getReason() == CancellationReason.Error) {
        System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
        System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
        System.out.println("CANCELED: Did you set the speech resource key and region values?");
      }
    }

    System.exit(0);
  }
}
