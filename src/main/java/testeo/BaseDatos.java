package testeo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
	 static final String URL = "jdbc:postgresql://localhost:5432/Crud"; 
	 static final String USER = "postgres"; 
	 static final String PASS = "ComproMiso15"; 
	 
	 public static Connection crearConexion()
			throws ClassNotFoundException, SQLException{ Class.forName("org.postgresql.Driver"); 
			 Connection conexion = DriverManager.getConnection(URL, USER, PASS); 
			 if (conexion != null){ 
				 System.out.print("Conexion establecida...");
				 return conexion; 
			} 
			 return null; }
				 
} 
	 




