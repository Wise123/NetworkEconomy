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

import tables.Client;



public class ClientsDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select * from clients";
	String sqlById = "select * from clients where id_client=:id_client";
	
	String sqlUpdateById = "update clients set "
			+ "id_admin=:id_admin, name=:name, "
			+ "last_name=:last_name, surname=:surname, "
			+ "city=:city, country=:country, "
			+ "post_index=:post_index, password=:password "
			+ "where id_client=:id_client";
	
	String sqlDeleteById = "delete from clients where id_client=:id_client";
	
	public ClientsDao(DriverManagerDataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Client> rowMapper = new RowMapper<Client>(){
		@Override
		public Client mapRow(ResultSet rs, int rownumber) throws SQLException{
			Client temp=new Client();
			temp.setIdClient(rs.getInt("id_client"));
			temp.setIdAdmin(rs.getInt("id_admin"));
			temp.setName(rs.getString("name"));
			temp.setLastName(rs.getString("last_name"));
			temp.setSurName(rs.getString("surname"));
			temp.setCity(rs.getString("city"));
			temp.setCountry(rs.getString("country"));
			temp.setPostIndex(rs.getInt("post_index"));
			temp.setPassword(rs.getInt("password"));
			return temp;
		}
	};
	
	
	
	public List<Client> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	public Client findById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlById, parameters, rowMapper);
	}
	
	
	public void update(Client arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_cleint", Integer.toString(arg.getIdClient()));
		parameters.put("id_admin", Integer.toString(arg.getIdAdmin()));
		parameters.put("name", arg.getName());
		parameters.put("last_name", arg.getLastName());
		parameters.put("surname", arg.getSurName());
		parameters.put("city", arg.getCity());
		parameters.put("country", arg.getCountry());
		parameters.put("post_index", Integer.toString(arg.getPostIndex()));
		parameters.put("password", Integer.toString(arg.getPassword()));
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(id));
		namedParameterJdbcTemplate.update(sqlDeleteById, parameters);
	}
}
