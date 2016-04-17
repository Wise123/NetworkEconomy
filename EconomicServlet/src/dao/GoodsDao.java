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
import tables.Good;

public class GoodsDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	String sqlCreate = "INSERT INTO goods(id_good, id_provider, name, price, description, category, count_on_stock, image_path) VALUES (:id_good, :id_provider, :name, :price, :description, :category, :count_on_stock, :image_path)";
	
	String sqlSelectAll = "select * from goods";
	String sqlByCategory = "select * from goods where category=:category";
	String sqlById = "select * from goods where id_good=:id_good";
	String sqlSelectAllCategories = "select category from goods group by category";
	
	
	String sqlSelectAllWithProviderName = "select goods.id_good, providers.title as id_provider, goods.name,goods.price,goods.description,goods.category,goods.count_on_stock,goods.image_path from goods inner join providers on goods.id_good=providers.id_provider";
	String sqlSelectByCategoryWithProviderName = "select goods.id_good, providers.title as id_provider, goods.name,goods.price,goods.description,goods.category,goods.count_on_stock,goods.image_path from goods inner join providers on goods.id_good=providers.id_provider where goods.category=:category";
	
	
	String sqlUpdateById = "update goods set " +
			"id_provider=:id_provider, name=:name, "
			+ "price=:price, description=:description, "
			+ "category=:category, count_on_stock=:count_on_stock, image_path=:image_path "
			+ "where id_good=:id_good";
	
	String sqlDeleteById = "delete from goods where id_good=:id_good";
	
	String sqlFindGoodsByOrderId = "select * from goods where id_good in (select id_good from order_good where id_order=:id_order)";
	
	public GoodsDao(DataSource dataSource){
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	RowMapper<Good> rowMapper = new RowMapper<Good>(){
		@Override
		public Good mapRow(ResultSet rs, int rownumber) throws SQLException{
			Good temp=new Good();
			temp.setIdGood(rs.getInt("id_good"));
			temp.setIdProvider(rs.getString("id_provider"));
			temp.setName(rs.getString("name"));
			temp.setPrice(rs.getInt("price"));
			temp.setDescription(rs.getString("description"));
			temp.setCategory(rs.getString("category"));
			temp.setCountOnStock(rs.getInt("count_on_stock"));
			temp.setImagePath(rs.getString("image_path"));
			return temp;
		}
	};
	
	
	
	public List<Good> findAll(){
		return jdbcTemplate.query(sqlSelectAll, rowMapper);
	}
	
	public List<Good> findGoodsByOrderId(String orderId){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_order", orderId);
		return namedParameterJdbcTemplate.query(sqlFindGoodsByOrderId,parameters, rowMapper);
	}
	
	public List<Good> findAllWithProviderName(){
		return jdbcTemplate.query(sqlSelectAllWithProviderName, rowMapper);
	}
	
	
	public List<Good> findByCategory(String category){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("category", category);
		return namedParameterJdbcTemplate.query(sqlByCategory, parameters, rowMapper);
	}
	
	public Good findById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_good", Integer.toString(id));
		return namedParameterJdbcTemplate.queryForObject(sqlById, parameters, rowMapper);
	}
	
	
	
	public void update(Good arg){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_provider", arg.getIdProvider());
		parameters.put("name", arg.getName());
		parameters.put("price", Integer.toString(arg.getPrice()));
		parameters.put("description", arg.getDescription());
		parameters.put("category", arg.getCategory());
		parameters.put("count_on_stock", Integer.toString(arg.getCountOnStock()));
		parameters.put("image_path", arg.getImagePath());
		parameters.put("id_good", Integer.toString(arg.getIdGood()));
		namedParameterJdbcTemplate.update(sqlUpdateById, parameters);
	}
	
	public void deleteById(int id){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_good", Integer.toString(id));
		namedParameterJdbcTemplate.update(sqlDeleteById, parameters);
	}
	
	public String[] selectAllCategories(){
		List<Map<String, Object>> temp =  jdbcTemplate.queryForList(sqlSelectAllCategories);
		String[] ans = new String[temp.size()];
		for (Map<String, Object> i : temp){
			ans[temp.indexOf(i)] = (String) i.get("category");
		}
		return ans;
	}
	
	public List<Good> findByCategoryWithProviderName(String category){
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("category", category);
		return namedParameterJdbcTemplate.query(sqlSelectByCategoryWithProviderName, parameters, rowMapper);
	}

	public void create(Good arg) {
		Map <String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("id_provider", arg.getIdProvider());
		parameters.put("name", arg.getName());
		parameters.put("price", Integer.toString(arg.getPrice()));
		parameters.put("description", arg.getDescription());
		parameters.put("category", arg.getCategory());
		parameters.put("count_on_stock", Integer.toString(arg.getCountOnStock()));
		parameters.put("image_path", arg.getImagePath());
		parameters.put("id_good", Integer.toString(arg.getIdGood()));
		namedParameterJdbcTemplate.update(sqlCreate,parameters);
	}
	
}
