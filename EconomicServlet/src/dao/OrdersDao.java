package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import tables.Order;

public class OrdersDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select name, id_order, id_client, date, price, status from orders join clients on orders.id_client = clients.id_client";
	String sqlByIdClient = "select * from orders where id_client=:id_client";
	String sqlByIdOrder = "select * from orders where id_order=:id_order";
	
	String sqlCreate = "insert into orders (id_order, id_client, date, price, status) values (:id_order, :id_client, :date, :price, :status)";
	
	String sqlUpdateById = "update orders set "
			+ "id_client=:id_client, date=:date, price=:price, status=:status "
			+ "where id_order=:id_order";
	
	String sqlDeleteById = "delete from orders where id_order= ";
	
	public OrdersDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Order> rowMapper = new RowMapper<Order>(){
		@Override
		public Order mapRow(ResultSet rs, int rownumber) throws SQLException{
			Order temp=new Order();
			temp.setIdOrder(rs.getInt("id_order"));
			temp.setIdClient(rs.getInt("id_client"));
			temp.setClient(rs.getString("name"));
			temp.setDate(rs.getDate("date"));
			temp.setPrice(rs.getInt("price"));
			temp.setStatus(rs.getBoolean("status"));
			return temp;
		}
	};
	
	RowMapper<Order> rowMapper2 = new RowMapper<Order>(){
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
		return namedParameterJdbcTemplate.query(sqlByIdClient, parameters, rowMapper2);
	}
	
	public Order findByIdOrder(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_order", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlByIdClient, parameters, rowMapper);
	}
	
	public void update(Order arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", String.valueOf(arg.getIdClient()));
		parameters.put("date", new SimpleDateFormat("dd.mm.yyyy").format(arg.getDate()));
		parameters.put("price", Integer.toString(arg.getPrice()));
		parameters.put("state", Boolean.toString(arg.isStatus()));
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteByIdOrder(int id){
		//Map <String, String> parameters = new LinkedHashMap<String, String>();
		//parameters.put("id_order", Integer.toString(id));
		//jdbcTemplate.update(sqlDeleteById, parameters);
		jdbcTemplate.update(sqlDeleteById + id);
	}

	public void create(Order arg) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_order", String.valueOf(arg.getIdOrder()));
		parameters.put("id_client", String.valueOf(arg.getIdClient()));
		parameters.put("date", arg.getDate().toString());
		parameters.put("price", String.valueOf(arg.getPrice()));
		parameters.put("status", String.valueOf(arg.isStatus()));
		namedParameterJdbcTemplate.update(sqlCreate,parameters);
	}

	
	
}
