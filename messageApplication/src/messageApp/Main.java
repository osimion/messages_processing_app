package messageApp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import messageApp.model.Message;

public class Main {

	public static void main(String[] args) {

		String messagesJson = args[0];
		List<Message> messages = parseJson(messagesJson);
		if(messages != null){
			Register register = new Register();
			register.processSales(messages);
		}
		
	}
	
	private static List<Message> parseJson (String json){
		List<Message> messages = null;
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			messages = mapper.readValue(new File(json), new TypeReference<List<Message>>(){});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return messages;
	}

}
