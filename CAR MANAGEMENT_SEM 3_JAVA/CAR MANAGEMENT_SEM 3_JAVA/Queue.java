import java.util.LinkedList;
public class Queue
{
    protected LinkedList list;
    
    public Queue()
    {
        list = new LinkedList();
    }
    
    public void enqueue(Object elem)
    {
        list.addLast(elem);
    }
    
    public Object dequeue()
    {
        return list.removeFirst();
    }
    
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    
    public Object front()
    {
        return list.getFirst();
    }
    
    public Object rear()
    {
        return list.getLast();
    }
    
    public int size()
    {
        return list.size();
    }
}