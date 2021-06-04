package labbProjDNA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PreProcessDNA {

	public static void main(String[] args) {
		
		String inFil=args[0];  // "C:\\temp\\Spruce_fingerprint_2017-03-10_16.48.olp.m4"
		String utFil=args[1]; // "C:\\temp\\edges.txt"
		
	}
	

	public void process(String inFil, String utFil ) throws IOException {
		
		//file to read
		File fil =new File (inFil);
		if(!fil.canRead()) {
			throw new IllegalArgumentException("Kan inte läsa filen: "+ inFil);
		}


			//file to write result
			FileWriter fw = new FileWriter(utFil);
			Scanner myReader = new Scanner(fil);
			int i = 0;

			int edges = 0;
			int contaned = 0;

			while (myReader.hasNextLine()) {
				
				i++;
				
				String line = myReader.nextLine();
				
				String[] data = line.split("\t"); 
				
				if(i % 100000 == 0) {
					System.out.println("inlästa rader: "+i);
				}
				
				//bad code
				int Start_of_overlap_in_first_contig = Integer.parseInt(data[5]);
				int End_of_overlap_in_first_contig = Integer.parseInt(data[6]);
				int Length_of_first_contig = Integer.parseInt(data[7]);

				int Start_of_overlap_in_second_contig = Integer.parseInt(data[9]);
				int End_of_overlap_in_second_contig = Integer.parseInt(data[10]);
				int Length_of_second_contig = Integer.parseInt(data[11]);
				
				
				//detects contagion in pair a,b where b subseqence of a

				if((End_of_overlap_in_first_contig - Start_of_overlap_in_first_contig) == Length_of_second_contig) {
					contaned++;
				
					//detects contagion in pair a,b where a subseqence of b
				}else if((End_of_overlap_in_second_contig - Start_of_overlap_in_second_contig) == Length_of_first_contig){

					contaned++;
					//else we write it to our reult file
				}else {
					edges++;
					fw.write(line +"\n");
					
				}
				
			}

			myReader.close();
			System.out.println("totalt antal rader: " + i);
			System.out.println("antal bortagna rader: " + contaned);
			System.out.println("kanter: " + edges);
			fw.close();
			
	}
	
	

	
	
}
