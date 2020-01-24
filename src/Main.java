import java.util.Scanner;

import Controller.GetCommand;
import Model.InvalidDirectionException;
import Model.Item;
import Model.Location;
import Model.Objects;

public class Main {
	
	private Location[] location;
	private Objects[] object;
	private GetCommand parser;
	private int spot = 1;

	public Main(Location[] location, Objects[] object, Item[] item)
	{
		this.location = location;
		this.object = object;
		parser = new GetCommand();
		String command = "";
		
		while (!command.startsWith("quit"))
		{
			boolean objectFound = false;
			
			System.out.println(location[spot].toString());
			System.out.println();
			
			System.out.println("Please Tell Me What to Do:");
			
			command = parser.command(command);
			
			String commands[] = command.split(" ");

			if(commands.length>1 && commands[0].equals("go"))
				commands[0]=commands[1];
			
			try
			{
				if (checkDirection(commands[0]))
					spot = location[spot].getExit(commands[0]);
			} catch (InvalidDirectionException e) {
				
				System.err.println(e);
				
			}
						
		}
		
		
	}
	
	private boolean checkDirection(String command)
	{
		boolean move = false;
		
		String[] direction = {"n","north","s","south","e","east","w","west","u","up","d","down",
				"i","in","o","out"};
		
		for (int i=0;i<direction.length;i++)
			if (direction[i].equals(command))
				move = true;
		
		return move;
		
		
	}

}
