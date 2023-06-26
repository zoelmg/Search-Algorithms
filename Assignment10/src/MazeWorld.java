import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import javalib.impworld.*;
import javalib.worldimages.TextImage;

//represent the Maze game world
class MazeWorld extends World {
  //the number of rows and columns
  //of the maze & set up of the maze
  int rows;
  int columns;
  ArrayList<ArrayList<Vertex>> maze;
  ArrayList<Edge> kruzkalWorklist;
  ArrayList<Edge> edgesInTree;
  ArrayList<Edge> wall;
  HashMap<Vertex, Edge> cameFromEdge;
  boolean isEdge;

  //user's current maze location
  int currentXLocation;
  int currentYLocation;

  //tick index and status of the games
  int index;
  boolean search;
  boolean autoGameFinished;
  boolean whichSearch;
  boolean playerTurn;

  //list of vertexes depending on the searches
  ArrayList<Vertex> visited;
  ArrayList<Vertex> BFSVisited;
  ArrayList<Vertex> DFSVisited;
  ArrayList<Vertex> correctPath;


  //constructor for a random maze
  MazeWorld(int columns, int rows) {
    if (columns == 0 || rows == 0) {
      throw new IllegalArgumentException("Invalid input; rows AND columns must"
          + " be greater than 0");
    } else {
      this.rows = rows;
      this.columns = columns;
      newMaze();
    }
  }

  //convenience constructor for a seeded maze
  MazeWorld(int rows, int columns, ArrayList<Integer> seededWeight) {
    this.rows = rows;
    this.columns = columns;
    this.maze = new ArrayList<ArrayList<Vertex>>();
    this.kruzkalWorklist = new ArrayList<Edge>();
    this.edgesInTree = new ArrayList<Edge>();
    this.wall = new ArrayList<Edge>();
    this.cameFromEdge = new HashMap<Vertex, Edge>();
    this.isEdge = false;

    makeVertex();

    //initializing the worklist by creating all edges
    for (int j = 0; j < this.maze.size(); j++) {
      for (int i = 0; i < this.maze.get(0).size(); i++) {

        //top edge
        if (j - 1 >= 0) {
          this.kruzkalWorklist.add(new Edge(this.maze.get(j - 1).get(i),
              this.maze.get(j).get(i), seededWeight.get(i), false));
        }

        //left edge
        if (i - 1 >= 0) {
          this.kruzkalWorklist.add(new Edge(this.maze.get(j).get(i - 1),
              this.maze.get(j).get(i), seededWeight.get(i) * 2, true));
        }
      }
    }

    //sort the worklist from smallest to largest weighted edges
    this.kruzkalWorklist = sortSmallest(this.kruzkalWorklist);

    //initialize the edgesInTree list and the correct 
    //list of walls (walls to be drawn where there are no connecting edges)
    //to create a minimum spanning tree, i.e the maze
    kruzkal();

    this.autoGameFinished = true;
    this.whichSearch = false;
    this.BFSVisited = this.bfs();
    this.DFSVisited = this.dfs();

    resetMaze();
  }



  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------
  ////////////// ============== MAZE-GRAPH DATA SET UP ================ /////////////////
  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------



  //generate a completely NEW Maze to play
  void newMaze() {
    this.maze = new ArrayList<ArrayList<Vertex>>();
    this.kruzkalWorklist = new ArrayList<Edge>();
    this.edgesInTree = new ArrayList<Edge>();
    this.wall = new ArrayList<Edge>();
    this.cameFromEdge = new HashMap<Vertex, Edge>();
    this.isEdge = false;

    makeVertex();
    connectEdges();

    //sort the all the edges in the worklist from smallest to largest weighted edges
    this.kruzkalWorklist = sortSmallest(this.kruzkalWorklist);

    //initialize the edgesInTree list and the correct 
    //list of walls (walls to be drawn where there are no connecting edges)
    //to create a minimum spanning tree, i.e the maze
    kruzkal();

    this.autoGameFinished = true;
    this.whichSearch = false;
    this.BFSVisited = this.bfs();
    this.DFSVisited = this.dfs();

    resetMaze();
  }

  //resets the graph WITHOUT generating a new Maze
  void resetMaze() {
    this.setVertexColors();

    this.index = 0;
    this.visited = new ArrayList<Vertex>();
    this.search = false;
    this.correctPath = new ArrayList<Vertex>();
    this.currentXLocation = 0;
    this.currentYLocation = 0;
    this.playerTurn = false;
  }

  //initialize the maze by creating all the vertex
  void makeVertex() {
    for (int j = 0; j < this.rows; j++) {
      this.maze.add(new ArrayList<Vertex>());
      for (int i = 0; i < this.columns; i++) {
        this.maze.get(j).add(
            new Vertex(13 * i + 13 / 2, 13 * j + 13 / 2, Color.GRAY));
      }
    }
    setVertexColors();
  }

  //set the maze to its original colors
  void setVertexColors() {
    for (int j = 0; j < this.rows; j++) {
      for (int i = 0; i < this.columns; i++) {
        this.maze.get(j).get(i).setColor(Color.gray);
      }
    }

    this.maze.get(0).get(0).setColor(Color.GREEN);
    this.maze.get(rows - 1).get(columns - 1).setColor(Color.BLUE);
  }

  //initializing the kruzkalWorklist by creating all edges
  void connectEdges() {
    for (int j = 0; j < this.rows; j++) {
      for (int i = 0; i < this.columns; i++) {

        //top edge
        if (j - 1 >= 0) {
          Edge temp = new Edge(this.maze.get(j - 1).get(i),
              this.maze.get(j).get(i), new Random().nextInt(100), false);

          this.kruzkalWorklist.add(temp);
        }

        //left edge
        if (i - 1 >= 0) {
          Edge temp = new Edge(this.maze.get(j).get(i - 1),
              this.maze.get(j).get(i), new Random().nextInt(100), true);
          this.kruzkalWorklist.add(temp);
        }
      }
    }
  }


  //create a list of valid outEdges for every vertex on the graph
  void makeOutEdges() {
    for(int j = 0; j < this.rows; j++) {
      for(int i = 0; i < this.columns; i++) {

        //top edges
        if (j - 1 >= 0 && has(this.maze.get(j).get(i),
            this.maze.get(j - 1).get(i), this.edgesInTree)) {
          this.maze.get(j - 1).get(i).addEdges(new Edge(this.maze.get(j - 1).get(i),
              this.maze.get(j).get(i), 0, false));
          this.maze.get(j).get(i).addEdges(new Edge(this.maze.get(j).get(i),
              this.maze.get(j - 1).get(i), 0, false));
        }

        //left edges
        if (i - 1 >= 0 && has(this.maze.get(j).get(i),
            this.maze.get(j).get(i - 1), this.edgesInTree)) {
          this.maze.get(j).get(i).addEdges(new Edge(this.maze.get(j).get(i),
              this.maze.get(j).get(i - 1), 0, false));
          this.maze.get(j).get(i - 1).addEdges(new Edge(this.maze.get(j).get(i - 1),
              this.maze.get(j).get(i), 0, false));
        }
      }
    }
  }

  //checks if a given list contains two vertexes, in whichever order
  public boolean has(Vertex v1, Vertex v2, ArrayList<Edge> list) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).from.equals(v1) && list.get(i).to.equals(v2)
          || list.get(i).to.equals(v1) && list.get(i).from.equals(v2)) {
        return true;
      }
    }
    return false;
  }


  //sort a list of edges edges from smallest weight to biggest weight
  ArrayList<Edge> sortSmallest(ArrayList<Edge> worklist) {
    ArrayList<Edge> temp = new ArrayList<Edge>(worklist.size());
    while (worklist.size() > 0) {
      Edge smallest = worklist.get(0);
      for (int i = 1; i < worklist.size(); i++) {
        if (worklist.get(i).weight < smallest.weight) {
          smallest = worklist.get(i);
        }
      }
      temp.add(smallest);
      worklist.remove(worklist.indexOf(smallest));
    }
    return temp;
  }


  //----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------
  /////////////////    =============  KRUSKAL ===================      /////////////////
  //----------------------------------------------------------------------------------//
  //----------------------------------------------------------------------------------//

  //the Kruskal formula for this graph which will link the vertexes together
  //by creating the minimum spanning tree
  void kruzkal() {
    HashMap<Vertex, Vertex> representatives = new HashMap<Vertex, Vertex>();

    for (int j = 0; j < this.rows; j++) {
      for (int i = 0; i < columns; i++) {
        representatives.put(this.maze.get(j).get(i), this.maze.get(j).get(i));
      }
    }

    this.wall = new ArrayList<Edge>(kruzkalWorklist);

    while (edgesInTree.size() < (columns * rows) - 1) {
      Vertex x = this.kruzkalWorklist.get(0).from;
      Vertex y = this.kruzkalWorklist.get(0).to;
      if (find(representatives, x).equals(find(representatives, y))) {
        kruzkalWorklist.remove(0);
      }
      else {
        this.edgesInTree.add(this.kruzkalWorklist.get(0));
        this.wall.remove(this.wall.indexOf(this.kruzkalWorklist.get(0)));
        union(representatives, find(representatives, x), find(representatives, y));
      }
    }
  }

  //Unions two representatives, and sets the value of 
  //one representative’s representative to the other.
  void union(HashMap<Vertex, Vertex> representatives, Vertex x, Vertex y) {
    representatives.remove(y, representatives.get(y));
    representatives.put(y, x);
  }

  //Recursively looks up a representative to find its parent that maps to itself
  Vertex find(HashMap<Vertex, Vertex> representatives, Vertex x) {
    if (representatives.get(x).equals(x)) {
      return x;
    }
    else {
      return find(representatives, representatives.get(x));
    }
  }









  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------
  /////////////  ============= BFS, DFS =================== ////////////////
  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------


  //returns an array-list containing all the visited vertex 
  //when breath-first search was used
  ArrayList<Vertex> bfs() {
    return searchHelp(new BFS<Vertex>(new LinkedList<Vertex>()));
  }

  //returns an array-list containing all the visited vertex 
  //when depth-first search was used
  ArrayList<Vertex> dfs() {
    return searchHelp(new DFS<Vertex>(new Stack<Vertex>()));
  }


  //Search through the maze for the correct Path
  ArrayList<Vertex> searchHelp(ICollection<Vertex> workinglist) {
    makeOutEdges();
    ArrayList<Vertex> alreadySeen = new ArrayList<Vertex>();

    // Initialize the work list from the starting vertex
    workinglist.add(this.maze.get(0).get(0));

    while (!workinglist.isEmpty()) {
      Vertex next = workinglist.remove();
      if (next.equals(this.maze.get(rows - 1).get(columns - 1))) {
        break;
      }

      else if (!alreadySeen.contains(next)) {
        for (Edge e : next.outEdges) {
          workinglist.add(e.to);
          if(!this.cameFromEdge.containsKey(e.to)) {
            this.cameFromEdge.put(e.to, e);
          }
        }

        alreadySeen.add(next);
      }
    }


    return alreadySeen;
  }


  //traces the hashmap backwards until 
  //the moving Vertex reaches the beginning Vertex from the ending Vertex
  public ArrayList<Vertex> reconstruct(Vertex next, ArrayList<Vertex> correctPath) {
    if(!next.equals(this.maze.get(0).get(0))){
      correctPath.add(next);
      reconstruct(this.cameFromEdge.get(next).from, correctPath);
    }

    correctPath.add(this.maze.get(0).get(0));
    return correctPath;
  }









  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------
  ////////////// ============== MAZEWORLD METHODS =================== /////////////////
  /////////////  ========== MAKESCENE, ONKEY, ONTICK ================ ////////////////
  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------

  //make the scene of the Maze game
  public WorldScene makeScene() {
    //create a worldScene that represent this maze (just the vertexes)
    WorldScene background = new WorldScene(columns * 13, rows * 19);
    for (int j = 0; j < rows; j++) {
      for (int i = 0; i < columns; i++) {
        background.placeImageXY(this.maze.get(j).get(i).
            drawSquare(this.maze.get(j).get(i).color),
            this.maze.get(j).get(i).x, 
            this.maze.get(j).get(i).y);
      }
    }

    //draws the walls between each cell (an abscence of an edge)
    for (int i = 0; i < this.wall.size(); i++) {
      background.placeImageXY(this.wall.get(i).drawWalls(), 
          this.wall.get(i).getXCoordinates(), this.wall.get(i).getYCoordinates());
    }


    //Display instruction in the initial state
    if (this.autoGameFinished && !this.playerTurn) {
      background.placeImageXY(new TextImage("Press: R to reset Maze anytime" , 12,  Color.black), 
          this.columns * 6, this.rows * 14);
      background.placeImageXY(new TextImage("M for new maze anytime, P to play, " , 12,  Color.black), 
          this.columns * 6, this.rows * 15);
      background.placeImageXY(new TextImage("B for BSF, D for DFS, S to toggle view" , 12,  Color.black), 
          this.columns * 6, this.rows * 16);
    }

    //Display score for BFS and DFS only when an auto search is occuring
    else if (this.search) {
      background.placeImageXY(new TextImage("SCORE OF WRONG MOVES FOR BFS: " + 
          (this.BFSVisited.size() -  this.correctPath.size()), 12, Color.black), 
          this.columns * 6, this.rows * 15);

      background.placeImageXY(new TextImage("SCORE OF WRONG MOVES FOR DFS: " + 
          (this.DFSVisited.size() -  this.correctPath.size()), 12,  Color.black), 
          this.columns * 6, this.rows * 17);

    }  else if (this.playerTurn) {
      background.placeImageXY(new TextImage("Press: R to reset Maze anytime" , 12,  Color.black), 
          this.columns * 6, this.rows * 14);
      background.placeImageXY(new TextImage("M for new maze anytime" , 12,  Color.black), 
          this.columns * 6, this.rows * 15);
      background.placeImageXY(new TextImage("PLAY MODE: Use up, down," , 12,  Color.black), 
          this.columns * 6, this.rows * 16);
      background.placeImageXY(new TextImage("left, right key to play" , 12,  Color.black), 
          this.columns * 6, this.rows * 17);
    }

    return background;
  }


  //animates the different searches and user's move
  //based on the current tick
  public void onTick() {

    //------- ANIMATES THE SEARCH ----------
    if (this.index < this.visited.size() && this.search) {
      this.autoGameFinished = false;
      this.visited.get(index).setColor(Color.pink);
      this.index++;
    } else {
      this.autoGameFinished = true;
      this.search = false;
    }

    //------- SHOWS CORRECT PATH AFTER SEARCH IS DONE -----
    if (this.index == this.visited.size()) {
      for (int i = 0; i < this.correctPath.size(); i++) {
        this.correctPath.get(i).setColor(Color.magenta);
      }
    }

    //------ ANIMATE USER'S MOVEMENT ---------
    if (this.currentXLocation == this.columns - 1 && this.currentYLocation == this.rows - 1) {
      this.correctPath = this.reconstruct(this.maze.get(this.rows - 1).get(this.columns - 1), 
          new ArrayList<Vertex>());
      for (int i = 0; i < this.correctPath.size(); i++) {
        this.correctPath.get(i).setColor(Color.magenta);
      }
      this.playerTurn = false;
    }
  }

  //keys to control the game
  public void onKeyEvent(String key) {

    // ------- RESET MAZE BY R -------------
    if (key.equalsIgnoreCase("r")) {
      resetMaze();
    }    

    // ------- GENERATE NEW BOARD BY M -------------
    else if (key.equalsIgnoreCase("m")) {
      newMaze();
    } 

    // ------- PLAY BREATH FIRST SEARCH ------------
    else if (key.equalsIgnoreCase("b") && this.autoGameFinished) {
      resetMaze();
      this.visited = this.bfs(); 
      this.correctPath = this.reconstruct(this.maze.get(this.rows - 1).get(this.columns - 1), 
          new ArrayList<Vertex>());
      this.search = true;
    }

    // ------- PLAY DEPTH FIRST SEARCH ------------
    else if (key.equalsIgnoreCase("d") && this.autoGameFinished) {
      resetMaze();
      this.visited = this.dfs(); 
      this.correctPath = this.reconstruct(this.maze.get(this.rows - 1).get(this.columns - 1), 
          new ArrayList<Vertex>());
      this.search = true;
    } 

    // ------- PLAY BREATH FIRST SEARCH AND DEPTH FIRST SEARCH VIEW ------------
    else if (key.equalsIgnoreCase("s") && this.autoGameFinished) {
      resetMaze();
      if (this.whichSearch) {
        for (int i = 0; i < this.BFSVisited.size(); i++) {
          this.BFSVisited.get(i).setColor(Color.gray);
        }
        for(int i = 0; i < this.DFSVisited.size(); i++) {
          this.DFSVisited.get(i).setColor(Color.pink);
        }
        this.maze.get(this.rows - 1).get(this.columns - 1).setColor(Color.pink);;
        this.whichSearch = !this.whichSearch;
      }
      else {
        for (int i = 0; i < this.DFSVisited.size(); i++) {
          this.DFSVisited.get(i).setColor(Color.gray);
        }
        for (int i = 0; i < this.BFSVisited.size(); i++) {
          this.BFSVisited.get(i).setColor(Color.pink);
        }
        this.maze.get(this.rows - 1).get(this.columns - 1).setColor(Color.pink);;
        this.whichSearch = !this.whichSearch;
      }
    }

    //------- START USER GAME PLAY ------------
    else if (key.equalsIgnoreCase("p")) {
      resetMaze();
      this.playerTurn = true;
    } 

    //------- USER GAME PLAY --------------------

    //handles when the user presses the down key
    else if (key.equals("down") && this.playerTurn && this.autoGameFinished) {
      this.downHelp(this.currentYLocation, this.currentXLocation);
      if (this.isEdge) {
        this.currentYLocation++;
      }
    }

    //handles when the user presses the up key
    else if (key.equals("up") && this.playerTurn && this.autoGameFinished) {
      this.upHelp(this.currentYLocation, this.currentXLocation);
      if (this.isEdge) {
        this.currentYLocation--;
      }
    }

    //handles when the user presses the left key
    else if (key.equals("left") && this.playerTurn && this.autoGameFinished) {
      this.leftHelp(this.currentYLocation, this.currentXLocation);
      if (this.isEdge) {
        this.currentXLocation--;
      }
    }

    //handles when the user presses the right key
    else if (key.equals("right") && this.playerTurn && this.autoGameFinished) {
      this.rightHelp(this.currentYLocation, this.currentXLocation);
      if (this.isEdge) {
        this.currentXLocation++;
      }
    }
  } 








  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------
  ////////////// ============== User Game Play Helpers =================== /////////////////
  //-----------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------
  public void gameHelp(Vertex from, Vertex to, int currentY, int currentX) {
    if (!this.has(from, to, this.wall)) {
      from.setColor(Color.green);
      to.setColor(Color.yellow);
      this.isEdge = true;
    } else {
      this.isEdge = false;
    }
  }

  //deals with the event in which the player presses the down arrow
  public void downHelp(int currentY, int currentX) {
    if (currentY < this.rows - 1) {
      this.isEdge = true;
      gameHelp(this.maze.get(currentY).get(currentX), 
          this.maze.get(currentY + 1).get(currentX),
          currentY, currentX);
    } else {
      this.isEdge = false;
    }
  }

  //deals with the event in which the player presses the up arrow
  public void upHelp(int currentY, int currentX) {
    if (currentY > 0) {
      this.isEdge = true;
      gameHelp(this.maze.get(currentY).get(currentX), 
          this.maze.get(currentY - 1).get(currentX),
          currentY, currentX);
    } else {
      this.isEdge = false;
    }
  }

  //deals with the event in which the player presses the right arrow
  public void rightHelp(int currentY, int currentX) {
    if (currentX < this.columns - 1) {
      this.isEdge = true;
      gameHelp(this.maze.get(currentY).get(currentX), 
          this.maze.get(currentY).get(currentX + 1),
          currentY, currentX);
    } else {
      this.isEdge = false;
    }
  }

  //deals with the event in which the player presses the left arrow
  public void leftHelp(int currentY, int currentX) {
    if (currentX > 0) {
      this.isEdge = true;
      gameHelp(this.maze.get(currentY).get(currentX), 
          this.maze.get(currentY).get(currentX - 1),
          currentY, currentX);
    } else {
      this.isEdge = false;
    }
  }

}