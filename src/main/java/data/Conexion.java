package data;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/com23555final?useSSL=false&useTimeZone=UTC&allowPublicKeyRetrieval=true";
   private static final String JDBC_USER = "root";
   private static final String JDBC_PASS = "Florista1909"; 
    
    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASS);
        
        ds.setInitialSize(100); 
        System.out.println(ds);
        System.out.println("dsStatus");
        return   ds;
    }
    
    public static Connection getConexion()throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace(System.out);
        }
            return getDataSource().getConnection();
        }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement st) throws SQLException{
        st.close();
    }
    
    public static void close(Connection cn) throws SQLException{
        cn.close();
    }
}
