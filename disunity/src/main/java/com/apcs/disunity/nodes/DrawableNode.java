package com.apcs.disunity.nodes;

import com.apcs.disunity.math.Vector2;

public abstract class DrawableNode extends Node<Node> {
  public DrawableNode() { super(); }
  public DrawableNode(Node... nodes) { super(nodes); }

  /// draws content of this node.
  /// @param offset where the node should be drawn
  public abstract void draw(Vector2 offset);
}
