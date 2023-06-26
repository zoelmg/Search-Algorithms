import java.util.Queue;


//represent a queue used in Breath-First Search
public class BFS<T> implements ICollection<T>{
  Queue<T> workinglist;

  BFS(Queue<T> workinglist){
    this.workinglist = workinglist;
  }

  //Is this queue empty?
  public boolean isEmpty() {
    return this.workinglist.isEmpty();
  }

  //Returns the first item of this queue
  // EFFECT: removes that first item
  public T remove() {
    return this.workinglist.remove();
  }

  //add items to this collection
  public void add(T item) {
    this.workinglist.add(item);
  }


}
