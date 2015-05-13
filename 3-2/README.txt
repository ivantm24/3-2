*/////////////////////////////*
///                         ///
///   3&2 THE JAVA GAME     ///
///                   v1.0  ///
*/////////////////////////////*
            By Ivan Taktuk & Leonardo Matos

Our Java application is a client-server game for the popular Dominican
card game 3&2 (Three and two). This is a single player or multi-player card game
that is played between four players taking turns.


//GAME MECHANICS--
////////////////////////

In this game four players are issued five random cards each.
The rest of the cards are put face down in a pile in the center of the table
(this is called the pick pile) then the card on top of this pile is place side
up next to it (this is called the discard pile).
Each player gets a turn where they can either take the card either on top of
the discard pile or on the pick pile and then discard one of the cards from the
six he now has in his hands and place it face up on the discard pile.
The objective of the game is to collect in your hands three cards of one rank
and two cards of another rank. (e.g: three 2’s and two Q’s). This is called
having 3&2.
The first player to have a 3&2 is the winner of the game.
   Players will take turns picking and discarding a card in a counter clock
side order, the first player being the one on the right side of the dealer.


//CODE STRUCTURE--
////////////////////////

The code is divided into four main packages:
_3_2Game
_3_2Game_client
_3_2Game_server
_3_2Game_DesktopGUI


_3_2Game 
This contains all the general declarations and rules of the game and also 
declares UI interfaces for easily migrating the game to other interfaces, it 
basically uses the command pattern.
This package also includes a single player console game used for testing.

_3_2Game_client
All the logic for the clients to communicate with the game server, one for 
playing the game one for chatting and one for game table manipulation and 
selection.

_3_2Game_server
The game server, it manages online player’s connections, game table and their 
assignment, chat sessions among players, it extends game class to also manage 
game rules.
The server runs on the port 3232, exclusively.

_3_2Game_DesktopGUI
All the classes used for implementing the Desktop GUI client of the game both 
single player and multiplayer.

//HOW TO PLAY THE GAME--
/////////////////////////////

*---USING THE SUPPLIED BINARIES---*
    Game Server
Run the 3-2Server jar file, using the command:
     java -jar “3-2 server.jar”
This will run the 3&2 Game server on a terminal window and clients will be able 
to connect.
Note: To play single player the server is not required 

    Game Client
    Simply run the 3-2.jar file in your computer by double clicking on it.

*---COMPILING THE GAME---*
To compile the game from source use the following command four times to compile 
the packages in the indicated order:

    javac PACKAGE_NAME/*.java

    Where “PACKAGE_NAME” is the name of the package, make sure you compile the 
packages in the order indicated below:

_3_2Game
_3_2Game_client
_3_2Game_server
_3_2Game_DesktopGUI
        
        After this the game should be ready to play.

*---PLAYING A SINGLE PLAYER GAME---*
To start the client simply run the “MainMenu” file inside of the 
_3_2Game_DesktopGUI and select the “single player” button and enter your player 
name.
The message window on the bottom will indicate which Player’s turn is. there 
will be three CPU players and you. The message window will also indicate each 
player’s activities.
When a player wins the system will indicate the game has ended.

*---PLAYING A MULTI-PLAYER GAME---*
Game Server
To start the server simply run the “server” file inside of the _3_2Game_Server 
package. After this clients can connect to the server to play the game.
The server runs on the port 3232, exclusively.

Game Client
To start the client simply run the “MainMenu” file inside of the 
_3_2Game_DesktopGUI and select the “single player” button and enter your player 
name and the server’s IP address to which the server should connect to (by 
default the localhost address is selected).
The message window on the bottom will indicate which Player’s turn is. there 
will be three CPU players and you. The message window will also indicate each 
player’s activities.
When a player wins the system will indicate the game has ended.
