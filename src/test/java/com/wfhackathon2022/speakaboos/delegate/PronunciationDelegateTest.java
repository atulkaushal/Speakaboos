package com.wfhackathon2022.speakaboos.delegate;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.wfhackathon2022.speakaboos.TestsCommonHelper;
import com.wfhackathon2022.speakaboos.dao.PronunciationDAO;
import com.wfhackathon2022.speakaboos.entity.Employee;
import com.wfhackathon2022.speakaboos.entity.PronunciationPreferences;
import com.wfhackathon2022.speakaboos.exception.PronunciationException;
import com.wfhackathon2022.speakaboos.helper.Helper;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsRequest;
import com.wfhackathon2022.speakaboos.io.model.GetEmployeeDetailsResponse;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.GetPronunciationInformationResponse;
import com.wfhackathon2022.speakaboos.io.model.ListEmployeesResponse;
import com.wfhackathon2022.speakaboos.io.model.SavePronunciationInformationRequest;
import com.wfhackathon2022.speakaboos.io.model.StatusMessageResponse;
import com.wfhackathon2022.speakaboos.service.AzureCognitiveServie;

@RunWith(MockitoJUnitRunner.class)
public class PronunciationDelegateTest {

	@InjectMocks
	private PronunciationDelegate pronunciationDelegate;
	
	@Mock
	private PronunciationDAO pronunciationDAO;
	
	@Mock
	private Helper commonHelper = null;
	
	@Mock
	private AzureCognitiveServie azureCognitiveServie;
	
	@Mock
	private MultipartFile nameAudio;
	
	TestsCommonHelper helper = null;
	
	
	@Before
	public void setUp() throws Exception {
		helper = new TestsCommonHelper();
		
	}
	
	 
	@Test
	public void testGetEmployeeDetails() {
		GetEmployeeDetailsRequest request = this.helper.createGetEmployeeDetailsRequest();
		Employee emp = this.helper.createEmployee(4, "Baohua", "Lee");		
		Optional<Employee> expectedEmployee = Optional.of(emp);
		Mockito.when(pronunciationDAO.getEmployeeDetails(request.getEmployeeId())).thenReturn(expectedEmployee);
		GetEmployeeDetailsResponse actualResponse = pronunciationDelegate.getEmployeeDetails(request);
		assertEquals(expectedEmployee.get().getEmployeeId(), actualResponse.getEmployeeId());
	}
	
	@Test(expected=PronunciationException.class)
	public void testGetEmployeeDetailsException() {
		GetEmployeeDetailsRequest request = this.helper.createGetEmployeeDetailsRequest();
		Optional<Employee> expectedEmployee = Optional.empty();
		Mockito.when(pronunciationDAO.getEmployeeDetails(Mockito.any())).thenReturn(expectedEmployee);
		pronunciationDelegate.getEmployeeDetails(request);
	}

	@Test
	public void testListEmployees() {
		List<Employee> expectedEmployeesList = this.helper.createEmployeeList();
		Mockito.when(pronunciationDAO.getEmployeeList()).thenReturn(expectedEmployeesList);
		ListEmployeesResponse actualEmployeesResponse = pronunciationDelegate.listEmployees();
		assertEquals(expectedEmployeesList.size(), actualEmployeesResponse.getEmployeeDetailsList().size());
	}
	
	@Test(expected=PronunciationException.class)
	public void testListEmployeesException() {
		List<Employee> expectedEmployeesList = new ArrayList<Employee>();
		Mockito.when(pronunciationDAO.getEmployeeList()).thenReturn(expectedEmployeesList);
		pronunciationDelegate.listEmployees();		
	}
	
	
	@Test
	public void testSavePronunciationInformation() {
		PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.of(pronunciationPreferences);
		SavePronunciationInformationRequest request = this.helper.createSavePronunciationInformationRequest();
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		StatusMessageResponse expectedResponse = this.helper.createStatusMessageResponse("Employee preference saved successfully");
		Mockito.when(commonHelper.createStatusMessageResponse(Mockito.anyString())).thenReturn(expectedResponse);
		StatusMessageResponse actualResponse = pronunciationDelegate.savePronunciationInformation(request);
		assertEquals("Employee preference saved successfully", actualResponse.getStatusMessages().get(0).getMessage());
	}
	
	@Test
	public void testSavePronunciationInformationPrefEmpty() {
		//PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.empty();
		SavePronunciationInformationRequest request = this.helper.createSavePronunciationInformationRequest();
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		StatusMessageResponse expectedResponse = this.helper.createStatusMessageResponse("Employee preference saved successfully");
		Mockito.when(commonHelper.createStatusMessageResponse(Mockito.anyString())).thenReturn(expectedResponse);
		StatusMessageResponse actualResponse = pronunciationDelegate.savePronunciationInformation(request);
		assertEquals("Employee preference saved successfully", actualResponse.getStatusMessages().get(0).getMessage());
	}
	
	@Test
	public void testSavePronunciationInformationOptFalse() {
		PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.of(pronunciationPreferences);
		SavePronunciationInformationRequest request = this.helper.createSavePronunciationInformationRequest();
		request.setOptOutFlag(false);
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		StatusMessageResponse expectedResponse = this.helper.createStatusMessageResponse("Employee preference saved successfully");
		Mockito.when(commonHelper.createStatusMessageResponse(Mockito.anyString())).thenReturn(expectedResponse);
		StatusMessageResponse actualResponse = pronunciationDelegate.savePronunciationInformation(request);
		assertEquals("Employee preference saved successfully", actualResponse.getStatusMessages().get(0).getMessage());
	}
	
	
	@Test
	public void testGetPronunciationInformation() {
		GetPronunciationInformationRequest request = this.helper.createGetPronunciationInformationRequest();
		PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.of(pronunciationPreferences);
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		GetPronunciationInformationResponse actualResponse = pronunciationDelegate.getPronunciationInformation(request);
		assertEquals(request.getLanguage(), actualResponse.getLanguage());
	}
	
	@Test
	public void testGetPronunciationInformationPrefEmpty() {
		GetPronunciationInformationRequest request = this.helper.createGetPronunciationInformationRequest();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.empty();
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		GetPronunciationInformationResponse actualResponse = pronunciationDelegate.getPronunciationInformation(request);
		assertEquals(request.getLanguage(), actualResponse.getLanguage());
	}
	
	@Test
	public void testGetPronunciationAudio() {
		GetPronunciationInformationRequest request = this.helper.createGetPronunciationInformationRequest();
		PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.of(pronunciationPreferences);
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		byte[] expectedAudio = new byte[10];
		Mockito.when(azureCognitiveServie.retrieveSpeech(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(expectedAudio);
		byte[] actualResponse = pronunciationDelegate.getPronunciationAudio(request);
		assertNotNull(actualResponse);
		assertEquals(expectedAudio.length, actualResponse.length);
	}
	
	@Test
	public void testGetPronunciationAudioPrefEmpty() {
		GetPronunciationInformationRequest request = this.helper.createGetPronunciationInformationRequest();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.empty();
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		byte[] expectedAudio = new byte[10];
		Mockito.when(azureCognitiveServie.retrieveSpeech(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(expectedAudio);
		byte[] actualResponse = pronunciationDelegate.getPronunciationAudio(request);
		assertNotNull(actualResponse);
		assertEquals(expectedAudio.length, actualResponse.length);
	}
	
	@Test
	public void testSavePronunciationAudio() {
		PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		pronunciationPreferences.setOptOutFlag(false);
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.of(pronunciationPreferences);
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		Mockito.doNothing().when(pronunciationDAO).savePronunciationInformation(Mockito.any());
		StatusMessageResponse expectedResponse = this.helper.createStatusMessageResponse("Audio saved successfully");		
		Mockito.when(commonHelper.createStatusMessageResponse(Mockito.anyString())).thenReturn(expectedResponse);
		StatusMessageResponse actualResponse = pronunciationDelegate.savePronunciationAudio(1, nameAudio);
		assertEquals("Audio saved successfully", actualResponse.getStatusMessages().get(0).getMessage());
	}
	
	@Test(expected=PronunciationException.class)
	public void testSavePronunciationAudioWithOptOut() {
		PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.of(pronunciationPreferences);
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		Mockito.doNothing().when(pronunciationDAO).savePronunciationInformation(Mockito.any());
		StatusMessageResponse expectedResponse = this.helper.createStatusMessageResponse("Audio saved successfully");		
		Mockito.when(commonHelper.createStatusMessageResponse(Mockito.anyString())).thenReturn(expectedResponse);
		pronunciationDelegate.savePronunciationAudio(1, nameAudio);
	}
	
	@Test(expected=PronunciationException.class)
	public void testSavePronunciationAudioWithIOException() throws IOException {
		PronunciationPreferences pronunciationPreferences = this.helper.createPronunciationPreferences();
		pronunciationPreferences.setOptOutFlag(false);
		Optional<PronunciationPreferences> optionalPronunciationPreferences = Optional.of(pronunciationPreferences);
		Mockito.when(pronunciationDAO.getPronunciationPreferences(Mockito.any())).thenReturn(optionalPronunciationPreferences);
		Mockito.doNothing().when(pronunciationDAO).savePronunciationInformation(Mockito.any());
		StatusMessageResponse expectedResponse = this.helper.createStatusMessageResponse("Audio saved successfully");		
		Mockito.when(commonHelper.createStatusMessageResponse(Mockito.anyString())).thenReturn(expectedResponse);
		MultipartFile file = Mockito.spy(nameAudio);
		Mockito.when(file.getBytes()).thenThrow(new IOException());
		pronunciationDelegate.savePronunciationAudio(1, file);		
	}

}
