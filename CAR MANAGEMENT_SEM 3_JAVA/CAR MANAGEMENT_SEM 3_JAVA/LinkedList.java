public class LinkedList {
    //insert specified element into the list
    private ListNode firstNode;
    private ListNode lastNode;
    private ListNode currNode;

    public LinkedList()
    {
        firstNode = lastNode = currNode = null;
    }

    public void insertAtBack (Car element)
    {
        // return the first data in the list
        if(isEmpty())
            firstNode = lastNode = new ListNode(element);
        else
            lastNode = lastNode.next = new ListNode(element);
    }

    public Object getFirst()
    {
        if(isEmpty())
            return null;
        else
        {
            currNode = firstNode;
            return currNode.data;
        }
    }
    
    public Object getNext()
    {
        if(currNode != lastNode)
        {
            currNode = currNode.next;
            return currNode.data;
        }
        else
            return null;
    }
    
    public boolean isEmpty()
    {
        return firstNode == null;
    }
    
    public void bubbleSort()
    {
        ListNode current = firstNode;
        ListNode index = null;
        Object temp;
        
        while (current != null)
        {
            index = current.next;
            while (index != null)
            {
                Car data1 = (Car)current.data;
                Car data2 = (Car)index.data;
                
                if ((data1.getCustomerName()).compareToIgnoreCase(data2.getCustomerName()) > 0)
                {
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
                
                index = index.next;
            }
            
            current = current.next;
        }
    }
}