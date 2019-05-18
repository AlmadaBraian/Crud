package testeo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection conexion = BaseDatos.crearConexion();
			eliminar(conexion);
			seleccionarElemento(conexion);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void eliminar(Connection conexion) throws SQLException {
		String comando = "Delete from persona where nombre=?";
		PreparedStatement declaracion = conexion.prepareStatement(comando);
		declaracion.setString(1,"Emanuel");
		int resultado = declaracion.executeUpdate();
		if(resultado>0) {
			JOptionPane.showMessageDialog(null, "Seha eliminado correctamente");
		}
		
	}
	
	static void insertar(Connection conexion) throws SQLException {
		String comando = " Insert into persona (idpersona,nombre, apellido, fecha_nac, sexo) values (?,?,?,?,?)";
		
		PreparedStatement declaracion = conexion.prepareStatement(comando);
		declaracion.setInt(1, 5);
		declaracion.setString(2, "Emanuel");
		declaracion.setString(3, "Gallardo");
		declaracion.setDate(4, new Date(93, 10, 14));
		declaracion.setInt(5, 1);
		
		int registro = declaracion.executeUpdate();
		
		if (registro>0) {
			JOptionPane.showMessageDialog(null, "Se insetro correctamente");
		}
	}
	
	static void seleccionarElemento(Connection conexion) throws SQLException {
		Statement ejecutor = conexion.createStatement();
		ResultSet respuesta = ejecutor.executeQuery("select * from persona");
		String texto = "";
		
		while (respuesta.next()) {
			texto += respuesta.getString("nombre") + " " +  respuesta.getString("apellido")+"\n";
		}
		JOptionPane.showMessageDialog(null, texto);
	}
	
	static void update(Connection conexion) throws SQLException {
		String comando = " Update persona set apellido=? where nombre=?";
		PreparedStatement declaracion = conexion.prepareStatement(comando);
		declaracion.setString(1,"Olivares");
		declaracion.setString(2,"Juan");
		int resultado=declaracion.executeUpdate();
		if(resultado>0) {
			JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente");
		}
	}

}
