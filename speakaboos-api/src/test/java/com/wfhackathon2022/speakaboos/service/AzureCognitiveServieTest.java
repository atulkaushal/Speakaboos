package com.wfhackathon2022.speakaboos.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.wfhackathon2022.speakaboos.exception.PronunciationException;

/** The Class AzureCognitiveServieTest. */
@RunWith(MockitoJUnitRunner.class)
public class AzureCognitiveServieTest {

  /** The azure cognitive servie. */
  @InjectMocks AzureCognitiveServie azureCognitiveServie;

  /** The result. */
  @Mock SpeechSynthesisResult result;

  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {}

  /** Test retrieve speech. */
  @Test
  public void testRetrieveSpeech() {
    byte[] actualResponse =
        azureCognitiveServie.retrieveSpeech("hello", "en-IN", new BigDecimal(5));
    Mockito.when(result.getReason())
        .thenAnswer(
            new Answer() {
              @Override
              public Object answer(InvocationOnMock invocation) throws Throwable {
                return ResultReason.SynthesizingAudioCompleted;
              }
            });
    assertNotNull(actualResponse);
  }

  /** Test retrieve speech exception. */
  @Test(expected = PronunciationException.class)
  public void testRetrieveSpeechException() {
    azureCognitiveServie.retrieveSpeech("hello", "abc", new BigDecimal(5));
  }

  /** Test retrieve speech cancelled. */
  @Test(expected = PronunciationException.class)
  public void testRetrieveSpeechCancelled() {
    Mockito.when(result.getReason())
        .thenAnswer(
            new Answer() {
              @Override
              public Object answer(InvocationOnMock invocation) throws Throwable {
                return ResultReason.Canceled;
              }
            });
    azureCognitiveServie.retrieveSpeech("cancelled", "en-IN", new BigDecimal(5));
  }
}
