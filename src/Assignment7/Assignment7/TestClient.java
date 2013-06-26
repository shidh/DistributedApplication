package Assignment7;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		OperateWordListImp operator = new OperateWordListImp();

		String id = Utils.getRandomString(20);
		String des = Utils.getRandomString(100);
		String uri = Utils.getRandomString(1024);
		
		String id1 = "apple";
		String des1 = "Apple";
		String uri1 = "apple.com";
		
		operator.addWord(id, des, uri);
		operator.addWord(id1, des1, uri1);
		operator.getWordInfo();
	}
}
