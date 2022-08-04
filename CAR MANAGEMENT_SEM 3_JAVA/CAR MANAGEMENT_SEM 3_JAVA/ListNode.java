public class ListNode
{
    Object data;
    ListNode next;
    
    public ListNode(Object o)
    {
        data = o;
    }
    
    ListNode(Object o, ListNode nextNode)
    {
        data = o;
        next = nextNode;
    }
    
    Object getObject()
    {
        return data;
    }
    
    ListNode getLink()
    {
        return next;
    }
    
}