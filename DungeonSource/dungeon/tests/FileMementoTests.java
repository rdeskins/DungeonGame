package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeon.FileMemento;
import dungeon.Memento;

class FileMementoTests {

	Memento fileMemento;
	File openFile;
	
	 @BeforeEach
	 void init() {
			fileMemento = new FileMemento("testfile.txt");
			openFile = new File("testfile.txt");
	 }
	 
	 @AfterEach
	 void cleanUp() {
		 openFile.delete();
	 }
	
	@Test
	void testGetSavedState() {
		byte[] expectedByteArray = "testing".getBytes();
		
		fileMemento.setState(expectedByteArray);
		
		byte[] testByteArray = fileMemento.getSavedState();
		
		assertArrayEquals(expectedByteArray, testByteArray);
	}

	@Test
	void testSetStateStoresStateInFile() {
		byte[] testByteArray = "testing".getBytes();
		
		fileMemento.setState(testByteArray);
	
		try {
			FileInputStream file = new FileInputStream("testfile.txt");
			assertArrayEquals(Files.readAllBytes(Paths.get("testfile.txt")), fileMemento.getSavedState());
			file.close();
		}
		catch(FileNotFoundException ex) {
			fail();
		}
		catch(IOException ex) {
			fail();
		}
	}
	
	@Test
	void testSetStateCreatesFile() {
		byte[] expectedByteArray = "testing".getBytes();
		
		fileMemento.setState(expectedByteArray);
		
			
		assertTrue(openFile.exists());
		
	}

}
