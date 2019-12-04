package dungeon;

public class MockMemento implements Memento {

	private byte[] state;
	
	@Override
	public byte[] getSavedState() {
		
		return this.state;
	}

	@Override
	public void setState(byte[] stateToSave) {
		// TODO Auto-generated method stub
		this.state = stateToSave;
	}

}
