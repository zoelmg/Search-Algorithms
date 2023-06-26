// Represents a mutable collection of items
interface ICollection<T> {
  
  // Is this collection empty?
  boolean isEmpty();
  
  // Returns the first item of the collection
  // EFFECT: removes that first item
  T remove();

  void add(T item);
}