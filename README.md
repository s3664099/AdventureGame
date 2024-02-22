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
disappears.