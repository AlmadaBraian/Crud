package testeo;

import java.sql.Connection;
import java.sql.SQLException;

public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con = BaseDatos.crearConexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
