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

import tables.Admin;


public class AdminsDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select * from admins";
	String sqlById = "select * from admins where id_admin=:id_admin";
	
	String sqlUpdateById = "update admins set "
			+ "login=:login, "
			+ "password=:password, email=:email, "
			+ "where id_admin=:id_admin";
	
	String sqlDeleteById = "delete from admins where id_admin=:id_admin";
	
	public AdminsDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Admin> rowMapper = new RowMapper<Admin>(){
		@Override
		public Admin mapRow(ResultSet rs, int rownumber) throws SQLException{
			Admin temp=new Admin();
			temp.setIdAdmin(rs.getInt("id_admin"));
			temp.setLogin(rs.getString("login"));
			temp.setPassword(rs.getInt("password"));
			temp.setEmail(rs.getString("email"));
			return temp;
		}
	};
	
	
	
	public List<Admin> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	public Admin findById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_admin", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlById, parameters, rowMapper);
	}
	
	
	public void update(Admin arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_admin", Integer.toString(arg.getIdAdmin()));
		parameters.put("login", arg.getLogin());
		parameters.put("password", Integer.toString(arg.getPassword()));
		parameters.put("email", arg.getEmail());
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_admin", Integer.toString(id));
		namedParameterJdbcTemplate.update(sqlDeleteById, parameters);
	}
}
