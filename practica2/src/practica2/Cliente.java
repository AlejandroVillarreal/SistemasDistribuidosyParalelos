package practica2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.common.AbstractNetworkParams;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

public class Cliente {
	
	
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
	
	
	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket s = null;
		try {
		// instancio el server con la IP y el PORT
			s = new Socket("25.6.241.75",5432);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		// envio un nombre
			//oos.writeObject("Pablo");
			//System.out.println(readFile());
			oos.writeObject(SisInfo());
			System.out.println(SisInfo());
		// recibo la respuesta (el saludo personalizado)
			//String ret = (String)ois.readObject();
		// muestro la respuesta que envio el server
			System.out.println("Informacion enviada al cliente!");
			}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if( ois != null ) ois.close();
			if( oos != null ) oos.close();
			if( s != null ) s.close();
		}
		
	}

}
