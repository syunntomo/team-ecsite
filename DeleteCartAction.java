package com.internousdev.mimosa.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mimosa.dao.CartInfoDAO;
import com.internousdev.mimosa.dto.CartInfoDTO;
import com.internousdev.mimosa.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCartAction extends ActionSupport implements SessionAware{

	private Collection<String> checkList;
	private String productId;

	private String sex;
	private List<String> sexList = new ArrayList<String>();
	private static final String MALE = "男性";
	private static final String FEMALE = "女性";
	private String defaultSexValue = MALE;

	private String productName;
	private String productNameKana;
	private String imageFileName;
	private String imageFilePath;
	private String price;
	private String releaseCompany;
	private String releaseDate;
	private String productCount;
	private String subtotal;

	private Map<String, Object> session;

	public String execute(){

		String result = ERROR;
		CommonUtility commonUtility = new CommonUtility();
		if(!commonUtility.checkSession(session)) {
			return "sessionTimeOut";
		}

		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		int count = 0;
		List<String> checkListErrorMessageList = new ArrayList<String>();

		String userId = null;
		if(session.containsKey("loginId")) {
			userId = String.valueOf(session.get("loginId"));
		}else if (session.containsKey("tempUserId")) {
			userId = String.valueOf(session.get("tempUserId"));
		}

		if(checkList == null){
			checkList = new ArrayList<>();
		}

		for(String productId: checkList){
			count += cartInfoDAO.delete(productId, userId);
		}

		//チェックボックスに1つもチェックされていない状態で削除ボタンを押したときにエラーメッセージを格納
		//データ消去が成功したときは、userIdを取りなおして、カート情報を取得して合計金額を計算しなおす
		if(count <= 0){
			checkListErrorMessageList.add("チェックされていません。");
			session.put("checkListErrorMessageList", checkListErrorMessageList);
			return ERROR;
		} else {
			List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();

			//カート情報の取得を行い、セッションに格納
			cartInfoDtoList = cartInfoDAO.getCartInfoDtoList(userId);
			Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();
			if(!(iterator.hasNext())){
				cartInfoDtoList = null;
			}
			session.put("cartInfoDtoList", cartInfoDtoList);

			//合計金額の計算
			int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.gettotalPrice(userId)));
			session.put("totalPrice", totalPrice);

			sexList.add(MALE);
			sexList.add(FEMALE);
			result = SUCCESS;
		}
		session.remove("checkListErrorMessageList");
		return result;
	}

	public Collection<String> getCheckList(){
		return checkList;
	}

	public void setCheckList(Collection<String> checkList){
		this.checkList = checkList;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getSex(){
		return sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public List<String> getSexList(){
		return sexList;
	}

	public void setSexList(List<String> sexList){
		this.sexList = sexList;
	}

	public String getDefaultSexValue(){
		return defaultSexValue;
	}

	public void setDefaultSexValue(String defaultSexValue){
		this.defaultSexValue = defaultSexValue;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductNameKana(){
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana){
		this.productNameKana = productNameKana;
	}

	public String getImageFileName(){
		return imageFileName;
	}

	public void setImageFileName(String imageFileName){
		this.imageFileName = imageFileName;
	}

	public String getImageFilePath(){
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath){
		this.imageFilePath = imageFilePath;
	}

	public String getPrice(){
		return price;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getReleaseCompany(){
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany){
		this.releaseCompany = releaseCompany;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getProductCount(){
		return productCount;
	}

	public void setProductCount(String productCount){
		this.productCount = productCount;
	}

	public String getSubtotal(){
		return subtotal;
	}

	public void setSubtotal(String subtotal){
		this.subtotal = subtotal;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}
