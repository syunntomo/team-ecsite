package com.internousdev.mimosa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mimosa.dao.CartInfoDAO;
import com.internousdev.mimosa.dto.CartInfoDTO;
import com.internousdev.mimosa.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware{
	private int productId;
	private String productName;
	private String productNameKana;
	private String imageFileName;
	private String imageFilePath;
	private int price;
	private String releaseCompany;
	private Date releaseDate;
	private String productCount;

	private Map<String,Object> session;

	public String execute(){

		String result = ERROR;
		CommonUtility commonUtility = new CommonUtility();
		if(!commonUtility.checkSession(session)) {
			return "sessionTimeOut";
		}
		String userId = null;
		String tempUserId = null;

		session.remove("checkListErrorMessageList");

		//loginId or tempUserId を使って、ログイン状態を確認している

		if(!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))){
			session.put("tempUserId", commonUtility.getRamdomValue());
		}

		if(session.containsKey("loginId")){
			userId = String.valueOf(session.get("loginId"));
		} else if(session.containsKey("tempUserId")){
			userId = String.valueOf(session.get("tempUserId"));
			tempUserId = String.valueOf(session.get("tempUserId"));
		}

		productCount = String.valueOf((productCount.split(",",0))[0]); //意味不明

		//cart_infoにデータを入力する

		CartInfoDAO dao = new CartInfoDAO();
		int count = dao.regist(userId, tempUserId, productId, productCount, price);
		if(count > 0){
			result = SUCCESS;
		}

		//cart_infoに入っている全情報をセッションに格納している
		//なお、カートに何も入っていない時、リストにnullを入れる
		List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
		cartInfoDtoList = dao.getCartInfoDtoList(userId);
		Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();
		if(!(iterator.hasNext())){
			cartInfoDtoList = null; //nullを入れることで表の外枠を消す
		}
		session.put("cartInfoDtoList", cartInfoDtoList);

		//カートに入っている全商品の合計金額を計算する
		int totalPrice = dao.gettotalPrice(userId);
		session.put("totalPrice", totalPrice);

		return result;
	}

	public int getProductId(){
		return productId;
	}

	public void setProductId(int productId){
		this.productId = productId;
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

	public int getPrice(){
		return price;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public String getReleaseCompany(){
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany){
		this.releaseCompany = releaseCompany;
	}

	public Date getReleaseDate(){
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getProductCount(){
		return productCount;
	}

	public void setProductCount(String productCount){
		this.productCount = productCount;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}