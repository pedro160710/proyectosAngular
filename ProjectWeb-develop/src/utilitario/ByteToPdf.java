package utilitario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ByteToPdf {
	
		public static void byteArrayToFile(byte[] bArray) {  
		    try {  
		        // Create file  
		        FileWriter fstream = new FileWriter(System.getProperty("user.dir")+File.separator+"reporte.pdf");  
		        BufferedWriter out = new BufferedWriter(fstream);  
		        for (Byte b: bArray) {  
		            out.write(b);  
		        }  
		        out.close();  
		    } catch (Exception e) {  
		        System.err.println("Error: " + e.getMessage());  
		    }  
		}
		


}
