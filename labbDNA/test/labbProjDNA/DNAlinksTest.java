package labbProjDNA;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.junit.Test;

import com.sun.source.tree.AssertTree;

public class DNAlinksTest {


	@Test
	public void testEnkeltExempel() throws Exception {
		
		
		GraphCalculator calculator=new GraphCalculator();
		
		
		//component 1
		calculator.add("Egon","Maya");
		calculator.add("Egon","Fabian");
		calculator.add("Egon","Freya");
		calculator.add("Egon","Emma");
		calculator.add("Egon","Björn");
		calculator.add("Egon","Elliot");
		calculator.add("Maya","Fabian");
		
		
		//component 2
		calculator.add("johan", "kalle");
		calculator.add("johan", "marie");
		calculator.add("johan", "hasse");
		calculator.add("johan", "nisse");
		calculator.add("nisse", "mia");
		
		
		calculator.add("Apa", "Banan");
		calculator.add("Apa","Kokosnöt");
		calculator.add("Elefant", "Banan");
		calculator.add("Lejon", "Apa");
		calculator.add("Krokodil", "Lejon");
		

		Map<ContigKey, NeighbourContigsAndComponent> dnaList = calculator.getContigKeyDataMap();
		assertTrue(dnaList.get(new ContigKey("johan")).getNeigbours().size()==4);
		assertTrue(dnaList.get(new ContigKey("Egon")).getNeigbours().size()==6);

		calculator.assembleContigKeyIntoComponents();
		assertTrue(dnaList.get(new ContigKey("Egon")).getKomponent().equals(1));		
		assertTrue(dnaList.get(new ContigKey("Maya")).getKomponent().equals(1));		
		assertTrue(dnaList.get(new ContigKey("Fabian")).getKomponent().equals(1));		
		assertTrue(dnaList.get(new ContigKey("Freya")).getKomponent().equals(1));		
		assertTrue(dnaList.get(new ContigKey("Emma")).getKomponent().equals(1));		
		assertTrue(dnaList.get(new ContigKey("Björn")).getKomponent().equals(1));		
		
		assertTrue(dnaList.get(new ContigKey("johan")).getKomponent().equals(2));
		assertTrue(dnaList.get(new ContigKey("nisse")).getKomponent().equals(2));
		assertTrue(dnaList.get(new ContigKey("mia")).getKomponent().equals(2));
		assertTrue(dnaList.get(new ContigKey("hasse")).getKomponent().equals(2));
		
		
		calculator.assembleContigKeyIntoComponents();
		
		calculator.setDegreeDistribution();
		
		calculator.setComponentSizeDistribution();
		
		assertTrue(calculator.getNumberOfComponents() == 3);
		
		calculator.printDegreeDistubition("C:\\temp\\NodeDegreeDistrubition.test.txt");
		
		calculator.printComponentSizeDistrubution("C:\\temp\\ComponentSizeDistrubtion.test.txt");
		
	}
	

	
	
	@Test
	public void testOnBigDataSet() throws Exception {
		
		
		GraphCalculator calculator=new GraphCalculator();
		
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
			
			calculator.add(data[0], data[1]);
			//System.out.println(data[0] + " " + data[1]);
			i++;
			
		}
		
		System.out.println("start analysing komp");
		calculator.assembleContigKeyIntoComponents();
		
		calculator.setDegreeDistribution();
		
		calculator.setComponentSizeDistribution();
		
		calculator.printDegreeDistubition("C:\\temp\\NodeDegreeDistrubition.txt");
		
		calculator.printComponentSizeDistrubution("C:\\temp\\ComponentSizeDistrubtion.txt");

		sc.close();

		
	}
	
	@Test
	public void testPreprocess() throws Exception {
		
		
		new PreProcessDNA().
		process("C:\\temp\\Spruce_fingerprint_2017-03-10_16.48.olp.m4", "C:\\temp\\edges.txt");
	}
	
	
	
}
