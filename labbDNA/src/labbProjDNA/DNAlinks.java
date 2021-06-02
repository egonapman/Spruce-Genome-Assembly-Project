package labbProjDNA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DNAlinks {
	
	//maps a key (hashcoded identifier) to it's neighbours and a component attribute
	private Map<ContigKey,  NeighbourContigsAndComponent> dnalinks;
	//Maps a componentNumber to its size
	private Map<Integer,  Integer > komponentStatistik;


	public DNAlinks() {
		dnalinks= new HashMap<>();
		komponentStatistik=new HashMap<>(); 
	}
	

	//adds a element to hashmap
	//every key (identifier) maps to list of it's neighboring nodes hence the size of the list is the degree
	public void add(String iden1, String iden2 ) {
		
		//contig key runs the idenfiers through a hashfunction 
		ContigKey key1= new ContigKey(iden1);
		ContigKey key2= new ContigKey(iden2);
		int i = 1;
		
		//if first key1 does not already exist we add it to hashmap and add key2 as its neighbor
		//else just add key2 as neighbor
		
		if (!dnalinks.containsKey(key1)) {
			dnalinks.put(key1, new NeighbourContigsAndComponent(key2));
		} else {
			dnalinks.get(key1).addNeighbour(key2);
		}
		
		//same as above but add key2 with key1 as a neighbor
		if (!dnalinks.containsKey(key2)) {
			dnalinks.put(key2, new NeighbourContigsAndComponent(key1));

		} else {
			dnalinks.get(key2).addNeighbour(key1);
		}
	}
	
	public void setKomponent() {
		Integer komponentNumber=0;
		for (Entry<ContigKey, NeighbourContigsAndComponent> ent: dnalinks.entrySet()) {
			NeighbourContigsAndComponent currNeAndComp=ent.getValue();
			if (currNeAndComp.komponentAlreadyDefined()) {
				continue;
			}else {
				komponentNumber++;
				tilldelaKomponentRekursiv(komponentNumber,currNeAndComp);
			}
		}
		
		
		
	}
	
	
	private void uppdateraKomponetStatistik(Integer komponentNumber) {
		if(komponentStatistik.containsKey(komponentNumber)) {
			int currVal=komponentStatistik.get(komponentNumber);
			komponentStatistik.put(komponentNumber, currVal+1);
		}else {
			komponentStatistik.put(komponentNumber,1);	
		}

	}

	private void tilldelaKomponentRekursiv(Integer komponentNumber, NeighbourContigsAndComponent currNeAndComp) {
		if (!currNeAndComp.komponentAlreadyDefined()) {
			uppdateraKomponetStatistik(komponentNumber);
			currNeAndComp.setKomponent(komponentNumber);
		}
		for (ContigKey k : currNeAndComp.getNeigbours()) {
			NeighbourContigsAndComponent myNeigbour = dnalinks.get(k);
			if (myNeigbour.komponentAlreadyDefined()) {
				continue;
			} else {
				tilldelaKomponentRekursiv(komponentNumber, myNeigbour);
			}
		}
	}

	
	

	public Map<ContigKey, NeighbourContigsAndComponent> getDnalinks() {
		return dnalinks;
	}
	
	//shows keys and values in dnalinks
	public void show() {
		
		for (Entry<ContigKey, NeighbourContigsAndComponent> ent: dnalinks.entrySet()) {
			
			ContigKey key = ent.getKey();
			
			NeighbourContigsAndComponent neigbours = ent.getValue();
			List<ContigKey> keysForFriends = neigbours.getNeigbours();
			
			System.out.print(key.getIdContig()+",key="+neigbours.getKomponent()+", friends= ");
			
			for (ContigKey k : keysForFriends) {
				
				System.out.print(k.getIdContig()+", ");	
			}
			
			System.out.println();
	
		}
		
		for (Entry<Integer, Integer> ent: komponentStatistik.entrySet()) {
				System.out.println(ent.getKey()+", "+ent.getValue());		
		}

	}
	public void showDegrees() {
		
		for (Entry<ContigKey, NeighbourContigsAndComponent> ent: dnalinks.entrySet()) {
			List<ContigKey> keysForFriends = ent.getValue().getNeigbours();
			System.out.println(keysForFriends.size());
		}
		
	}
	
}
