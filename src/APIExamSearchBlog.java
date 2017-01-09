//네이버 검색 API예제는 블로그를 비롯 전문자료까지 호출방법이 동일하므로 blog검색만 대표로 예제를 올렸습니다.
// 네이버 검색 API 예제 - blog 검색
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
        	String keyword = "문재인";//검색어
        	String display = "10"; //~100
        	String start = "11"; //1~1000
        	String sort = "sim"; //sim=유사도, date=최신순
        	String text = URLEncoder.encode(keyword, "UTF-8")+"";
            //String apiURL = "https://openapi.naver.com/v1/search/news?query="+ text; // json 결과
            String apiURL = "https://openapi.naver.com/v1/search/news.xml?display=" + display + "&start=" + start + "&sort=" + sort + "&query=" + text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            } else {  // 에러 발생
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