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

import tables.Order;

public class OrderDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select * from orders";
	String sqlByIdClient = "select * from orders where id_client=:idClient";
	
	public OrderDao(DriverManagerDataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Order> rowMapper = new RowMapper<Order>(){
		@Override
		public Order mapRow(ResultSet rs, int rownumber) throws SQLException{
			Order temp=new Order();
			temp.setIdOrder(rs.getInt("id_order"));
			temp.setIdClient(rs.getInt("id_client"));
			temp.setDate(rs.getDate("date"));
			temp.setPrice(rs.getInt("price"));
			temp.setStatus(rs.getBoolean("status"));
			return temp;
		}
	};
	
	public List<Order> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	public List<Order> findByIdClient(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(id));
		return namedParameterJdbcTemplate.query(sqlByIdClient, parameters, rowMapper);
	}
}
