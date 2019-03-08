

public class LinkedListQueue<E> implements Queue<E>{

  private SinglyLinkedList<E> list;

  public LinkedListQueue(){
    list = new SinglyLinkedList<E>();
  }
  public int size(){
    return list.size();
  }
  public boolean isEmpty(){
    return list.size() == 0;
  }
  public E first(){
    return list.first();
  }
  public void enqueue(E node){
    list.addLast(node);
  }
  public E dequeue(){
    return list.removeFirst();
  }
}
