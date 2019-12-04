package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

import dungeon.FileMemento;
import dungeon.Memento;

class FileMementoTests {

	@Test
	void testGetSavedState() {
		Memento fileMemento = new FileMemento("testfile.txt");
		byte[] expectedByteArray = "testing".getBytes();
		
		fileMemento.setState(expectedByteArray);
		
		byte[] testByteArray = fileMemento.getSavedState();
		
		File file = new File("testfile.txt");
		
		file.delete();
		
		assertArrayEquals(expectedByteArray, testByteArray);
	}

	@Test
	void testSetStateStoresStateInFile() {
		File openFile = new File("testfile.txt");
		Memento fileMemento = new FileMemento("testfile.txt");
		byte[] testByteArray = "testing".getBytes();
		
		fileMemento.setState(testByteArray);
	
		try {
			FileInputStream file = new FileInputStream("testfile.txt");
			assertArrayEquals(file.readAllBytes(), fileMemento.getSavedState());
			file.close();
		}
		catch(FileNotFoundException ex) {
			fail();
		}
		catch(IOException ex) {
			fail();
		}
		
		openFile.delete();
	}
	
	@Test
	void testSetStateCreatesFile() {
		Memento fileMemento = new FileMemento("testfile.txt");
		byte[] expectedByteArray = "testing".getBytes();
		
		fileMemento.setState(expectedByteArray);
	
		
		File file = new File("testfile.txt");
		
			
		assertTrue(file.exists());
		
	}

}
