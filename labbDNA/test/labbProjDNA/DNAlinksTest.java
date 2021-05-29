package labbProjDNA;

import org.junit.Test;

public class DNAlinksTest {

	@Test
	public void testKalle() throws Exception {
		
		
		DNAlinks links=new DNAlinks();
		links.add("Kalle","Johan");
		links.add("Kalle","Fredrik");
		
			links.show();	
		
	}
	
}
