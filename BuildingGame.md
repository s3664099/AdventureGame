# Instructions to Build a Game #

This file is a basic outline of the structure of the program with details of the various classes. The idea is to be able to build a game
without having to make any alterations to any of the other classes. Namely following the instructions here and building the game by creating
them in the Data_Process class in the Data Process file. With this I will go through Locations, Exits, and Items and outline how they are 
used and how customise the objects based on what functions they provide. Needless to say, this file will be added to and further extensions
to the game are added. Also, only methods that are needed to build the game will be explored. There are numerous other methods that are not needed
for building a game, and needless to say, they will remain hidden.

## Location ##

*Constructor - Location <name> = new Location(Location Name, Location Description)*
	This builds the basic location. The constructor takes two strings, one for the brief name, and one for the detailed description. The first
time a player goes into the location the full description is display. Afterwards only the brief name is displayed, If the player 'looks' then the
description will be displayed.

*Add Exit addExit(exit)*
	This function adds an exit to the location. Exits are objects explained further below. The method takes an exit object.

*Add Item addItem(item)*
	This function adds an item to the location. Items are objects explained further below.

*Set Treasure Store setTreasureStore()*
	This method sets the location as a place to store treasures.

## Exit ##

## Item ##

## Basic Commands ##
