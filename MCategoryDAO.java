package com.internousdev.mimosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.mimosa.dto.MCategoryDTO;
import com.internousdev.mimosa.util.DBConnector;

public class MCategoryDAO {

	public List<MCategoryDTO> getMCategoryList(){
		DBConnector dbconnector=new DBConnector();
		Connection connection=dbconnector.getConnection();
		List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
		String sql="select * from m_category";

		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(resultSet.getInt("id"));
				mCategoryDTO.setCategoryId(resultSet.getInt("category_id"));
				mCategoryDTO.setCategoryName(resultSet.getString("category_name"));
				mCategoryDTO.setCategoryDescription(resultSet.getString("category_description"));
				mCategoryDTO.setInsertDate(resultSet.getDate("insert_date"));
				mCategoryDTO.setUpdateDate(resultSet.getDate("update_date"));
				mCategoryDtoList.add(mCategoryDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
			connection.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
		return mCategoryDtoList;
	}
}
