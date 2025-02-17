# Instructions to Build a Game #

This file is a basic outline of the structure of the program with details of the various classes. The idea is 
to be able to build a game without having to make any alterations to any of the other classes. Namely following
the instructions here and building the game by creating them in the Data_Process class in the Data Process 
file. With this I will go through Locations, Exits, and Items and outline how they are used and how customise 
the objects based on what functions they provide. Needless to say, this file will be added to and further 
extensions to the game are added. Also, only methods that are needed to build the game will be explored. There 
are numerous other methods that are not needed for building a game, and needless to say, they will remain 
hidden.

When creating objects, the actual types of object needs to be created.

Further, a top score will need to added as well. At this stage the only way to complete a game is to 
collect all of the treasures and place them where the treasures are to be stored.

## Location ##

*Constructor - Location <name> = new Location(Location Name, Location Description)*

This builds the basic location. The constructor takes two strings, one for the brief name, and one for the
detailed description. The first time a player goes into the location the full description is display. 
Afterwards only the brief name is displayed, If the player 'looks' then the description will be displayed.

*Constructor - public Location(String name, String description, String endComment)*

This constructor is used to create a room where if the player enters it, it triggers an end condition. This
room could be a room where the player dies, or where, by entering the room, the player finishes the game.
And end comment is included in the room.

*Add Exit addExit(exit)*

This function adds an exit to the location. Exits are objects explained further below. The method takes an exit 
object.

*Add Item addItem(item)*

This function adds an item to the location. Items are objects explained further below.

*Set Treasure Store setTreasureStore()*

This method sets the location as a place to store treasures.

*setScore()*

This method is used to set the room to give the player a score upon entering it. The player will only
recieve the score the first time the room is entered.

## Exit ##
Exit has an abstract class and an interface. Honestly, I really don't know why, but I suspect it is to make it 
easier to pass through the different exit types, of which there are a number. Mind you, for constructing 
adevnture games these functions aren't needed to be known about, particularly since most of the functions where 
these are used will be hidden from the creator.
However, I will go through each of the different types of exit one by one. Methods that don't apply or are 
repeated won't be metioned.

### OrdinaryExit ###
You could say that this is the basis of all of the other exits. Basically an ordinary exit is an exit that the 
play can ause without any obstruction. The Ordinary exit can be a cardinal direction (north, south, east, west 
etc) or it can be an object (house, cave, jetty). The thing with the oridnary exit is that the player uses it 
and it will take them to the next location.

*Constructor - OrdinaryExit <name> = new OrdinaryExit(String name, Destination location, boolean Direction)*

This is the simples construction. It takes the name of the exit, the destination, and whether it is a direction 
(true)) or not (false). The idea behind this is that the response that is displayed changes based upon whether 
it is a direction or not.

*Constructor - OrdinaryExit <name> = new OrdinaryExit(String name, String command, Destination location, 
													  boolean Direction, String Description)*

The second constructor contains a String parameter where the command is different from the name (the command to use the exit is based on
the name). It also contains a parameter to hold a description for the exit, whish is displayed if the player looks/examines the direction.

*addDescription(String description)*

This method adds a description to the exit which is displayed if the player looks at/examines the direction. 
The description is usually more detailed than the name, and may/may not contain clues.

### closeableExit ###

*Constructor CloseableExit(String name, String command, Location destination, boolean closed, 
						   String description)*

This class involves exits that can be closed, though aren't lockable (such as a curtain). Using this method has
an open boolean which flags whether the place is open/closed. The moveDirection method will check if the exit 
is open, and will display the appropriate response. There is also a method that will block the movemenr if it 
is closed. There are two constructors - one will set the open/close boolean, and another will add an item, 
though it is not clear what this item is used for.

*public void openClose()*

You can use this method to set the initial open/closed state of a closeable door.

### lockableExit ###

*Constructor LockableExit(String name, String command, Location destination, boolean closed, 
						  String description, CarriableItem key)*

This class involves exits that can be locked. The constructor, in addition to the other parameters, will take 
a boolean that says whether it is currently locked (usually it will be, but sometimes it might not be) and an
object which is the key. This object will need to be in the player's possession to be able to unlock the exit.
The item that is passed into the constructor must be a carriable item. 

## Item ##
Items, like exits, have an interface and an abstract class. The difference between an item and an exit is that
you can't move through them. You could say that items are anything that aren't exits, but that doesn't really 
make sense considering that exits are a class of their own. Like exits, there are a number of types of items, 
which I will go through one by one.

### ImoveableItem ###

*Constructor ImmoveableItem(String name, String description)*

This is the most basic type of item, namely something that simply exists and the player can only look at it. 
From this all other items are built. The constructor takes two parameters, both of them strings. One of them 
is the description that will appear when the details of the location is displayed. The other is the description
that is given when the item is examined.

### CarriableItem ###

*Constructor CarriableItem(String name, String description)*

The constructor for this type of item is the same, namely the name that appears in the location description,
and the description that is display when examined. The difference is that this item can be picked up, carried, 
and put down in a different location. There is also a method that will change the description and name of the
item, however I am unsure where this would apply.

### MoveableItem ###

*Constructor MoveableItem(String name, String description, Exit exit)*
*Constructor MoveableItem(String name, String description, Item item)*

This item hides either another item or an exit, and this can be revealed by moving the item. There are two
constructors for this item, one that takes an exit, and one that takes an item. When the item is moved, the 
item/exit is revealed. However, there is also the Cover, which effectively does the same thing, except that 
the cover can hide multiple items/exits, and also uses a different verb (this will need to be) changed. It 
is also possible that the cover may disappear once moved.

There are methods that will set whether the item has already been moved, so that it can't be moved multiple
times. There are also methods that will reveal the item/exit that it is hiding.

### Container ###

*Constructor Container(String name, String description)*
*Constructor Container(String name, String description, boolean closed)*
*Constructor Container(String name, String description, boolean locked, boolean closed, Item key)*

A container is an immoveable item that can hold objects. There are three types of constructors, the first 
which simply takes a name and a description. The second which sets whether it is closeable and closed, 
but is not lockable. The third is lockable, and sets whether it is closed and locked, but it also sets the 
key that is used to unlock the container.

*addItem(CarriableItem item)*

This method is used to add items to the container.

### Cover ###

*Constructor Cover(String name,String description,Exit hiddenExit,boolean remove)*
*Constructor Cover(String name,String description,Item hiddenItem,boolean remove)*
*Constructor Cover(String name,String description,Exit hiddenExit,Item hiddenItem, boolean remove)*

A cover hides either an item or an exit, or both. Moving the cover will reveal the item/exit that is being
cover. A boolean is used to determine whether the cover disappears when the item/exit has been revealed.
If multiple items/exits are added then a random one will be revealed when the cover is moved.

*public void addExit(Exit hiddenExit)*
This adds an exit to the cover.

*public void addItem(Item hiddenItem)*
This adds an item to the cover.

### Being ###

*Constructor Being(String name, String description, Conversation conversation, Boolean extended)*
A being is an item that you have have a conversation with. The constructor takes the conversation, and a
boolean flags whether the conversation is extended or not. The difference is that a non-extended conversation
simply has the being say a single response, where as with an extended conversation, the player can interact
with the being.

*public void setLeave(String leave)*
This method is used to set the leave conversation, which is the conversation that the player gets when they
wish to end the discussion.

*public void setLeaveConvo(String leaveConvo, Conversation endConversation)*
This is similar to the above, however a different conversation is added indicating that the being will no longer
wish to speak with the player if the conversation has been terminated early.

*public void setExtended()*
This changes the extended boolean, such as if the initial conversation has ended and 

## Conversation ##

*Constructor Conversation(String response)*
*Constructor Conversation(String response, ArrayList<Query> queries, String endConvo)*
*Constructor Conversation(String response, String endConvo)*

This class handles conversations. One of the constructors is just a simple response, however the second one
is used for a more complex conversation which includes queries. These are actually made up of multiple
conversation classes, where selecting a query will take the player to that specific conversation, and those
queries (or a simple one while will end the conversation).

*public void addQuery(Query query)*
This adds a new query to the conversation

## Query ##

*Constructor Query(String question, Conversation conversation)*
*Constructor Query(String question)*

This creates a query. The constructor either just adds a query, or it adds the query and the conversation that
it produces.public void addConversation(Conversation conversation)*

*public void addConversation(Conversation conversation)*
This adds a conversation to the query.

*public void setEndConvo(String endConvo)*
This adds an end conversation to the query.

## Basic Commands ##

These are the current commands, and any synonyms.

Go 				North, N, South, S, East, E, West, W, Up, U, Down, D
Open
Close
Inventory
Look
Take 			Get
Drop
Unlock
Lock
Move
Save
Load
Talk
Speak
