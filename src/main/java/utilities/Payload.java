package utilities;

public class Payload {
	
	public static String getAddPostData() {
		
		String addpost = "{" +
		        "\"title\": \"First Post Added through Automation\"," +
		        "\"body\": \"bar\"," +
		        "\"userId\": 1" +
		    "}";
		
		return addpost;
		
		
		
	}
	
	
	public static String getUpdatePostData() {
		String updatepost="{\"id\": 1," +
                "\"title\": \"First Post changed through rest\"," +
                "\"body\": \"bar\"," +
                "\"userId\": 1" +
                "}";
		return updatepost;
	}

}
