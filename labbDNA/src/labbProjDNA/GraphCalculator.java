package labbProjDNA;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class GraphCalculator {
	
	//maps a key (hashcoded identifier) to it's neighbours and a component attribute
	private Map<ContigKey,  NeighbourContigsAndComponent> contigKeyDataMap;
	//Maps a componentNumber to the size of the component
	private Map<Integer,  Integer > componentSizeMap;
	private Map<Integer,  Integer > degreeDataHistogram;
	private Map<Integer,  Integer > componentSizeDistributionMap;



	public GraphCalculator() {
		contigKeyDataMap= new HashMap<>();
		componentSizeMap=new HashMap<>(); 
			
	}
	

	//adds a element to hashmap
	//every key (identifier) maps to list of it's neighboring nodes hence the size of the list is the degree
	public void add(String iden1, String iden2 ) {
		
		//contig key runs the idenfiers through a hashfunction 
		ContigKey key1= new ContigKey(iden1);
		ContigKey key2= new ContigKey(iden2);
		
		//if first key1 does not already exist we add it to hashmap and add key2 as its neighbor
		//else just add key2 as neighbor
		
		if (!contigKeyDataMap.containsKey(key1)) {
			contigKeyDataMap.put(key1, new NeighbourContigsAndComponent(key2));
		} else {
			contigKeyDataMap.get(key1).addNeighbour(key2);
		}
		
		//same as above but add key2 with key1 as a neighbor
		if (!contigKeyDataMap.containsKey(key2)) {
			contigKeyDataMap.put(key2, new NeighbourContigsAndComponent(key1));

		} else {
			contigKeyDataMap.get(key2).addNeighbour(key1);
		}
	}
	
	
	//Traverses the graph by iterating each iteration we check if node has already been visited if not
	//we give the node and start iterating it's neighbors, for each nighbor
	//we use recuersion to visit it neigbor until we have explored an entire component 
	public void assembleContigKeyIntoComponents() {
		Integer komponentNumber=0;
		for (Entry<ContigKey, NeighbourContigsAndComponent> ent: contigKeyDataMap.entrySet()) {
			NeighbourContigsAndComponent currNeAndComp=ent.getValue();
			if (currNeAndComp.komponentAlreadyAssigned()) {
				continue;
			}else {
				komponentNumber++;
				assignComponentRecursivly(komponentNumber,currNeAndComp);
			}
		}
		
		
		
	}
	
	
	public void setDegreeDistribution() {
		
		degreeDataHistogram = new HashMap<>();
		
		for (Entry<ContigKey, NeighbourContigsAndComponent> ent : contigKeyDataMap.entrySet()) {
			Integer degree = ent.getValue().getNeigbours().size();
			if (degreeDataHistogram.containsKey(degree)) {
				Integer nyttAntal = degreeDataHistogram.get(degree) + 1;
				degreeDataHistogram.put(degree, nyttAntal);
			} else {
				degreeDataHistogram.put(degree, 1);
			}
		}
	}
	
	public void setComponentSizeDistribution() {
		
		componentSizeDistributionMap = new HashMap<>();
		
		for(Entry<Integer, Integer > ent : componentSizeMap.entrySet() ) {
			Integer size = ent.getValue();
			
			if(componentSizeDistributionMap.containsKey(size)) {
				Integer nyttAntal = componentSizeDistributionMap.get(size) + 1;
				componentSizeDistributionMap.put(size, nyttAntal);
			}else {
				componentSizeDistributionMap.put(size, 1);
			}
		}
	}
	
	
	public void printDegreeDistubition(String utFil) throws IOException {
		
		FileWriter fw1 = new FileWriter(utFil);
		
		fw1.write("Degree\tFrequency\n");
		
		for ( Entry<Integer, Integer> ent : degreeDataHistogram.entrySet()) {
			
			fw1.write(ent.getKey()+ "\t"+ent.getValue()+"\n");

		}
		fw1.close();
	}
	
	public void printComponentSizeDistrubution(String utFil) throws IOException {
		
		FileWriter fw2 = new FileWriter(utFil);
		
		fw2.write("Size\tFrequency\n");
		for ( Entry<Integer, Integer> ent : componentSizeDistributionMap.entrySet()) {
			
			fw2.write(ent.getKey()+"\t"+ent.getValue()+"\n");

		}
		fw2.close();
	}
	
	//updates a given nodes component if 
	private void updateComponentSize(Integer komponentNumber) {
		
		
		if(componentSizeMap.containsKey(komponentNumber)) {
			int currVal=componentSizeMap.get(komponentNumber);
			componentSizeMap.put(komponentNumber, currVal+1);
		}else {
			componentSizeMap.put(komponentNumber,1);	
		}

	}
	

	


	private void assignComponentRecursivly(Integer komponentNumber, NeighbourContigsAndComponent currNeAndComp) {
	
		if (!currNeAndComp.komponentAlreadyAssigned()) {
			updateComponentSize(komponentNumber);
			currNeAndComp.setKomponent(komponentNumber);
		}
		
		for (ContigKey k : currNeAndComp.getNeigbours()) {
			NeighbourContigsAndComponent myNeigbour = contigKeyDataMap.get(k);
			if (myNeigbour.komponentAlreadyAssigned()) {
				continue;
			} else {
				assignComponentRecursivly(komponentNumber, myNeigbour);
			}
		}
	}

	
	

	public Map<ContigKey, NeighbourContigsAndComponent> getContigKeyDataMap() {
		return contigKeyDataMap;
	}
	

	
	
	
}



