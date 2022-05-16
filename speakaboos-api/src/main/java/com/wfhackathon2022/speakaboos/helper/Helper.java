package com.wfhackathon2022.speakaboos.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wfhackathon2022.speakaboos.io.model.StatusMessage;
import com.wfhackathon2022.speakaboos.io.model.StatusMessageResponse;

@Component
public class Helper {

	public StatusMessageResponse createStatusMessageResponse(String message) {
		StatusMessageResponse response = new StatusMessageResponse();
		StatusMessage msg = new StatusMessage();
		msg.setMessage(message);
		List<StatusMessage> list = new ArrayList<StatusMessage>();
		list.add(msg);
		response.setStatusMessages(list);
		return response;
		
	}

	
}
