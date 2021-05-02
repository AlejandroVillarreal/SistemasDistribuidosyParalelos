package practica2;


import java.util.List;

//-------IMPORTS---------------------------------
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.common.AbstractOSFileStore;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Constants;
//-------IMPORTS---------------------------------

public class Pruebas {
	
	
	public static void main(String[] args) {
		
		SystemInfo systemInfo = new SystemInfo();
		
		OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
		HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
		GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
		FileSystem fileSystem = operatingSystem.getFileSystem();
		
		List<HWDiskStore> hdds = hardwareAbstractionLayer.getDiskStores();
		List <OSFileStore> fileStores = fileSystem.getFileStores();
		
		String processorIdentifier = centralProcessor.getProcessorIdentifier().getName();
		String processorModel = centralProcessor.getProcessorIdentifier().getModel();
		String osFamily = operatingSystem.getFamily();
		String model = centralProcessor.getProcessorIdentifier().getModel();
		
		Long hddSize =  hdds.get(0).getSize()/1073741924;
		Long freeSpace = fileStores.get(0).getFreeSpace()/1073741924;
		Float cpuFreq = (float) centralProcessor.getMaxFreq();
		Float ramMem = (float) globalMemory.getTotal()/1073741924;
		System.out.println("S.O: "+osFamily+
				"\nProcesador: "+processorIdentifier + 
				"\nVelocidad Procesador: "+cpuFreq.toString().substring(0,4)+" Ghz"+
				"\nMemoriaRam: "+ramMem.toString().substring(0,4) + " GB"+
				"\nHDD: "+hddSize +" GB"+
				"\nHDD Espacio Disponible: "+freeSpace+" GB");
	}

}
