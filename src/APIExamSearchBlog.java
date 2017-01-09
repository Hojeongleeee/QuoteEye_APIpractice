//���̹� �˻� API������ ��α׸� ��� �����ڷ���� ȣ������ �����ϹǷ� blog�˻��� ��ǥ�� ������ �÷Ƚ��ϴ�.
// ���̹� �˻� API ���� - blog �˻�
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExamSearchBlog {

    public static void main(String[] args) {

        String clientId = Credentials.clientId;
        String clientSecret = Credentials.clientSecret;

        try {
        	String keyword = "������";//�˻���
        	String display = "10"; //~100
        	String start = "11"; //1~1000
        	String sort = "sim"; //sim=���絵, date=�ֽż�
        	String text = URLEncoder.encode(keyword, "UTF-8")+"";
            //String apiURL = "https://openapi.naver.com/v1/search/news?query="+ text; // json ���
            String apiURL = "https://openapi.naver.com/v1/search/news.xml?display=" + display + "&start=" + start + "&sort=" + sort + "&query=" + text; // xml ���
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(new String( response.toString() ));
        } catch (Exception e) {
            System.out.println(e);
        }
 
    }
}