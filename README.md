# Sokoban Game README

## Overview
Sokoban puzzle game , where the player navigates a warehouse and pushes crates to designated storage locations.

## Installation and Setup
1. **Clone the Repository**: 
    - Clone this repository to your local machine using Git:
      ```
      git clone https://github.com/alarmant0/Sokoban
      ```

2. **Open in Java IDE**:
    - Open your preferred Java IDE
    - Import the project into your IDE

3. **Build and Compile**:
    - Build the project to compile the Java code in your IDE.

## Running the Game
1. **Execute Main Class**:
    - Run the main class to start the game.

2. **Game Controls**:
    - Use the arrow keys to move the player character.
    - Push the crates to designated storage locations to complete each level.

## Creating a JAR File
1. **Build JAR in IDE**:
    - In your Java IDE, look for an option to build an executable JAR file.
    - Select the appropriate main class and specify the output location for the JAR file.

2. **Build JAR using Command Line**:
    - Navigate to the root directory of the project in a terminal or command prompt.
    - Use the following command to compile the Java code and create a JAR file:
      ```
      javac -d bin src/*.java
      jar cfe Sokoban.jar Main -C bin .
      ```

3. **Run the JAR File**:
    - Once the JAR file is created, you can run the game by executing the JAR file:
      ```
      java -jar Sokoban.jar
      ```

## Additional Notes
- Ensure you have a Java Development Kit (JDK) installed on your system to compile and run the game.
