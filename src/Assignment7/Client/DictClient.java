package Client;

import Assignment7.Utils;

public class DictClient {
    public static void main(String[] args) {
    	try{
    		String id = Utils.getRandomString(20);
    		String des = Utils.getRandomString(100);
    		String uri = Utils.getRandomString(1024);
    		
    		String id1 = "apple";
    		String des1 = "Apple";
    		String uri1 = "apple.com";
    		
    		//Add first Word
    		DictionaryStub stub = new DictionaryStub();
    		DictionaryStub.AddWord setRequest = new DictionaryStub.AddWord();
    		setRequest.setId(id);
    		setRequest.setDescription(des);
    		setRequest.setUri(uri);  		
    		
    		DictionaryStub.AddWordResponse response = stub.addWord(setRequest);
    		System.out.println(response.get_return());
    		
    		//Add second Word
    		setRequest.setId(id1);
    		setRequest.setDescription(des1);
    		setRequest.setUri(uri1);  		
            DictionaryStub.AddWordResponse response2 = stub.addWord(setRequest);
    		System.out.println(response2.get_return());
    		
    		//Get list of indexs
    		DictionaryStub.GetWordInfo getRequest = new DictionaryStub.GetWordInfo();
    		DictionaryStub.GetWordInfoResponse getResponse = stub.getWordInfo(getRequest);
    		String[] index = getResponse.get_return();
    		for(int i=0; i<index.length; i++){
    			System.out.println(index[i]);
    		}
    		
    	} catch (Exception e){}
    }
}
