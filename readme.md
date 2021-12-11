# Pebbles

A final project for Object Oriented Programming course using Java 8. The purpose is to take what you learn in the course and apply it in real world projects.

Participants:
- Muhammad Hidayat (05111940000131)
- Nouvelli Cornelia (05111940000011)

This project uses the Maven build tool to aid in production. It is recommended to use Eclipse to work on this project. But if you have Maven installed, you can run the project even without Eclipse.

## Working on the project

1. Clone the project

   ```
   git clone https://github.com/return215/pebbles-java.git
   ```

2. Import the project to Eclipse

   File > Import > Maven > Existing Maven Projects

   Select the folder that you cloned into, and press Finish.

## Using Eclipse

It is recommended that you read [this post](http://www.instanceofjava.com/2018/02/how-to-run-two-java-programs-simultaneously.html) on how to use multiple consoles to run several Java programs at once.

Run the first class (Server), then pin the console and add a new Console view. After that, you can run the second class (Client).

## Using Maven

This part applies if you have already installed Maven on your system.

- Use `mvn compile` to build the project
- Use `mvn exec:java -D exec.mainClass=<theMainClass>` where `<theMainClass>` are one of the following:
  - `xyz.return215.app.pebbles.PebblesClient`
  - `xyz.return215.app.pebbles.PebblesServer`
