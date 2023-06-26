import java.awt.Color;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

//represent an edge/wall of a maze
class Edge {
  Vertex from;
  Vertex to;
  int weight;
  boolean vertical;

  //constructor
  Edge(Vertex from, Vertex to, int weight, boolean vertical) {
    this.from = from;
    this.to = to;
    this.weight = weight;
    this.vertical = vertical;
  }


  //draw an edge of a maze
  WorldImage drawWalls() {
    if (this.vertical) {
      return new RectangleImage(2, 13, OutlineMode.SOLID, Color.BLACK);
    }
    else {
      return new RectangleImage(13, 2, OutlineMode.SOLID, Color.BLACK);
    }
  }

  //check the equality of this edge and a given object
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Edge)) { 
      return false; 
    }
    // this cast is safe, because we just checked instanceof
    Edge that = (Edge)other;
    return this.from.equals(that.from)
        && this.to.equals(that.to)
        && this.weight == that.weight
        && this.vertical == (that.vertical);
  }

  //overriding the hashcode for this object
  @Override
  public int hashCode() {
    return this.from.hashCode() * 10000 + this.to.hashCode() + weight;
  }

  //return the x coordinates of this edge on the graph of a maze
  int getXCoordinates() {
    return this.from.trueX() + this.to.trueX();
  }

  //return the y coordinates of this edge on the graph of a maze
  int getYCoordinates() {
    return this.from.trueY() + this.to.trueY();
  }
}