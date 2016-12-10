/**
 * Created by Lenny on 2/6/16.
 */
import java.util.*;

public class Node {
    private Node child;
    private Node neighbor;
    private char data;
    private boolean end;
    private LinkedList myList;

    Node(char data, LinkedList myList){
        this.data=data;
        child=null;
        neighbor=null;
        end=false;
        this.myList=myList;
    }

    Node(char data, Node child, Node neighbor, boolean end, LinkedList myList){
        this.data=data;
        this.child=child;
        this.neighbor=neighbor;
        this.end=end;
        this.myList=myList;
    }

    Node(){
        child=null;
        neighbor=null;
        data='\0';
        end=false;
        myList=null;
    }

    public void setMyList(LinkedList myList) {
        this.myList = myList;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public void setData(char data) {
        this.data = data;
    }

    public void setNeighbor(Node neighbor) {
        this.neighbor = neighbor;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public char getData() {
        return data;
    }

    public Node getChild() {
        return child;
    }

    public Node getNeighbor() {
        return neighbor;
    }

    public boolean hasMyList() { return myList != null;}

    public LinkedList getMyList() {
        return myList;
    }

    public boolean isEnd() {
        return end;
    }

    public boolean hasChild(){
        if(child!=null) return true;
        return false;
    }

    public boolean hasNeighbor(){
        if(neighbor!=null) return true;
        return false;
    }
}
