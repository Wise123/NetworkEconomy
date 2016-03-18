package controllers;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.h2.Driver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

 

@Controller
public class PagesController {
	@RequestMapping("/index")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse response) {
		
		 try
	        {
	            Class.forName("org.h2.Driver");
	            Connection con = DriverManager.getConnection("jdbc:h2:/WEB-INF/h2", "test", "" );
	            Statement stmt = con.createStatement();
	            //stmt.executeUpdate( "DROP TABLE table1" );
	            //stmt.execute( "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES" );
	            //stmt.executeUpdate( "INSERT INTO table1 ( user ) VALUES ( 'Claudio' )" );
	            //stmt.executeUpdate( "INSERT INTO table1 ( user ) VALUES ( 'Bernasconi' )" );
	 
	            ResultSet rs = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES");
	            while( rs.next() )
	            {
	                String name = rs.getString("TABLE_NAME");
	                System.out.println( name );
	            }
	            stmt.close();
	            con.close();
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace();
	        }
		
		return new ModelAndView("/WEB-INF/jsp/index.jsp");
	}
}