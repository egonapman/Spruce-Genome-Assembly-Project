package labbProjDNA;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DnaStarter {
	
	
	
	public static void main(String[] args) throws IOException{
			
			
		GraphCalculator links=new GraphCalculator();
			
		File file = new File("C:\\temp\\scripts\\UniqueEdges.txt");
			
		Scanner sc = new Scanner(file);
		int i = 0;
		//links.add("contig_1004738", "contig_1006287");
		while(sc.hasNextLine()) {

			if (i%100000==0) {
					System.out.println(i);
				}
			
				String line = sc.nextLine();
				
				String[] data = line.split(" "); 
				
				links.add(data[0], data[1]);
				//System.out.println(data[0] + " " + data[1]);
				i++;
				
			}
			
			System.out.println("start analysing komp");
			links.assembleContigKeyIntoComponents();
			
			links.setDegreeDistribution();
			
			links.setComponentSizeDistribution();
			
			links.printDegreeDistubition("C:\\temp\\NodeDegreeDistrubition.txt");
			
			links.printComponentSizeDistrubution("C:\\temp\\ComponentSizeDistrubtion.txt");

			sc.close();

			
		}


	

}
