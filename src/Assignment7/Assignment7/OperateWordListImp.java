package Assignment7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OperateWordListImp implements OperateWordList {
	private List<Word> dic =  new ArrayList<Word>();
	private List<String> index =  new ArrayList<String>(); 

	@Override
	public List<String> getWordInfo() {
		String indexAndID = null;
		for (Iterator iter=dic.iterator(); iter.hasNext();){
			Word word = (Word) iter.next();
			word.getDescription();
			word.getIdentifier();
			word.getURI();
			indexAndID = "Index: "+ dic.indexOf(word) + "    ID: "+word.getIdentifier();
			System.out.println(indexAndID);
			index.add(indexAndID);
		}

		return index;
	}

	@Override
	public int addWord(String id, String description, String uri) {
		// TODO Auto-generated method stub
		Word newWord = new Word();
		newWord.setIdentifier(id);
		newWord.setDescription(description);
		newWord.setURI(uri);
		dic.add(newWord);
		
		return dic.indexOf(newWord);
	}

}
