# Disunity Web Frontend

This is a little web frontend for the Ljaag game. It provides a user friendly way  of launching the game and some more instructions kinda like steam.

# Future work

- [ ] Add auth
- [ ] Add multiplayer
- [ ] Add a nicer UI
- [ ] Add java applett (not the best performance, but it's a start)

## Prerequisites

- Node.js (v18 or newer recommended)
- pnpm package manager
- Java Development Kit (JDK) version 23 (Per request of Sharvil)
- Maven build tool

## Getting Started

### Step 1: Fix Java Project

Before running the web frontend, make sure the Java project builds correctly:

```bash
# Navigate to the project root directory
cd Project-APCS

# Make sure run.sh is executable
chmod +x run.sh

# Run the Maven build
./run.sh
```

If the Java build completes successfully, you'll see the LJAAG game launch in its own window.

### Step 2: Run the Web Frontend

```bash
# Navigate to the web directory
cd disunity-web

# Install dependencies (if not already installed)
pnpm install

# Start the development server
pnpm run dev
```

## Using the Web Frontend

1. Open your browser to the URL displayed in the terminal (usually http://localhost:5173)
2. Click the "Play Game" button on the home page
3. Click the "Start Game" button on the game page
4. Look for the Java game window that will appear on your desktop (outside the browser)
5. Use WASD or arrow keys to control the character in the game
6. When finished, click "Stop Game" to close the game application

## How It Works

The frontend provides a user-friendly interface for launching the Java game:

- When you click "Start Game", the frontend executes the `run.sh` script in the parent directory
- The game runs as a separate process outside the browser
- The game status is monitored and displayed in an iframe within the web interface
- When you click "Stop Game", the process is terminated

## Important Notes

- The Java game runs as a standalone application outside the browser window
- You need to have Java and Maven properly configured on your system
- The web frontend acts as a launcher and controller for the game, not as a container
- The container functionality is not yet implemented though it is planned
