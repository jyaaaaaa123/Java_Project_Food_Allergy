package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ConnApi {
	
	private String name;
	private String page;
	
	
	public ConnApi(String name, String page) {
		this.setName(name);
		this.setPage(page);
	}
	
	
	public static String ConnApi_func(String name, String page) throws IOException {
		String apiUrl = "http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService" + 
				 	//¿Œ¡ı≈∞
			 		"?serviceKey=xHbWN9H2JB4kcX2gFBD%2BeQb3gULH1fu5w2EOxawu21IQwAxEvB%2B6CF%2By938lJskZ7n%2FKOG473u%2BDkhvVsuNXqg%3D%3D&prdlstNm=" + URLEncoder.encode(name,"UTF-8") + "&pageNo=" + URLEncoder.encode(page,"UTF-8");
			 		
		 
		URL url = new URL(apiUrl);
		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		return apiUrl;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	
}
