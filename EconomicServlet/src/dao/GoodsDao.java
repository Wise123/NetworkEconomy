package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import tables.Good;

public class GoodsDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select * from goods";
	String sqlByCategory = "select * from goods where category=:category";
	
	
	public GoodsDao(DriverManagerDataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Good> rowMapper = new RowMapper<Good>(){
		@Override
		public Good mapRow(ResultSet rs, int rownumber) throws SQLException{
			Good temp=new Good();
			temp.setIdGood(rs.getInt("id_good"));
			temp.setIdProvider(rs.getInt("id_provider"));
			temp.setName(rs.getString("name"));
			temp.setPrice(rs.getInt("price"));
			temp.setDescription(rs.getString("description"));
			temp.setCountOnStock(rs.getInt("count_on_stock"));
			temp.setImagePath(rs.getString("image_path"));
			return temp;
		}
	};
	
	
	
	public List<Good> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	
	public List<Good> findByCategory(String category){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("category", category);
		return namedParameterJdbcTemplate.query(sqlByCategory, parameters, rowMapper);
	}
}
