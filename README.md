# Project-APCS

## Disunity Nodes

**Node** is the base node and has children.

**DrawnNode** is a Node that is visible in the game.

**UndrawnNode** is a Node that is not visible in the game. They can only have other UndrawnNodes as children.

**Node2D** is a DrawnNode with a 2D position. It renders its children relative to its own position.

**Sprite** is a Node2D that renders an image or animation.

**Body** is a Node2D that handles movement and collision.

**MoveAction** is an UndrawnNode that performs a movement action.

## Game Nodes

**PlayerBody** is a Body that is controlled by player inputs.

**WalkAction** is a MoveAction that allows directional movement.
