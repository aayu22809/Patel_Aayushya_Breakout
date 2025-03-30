package com.apcs.disunity.nodes;

/// node that is guaranteed not to have any drawing node, such as scripts
public class NonDrawingNode extends Node<NonDrawingNode> {
  public NonDrawingNode() { super(); }
  public NonDrawingNode(NonDrawingNode... nodes) { super(nodes); }
}
