package labbProjDNA;

public class ContigKey {

	private int Id;
	private String idContig; // Ta bor sen

	public ContigKey(String idContig) {
		Id = getHashCode(idContig);
		this.idContig=idContig; //Ta bort sen
		
	}

	private int getHashCode(String str) {
		return str.hashCode();
	}

	public int getId() {
		return Id;
	}

	//Ta bort sen
	public String getIdContig() {
		return idContig;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContigKey other = (ContigKey) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

}
