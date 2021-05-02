package practica2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static void main(String[] args) throws Exception {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		int suma_total = 0;
		while( true ) {
			try {
	// el ServerSocket me da el Socket
				s = ss.accept();
	// informacion en la consola
				System.out.println("Se conectaron desde la IP: "+s.getInetAddress());
	// enmascaro la entrada y salida de bytes
				ois = new ObjectInputStream( s.getInputStream() );
				oos = new ObjectOutputStream( s.getOutputStream() );
	// leo el nombre que envia el cliente
				//String nom = (String)ois.readObject();
				ArrayList<String> listaPC = new ArrayList <String>();
				//ArrayList<Integer> lista_numeros = new ArrayList <Integer>();
				listaPC = (ArrayList<String>) ois.readObject();
				//suma_total = suma(lista_numeros);
	// armo el saludo personalizado que le quiero enviar
				//String saludo = "Hola Mundo ("+nom+") _ "+System.currentTimeMillis();
				//String saludo = "La suma de los numeros es: " +String.valueOf(suma_total);
				//String saludo = listaPC.toString();
	// envio el saludo al cliente
				//oos.writeObject(saludo);
				//System.out.println("Saludo enviado...");
				System.out.println(listaPC.toString());
				}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				if( oos !=null ) oos.close();
				if( ois !=null ) ois.close();
				if( s != null ) s.close();
				System.out.println("Conexion cerrada!");
			}
		}
	}

}
