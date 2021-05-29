package labbProjDNA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DNAlinks {
	
	private Map<ContigKey,  List<ContigKey>> dnalinks;

	public DNAlinks() {
		dnalinks= new HashMap<>();
	}
	
	
	
	
	public void add(String contig1, String contig2 ) {
		
		ContigKey key1= new ContigKey(contig1);
		ContigKey key2= new ContigKey(contig2);
		//hanterad contig 1
		if (!dnalinks.containsKey(key1)) {
			dnalinks.put(key1, new ArrayList<>(Arrays.asList(key2)));
		} else {
			dnalinks.get(key1).add(key2);
		}
		
		
		if (!dnalinks.containsKey(key2)) {
			dnalinks.put(key2, new ArrayList<>());
		}
			dnalinks.get(key2).add(key1);
			
		
	}




	public Map<ContigKey, List<ContigKey>> getDnalinks() {
		return dnalinks;
	}
	
	public void show() {
		for (Entry<ContigKey, List<ContigKey>> ent: dnalinks.entrySet()) {
			ContigKey key = ent.getKey();
			 List<ContigKey> keysForFriends = ent.getValue();
			System.out.print(key.getIdContig()+", Friends=");
			for (ContigKey k : keysForFriends) {
				System.out.print(k.getIdContig()+", ");	
			}
			System.out.println("");
			
			
		}
	}
	


}
