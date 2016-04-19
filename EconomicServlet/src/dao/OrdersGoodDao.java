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

public class OrdersGoodDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	
	String sqlCreate = "insert into order_good (id_order, id_good) values (:id_order, :id_good)";
	
	
	public OrdersGoodDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<OrderGood> rowMapper = new RowMapper<OrderGood>(){
		@Override
		public OrderGood mapRow(ResultSet rs, int rownumber) throws SQLException{
			OrderGood temp=new OrderGood();
			temp.setIdOrder(rs.getInt("id_order"));
			temp.setIdGood(rs.getInt("id_good"));
			return temp;
		}
	};
	

	public void create(OrderGood arg) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_order", String.valueOf(arg.getIdOrder()));
		parameters.put("id_good", String.valueOf(arg.getIdGood()));
		namedParameterJdbcTemplate.update(sqlCreate,parameters);
	}
	
	
}
