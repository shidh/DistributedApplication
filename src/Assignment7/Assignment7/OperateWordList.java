package Assignment7;

import java.util.List;
public interface OperateWordList {
	
	/**
	 * Get all identifiers and corresponding indexs 
	 * @return a list of index and id of Words
	 */
	public List<String> getWordInfo();
	
	
	/**
	 * add a Word
	 * @param identifier the identifier of the word
	 * @param description the description of the worde
	 * @param URI
	 * @return the Word index
	 */
	public int addWord(String identifier , String description, String URI);
	
}
