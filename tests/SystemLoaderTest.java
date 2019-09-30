import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import model.SystemLoader;

public class SystemLoaderTest extends SystemLoader{

	@Before
	public void setUp() {
		ArrayList<String> lignes = new ArrayList<String>();
		lignes.add("phrase test");
		lignes.add("phrase test 2") ;
	}
	
	@Test
	public void testremoveAccent() {
		String str ="phrase possédant un accent";
		String res = removeAccent(str);
		assertTrue(res.equals("phrase possedant un accent"));
	}
	
	
	@Test
	public void testoccurenceReader() {
		String str ="phrase accent possédant un accent ";
		assertTrue(2 == str.occurenceReader("accent"));
	}
	
	@Test
	public void testnameReader() {
		
	}
	
	
	
}
