package model;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

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
	
	/*
	@Test
	public void testoccurenceReader() {
		System.out.println(" "+occurenceReader("azerty"));
		assertTrue(0 != occurenceReader("soleil"));
	}
	*/
	
	

	
}
