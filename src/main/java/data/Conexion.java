package data;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/libros?useSSL=false&useTimeZone=UTC&allowPublicKeyRetrieval=true";
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
            System.out.println("Conexión a la base de datos exitosa.");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace(System.out);
            System.out.println("Error al cargar el driver de la base de datos.");
        }
            return getDataSource().getConnection();
        }   
  
    public static void close(Connection con, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); // o manejo adecuado de la excepción
        }
    }
}
