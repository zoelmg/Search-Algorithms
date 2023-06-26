import java.util.Stack;

//represent a stacks used in Breath-First Search
public class DFS<T> implements ICollection<T>{
  Stack<T> workinglist;

  DFS(Stack<T> workinglist){
    this.workinglist = workinglist;
  }

  //Is this stack empty?
  public boolean isEmpty() {
    return this.workinglist.isEmpty();
  }

  //Returns the first item of the stack
  // EFFECT: removes that first item
  public T remove() {
    return this.workinglist.pop();
  }

  //add items to this collection
  public void add(T item) {
    this.workinglist.push(item);
  }
}