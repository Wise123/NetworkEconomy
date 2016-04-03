package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import tables.RegularOrder;


public class RegularOrdersDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select * from regular_orders";
	String sqlById = "select * from regular_orders where id_regord=:id_regord";
	
	String sqlUpdateById = "update regular_orders set "
			+ "id_client=:id_client, count_of_goods=:count_of_goods, name=:name, price=:price, count_of_months=:count_of_months"
			+ "where id_regord=:id_regord";
	
	String sqlDeleteById = "delete from regular_orders where id_regord=:id_regord";
	
	public RegularOrdersDao(DriverManagerDataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<RegularOrder> rowMapper = new RowMapper<RegularOrder>(){
		@Override
		public RegularOrder mapRow(ResultSet rs, int rownumber) throws SQLException{
			RegularOrder temp=new RegularOrder();
			temp.setIdRegord(rs.getInt("id_regord"));
			temp.setIdClient(rs.getInt("id_client"));
			temp.setCountOfGoods(rs.getInt("count_of_goods"));
			temp.setName(rs.getString("name"));
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
	
	public RegularOrder deleteByIdOrder(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_regorg", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlDeleteById, parameters, rowMapper);
	}
}
