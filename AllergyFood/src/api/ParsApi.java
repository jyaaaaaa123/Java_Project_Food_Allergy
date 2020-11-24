package api;

import java.awt.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParsApi {
	public NodeList nList;
	
	//생성자
	public ParsApi(String apiUrl) {
		try {
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(apiUrl);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("item");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//제품목록 파싱
	public ArrayList<String> ParsApi_Namelist() {
		ArrayList<String> nameList = new ArrayList<String>();
		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				nameList.add(getTagValue("prdlstNm", eElement));
			}
		 }
		return nameList;
	}
	
	public ArrayList<String> ParsApi_Manufacturelist() {
		ArrayList<String> manufactureList = new ArrayList<String>();
		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				manufactureList.add(getTagValue("manufacture", eElement));
			}
		 }
		return manufactureList;
	}
	
	//알레르기 목록 파싱 - 수정 필요
	public String[] ParsApi_allergyList(String foodname) {
		String[] all = new String[4];
		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				if(getTagValue("prdlstNm", eElement).equals(foodname)) {
					all[0] = getTagValue("prdlstNm", eElement);
					all[1] = getTagValue("manufacture", eElement);
					all[2] = getTagValue("allergy", eElement);
					all[3] = getTagValue("imgurl1", eElement);
				}
				
			}
		 }
		return all;
	}
	
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
}
