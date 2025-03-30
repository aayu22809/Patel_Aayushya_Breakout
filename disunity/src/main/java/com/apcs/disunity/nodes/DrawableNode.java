package com.apcs.disunity.nodes;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;

/// a node that has children which may draw to a buffer.
public abstract class DrawableNode extends Node<Node> {
  public DrawableNode() { super(); }
  public DrawableNode(Node... nodes) { super(nodes); }

  /// draws content of this node to buffer.
  /// use {@link Game#getInstance()} and {@link Game#getBuffer()} to access current buffer.
  /// @param offset where the node should be drawn
  public abstract void draw(Vector2 offset);
}
