//java 获取响应头response header     
//java Monitor [url]http://www.baidu.com[/url] 1>>CheckHttpResponse_log.txt
//Create By Ajian . 


import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class Monitor {
	public static void main(String[] args) throws IOException{
	       String testUrl = new String("");
	       java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       if (args.length > 0) {
	           testUrl = args[0];
	       } else {
	           testUrl = "http://www.baidu.com/index.php";
	       }
	       System.out.println("The URL to be test is: " + testUrl);
	       System.out.println("The Date is ：" + sdf.format(new java.util.Date()));
	      URL url = new URL(testUrl);
	      HttpURLConnection conn = null;
	      int responseCode = 0;
	      try {
	           conn = (HttpURLConnection)url.openConnection();
	          responseCode = conn.getResponseCode();
	      } catch (java.net.UnknownHostException uhE) {
	          responseCode = 700;//UnknownHost
	          System.out.println("UnknownHost");
	      }
	      System.out.println("The ResponseCode is : " + responseCode);

	        if (responseCode != 200) {
	        	HttpURLConnection monitConn = null;
	            System.out.println("报警结果是" + "\n");
	        }

	   }
} 

