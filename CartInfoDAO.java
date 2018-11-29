package com.internousdev.mimosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import  java.util.List;

import com.internousdev.mimosa.dto.CartInfoDTO;
import  com.internousdev.mimosa.util.DBConnector;

public class CartInfoDAO {

	/*cart_info/product_info データベースからデータを取り出すメソッド
	 *返り値がList配列なので、注意*/
	public List<CartInfoDTO> getCartInfoDtoList(String loginId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();

		String sql = "SELECT"
					+ " ci.id as id,"
					+ " ci.user_id as user_id,"
					+ " ci.temp_user_id as temp_user_id,"
					+ "	ci.product_id as product_id,"
					+ " sum(ci.product_count) as product_count,"
					+ " pi.price as price,"
					+ " pi.regist_date as regist_date,"
					+ " pi.update_date as update_date,"
					+ " pi.product_name as product_name,"
					+ " pi.product_name_kana as product_name_kana,"
					+ " pi.product_description as product_description,"
					+ " pi.category_id as category_id,"
					+ " pi.image_file_path as image_file_path,"
					+ " pi.image_file_name as image_file_name,"
					+ " pi.release_date as release_date,"
					+ " pi.release_company as release_company,"
					+ " pi.status as status,"
					+ " sum(pi.price * (ci.product_count)) as subtotal"
					+ " FROM cart_info ci"
					+ " LEFT JOIN product_info pi"
					+ " ON ci.product_id = pi.product_id"
					+ " WHERE ci.user_id = ?"
					+ " GROUP BY product_id";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				CartInfoDTO dto = new CartInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setTempUserId(rs.getString("temp_user_id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductCount(rs.getInt("product_count"));
				dto.setPrice(rs.getInt("price"));
				dto.setRegistDate(rs.getDate("regist_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getString("status"));
				dto.setSubtotal(rs.getInt("subtotal"));
				cartInfoDtoList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return cartInfoDtoList;
	}

	//カートに入っている全製品の合計金額を計算する
	public int gettotalPrice(String userId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int totalPrice = 0;

		String sql = "SELECT sum(product_count * price) AS total_price FROM cart_info WHERE user_id=? GROUP BY user_id";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				totalPrice = rs.getInt("total_price");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return totalPrice;
	}

	//カートに追加するメソッド
	public int regist(String userId, String tempUserId, int productId, String productCount, int price){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;

		String sql ="INSERT INTO cart_info(user_id, temp_user_id, product_id, product_count, price, regist_date) "
				+ " VALUES(?,?,?,?,?,now())";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, tempUserId);
			ps.setInt(3, productId);
			ps.setString(4, productCount);
			ps.setInt(5, price);

			count = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//cart_infoにあるレコード1行を削除する
	public int delete(String productId, String userId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;

		String sql = "DELETE FROM cart_info WHERE product_id=? AND user_id =?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, productId);
			ps.setString(2, userId);

			count = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//cart_infoにある全てのデータを削除する
	public int deleteAll(String userId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;

		String sql = "DELETE FROM cart_info WHERE user_id = ?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			count = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//一時的なIDから、正規IDへ移すメソッド
	public int linkToLoginId(String tempUserId, String loginId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;

		String sql ="UPDATE cart_info SET user_id=?, temp_user_id=null WHERE temp_user_id=?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, tempUserId);

			count = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}
}