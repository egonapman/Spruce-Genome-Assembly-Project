package labbProjDNA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Graph {
	
	public Graph() throws IOException {

		File fil =new File ("C:\\temp\\Spruce_fingerprint_2017-03-10_16.48.olp.m4");

		if(fil.canRead()) {
			
			FileWriter fw = new FileWriter("C:\\temp\\edges.txt");
			Scanner myReader = new Scanner(fil);
			int i = 0;
			
			double potentialEdges = 100;
			double edges = 0;

			while (myReader.hasNextLine() &&  i < potentialEdges) {
				
				i++;
				
				String line = myReader.nextLine();
				
				String[] data = line.split("\t"); 
				

				int Start_of_overlap_in_first_contig = Integer.parseInt(data[5]);
				int End_of_overlap_in_first_contig = Integer.parseInt(data[6]);
				int Length_of_first_contig = Integer.parseInt(data[7]);

				int Start_of_overlap_in_second_contig = Integer.parseInt(data[9]);
				int End_of_overlap_in_second_contig = Integer.parseInt(data[10]);
				int Length_of_second_contig = Integer.parseInt(data[11]);

				if((End_of_overlap_in_first_contig - Start_of_overlap_in_first_contig) == Length_of_second_contig) {
					
					System.out.println(line + " CONTAINED");
					
				}else if((End_of_overlap_in_second_contig - Start_of_overlap_in_second_contig) == Length_of_first_contig){
					System.out.println(line + " CONTAINED*");
				}else {
					edges++;
					fw.write(line +"\n");
					
				}
				
			}
			
			double ratio = edges/potentialEdges;
			System.out.println("Edges: "+ edges);
			System.out.println(ratio + "% are contaiend");
			myReader.close();
			fw.close();
			
		}
		
	}

}

	


