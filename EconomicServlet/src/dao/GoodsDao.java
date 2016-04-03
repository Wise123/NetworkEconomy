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
	String sqlById = "select * from goods where id_good=:id_good";
	
	String sqlUpdateById = "update goods set " +
			"id_provider=:id_provider, name=:name, "
			+ "price=:price, description=:description, "
			+ "category=:category, count_on_stock=:count_on_stock, image_path=:image_path "
			+ "where id_good=:id_good";
	
	String sqlDeleteById = "delete from goods where id_good=:id_good";
	
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
			temp.setCategory(rs.getString("category"));
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
	
	public Good findById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_good", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlById, parameters, rowMapper);
	}
	
	
	
	public void update(Good arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_provider", Integer.toString(arg.getIdProvider()));
		parameters.put("name", arg.getName());
		parameters.put("price", Integer.toString(arg.getPrice()));
		parameters.put("description", arg.getDescription());
		parameters.put("category", arg.getCategory());
		parameters.put("count_on_stock", Integer.toString(arg.getCountOnStock()));
		parameters.put("image_path", arg.getImagePath());
		parameters.put("id_good", Integer.toString(arg.getIdGood()));
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_good", Integer.toString(id));
		namedParameterJdbcTemplate.update(sqlDeleteById, parameters);
	}
	
}
