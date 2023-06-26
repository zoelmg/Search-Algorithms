import java.awt.Color;
import java.util.ArrayList;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

//represent a vertex/cell of a maze
class Vertex {
  int x;
  int y;
  ArrayList<Edge> outEdges;
  Color color;

  Vertex(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.outEdges = new ArrayList<Edge>();
    this.color = color;
  }


  //checking equality of this vertex and a given object
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Vertex)) {
      return false;
    }

    Vertex v = (Vertex)other;
    return this.x == (v.x)
        && this.y == v.y;
  }
  
  //overriding the hashcode for this object
  @Override
  public int hashCode() {
    return this.x * 10000 + this.y;
  }

  //draw a vertex
  WorldImage drawSquare(Color c) {
    return new RectangleImage(13, 13, OutlineMode.SOLID, c);
  }

  //return the true x coordinates of this vertex on the graph of a maze
  int trueX() {
    return this.x / 2;
  }

  //return the true y coordinate of this vertex on the graph of a maze
  int trueY() {
    return this.y / 2;
  }
  
  void setColor(Color c) {
    this.color = c;
  }
  
  void addEdges(Edge e) {
    this.outEdges.add(e);
  }

}