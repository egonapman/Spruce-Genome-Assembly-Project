package labbProjDNA;

public class ContigKey {

	private int Id;

	private String idContig; // Ta bor sen

	public ContigKey(String idContig) {
		Id = getHashCode2(idContig);
		this.idContig=idContig; //Ta bort sen
	}

	private int getHashCode2(String str) {
		return str.hashCode();
	}

	public int getId() {
		return Id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idContig == null) ? 0 : idContig.hashCode());
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
		if (idContig == null) {
			if (other.idContig != null)
				return false;
		} else if (!idContig.equals(other.idContig))
			return false;
		return true;
	}





}
