package dungeon;

public class FileMemento implements Memento {

	private byte[] state;
	
	public FileMemento(byte[] stateToSave)
	{
		this.state = state;
	}
	
	@Override
	public byte[] getSavedState() {
		// TODO Auto-generated method stub
		return this.state;
	}

}
