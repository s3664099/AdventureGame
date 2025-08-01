# AdventureGame

A basic adventure game that I am developing to practice my coding.
When I was a kid I used to write a lot of adventure games using Commodore 64 Basic.
What I have been doing here is taking the concepts and attempting to create something similar using
Java as the main language.

## The Program ##

The game, for want of a better word uses the model/view/controller pattern to at least give it some structure.
However, it also has a data package which, while technically part of the model package, it is designed to hold all
of the classes that hold the basic data related to the game.

The data is divided into three interfaces - Location, Item, and Exit. The location is the top object and contains 
everything else. The exit contains a link to another location. Item and Exit are then divided into separate classes,
each of them having an abstract class as a base. This is to allow the item/exit to perform different functions, such as being
closable, lockable, or carriable (in the case of items - you can't carry an exit, obviously).

The view package is holds the classes that will display the game to the user. At this stage it is text, but it is designed
to be moved from the console.

The controller package holds the methods that take the player's input, but also functions that build the parser and return it to
the model. Of course if we move to a more graphical model, this can change, but at this stage, it just takes a two word text input
however, it is my intention to change this.

The model holds the main class that runs the program, and the command class, that processes and executes the command. The command
class takes a verb and a noun (or in some cases just a noun). It goes through each of the possible nouns and calls methods related
to these verbs.

The current verbs are:

- movement
- look
- take/drop
- open/close
- lock/unlock
- move
- inventory
- save
- load
- talk
- speak
- put
- read

## Classes ##

### Default Package ###
---
#### Start Class ####

**Overview**  
The `Start` class is the entry point for launching the adventure game. It initializes the game by creating an instance of the `Main` class and invoking its `run` method to start the game loop.

**Purpose**  
The primary purpose of the `Start` class is to:  
- Serve as the launching point for the adventure game.  
- Initialize the game by creating an instance of the `Main` class.  
- Start the game by calling the `run` method of the `Main` class.  

**Key Components**  

**1. Instance Variables**  
- The `Start` class does not have any instance variables. It is a simple class with a single static method.

**2. Methods**  
- **`public static void main(String[] args)`**:  
  - The `main` method is the entry point of the application.  
  - It creates an instance of the `Main` class and calls its `run` method to start the game.  

**Usage**  
To use the `Start` class, simply execute it as the main class of the application. This will launch the adventure game.

**Best Practices**  
1. **Error Handling**:  
   - Add a `try-catch` block to handle exceptions that may occur during the execution of the `Main` class. This ensures the program does not crash unexpectedly and provides meaningful feedback to the user.  

2. **Logging**:  
   - Integrate a logging framework (e.g., `java.util.logging` or `Log4j`) to track the startup process and runtime events. This helps with debugging and monitoring the game's behavior.  

**Why Use This Class?**  
- **Simplicity**: The `Start` class is straightforward and easy to understand, making it an ideal entry point for the game.  
- **Separation of Concerns**: It separates the responsibility of launching the game from the core game logic, which is handled by the `Main` class.  
- **Standard Entry Point**: The `main` method is the standard entry point for Java applications, making it clear where the program execution begins.  

By using the `Start` class, you ensure a clean and organized way to launch your adventure game while maintaining flexibility for future enhancements.

--- 

### Control Package ###

### Data Package ###

### Model Package ###

#### Main Class ####

**Overview**  
The `Main` class is the core of the adventure game. It manages the game loop, processes player commands, and handles game state, including the player's inventory, score, and current location.

**Purpose**  
The primary purpose of the `Main` class is to:  
- Initialize the game by loading data and setting up dependencies.  
- Process player commands and update the game state accordingly.  
- Manage the game loop until an end condition is met (e.g., winning the game or reaching a game-over state).  

**Key Components**  

**1. Instance Variables**  
- **`command`**: A list of commands entered by the player.  
- **`inventory`**: A list of items the player has collected.  
- **`score`**: The player's current score.  
- **`gameData`**: Handles game data and initialization.  
- **`display`**: Manages text output to the player.  
- **`input`**: Handles player input.  
- **`parser`**: Parses player commands into actionable instructions.  
- **`processor`**: Processes parsed commands and updates the game state.  

**2. Methods**  
- **`public Main(Data_Process gameData, Display_Text display, Input input, Parser parser, Command processor)`**:  
  - Constructor that initializes the game with dependencies injected.  
- **`public void run() throws Exception`**:  
  - The main game loop. It processes player commands, updates the game state, and checks for end conditions.  
- **`private boolean checkEndConditions(Location currentLocation, Command processor)`**:  
  - A helper method that checks if the game should end (e.g., the player wins or loses).  

**Usage**  
To use the `Main` class, initialize it with the required dependencies and call the `run` method. The `Start` class typically handles this initialization and error handling.

Example:
```java
Data_Process gameData = new Data_Process();
Display_Text display = new Display_Text();
Input input = new Input();
Parser parser = new Parser();
Command processor = new Command(gameData.getScore());

Main main = new Main(gameData, display, input, parser, processor);
main.run();
```

**Best Practices**  
1. **Error Propagation**:  
   - The `run` method propagates exceptions to the caller (`Start` class), ensuring centralized error handling.  

2. **Encapsulation**:  
   - Instance variables are private, and dependencies are injected via the constructor. This improves flexibility and testability.  

3. **Separation of Concerns**:  
   - The `Main` class focuses on game logic, while other classes handle input, output, parsing, and command processing.  

4. **Use Constants**:  
   - Hardcoded strings (e.g., prompts and messages) are defined as constants for easier maintenance and localization.  

5. **Optimize Redundant Checks**:  
   - Helper methods like `checkEndConditions` are used to avoid repetitive logic and improve readability.  

**Example**  
Here’s an example of how the `Main` class is used in conjunction with the `Start` class:
```java
public class Start {
    private static final Logger logger = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) {
        try {
            // Initialize dependencies
            Data_Process gameData = new Data_Process();
            Display_Text display = new Display_Text();
            Input input = new Input();
            Parser parser = new Parser();
            Command processor = new Command(gameData.getScore());

            // Create and run the game
            Main main = new Main(gameData, display, input, parser, processor);
            main.run();
        } catch (Exception e) {
            logger.severe("An error occurred while running the game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

**Why Use This Class?**  
- **Core Game Logic**: The `Main` class encapsulates the core functionality of the game, making it the central component of the application.  
- **Modular Design**: By delegating responsibilities to other classes, the `Main` class adheres to the principle of separation of concerns.  
- **Flexibility**: Dependency injection and error propagation make the class easy to test, extend, and maintain.  
- **Clear Structure**: The use of helper methods and constants improves readability and organization.  

By using the `Main` class, you ensure a clean and organized implementation of your adventure game's core logic, making it easier to build upon and maintain in the future.

---

### View Package ###

## Update List ##

**23 August 2023**
I have gone back to redo this game, so this is the new one that I have started to write.

**8 December 2023**
Well, I haven't actually written all that much here. So I'll pretty much say that I'm up to the closeable
and lockable items.

**22 January 2024**
Actually, it's been a while since I've touched this program. Anyway, I've added the lock/unlock and
the open/close for the containers. I will now move onto moveable items.

**23 January 2024**
Added item subclass for a moveable item. This item will hide either an item or an exit. Methods are added to check if the
item can be moved. Also a method and a boolean to see if the moveable item has already been moved.
Of course, this is only for hiding a single item/exit. It will need to change if it holds more than one item/exit, or even if
it hides an item and an exit. Oh, and also if it hides another thing if moved. Yeah, these adventure games can be tricky,
though we only need to do what is needed for a particular game, and can add further implementations as they arise.

**24 January 2024**
Added the move functionality to the game

**25 January 2024**
Added a system for collecting and storing treasures, and also scoring based on these treasures. This is a very simplistic scoring
system, and only takes into account treasures stored at a specific location. Most adventure games really don't need a scoring system
however, some complicated ones have used scoring to determine how much a player has achieved.

**26 January 2024**
Started creating the save/load functionality by making classes serializable. Added the save method, though still need to add the
query if the file already exists

**27 January 2024**
Got save game working, and tested correctly. Saved games are saved in a separate folder. Need load game to test if it loads correctly.
Now have the load game working correctly, and I do not need to unpack all of the classes either. I am absolutely stoked.

**28 January 2024**
Basically working on the functionality where if a player looks at an item, if it is hiding an item or exit, it will reveal it. I do not
believe it necessarily needs to be a carriable item either.

**29 January 2024**
Finished the function where you can look at an object an reveal either and item or an object. I call it a cover. Of course the commands are
simplistic, and I will consider a more complex parser later, including more than just a noun/verb (though that can be tricky if we want to
make nonsensical comments nonsensical)

**30 January 2024**
Effectively completed first part of the game. We should be able to make a basic adventure game with everything available. The final part
was to create a game that makes sure that everything works. Of course, I just realised that there is technically no way to win, or an end case,
so I probably should add something.

**22 February 2024**
Fixed some issued that arose with the cover functionality. Need to add the functionality where it removes the item from the list if it 
disappears. The item is now removed if flagged to be removed. Need to test with items, multiple items, multiple exits, and exits and items

**13 March 2024**
The multiple exits in a cover works. Added function to add more exits and items to a cover.

**14 March 2024**
The cover for items now works. Includes multiple items.

**21 March 2024**
The cover functions now work perfectly (though not as random as I liked, but at least it works). Added ability to examine items carried.

**23 March 2024**
Fixed errors with containers. Now can close and lock containers. Cannot lock and open container or exit, and cannot take an item from a
closed container. Started working on conversations

**27 March 2024**
Started working on the conversation functionality. Completed the basic (single response) conversation, but not tested

**30 March 2024**
Tested the talk function, and fixed up the response variable.

**4 April 2024**
Added a boolean to determine whether conversation extended or not. Started building extended conversations 

**7 April 2024**
Added ability of items to be revealed when closeable exits are opened.

**9 May 2024**
The basic conversation is now complete

**10 May 2024**
Made variables private. Added end conversation, and have being leave if flagged.

**11 May 2024**
Added functionality so that a conversation can change, such that an extended conversation becomes a single line. Started 
building end conversation.

**14 May 2024**
Fixed error with the ending conversation being in the wrong spot in the for statements

**8 June 2024**
Fixed error with the queries not continuing if end the conversation early. Cleared the endConv array. Finished conversation
functions.

**23 June 2024**
Added details for the exit and item classes.

**17 August 2024**
Added quit command and finishing functionality

**28 August 2024**
Completed testing and increased version to version 1

**19 August 2024**
Added scoring and end game features for entering a room.

**20 August 2024**
Working on multiple commands and more complex parser

**22 August 2024**
Completed complex parser for look, take, drop & go

**25 August 2024**
Using adjectives for exits now works.

**26 August 2024**
Adding adjectives for items

**31 August 2024**
Added diagonal directions

**1 September 2024**
Added ability to take and drop all.

**11 January 2025**
Changed the lock and unlock to have to nominate what you are using. Added the with command. Added an object to hold user command
details. Added ability to push something into a container. Created a bag, which is a container that can be carried. Added a read
command.

**16 January 2025**
Completed the bag and can put items into it.



---

#### Class Name ####

**Overview**

**Purpose**

**Key Components**

**1. Instance Variables**

**2. Methods**

**Usage**

**Best Practices**

**Example**

**Why Use This Class?**
