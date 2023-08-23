
public class Exit {
	
	private String description;
	private Location destination;
	private boolean locked;
	private boolean closed;
	private boolean direction;
	
	public Exit(String description, Location destination, boolean locked, 
				boolean closed,boolean direction) {
		this.description = description;
		this.destination = destination;
		this.locked = locked;
		this.closed = closed;
		this.direction = direction;
	}
	
	public String move() {
		
		String response = "";
		
		if (locked) {
			response = response.format("The %s is locked%n", description);
		} else if (closed) {
			response = response.format("The %s is closed%n", description);
		} else if (!direction) {
			response = response.format("You pass through the %s and enter a %s%n",
										this.description,this.destination.getName());	
		} else {
			response = response.format("You go %s and enter a %s%n",this.description,
										this.destination.getName());
		}
		
		return response;
	}
	
	public String getDescription() {
		return this.description;
	}
}
