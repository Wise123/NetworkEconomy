package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import tables.Order;
import tables.RegularOrder;


public class RegularOrdersDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlCreate = "insert into regular_orders (id_regord, id_client, count_of_goods, price, count_of_months) values (:id_regord, :id_client, :count_of_goods, :price, :count_of_months)";
	
	String sqlSelectAll = "select name, id_regord, id_client, client, count_of_goods, price, count_of_months from regular_orders join clients on regular_orders.id_client = clients.id_client";
	
	String sqlById = "select * from regular_orders where id_regord=:id_regord";
	
	String sqlUpdateById = "update regular_orders set "
			+ "id_client=:id_client, count_of_goods=:count_of_goods, name=:name, price=:price, count_of_months=:count_of_months"
			+ "where id_regord=:id_regord";
	
	String sqlDeleteById = "delete from regular_orders where id_regord=";
	
	String sqlByClientId = "select * from regular_orders where id_client=:id_client";
	
	public RegularOrdersDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<RegularOrder> rowMapper = new RowMapper<RegularOrder>(){
		@Override
		public RegularOrder mapRow(ResultSet rs, int rownumber) throws SQLException{
			RegularOrder temp=new RegularOrder();
			temp.setIdRegord(rs.getInt("id_regord"));
			temp.setIdClient(rs.getInt("id_client"));
			temp.setClient(rs.getString("name"));
			temp.setCountOfGoods(rs.getInt("count_of_goods"));
			temp.setPrice(rs.getInt("price"));
			temp.setCountOfMonth(rs.getInt("count_of_months"));
			return temp;
		}
	};
	
	RowMapper<RegularOrder> rowMapper2 = new RowMapper<RegularOrder>(){
		@Override
		public RegularOrder mapRow(ResultSet rs, int rownumber) throws SQLException{
			RegularOrder temp=new RegularOrder();
			temp.setIdRegord(rs.getInt("id_regord"));
			temp.setIdClient(rs.getInt("id_client"));
			temp.setCountOfGoods(rs.getInt("count_of_goods"));
			temp.setPrice(rs.getInt("price"));
			temp.setCountOfMonth(rs.getInt("count_of_months"));
			return temp;
		}
	};
	
	public List<RegularOrder> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	public List<RegularOrder> findById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_regorg", Integer.toString(id));
		return namedParameterJdbcTemplate.query(sqlById, parameters, rowMapper);
	}
	
	public RegularOrder findByIdOrder(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_regorg", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlById, parameters, rowMapper);
	}
	
	public void update(RegularOrder arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_regorg", Integer.toString(arg.getIdRegord()));
		parameters.put("id_client", Integer.toString(arg.getIdClient()));
		parameters.put("price", Integer.toString(arg.getPrice()));
		parameters.put("count_of_months", Integer.toString(arg.getCountOfMonth()));
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteByIdOrder(int id){
		jdbcTemplate.update(sqlDeleteById + id);
	}

	public void create(RegularOrder arg) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_regord", String.valueOf(arg.getIdRegord()));
		parameters.put("id_client", String.valueOf(arg.getIdClient()));
		parameters.put("count_of_goods", String.valueOf(arg.getCountOfGoods()));
		parameters.put("price", String.valueOf(arg.getPrice()));
		parameters.put("count_of_months", String.valueOf(arg.getCountOfMonth()));
		namedParameterJdbcTemplate.update(sqlCreate,parameters);
	}

	public List<RegularOrder> findByIdClient(Integer userId) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(userId));
		return namedParameterJdbcTemplate.query(sqlByClientId, parameters, rowMapper2);
	}

}
