package practica2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.common.AbstractNetworkParams;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

public class Server {
	
	public static void main(String[] args) throws Exception {
		
		
		
		
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		Socket s = null;
		ServerSocket ss = new ServerSocket(5432);
		int suma_total = 0;
		ArrayList <ArrayList<String>> listaPC = new ArrayList <ArrayList<String>>();
		listaPC.add(SisInfo());
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
				
				ArrayList <String> pcInfo = new ArrayList <String>();
				//ArrayList<Integer> lista_numeros = new ArrayList <Integer>();
				pcInfo = (ArrayList<String>) ois.readObject();
				listaPC.add(pcInfo);
				//suma_total = suma(lista_numeros);
	// armo el saludo personalizado que le quiero enviar
				//String saludo = "Hola Mundo ("+nom+") _ "+System.currentTimeMillis();
				//String saludo = "La suma de los numeros es: " +String.valueOf(suma_total);
				//String saludo = listaPC.toString();
	// envio el saludo al cliente
				//oos.writeObject(saludo);
				//System.out.println("Saludo enviado...");
				
				if(listaPC.size() >= 3) {
					//System.out.println(generateRows(listaPC));
					//System.out.println(listaPC.get(2).get(6));
					Table table = new Table(generateRows(listaPC));
					table.showTable(table);
					
				}
				//System.out.println(listaPC.toString());
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
	
    public static String[][] generateRows (ArrayList <ArrayList<String>> arrList) {
    	
    	String [][] rowsGenerated = new String [3][7];
    	
    	for(int i=0;i < 3; i++) {
    		//ArrayList sublist = arrList.get(i);
    		for(int j=0;j < 7; j++) {
    			rowsGenerated[i][j] = arrList.get(i).get(j).toString();
    		}
    	}
    	//System.out.println(rowsGenerated.toString());
    	System.out.println(Arrays.deepToString(rowsGenerated));
    	return rowsGenerated;
    }
	
    static ArrayList<String> SisInfo () {
		
		SystemInfo systemInfo = new SystemInfo();
		
		ArrayList <String> systemSpecs = new ArrayList<String> ();
		
		OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
		HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
		GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
		FileSystem fileSystem = operatingSystem.getFileSystem();
		AbstractNetworkParams abstractNetworkParams = new AbstractNetworkParams() {
			
			@Override
			public String getIpv6DefaultGateway() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getIpv4DefaultGateway() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		
		List<HWDiskStore> hdds = hardwareAbstractionLayer.getDiskStores();
		List <OSFileStore> fileStores = fileSystem.getFileStores();
		
		String name = abstractNetworkParams.getHostName();
		String processorIdentifier = centralProcessor.getProcessorIdentifier().getName();
		String processorModel = centralProcessor.getProcessorIdentifier().getModel();
		String osFamily = operatingSystem.getFamily();
		String model = centralProcessor.getProcessorIdentifier().getModel();
		
		Long hddSize =  hdds.get(0).getSize()/1073741924;
		Long freeSpace = fileStores.get(0).getFreeSpace()/1073741924;
		Float cpuFreq = (float) centralProcessor.getMaxFreq();
		Float ramMem = (float) globalMemory.getTotal()/1073741924;
		
		String cpuVel = cpuFreq.toString().substring(0,4)+" GHz";
		String totRam = ramMem.toString().substring(0,4) + " GB";
		String hddTot = hddSize +" GB";
		String hddAvailable = freeSpace+" GB";
		
//		System.out.println("S.O: "+osFamily+
//				"\nProcesador: "+processorIdentifier + 
//				"\nVelocidad Procesador: "+cpuFreq.toString().substring(0,4)+" Ghz"+
//				"\nMemoriaRam: "+ramMem.toString().substring(0,4) + " GB"+
//				"\nHDD: "+hddSize +" GB"+
//				"\nHDD Espacio Disponible: "+freeSpace+" GB");
		systemSpecs.add(name);
		systemSpecs.add(processorIdentifier);
		systemSpecs.add(cpuVel);
		systemSpecs.add(totRam);
		systemSpecs.add(hddTot);
		systemSpecs.add(hddAvailable);
		systemSpecs.add(osFamily);
		
		return systemSpecs;
		
	}

}
