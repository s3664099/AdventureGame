package Model;

public class InvalidDirectionException extends Exception {
	
	public InvalidDirectionException()
	{
		super("You cannot move in that direction");
	}

}
