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

import tables.Card;

public class CardsDao {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlSelectAll = "select * from cards";
	String sqlById = "select * from cards where id_card=:id_card";
	
	String sqlByClientId = "select * from cards where id_client=:id_client";
	
	String sqlUpdateById = "update cards set " +
			"id_client=:id_client, number=:number, "
			+ "year_of_end=:year_of_end "
			+ "where id_card=:id_card";
	
	String sqlDeleteById = "delete from cards where id_card=:id_card";
	
	public CardsDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Card> rowMapper = new RowMapper<Card>(){
		@Override
		public Card mapRow(ResultSet rs, int rownumber) throws SQLException{
			Card temp=new Card();
			temp.setIdCard(rs.getInt("id_card"));
			temp.setIdClient(rs.getInt("id_client"));
			temp.setNumber(rs.getInt("number"));
			temp.setYearOfEnd(rs.getInt("year_of_end"));
			return temp;
		}
	};
	
	
	
	public List<Card> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	public Card findById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_card", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlById, parameters, rowMapper);
	}
	
	public Card findByClientId(int id_client){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_client", Integer.toString(id_client));
		return namedParameterJdbcTemplate.queryForObject(sqlByClientId, parameters, rowMapper);
	}
	
	
	
	public void update(Card arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_card", Integer.toString(arg.getIdCard()));
		parameters.put("id_client", Integer.toString(arg.getIdClient()));
		parameters.put("number", Integer.toString(arg.getNumber()));
		parameters.put("year_of_end", Integer.toString(arg.getYearOfEnd()));
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_card", Integer.toString(id));
		namedParameterJdbcTemplate.update(sqlDeleteById, parameters);
	}
}
