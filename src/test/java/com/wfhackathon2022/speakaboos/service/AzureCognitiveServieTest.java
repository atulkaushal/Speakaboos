package com.wfhackathon2022.speakaboos.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AzureCognitiveServieTest {

	@InjectMocks
	AzureCognitiveServie azureCognitiveServie;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRetrieveSpeech() {
		byte[] actualResponse = azureCognitiveServie.retrieveSpeech("hello", "en-IN", new BigDecimal(5));
		assertNotNull(actualResponse);
	}

}
