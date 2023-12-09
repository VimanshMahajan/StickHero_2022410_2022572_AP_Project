AP PROJECT
                                GROUP MEMBERS: RIYA GUPTA->2022410
                                               VIMANSH MAHAJAN-> 2022572

# BONUS SECTION ATTEMPTED:
    Introduced a new feature in the game called SWORDS, which are displayed randomly in the arena, and the player needs to
dodge them by flipping over to the bottom of the stick by pressing the down arrow key. If the player touches the sword,
he gets killed and loses the game.

# DESIGN PATTERN USED:
    1-> Singleton Design Pattern: SINGLETON DESIGN PATTERN USED, TO ENSURE ONLY ONE PLAYER OBJECT IS PRESENT THROUGHOUT THE GAME.

    2-> State Design Pattern: STATE DESIGN PATTERN IS USED TO IMPLEMENT SCENE CHANGES BETWEEN FIRST, SECOND AND THIRD SCENES
     i.e. HOME PAGE, GAME PAGE AND GAME OVER PAGE.

# JUnit Testing:
    We have included 2 Junit tests for some classes and they are available under the 'test' section in the code.
    

1) Player Class

The Player class is a crucial component of the Stick Hero Game project, designed using the Singleton design pattern to ensure
the existence of only one player object throughout the game. Key attributes and methods include:

 -> Properties

- position: Represents the current position of the player.
- stickLength: Stores the current length of the player's stick.
- score: Represents the overall score of the player.
- numTotalCherries: Static attribute representing the total number of cherries collected by all players.
- currentScore: Represents the player's score for the current session.
- bestScore: Static attribute representing the best score achieved by any player.
- playerImage: Represents the graphical representation of the player as an ImageView.

 -> Methods

- getInstance(): Implements the Singleton pattern to retrieve or create the single instance of the player.
- getPosition(): Retrieves the current position of the player.
- setPosition(double position): Sets the position of the player.
- getStickLength(): Retrieves the current length of the player's stick.
- setStickLength(double stickLength): Sets the length of the player's stick.
- getScore(): Retrieves the overall score of the player.
- setScore(int score): Sets the overall score of the player.
- getNumTotalCherries(): Retrieves the total number of cherries collected by all players.
- setNumTotalCherries(int numTotalCherries): Sets the total number of cherries collected by all players.
- getCurrentScore(): Retrieves the player's score for the current session.
- setCurrentScore(int currentScore): Sets the player's score for the current session.
- getBestScore(): Retrieves the best score achieved by any player.
- setBestScore(int bestScore): Sets the best score achieved by any player.
- getPlayerImage(): Retrieves the graphical representation of the player.
- setPlayerImage(ImageView playerImage): Sets the graphical representation of the player.
- movePlayer(): Placeholder method for implementing player movement.
- extendStick(): Placeholder method for implementing stick extension.
- fallPlayer(): Placeholder method for determining if the player has fallen.
- flipPlayer(): Placeholder method for determining if the player has flipped.
- collectCherry(): Placeholder method for implementing the collection of cherries.
- revivePlayer(): Placeholder method for determining if the player can be revived.
- updateHighScore(): Placeholder method for updating the best score.

The Player class encapsulates the state and behavior of the player in the Stick Hero Game, providing methods for various player actions and interactions with the game environment.


2) StickBridge Class

The StickBridge class is a significant component of the Stick Hero Game project, representing the stick used to bridge
 gaps in the game. This class extends the functionality of the Rectangle class from JavaFX and incorporates features for
 stick manipulation during gameplay. Key attributes and methods include:

 -> Properties

- stick: Represents the stick object as a JavaFX Rectangle.
- initialHeight: Stores the initial height of the stick.
- isReleased: Indicates whether the space bar is released.

 -> Methods

- isReleased(): Retrieves the current release state of the space bar.
- setReleased(boolean released): Sets the release state of the space bar.
- getStick(): Retrieves the stick object.
- getInitialHeight(): Retrieves the initial height of the stick.
- setInitialHeight(double initialHeight): Sets the initial height of the stick.
- elongateStick(): Increases the height of the stick while the space bar is pressed.
- releaseStick(): Sets the stick height back to the initial height and applies a 90-degree rotation when the space bar is released.
- resetStick(): Resets the stick to its initial state with a height of 0 and applies a 180-degree rotation.
- appropriateLength(): Placeholder method for checking if the stick length is appropriate (implementation pending).
- resetReleasedState(): Resets the release state, allowing the stick to elongate again.
- handleKeyPress(KeyCode keyCode): Handles the space bar press to elongate the stick.
- handleKeyRelease(KeyCode keyCode): Handles the space bar release to release and rotate the stick.

The StickBridge class encapsulates the behavior of the stick in the Stick Hero Game, providing methods for stick manipulation and interaction with the game environment.


3) Cherry Class

The Cherry class is an integral part of the Stick Hero Game project, extending the JavaFX ImageView class. It represents
cherry objects within the game, serving as a visual element. Each instance of this class is associated with an image,
providing the graphical representation of cherries on the screen.

 -> Properties

- position: Represents the position of the cherry on the screen.

 -> Methods

- getPosition(): Retrieves the current position of the cherry.
- setPosition(double position): Sets the position of the cherry on the screen.

 -> Constructor

- Cherry(String cherryImage): Initializes a new cherry instance with the specified image.


The Cherry class allows for the easy integration of cherry objects into the Stick Hero Game, providing a visually
appealing aspect to the gameplay.

4) Swords Class

The Swords class is a component of the Stick Hero Game project, designed to extend the JavaFX ImageView class.
It serves as a representation of sword objects within the game. Each instance of the class is associated
with an image, providing a visual element for the swords displayed on the screen. The class includes a
position attribute, allowing for dynamic tracking of the sword's location.

5) HELLO APPLICATION class
Game Initialization:

The game starts with a main menu (createScene1) that includes a title, a play button, and a character image.
The player initiates the game by pressing the play button.
Main Game Scene (createScene2):

The main game scene consists of platforms, a character, a stick, cherries, and swords.
The character can move horizontally across platforms and extend a stick to bridge gaps between platforms.
Platform Generation:

Platforms are generated with random widths and positions to create a varying level of difficulty.
Stick Extension:

The player can extend the stick by pressing the spacebar. The stick's length is determined by how long the spacebar is held.
Player Movement:

The player can rotate the character by 180 degrees using the down arrow key and can rotate him back upwards by pressing
the UP ARROW key.

Cherry and Sword Interaction:

Cherries appear on the platforms, and collecting them increases the player's score.
Swords can also appear, and touching them results in the player getting killed.

Score and UI:

The player's score is displayed on the screen.
The UI includes background images, buttons, and text elements.

Game Over:

If the player falls or touches a sword, a game over sequence is triggered.
A new screen pops up displaying the game over screen with a summary of the player's performance.

Sound Effects:

Sound effects are used for actions such as stick extension, character rotation, and game over events.
File Reading:

The code reads the initial cherry count from a file (CherryCount.txt).
Updates the high score in a text file "HighScore.txt" and updates the total cherries after end of every game and before
starting a new game

Stage Management:
Multiple stages are used to transition between the main menu, the game scene, and the game over screen.
