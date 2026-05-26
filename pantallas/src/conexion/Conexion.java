package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	 Connection conectar = null;
    String usuario = "postgres.iqvjcgyvapevekvvweyf";
    String contraseña ="POO_2026COMBIS";
    String bd = "postgres";
    String ip ="aws-1-us-west-2.pooler.supabase.com";
    String puerto = "5432";
    String cadena ="jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;

	    public Connection conectar() {

	        try {

	            Class.forName("org.postgresql.Driver");

	            conectar = DriverManager.getConnection(cadena,usuario,contraseña );

	        } catch (Exception e) {
	        	
				System.out.println(e);

	        	
	        }

	        return conectar;
	    }
}
