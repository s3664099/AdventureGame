package Model;

public class Door extends Objects {
	
	/**
	 * This represents an obstruction to the player than prevents
	 * them from entering another location. Further work needs to be done on this.
	 * 
	 * 
	 */
	
	private boolean isLocked = false;
	
	public Door(String name, int location) {
		
		super(name, location);
	}
	
	public Door(String name, int location, boolean isLocked) {
		super(name, location);
		
		this.isLocked = isLocked;
	}
	
	public boolean checkLocked() {
		return isLocked;
	}
	

}
