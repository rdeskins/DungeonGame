package dungeon;

import java.io.*;

public class FileMemento implements Memento {

	private String fileName;
	
	public FileMemento(String fileName)
	{
		this.fileName = fileName;
	}
	
	@Override
	public byte[] getSavedState() {
		File file = new File(fileName);
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			byte[] returnBytes = fis.readAllBytes();
			fis.close();
			return returnBytes;
		}
		catch(FileNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	@Override
	public void setState(byte[] stateToSave) {
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(stateToSave);
			fos.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}
