#!/bin/bash

# Start script for Disunity Web Frontend with LJAAG Game

# Make sure run.sh is executable
cd ..
chmod +x run.sh
echo "✅ Made run.sh executable"

# Install dependencies if node_modules doesn't exist
cd disunity-web
if [ ! -d "node_modules" ]; then
  echo "📦 Installing dependencies..."
  npm install
else
  echo "✅ Dependencies already installed"
fi

# Start the development server
echo "🚀 Starting development server..."
echo "Once the server starts, open http://localhost:5173 in your browser"
echo "Click 'Play Game' and then 'Start Game' to launch the Java application"
echo ""
npm run dev
