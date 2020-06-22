package com.start.pro.util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_Mounui;
import com.start.pro.dto.DTO_Paging;

@Component
public class Util_JSON {

	private String str;
	private String key;

	public Util_JSON() {}

	public void setStr(String str) {
		this.str = str;
	}
	public String getStr() {
		List<String> list = jsonToList(str, key);
		String lists = null;
		if(list.size()>1) {
			lists = list.get(0) + "외 " +(list.size()-1);
		}else {
			lists = list.get(0);
		}
		System.out.println(lists);
		return lists;
	}

	public void setKey(String key) {
		System.out.println("키 설정한다"+key);
		this.key = key;
		System.out.println("이게 왜 오류나 ㅋㅋㅋ");
	}






	// str -> jsonArray -> List<String>
	public List<String> jsonToList(String str, String key){

		JSONParser parser = new JSONParser();
		List<String> list = new ArrayList<String>();
		Object obj = null;
		try {
			obj = parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSONArray jArr = (JSONArray) obj;
		for (int i = 0; i < jArr.size(); i++) {
			JSONObject jObj = (JSONObject) jArr.get(i);
			list.add((String) jObj.get(key));
			System.out.println("몇번째??"+list.get(i));
		}

		return list;
	}

	//List<String> -> jsonArray -> String
	@SuppressWarnings("unchecked")
	public String listToJson(List<String> list,String key) {
		JSONArray jarr = new JSONArray(); 
		for (String str : list) {
			JSONObject obj = new JSONObject();
			obj.put(key, str);
			jarr.add(obj);
		}
		System.out.println(jarr.toJSONString());
		return jarr.toJSONString();
	}


	//이메일 기록 보기 페이징 제이슨
	//json 객체로 만들어주는 메소드 JSONObject{}, JSONArray[{}{}{}...] -> {키 : [{키1:값1},{키2:값2}...]}
	@SuppressWarnings("unchecked")
	public JSONObject EmailmakeJson(List<DTO_Email> lists, DTO_Paging row) {
		//			System.out.println("______________________________"+row.toString());
		JSONArray jLists = new JSONArray(); // [{"":""},{"":""},{"":""}]
		JSONObject jdto = new JSONObject(); // {"":[{"":""},{"":""},{"":""}]}
		JSONObject json = new JSONObject(); // {"":""}
		for (DTO_Email dto : lists) {
			System.out.println("이게 돌아?");
			jdto = new JSONObject();
			jdto.put("category_code", dto.getCategory_code());
			jdto.put("email_seq", dto.getEmail_seq());
			jdto.put("successchk", dto.getSuccesschk());
			jdto.put("email_title", dto.getEmail_title());
			setKey("user_email");
			setStr(dto.getUser_email());
			jdto.put("user_email", getStr());
			jdto.put("regdate", dto.getRegdate());
			jLists.add(jdto);
		}

		jdto = new JSONObject();
		jdto.put("pageList", row.getPageList());
		jdto.put("index", row.getIndex());
		jdto.put("pageNum", row.getPageNum());
		jdto.put("listNum", row.getListNum());
		jdto.put("total", row.getTotal());
		jdto.put("count", row.getCount());

		json.put("lists", jLists);
		json.put("row", jdto);

		System.out.println(json.toJSONString());

		return json;
	}

	
	//관리자 문의  보기 제이슨
	@SuppressWarnings("unchecked")
	public JSONObject MounuimakeJson(List<DTO_Mounui> lists, DTO_Paging row) {
		//			System.out.println("______________________________"+row.toString());
		JSONArray jLists = new JSONArray(); // [{"":""},{"":""},{"":""}]
		JSONObject jdto = new JSONObject(); // {"":[{"":""},{"":""},{"":""}]}
		JSONObject json = new JSONObject(); // {"":""}
		for (DTO_Mounui dto : lists) {
			System.out.println("이게 돌아?");
			jdto = new JSONObject();
			jdto.put("category_title", dto.getCategory_title());
			jdto.put("mounui_seq", dto.getMounui_seq());
			jdto.put("title", dto.getTitle());
			jdto.put("category_seq", dto.getCategory_seq());
			jdto.put("board_code", dto.getBoard_code());
			jdto.put("regdate", dto.getRegdate());
			jdto.put("replychk", dto.getReplychk());
			jdto.put("delchk", dto.getDelchk());
			jLists.add(jdto);
		}

		jdto = new JSONObject();
		jdto.put("pageList", row.getPageList());
		jdto.put("index", row.getIndex());
		jdto.put("pageNum", row.getPageNum());
		jdto.put("listNum", row.getListNum());
		jdto.put("total", row.getTotal());
		jdto.put("count", row.getCount());

		json.put("lists", jLists);
		json.put("adminMounuiBoardrow", jdto);

		System.out.println(json.toJSONString());

		return json;
	}

	//사용자 문의보기 제이슨
	@SuppressWarnings("unchecked")
	public JSONObject UserMounuimakeJson(List<DTO_Mounui> lists, DTO_Paging row) {
		//			System.out.println("______________________________"+row.toString());
		JSONArray jLists = new JSONArray(); // [{"":""},{"":""},{"":""}]
		JSONObject jdto = new JSONObject(); // {"":[{"":""},{"":""},{"":""}]}
		JSONObject json = new JSONObject(); // {"":""}
		for (DTO_Mounui dto : lists) {
			System.out.println("이게 돌아?");
			jdto = new JSONObject();
			jdto.put("mounui_seq", dto.getMounui_seq());
			jdto.put("category_title", dto.getCategory_title());
			jdto.put("title", dto.getTitle());
			jdto.put("regdate", dto.getRegdate());
			jdto.put("replychk", dto.getReplychk());
			jLists.add(jdto);
		}
		
		jdto = new JSONObject();
		jdto.put("pageList", row.getPageList());
		jdto.put("index", row.getIndex());
		jdto.put("pageNum", row.getPageNum());
		jdto.put("listNum", row.getListNum());
		jdto.put("total", row.getTotal());
		jdto.put("count", row.getCount());
		
		json.put("lists", jLists);
		json.put("userMounuiBoardrow", jdto);
		
		System.out.println(json.toJSONString());
		
		return json;
	}

}
