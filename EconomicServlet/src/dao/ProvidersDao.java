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

import tables.Provider;


public class ProvidersDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select * from providers";
	String sqlById = "select * from providers where id_provider=:id_provider";
	
	String sqlUpdateById = "update providers set "
			+ "address=:address, title=:title, description=:description"
			+ "where id_provider=:id_provider";
	
	String sqlDeleteById = "delete from providers where id_provider=:id_provider";
	
	public ProvidersDao(DriverManagerDataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Provider> rowMapper = new RowMapper<Provider>(){
		@Override
		public Provider mapRow(ResultSet rs, int rownumber) throws SQLException{
			Provider temp=new Provider();
			temp.setIdProvider(rs.getInt("id_provider"));
			temp.setAddress(rs.getString("address"));
			temp.setTitle(rs.getString("title"));
			temp.setDescription(rs.getString("description"));
			return temp;
		}
	};
	
	public List<Provider> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	public List<Provider> findByIdClient(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_provider", Integer.toString(id));
		return namedParameterJdbcTemplate.query(sqlById, parameters, rowMapper);
	}
	
	public Provider findByIdOrder(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_provider", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlById, parameters, rowMapper);
	}
	
	public void update(Provider arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_provider", Integer.toString(arg.getIdProvider()));
		parameters.put("address", arg.getAddress());
		parameters.put("title", arg.getTitle());
		parameters.put("desctiption", arg.getDescription());
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public Provider deleteByIdOrder(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_provider", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlDeleteById, parameters, rowMapper);
	}
}
