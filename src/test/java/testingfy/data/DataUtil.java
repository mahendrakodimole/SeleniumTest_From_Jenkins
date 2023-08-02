package testingfy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataUtil {
	
	public static Object[][] getJsonData(String filePath) {
	//Read json to String
		String jsonData="";
		try {
			
			jsonData=	FileUtils.readFileToString(new File(filePath ),StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Convert String to List of HashMap of String Type
		ObjectMapper objectMapper =new ObjectMapper();
		List<HashMap<String,String>> listOfHashMap = null;
		try {
			listOfHashMap=objectMapper.readValue(jsonData,new TypeReference<List<HashMap<String,String>>>(){
			});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Object dataObject[][]=new Object[listOfHashMap.size()][1];
		
		int index=0;
		for(HashMap map:listOfHashMap) {
			dataObject[index++][0]=map;
		}
		
//		for(Object []i:dataObject) {
//			for(Object j:i) {
//				System.out.println(j
//						+" ");
//			}
//			System.out.println();
//		}
		
		return dataObject;
	}
}
