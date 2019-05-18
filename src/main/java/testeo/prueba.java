package testeo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection conexion = BaseDatos.crearConexion();
			//insertar(conexion,"persona"); 
			//eliminar(conexion,"persona","nombre","Alfredo");
			mostrarElemento(conexion,"persona");
			update(conexion,"persona", "nombre", "idpersona", 1234,  "Esteban" );
			mostrarElemento(conexion,"persona");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void eliminar(Connection conexion,String tabla,String columna, String string) throws SQLException {
		String comando = "Delete from " +tabla+ " where "+columna+"=?";
		PreparedStatement declaracion = conexion.prepareStatement(comando);
		declaracion.setString(1,string);
		int resultado = declaracion.executeUpdate();
		if(resultado>0) {
			JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
		}
		
	}
	
	@SuppressWarnings("deprecation")
	static void insertar(Connection conexion,String tabla) throws SQLException {
		String columnas="";
		String values="";
		String[] tipos = null;
		String[] col = null;
		String[] fecha = {"anio","mes","dia"};
		
		if(tabla.equals("persona")) {
			columnas="(idpersona, nombre, apellido, fecha_nac, sexo)";
			values="(?,?,?,?,?)";
			String []tip= {"int","string","string","date","boolean"};
			String []colu= {"idpersona","nombre","apellido","fecha_nac","sexo"};
			
			tipos=tip;
			col=colu;
		}
		String comando = " Insert into "+tabla+" "+columnas+" values "+values;
		
		PreparedStatement declaracion = conexion.prepareStatement(comando);
		Scanner lector = new Scanner(System.in);
		
		for (int i=0; i<tipos.length;i++) {
			if (tipos[i].equals("int")) {
				System.out.println("inserte valor numerico para "+col[i]);
				declaracion.setInt(i+1,lector.nextInt());
			}else if (tipos[i].equals("string")) {
				//String cosa=lector.nextLine();
				System.out.println("inserte valor tipo texto para "+col[i]);
				
				declaracion.setString(i+1,lector.next());
			}
			else if (tipos[i].equals("date")) {
				int anio=90,mes=1,dia=1;
				
				for(int j=0;j<3;j++) {
					System.out.println("inserte valor numerico para "+col[i]);
					System.out.println(fecha[j]);
					int numero=lector.nextInt();
					if(j==0) {
						anio=numero;
					}if(j==1) {
						mes=numero-1;
					}else {
						dia=numero;
					}
				}
				declaracion.setDate(i+1,new Date(anio,mes,dia));
			}
			else if(tipos[i].equals("boolean")) {
				boolean b=false;
				System.out.println("inserte valor tipo texto para "+col[i]);
				String valor=lector.next();
				declaracion.setString(i+1,valor);
				if(valor.equalsIgnoreCase("m") || valor.equalsIgnoreCase("masculino")) {
					b=true;
				}else {
					b=false;
				}
				declaracion.setBoolean(i+1,b);
			}

		}
		lector.close();
		declaracion.executeUpdate();
		return;

	}
	
	static void mostrarElemento(Connection conexion,String tabla) throws SQLException {
		Statement ejecutor = conexion.createStatement();
		ResultSet respuesta = ejecutor.executeQuery("select * from "+tabla);
		String texto = "";
		
		while (respuesta.next()) {
			texto += respuesta.getString("nombre") + " " +  respuesta.getString("apellido")+"\n";
		}
		JOptionPane.showMessageDialog(null, texto);
	}
	
	static void update(Connection conexion,String tabla, String columna1, String columna2, int valorAmodificar, String nuevoValor ) throws SQLException {
		
		String comando = " Update "+tabla+" set "+columna1+"=? where "+columna2+"=?";
		PreparedStatement declaracion = conexion.prepareStatement(comando);
		declaracion.setString(1,nuevoValor);
		declaracion.setInt(2,valorAmodificar);
		int resultado=declaracion.executeUpdate();
		if(resultado>0) {
			JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente");
		}
	}

}
