package dungeon;

public interface Memento {
	public byte[] getSavedState();
	public void setState(byte[] stateToSave);
}
