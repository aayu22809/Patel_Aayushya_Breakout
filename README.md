# Project-APCS

## Disunity Nodes

**Node** is the base node and has children.

**UndrawnNode** is a Node that is not visible in the game. They can only have other UndrawnNodes as children.

**Camera** is a UndrawnNode that controls the viewport.

**Controller** is an UndrawnNode that controls a body node.

**MoveAction** is an UndrawnNode that performs a movement action.

**DrawnNode** is a Node that is visible in the game.

**Node2D** is a DrawnNode with a transform. It renders its children relative to its own transform.

**Sprite** is a Node2D that renders an image or animation.

**Body** is a Node2D that handles movement and collision.

## Game Nodes

**PlayerController** is a Controller that is controlled by player inputs.

**WalkAction** is a MoveAction that allows directional movement.
