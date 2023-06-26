import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import javalib.impworld.WorldScene;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.RectangleImage;
import tester.Tester;

//examples and tests for methods of 
//Vertex, Edge, Graph, and MazeWorld
class ExamplesMaze {

  //the constructor
  ExamplesMaze(){}

  //=========== EXAMPLES SETUP FOR TESTING ===========
  //example fields
  Vertex from1;
  Vertex to1;
  Vertex from2;
  Vertex to2;
  Edge edge1;
  Edge edge2;
  Edge edge3;

  HashMap<Vertex, Vertex> hashmap1;

  HashMap<Vertex, Vertex> hashmap2;

  MazeWorld graph1;
  MazeWorld graph2;
  MazeWorld graph3;

  Vertex three;
  Vertex four;
  Vertex six;
  Vertex seven;
  Vertex nine;
  Vertex eleven;
  Vertex ten;
  Vertex fourteen;
  Vertex sixteen;
  Vertex eighteen;
  Vertex twenty;
  Vertex twentytwo;
  Vertex nine2;
  Vertex three2;
  Vertex six2;
  Vertex four2;
  Vertex five2;

  Vertex vertex1;
  Vertex vertex2;
  Vertex vertex3;
  Edge edgeOne;
  Edge edgeTwo;
  Edge edgeThree;
  Edge edgeFour;
  Edge edgeFive;


  ArrayList<Edge> edgeList1;
  ArrayList<Edge> edgeList2;
  ArrayList<Integer> weightList2by2;
  ArrayList<Integer> weightList3by2;
  ArrayList<Integer> weightList4by4;

  MazeWorld graphRandom;
  MazeWorld graphSeed1;
  MazeWorld graphSeed2;
  MazeWorld graphSeed3;

  MazeWorld mazeSeed1;
  MazeWorld mazeSeed2;
  MazeWorld mazeSeed3;

  WorldScene maze1;
  WorldScene maze2;
  WorldScene maze3;


  void initSeeds() {
    this.weightList2by2 = new ArrayList<Integer>(Arrays.asList(
        5, 16, 20, 15));

    this.weightList3by2 = new ArrayList<Integer>(Arrays.asList(
        5, 16, 20, 15, 19, 20, 3));

    this.weightList4by4 = new ArrayList<Integer>(Arrays.asList(
        5, 16, 20, 15, 19, 20, 3, 24, 52, 4, 11, 15, 18, 19, 40, 36, 14));

    this.mazeSeed1 = new MazeWorld(2, 2, weightList2by2);

    this.mazeSeed2 = new MazeWorld(3, 2, weightList3by2);

    this.mazeSeed3 = new MazeWorld(4, 4, weightList4by4);
  }

  //initial examples of Vertex/Edges/Graphs
  //for tests: equals(), draw(), sortSmallest(), makeScene()
  void initExamples() {
    this.vertex1 = new Vertex(20, 20, Color.GRAY);
    this.vertex2 = new Vertex(30, 20, Color.GRAY);
    this.vertex3 = new Vertex(30, 20, Color.GRAY);
    this.edgeOne = new Edge(vertex1, vertex2, 20, true);
    this.edgeTwo = new Edge(vertex2, vertex1, 45, true);
    this.edgeThree = new Edge(vertex1, vertex2, 20, true);
    this.edgeFour = new Edge(vertex2, vertex1, 30, false);
    this.edgeFive = new Edge(vertex1, vertex2, 15, true);

    this.edgeList1 = new ArrayList<Edge>(
        Arrays.asList(edgeOne, edgeTwo, edgeThree, edgeFour, edgeFive));

    this.edgeList2 = new ArrayList<Edge>(
        Arrays.asList(edgeOne, edgeOne, edgeOne, edgeOne, edgeOne));

    this.weightList2by2 = new ArrayList<Integer>(Arrays.asList(
        5, 16, 20, 15));

    this.weightList3by2 = new ArrayList<Integer>(Arrays.asList(
        5, 16, 20, 15, 19, 20, 3));

    this.weightList4by4 = new ArrayList<Integer>(Arrays.asList(
        5, 16, 20, 15, 19, 20, 3, 24, 52, 4, 11, 15, 18, 19, 40, 36, 14));

    this.graphRandom = new MazeWorld(3, 2);

    //how seeded maze1 should look like
    this.maze1 = new WorldScene(26, 26);
    this.maze1.placeImageXY(
        new RectangleImage(26, 26, OutlineMode.SOLID, Color.gray), 6, 6);
    this.maze1.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 6);
    this.maze1.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 6, 19);
    this.maze1.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 19);
    this.maze1.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.green), 6, 6);
    this.maze1.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.blue), 19, 19);
    this.maze1.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 12, 18);


    //how seeded maze2 should look like
    this.maze2 = new WorldScene(39, 26);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 6, 6);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 6);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 32, 6);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 6, 19);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 19);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 32, 19);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.green), 6, 6);
    this.maze2.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.blue), 32, 19);
    this.maze2.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 12, 18);
    this.maze2.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 25, 18);


    //how seeded maze3 should look like
    this.maze3 = new WorldScene(52, 52);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 6, 6);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 6);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 32, 6);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 45, 6);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 6, 19);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 19);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 32, 19);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 45, 19);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 6, 32);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 32);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 32, 32);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 45, 32);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 6, 45);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 19, 45);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 32, 45);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray), 45, 45);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.green), 6, 6);
    this.maze3.placeImageXY(
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.blue), 45, 45);

    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 38, 18);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 38, 32);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 38, 44);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 12, 18);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 12, 32);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 12, 44);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 25, 18);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 25, 32);
    this.maze3.placeImageXY(
        new RectangleImage(1, 13, OutlineMode.SOLID, Color.black), 25, 44);

    this.graph1 = new MazeWorld(13, 13);
    this.graph2 = new MazeWorld(13, 13);
    this.graph3 = new MazeWorld(13, 13);
  }

  //initialize some example Vertex/Edges to check for coordinates
  void initCoordinate() {
    from1 = new Vertex(22, 199, Color.GRAY);
    to1 = new Vertex(44, 255, Color.GRAY);
    edge1 = new Edge(from1, to1, 3, true);
    from2 = new Vertex(234, 12935, Color.GRAY);
    to2 = new Vertex(4, 5, Color.GRAY);
    edge2 = new Edge(from2, to2, 7, false);
    edge3 = new Edge(from1, to2, 3, false);
  }

  //initialize example HashMaps to check method Union
  void initUnion() {
    hashmap1 = new HashMap<Vertex, Vertex>();
    hashmap2 = new HashMap<Vertex, Vertex>();

    graph1 = new MazeWorld(4, 4);

    hashmap1.put(from1, from1);
    hashmap1.put(from2, from2);
    hashmap1.put(to1, from2);

    hashmap2.put(from1, from1);
    hashmap2.put(from2, from1);
    hashmap2.put(to1, from1);
  }

  //initialize example HashMaps to check for method Find
  void initFind() {
    hashmap1 = new HashMap<Vertex, Vertex>();

    from1 = new Vertex(22, 199, Color.GRAY);
    to1 = new Vertex(44, 255, Color.GRAY);
    from2 = new Vertex(234, 12935, Color.GRAY);

    graph1 = new MazeWorld(4, 4);
    hashmap1.put(from1, from1);
    hashmap1.put(from2, from1);
    hashmap1.put(to1, from2);
  }


  /////////////////////   METHOD TESTS   ///////////////////

  //***** IN ORDER OF EDGE, VERTEX, MAZEWORLD ********

  //test to check for overriding equality
  void testEquals(Tester t) {
    initExamples();
    //test for equals() for Edges
    t.checkExpect(edgeOne.equals(edgeOne), true);
    t.checkExpect(edgeOne.equals(edgeThree), true);
    t.checkExpect(edgeOne.equals(edgeTwo), false);
    t.checkExpect(edgeOne.equals(edgeFour), false);
    t.checkExpect(edgeOne.equals(edgeFive), false);

    //test for equals() for Vertex
    t.checkExpect(vertex1.equals(vertex1), true);
    t.checkExpect(vertex2.equals(vertex3), true);
    t.checkExpect(vertex1.equals(vertex2), false);
  }

  //test to check for overriding hashCode()
  void testHashCode(Tester t) {
    initExamples();

    //test for hashCode() for Edges
    t.checkExpect(edgeOne.hashCode(), 2000500040);
    t.checkExpect(edgeTwo.hashCode(), -1294567231);
    t.checkExpect(edgeThree.hashCode(), 2000500040);

    //test for hashCode() for Vertex
    t.checkExpect(vertex1.hashCode(), 200020);
    t.checkExpect(vertex2.hashCode(), 300020);
  }

  //test for coordinate-getting methods
  void testCoordinates(Tester t) {
    initCoordinate();
    //test for getX() 
    t.checkExpect(edge1.getXCoordinates(), 33);
    t.checkExpect(edge2.getXCoordinates(), 119);
    t.checkExpect(edge3.getXCoordinates(), 13);

    //test for getY() 
    t.checkExpect(edge1.getYCoordinates(), 226);
    t.checkExpect(edge2.getYCoordinates(), 6469);
    t.checkExpect(edge3.getYCoordinates(), 101);

    //test for trueX()
    t.checkExpect(from1.trueX(), 11);
    t.checkExpect(to1.trueX(), 22);
    t.checkExpect(from2.trueX(), 117);
    t.checkExpect(to2.trueX(), 2);

    //test for trueY()
    t.checkExpect(from1.trueY(), 99);
    t.checkExpect(to1.trueY(), 127);
    t.checkExpect(from2.trueY(), 6467);
    t.checkExpect(to2.trueY(), 2);
  }


  //test for drawing vertex/walls/squares
  void testDraw(Tester t) {
    initExamples();
    //test for draw Edges: drawSqaure()
    t.checkExpect(vertex1.drawSquare(Color.blue), 
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.blue));
    t.checkExpect(vertex2.drawSquare(Color.gray), 
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.gray));
    t.checkExpect(vertex2.drawSquare(Color.green), 
        new RectangleImage(13, 13, OutlineMode.SOLID, Color.green));



    //test for draw Vertex: drawWalls()
    t.checkExpect(edgeOne.drawWalls(), 
        new RectangleImage(2, 13, OutlineMode.SOLID, Color.black));
    t.checkExpect(edgeTwo.drawWalls(), 
        new RectangleImage(2, 13, OutlineMode.SOLID, Color.black));
    t.checkExpect(edgeFour.drawWalls(), 
        new RectangleImage(13, 2, OutlineMode.SOLID, Color.black));
  }

  //test for Vertex: setColor()
  void testSetColor(Tester t) {
    initExamples();

    //checks color before
    t.checkExpect(vertex1.color, Color.GRAY);
    t.checkExpect(vertex2.color, Color.GRAY);
    t.checkExpect(vertex3.color, Color.GRAY);

    //calls method
    vertex1.setColor(Color.blue);
    vertex2.setColor(Color.pink);
    vertex3.setColor(Color.orange);

    //checks color after
    t.checkExpect(vertex1.color, Color.blue);
    t.checkExpect(vertex2.color, Color.pink);
    t.checkExpect(vertex3.color, Color.orange);

  }

  //test newMaze() in MazeWorld
  void testNewMaze(Tester t) {
    initExamples();

    //make sure isEdge is false
    t.checkExpect(graph1.isEdge, false);
    t.checkExpect(graph2.isEdge, false);
    t.checkExpect(graph3.isEdge, false);

    //make sure kruzkalworlist is not empty
    t.checkExpect(graph1.kruzkalWorklist.size() > 0, true);
    t.checkExpect(graph2.kruzkalWorklist.size() > 0, true);
    t.checkExpect(graph3.kruzkalWorklist.size() > 0, true);

    //make sure edgesInTree is not empty
    t.checkExpect(graph1.edgesInTree.size() > 0, true);
    t.checkExpect(graph2.edgesInTree.size() > 0, true);
    t.checkExpect(graph3.edgesInTree.size() > 0, true);

    //make sure wall is not empty
    t.checkExpect(graph1.wall.size() > 0, true);
    t.checkExpect(graph2.wall.size() > 0, true);
    t.checkExpect(graph3.wall.size() > 0, true);

    //make sure BFSVisited is not empty
    t.checkExpect(graph1.BFSVisited.size() > 0, true);
    t.checkExpect(graph2.BFSVisited.size() > 0, true);
    t.checkExpect(graph3.BFSVisited.size() > 0, true);

    //make sure DFSVisited is not empty
    t.checkExpect(graph1.DFSVisited.size() > 0, true);
    t.checkExpect(graph2.DFSVisited.size() > 0, true);
    t.checkExpect(graph3.DFSVisited.size() > 0, true);

    //make sure autoGameFinished is true
    t.checkExpect(graph1.autoGameFinished, true);
    t.checkExpect(graph2.autoGameFinished, true);
    t.checkExpect(graph3.autoGameFinished, true);

    //make sure whichSearch is false;
    t.checkExpect(graph1.whichSearch, false);
    t.checkExpect(graph2.whichSearch, false);
    t.checkExpect(graph3.whichSearch, false);
  }

  //test resetMaze() in MazeWorld
  void testResetMaze(Tester t) {
    initExamples();

    //make sure index is 0
    t.checkExpect(graph1.index, 0);
    t.checkExpect(graph2.index, 0);
    t.checkExpect(graph3.index, 0);

    //make sure visited is empty
    t.checkExpect(graph1.visited.size() > 0, false);
    t.checkExpect(graph2.visited.size() > 0, false);
    t.checkExpect(graph3.visited.size() > 0, false);

    //make sure search is false
    t.checkExpect(graph1.search, false);
    t.checkExpect(graph2.search, false);
    t.checkExpect(graph3.search, false);

    //make sure correctPath is empty
    t.checkExpect(graph1.correctPath.size() > 0, false);
    t.checkExpect(graph2.correctPath.size() > 0, false);
    t.checkExpect(graph3.correctPath.size() > 0, false);

    //make sure currentXLocation and currentYLocation 
    //is 0
    t.checkExpect(graph1.currentXLocation, 0);
    t.checkExpect(graph2.currentXLocation, 0);
    t.checkExpect(graph3.currentXLocation, 0);
    t.checkExpect(graph1.currentYLocation, 0);
    t.checkExpect(graph2.currentYLocation, 0);
    t.checkExpect(graph3.currentYLocation, 0);

    //make sure playerTurn is false
    t.checkExpect(graph1.playerTurn, false);
    t.checkExpect(graph2.playerTurn, false);
    t.checkExpect(graph3.playerTurn, false);
  }

  //test for makeVertex() in MazeWorld
  void testMakeVertex(Tester t) {
    initExamples();

    //test sizes of board
    t.checkExpect(graph1.maze.size(), 13);
    t.checkExpect(graph2.maze.size(), 13);
    t.checkExpect(graph3.maze.size(), 13);

    //test colors of middle pieces
    t.checkExpect(graph1.maze.get(1).get(1).color, Color.GRAY);
    t.checkExpect(graph2.maze.get(1).get(1).color, Color.GRAY);
    t.checkExpect(graph3.maze.get(1).get(1).color, Color.GRAY);

    t.checkExpect(graph1.maze.get(10).get(10).color, Color.GRAY);
    t.checkExpect(graph2.maze.get(10).get(10).color, Color.GRAY);
    t.checkExpect(graph3.maze.get(10).get(10).color, Color.GRAY);

    t.checkExpect(graph1.maze.get(11).get(11).color, Color.GRAY);
    t.checkExpect(graph2.maze.get(11).get(11).color, Color.GRAY);
    t.checkExpect(graph3.maze.get(11).get(11).color, Color.GRAY);
  }

  //test for setVertexColor() in MazeWorld
  void testSetVertexColor(Tester t) {
    initExamples();

    //test the color of the start vertex
    t.checkExpect(graph1.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(graph2.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(graph3.maze.get(0).get(0).color, Color.GREEN);

    //test the color of the end vertex
    t.checkExpect(graph1.maze.get(12).get(12).color, Color.BLUE);
    t.checkExpect(graph2.maze.get(12).get(12).color, Color.BLUE);
    t.checkExpect(graph3.maze.get(12).get(12).color, Color.BLUE);
  }

  //test for connectEdges() in MazeWorld
  void testConnectEdges(Tester t) {
    initExamples();

    //tests to see if connectEdges() is adding to KruzkalWorklist
    //we didn't call connectEdges() because the constructor calls it
    t.checkExpect(graph1.kruzkalWorklist.size() > 0, true);
    t.checkExpect(graph2.kruzkalWorklist.size() > 0, true);
    t.checkExpect(graph3.kruzkalWorklist.size() > 0, true);
  }

  //test for makeOutEdges() in MazeWorld
  void testMakeOutEdges(Tester t) {
    initSeeds();
    mazeSeed1.makeOutEdges();

    // ===================== 2X2 GAME BOARD =====================

    //test to see if every out edge of each cell on the board is in the
    //list of total edges in the tree
    for(Edge e : mazeSeed1.maze.get(0).get(0).outEdges) {
      t.checkExpect(mazeSeed1.has(e.to, e.from, mazeSeed1.edgesInTree), true);
    }
    for(Edge e : mazeSeed1.maze.get(0).get(1).outEdges) {
      t.checkExpect(mazeSeed1.has(e.to, e.from, mazeSeed1.edgesInTree), true);
    }
    for(Edge e : mazeSeed1.maze.get(1).get(0).outEdges) {
      t.checkExpect(mazeSeed1.has(e.to, e.from, mazeSeed1.edgesInTree), true);
    }
    for(Edge e : mazeSeed1.maze.get(1).get(1).outEdges) {
      t.checkExpect(mazeSeed1.has(e.to, e.from, mazeSeed1.edgesInTree), true);
    }

    // ===================== 4X4 GAME BOARD =====================
    mazeSeed3.makeOutEdges();

    //test to see if every out edge of each cell on the board is in the
    //list of total edges in the tree
    for(Edge e : mazeSeed3.maze.get(0).get(0).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(1).get(0).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(2).get(0).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(3).get(0).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }

    for(Edge e : mazeSeed3.maze.get(0).get(1).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(1).get(1).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(2).get(1).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(3).get(1).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }

    for(Edge e : mazeSeed3.maze.get(0).get(2).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(1).get(2).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(2).get(2).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(3).get(2).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }

    for(Edge e : mazeSeed3.maze.get(0).get(3).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(1).get(3).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(2).get(3).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
    for(Edge e : mazeSeed3.maze.get(3).get(3).outEdges) {
      t.checkExpect(mazeSeed3.has(e.to, e.from, mazeSeed3.edgesInTree), true);
    }
  }

  //test for has() in MazeWorld
  void testHas(Tester t) {
    initExamples();

    t.checkExpect(graph1.has(vertex2, vertex1, edgeList2), true);
    t.checkExpect(graph2.has(vertex2, vertex3, edgeList2), false);
    t.checkExpect(graph3.has(vertex1, vertex2, edgeList1), true);
  }


  //test Kruzkal() in MazeWorld
  //Sort list of edges to create a minimum spanning tree
  void testKruzkal(Tester t) {
    initSeeds();

    // ===================== 2X2 GAME BOARD =====================

    for(Edge e : mazeSeed1.wall) {
      t.checkExpect(mazeSeed1.edgesInTree.contains(e), false);
    }


    for(Edge e : mazeSeed1.wall) {
      t.checkExpect(mazeSeed1.kruzkalWorklist.contains(e), true);
    }

    // ===================== 3X2 GAME BOARD =====================

    for(Edge e : mazeSeed2.wall) {
      t.checkExpect(mazeSeed2.edgesInTree.contains(e), false);
    }

    for(Edge e : mazeSeed2.wall) {
      t.checkExpect(mazeSeed2.kruzkalWorklist.contains(e), true);
    }

    // ===================== 4X4 GAME BOARD =====================

    for(Edge e : mazeSeed3.wall) {
      t.checkExpect(mazeSeed3.edgesInTree.contains(e), false);
    }
  }

  //test find() in MazeWorld
  void testFind(Tester t) {
    initFind();

    t.checkExpect(graph1.find(hashmap1, from1), from1);
    t.checkExpect(graph1.find(hashmap1, to1), from1);
    t.checkExpect(graph1.find(hashmap1, from2), from1);
  }

  //test union() in MazeWorld
  void testUnion(Tester t) {
    initUnion();

    graph1.union(hashmap1, from1, from2);
    graph1.union(hashmap1, from1, to1);
    graph1.union(hashmap1, from1, from1);

    t.checkExpect(hashmap1, hashmap2);
    t.checkExpect(hashmap1, hashmap2);
    t.checkExpect(hashmap1, hashmap2);
  }

  //test sortSmallest() in graph
  //to sort an list of edges from smallest to largest weight
  void testSortSmallest(Tester t) {
    initExamples();
    //test where most edges have different weight
    t.checkExpect(graphRandom.sortSmallest(edgeList1), 
        new ArrayList<Edge>(
            Arrays.asList(edgeFive, edgeOne, edgeThree, edgeFour, edgeTwo)));

    //test where all edges have the same weight(it should not impact their oder)
    t.checkExpect(graphRandom.sortSmallest(edgeList2), new ArrayList<Edge>(
        Arrays.asList(edgeOne, edgeOne, edgeOne, edgeOne, edgeOne)));
  }


  //test reconstruct() in MazeWorld
  void testReconstruct(Tester t) {
    initExamples();
    initSeeds();

    t.checkExpect(graph1.reconstruct(this.graph1.maze.get(12).get(12), 
        new ArrayList<Vertex>()).size() < graph1.bfs().size(), true);
    t.checkExpect(graph2.reconstruct(this.graph2.maze.get(12).get(12), 
        new ArrayList<Vertex>()).size() < graph2.bfs().size(), true);
    t.checkExpect(graph3.reconstruct(this.graph3.maze.get(12).get(12), 
        new ArrayList<Vertex>()).size() < graph3.bfs().size(), true);
    t.checkExpect(mazeSeed1.reconstruct(this.graph3.maze.get(1).get(1), 
        new ArrayList<Vertex>()).size() < graph3.bfs().size(), true);
    t.checkExpect(mazeSeed2.reconstruct(this.graph3.maze.get(2).get(1), 
        new ArrayList<Vertex>()).size() < graph3.bfs().size(), true);
    t.checkExpect(mazeSeed3.reconstruct(this.graph3.maze.get(3).get(3), 
        new ArrayList<Vertex>()).size() < graph3.bfs().size(), true);
  }

  //test for invalid input for the game
  void testCheckExceptions(Tester t) {
    t.checkConstructorException(
        new IllegalArgumentException("Invalid input; rows AND columns must"
            + " be greater than 0"),
        "MazeWorld", 0, 0);

    t.checkConstructorException(
        new IllegalArgumentException("Invalid input; rows AND columns must"
            + " be greater than 0"),
        "MazeWorld", 0, 1);

    t.checkConstructorException(
        new IllegalArgumentException("Invalid input; rows AND columns must"
            + " be greater than 0"),
        "MazeWorld", 1, 0);
  }


  //test GameHelpers
  void testGameHelp(Tester t) {
    initSeeds();
    initExamples();

    // ===================== 2X2 GAME BOARD ===================== 

    //TEST GAMEHELP() IN MAZEWORLD
    this.mazeSeed1.gameHelp(mazeSeed1.maze.get(0).get(0), 
        mazeSeed1.maze.get(0).get(1), 0, 0);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed1.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed1.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed1.gameHelp(mazeSeed1.maze.get(1).get(0), 
        mazeSeed1.maze.get(1).get(1), 1, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed1.maze.get(1).get(0).color, Color.GRAY);
    t.checkExpect(mazeSeed1.isEdge, false);



    // TEST DOWNHELP() IN MAZEWORLD
    this.mazeSeed1.downHelp(0, 0);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed1.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed1.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed1.downHelp(1, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed1.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed1.isEdge, false);



    // TEST UPHELP() IN MAZEWORLD
    this.mazeSeed1.upHelp(1, 1);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed1.maze.get(1).get(1).color, Color.GREEN);
    t.checkExpect(mazeSeed1.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed1.upHelp(0, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed1.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed1.isEdge, false);



    // TEST RIGHTHELP() IN MAZEWORLD
    this.mazeSeed1.rightHelp(1, 0);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed1.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed1.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed1.rightHelp(0, 1);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed1.maze.get(0).get(1).color, Color.YELLOW);
    t.checkExpect(mazeSeed1.isEdge, false);



    // TEST LEFTHELP() IN MAZEWORLD
    this.mazeSeed1.leftHelp(0, 1);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed1.maze.get(0).get(1).color, Color.GREEN);
    t.checkExpect(mazeSeed1.maze.get(0).get(0).color, Color.YELLOW);

    this.mazeSeed1.leftHelp(0, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed1.maze.get(0).get(0).color, Color.YELLOW);
    t.checkExpect(mazeSeed1.isEdge, false);



    // ===================== 3X2 GAME BOARD ===================== 

    //TEST GAMEHELP() IN MAZEWORLD
    this.mazeSeed2.gameHelp(mazeSeed2.maze.get(0).get(0), 
        mazeSeed2.maze.get(0).get(1), 0, 0);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed2.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed2.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed2.gameHelp(mazeSeed1.maze.get(1).get(0), 
        mazeSeed2.maze.get(1).get(1), 1, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed2.maze.get(1).get(0).color, Color.GRAY);
    t.checkExpect(mazeSeed2.isEdge, false);



    // TEST DOWNHELP() IN MAZEWORLD
    this.mazeSeed1.downHelp(0, 0);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed2.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed2.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed1.downHelp(1, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed2.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed2.isEdge, false);



    // TEST UPHELP() IN MAZEWORLD
    this.mazeSeed2.upHelp(1, 1);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed2.maze.get(1).get(1).color, Color.GREEN);
    t.checkExpect(mazeSeed2.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed2.upHelp(0, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed2.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed2.isEdge, false);



    // TEST RIGHTHELP() IN MAZEWORLD
    this.mazeSeed2.rightHelp(1, 0);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed2.maze.get(0).get(0).color, Color.GREEN);
    t.checkExpect(mazeSeed2.maze.get(0).get(1).color, Color.YELLOW);

    this.mazeSeed2.rightHelp(0, 1);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed2.maze.get(0).get(1).color, Color.YELLOW);
    t.checkExpect(mazeSeed2.isEdge, false);



    // TEST LEFTHELP() IN MAZEWORLD
    this.mazeSeed2.leftHelp(0, 1);

    //allows the user to move because there is no wall
    t.checkExpect(mazeSeed2.maze.get(0).get(1).color, Color.GREEN);
    t.checkExpect(mazeSeed2.maze.get(0).get(0).color, Color.YELLOW);

    this.mazeSeed2.leftHelp(0, 0);

    //doesn't allow the user to move because there is a wall
    t.checkExpect(mazeSeed2.maze.get(0).get(0).color, Color.YELLOW);
    t.checkExpect(mazeSeed2.isEdge, false);
  }

  void testBFSDFS(Tester t) {
    initExamples();

    // ========== DFS and BFS CLASS TESTS ============
    DFS<Vertex> s = new DFS<Vertex>(new Stack<Vertex>());
    BFS<Vertex> q = new BFS<Vertex>(new LinkedList<Vertex>());

    //checks if the stack/queue is originally empty
    t.checkExpect(s.isEmpty(), true);
    t.checkExpect(q.isEmpty(), true);

    //adds items
    s.add(ten);
    s.add(eleven);
    q.add(ten);
    q.add(eleven);

    //checks if the stack/queue is no longer empty
    t.checkExpect(s.isEmpty(), false);
    t.checkExpect(q.isEmpty(), false);

    //removes items
    s.remove();
    s.remove();
    q.remove();
    q.remove();

    //checks if the stack/queue is empty
    t.checkExpect(s.isEmpty(), true);
    t.checkExpect(q.isEmpty(), true);
  }

  void testOnKey(Tester t) {
    initSeeds();

    //because the letter P wasn't pressed before the arrow keys, 
    //isEdge will always be false
    mazeSeed1.onKeyEvent("up");
    t.checkExpect(mazeSeed1.isEdge, false);
    mazeSeed1.onKeyEvent("left");
    t.checkExpect(mazeSeed1.isEdge, false);
    mazeSeed1.onKeyEvent("down");
    t.checkExpect(mazeSeed1.isEdge, false);
    mazeSeed1.onKeyEvent("right");
    t.checkExpect(mazeSeed1.isEdge, false);

    //allows the player to play
    mazeSeed1.onKeyEvent("P");

    //location is 0, 0, the player shouldn't be able to move up or left
    //represented by isEdge being false
    mazeSeed1.onKeyEvent("up");
    t.checkExpect(mazeSeed1.isEdge, false);
    mazeSeed1.onKeyEvent("left");
    t.checkExpect(mazeSeed1.isEdge, false);

    mazeSeed1.currentXLocation = 1;
    mazeSeed1.currentYLocation = 1;

    //location is 1, 1 the player shouldn't be able to move down or right
    //represented by isEdge being false
    mazeSeed1.onKeyEvent("right");
    t.checkExpect(mazeSeed1.isEdge, false);
    mazeSeed1.onKeyEvent("down");
    t.checkExpect(mazeSeed1.isEdge, false);

    mazeSeed1.currentXLocation = 1;
    mazeSeed1.currentYLocation = 1;

    //at location 1, 1, the player should be able to move up OR left
    //represented by the X and Y locations changing
    mazeSeed1.onKeyEvent("up");
    t.checkExpect(mazeSeed1.currentXLocation, 1);
    t.checkExpect(mazeSeed1.currentYLocation, 0);
    
    mazeSeed1.currentXLocation = 1;
    mazeSeed1.currentYLocation = 1;
    
    mazeSeed1.onKeyEvent("left");
    t.checkExpect(mazeSeed1.currentXLocation, 1);
    t.checkExpect(mazeSeed1.currentYLocation, 1);

    mazeSeed1.currentXLocation = 0;
    mazeSeed1.currentYLocation = 0;

    //at location 0, 0, the player should be able to move down OR right
    //represented by the X and Y locations changing
    mazeSeed1.onKeyEvent("right");
    t.checkExpect(mazeSeed1.currentXLocation, 1);
    t.checkExpect(mazeSeed1.currentYLocation, 0);
    
    mazeSeed1.currentXLocation = 0;
    mazeSeed1.currentYLocation = 0;
    
    mazeSeed1.onKeyEvent("down");
    t.checkExpect(mazeSeed1.currentXLocation, 0);
    t.checkExpect(mazeSeed1.currentYLocation, 1);
  }



  // ========== END METHOD TESTS ============


  // ============== PLAY THE GAME HERE ===========
  //Change input for MazeWorld to change maze to desire rows and columns
  //initialize the bigBang for MazeWorld game
  void testMazeWorld(Tester t) {
    MazeWorld starterWorld = new MazeWorld(50, 30);
    starterWorld.bigBang(starterWorld.rows * 50, 
        starterWorld.columns * 50, 0.05);
  }

}
