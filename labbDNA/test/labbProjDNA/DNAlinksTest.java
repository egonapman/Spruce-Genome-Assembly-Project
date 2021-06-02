package labbProjDNA;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

public class DNAlinksTest {


	@Test
	public void testEnkeltExempel() throws Exception {
		
		
		DNAlinks links=new DNAlinks();
		
		
		//component 1
		links.add("Egon","Maya");
		links.add("Egon","Fabian");
		links.add("Egon","Freya");
		links.add("Egon","Emma");
		links.add("Egon","Björn");
		links.add("Egon","Elliot");
		links.add("Maya","Fabian");
		
		
		//component 2
		links.add("johan", "kalle");
		links.add("johan", "marie");
		links.add("johan", "hasse");
		links.add("johan", "nisse");
		links.add("nisse", "mia");
		
		
		links.add("Apa", "Banan");
		links.add("Apa","Kokosnöt");
		links.add("Elefant", "Banan");
		links.add("Lejon", "Apa");
		links.add("Krokodil", "Lejon");
		

		Map<ContigKey, NeighbourContigsAndComponent> dnaList = links.getDnalinks();
		assertTrue(dnaList.get(new ContigKey("johan")).getNeigbours().size()==4);
		assertTrue(dnaList.get(new ContigKey("Egon")).getNeigbours().size()==6);

		links.setKomponent();
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
		links.show();	
		

		
	}
	
	
	
	
	
	@Test
	public void testOnBigDataSet() throws Exception {
		
		
		DNAlinks links=new DNAlinks();
		
		File file = new File("C:\\temp\\scripts\\UniqueEdges.txt");
		
		Scanner sc = new Scanner(file);
		int i = 0;
		//links.add("contig_1004738", "contig_1006287");
		while(sc.hasNextLine() && i < 1700000) {

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
		links.setKomponent();
		links.show();
		sc.close();

		
	}
	
	
	
	
}
