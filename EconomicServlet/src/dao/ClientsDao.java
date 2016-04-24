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

import tables.Client;



public class ClientsDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String insertCl = "insert into clients (id_client, name, lastname, surname, city, country, post_index, password, isadmin) values (:id_client, :name, :lastname, :surname, :city, :country, :post_index, :password, :isadmin)";


	
	String sqlSelectAll = "select * from clients";
	String sqlById = "select * from clients where id_client=:id_client";
	
	String sqlUpdateById = "update clients set "
			+ "id_admin=:id_admin, name=:name, "
			+ "lastname=:lastname, surname=:surname, "
			+ "city=:city, country=:country, "
			+ "post_index=:post_index, password=:password "
			+ "isadmin=:isadmin"
			+ "where id_client=:id_client";
	
	String sqlUpdatePassById = "update clients set password=:password where id_client=:id_client";
	
	String sqlDeleteById = "delete from clients where id_client=:id_client";
	
	String sqlFindByNameAndPass = "select * from clients where name=:name and password=:password";
	
	public ClientsDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Client> rowMapper = new RowMapper<Client>(){
		@Override
		public Client mapRow(ResultSet rs, int rownumber) throws SQLException{
			Client temp=new Client();
			temp.setIdClient(rs.getInt("id_client"));
			temp.setName(rs.getString("name"));
			temp.setLastName(rs.getString("lastname"));
			temp.setSurname(rs.getString("surname"));
			temp.setCity(rs.getString("city"));
			temp.setCountry(rs.getString("country"));
			temp.setPostIndex(rs.getInt("post_index"));
			temp.setPassword(rs.getString("password"));
			temp.setAdmin(rs.getBoolean("isadmin"));
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
		parameters.put("id_client", Integer.toString(arg.getIdClient()));
		parameters.put("name", arg.getName());
		parameters.put("lastname", arg.getLastName());
		parameters.put("surname", arg.getSurname());
		parameters.put("city", arg.getCity());
		parameters.put("country", arg.getCountry());
		parameters.put("post_index", Integer.toString(arg.getPostIndex()));
		parameters.put("password", arg.getPassword());
		parameters.put("isadmin", Boolean.toString(arg.isAdmin()));
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(id));
		namedParameterJdbcTemplate.update(sqlDeleteById, parameters);
	}

	public Client findByNameAndPass(String login, String password) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("name", login);
		parameters.put("password", password);
		return namedParameterJdbcTemplate.queryForObject(sqlFindByNameAndPass, parameters, rowMapper);
	}

	public int updatePass(Integer id, String newPassword) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(id));
		parameters.put("password", newPassword);
		return namedParameterJdbcTemplate.update(sqlUpdatePassById, parameters);
	}

	public void create(Client arg) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(arg.getIdClient()));
		parameters.put("name", arg.getName());
		parameters.put("lastname", arg.getLastName());
		parameters.put("surname", arg.getSurname());
		parameters.put("city", arg.getCity());
		parameters.put("country", arg.getCountry());
		parameters.put("post_index", Integer.toString(arg.getPostIndex()));
		parameters.put("password", arg.getPassword());
		parameters.put("isadmin", Boolean.toString(arg.isAdmin()));
		namedParameterJdbcTemplate.update(insertCl, parameters);
	}
}
