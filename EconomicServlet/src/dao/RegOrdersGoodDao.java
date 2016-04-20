package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import tables.OrderGood;
import tables.RegOrderGood;

public class RegOrdersGoodDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	
	String sqlCreate = "insert into reg_order_good (id_reg_order, id_good) values (:id_reg_order, :id_good)";
	
	
	public RegOrdersGoodDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<OrderGood> rowMapper = new RowMapper<OrderGood>(){
		@Override
		public OrderGood mapRow(ResultSet rs, int rownumber) throws SQLException{
			OrderGood temp=new OrderGood();
			temp.setIdOrder(rs.getInt("id_reg_order"));
			temp.setIdGood(rs.getInt("id_good"));
			return temp;
		}
	};
	

	public void create(RegOrderGood og) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_reg_order", String.valueOf(og.getIdOrder()));
		parameters.put("id_good", String.valueOf(og.getIdGood()));
		namedParameterJdbcTemplate.update(sqlCreate,parameters);
	}
	
	
}
