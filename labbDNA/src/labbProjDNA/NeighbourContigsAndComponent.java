package labbProjDNA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeighbourContigsAndComponent {
	


	private List<ContigKey> neigbours;
	private Integer komponent;


	public NeighbourContigsAndComponent(ContigKey key) {
		neigbours = new ArrayList<>(Arrays.asList(key));
		komponent = null;
	}

	public void addNeighbour(ContigKey key) {
		neigbours.add(key);
	}
	
	public Integer getKomponent() {
		return komponent;
	}

	public void setKomponent(Integer komp) {
		this.komponent = komp;
	}

	public boolean komponentAlreadyDefined() {
		return komponent != null;
	}

	public List<ContigKey> getNeigbours() {
		return neigbours;
	}

}
