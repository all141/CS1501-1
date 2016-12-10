/**
 * Created by Lenny on 2/6/16.
 */
public class LinkedList {
    public Node head;
    public int size;

    public LinkedList(char c){
        head = new Node(c, this);
        size=1;
    }

    public LinkedList(){
        head = new Node('\0', this);
        size=1;
    }

    public Node contains(char c){

        Node searcher = head;
        if(searcher.getData()==c) return searcher;

        while(searcher.hasNeighbor()){

            searcher = searcher.getNeighbor();
            if(searcher.getData()==c) return searcher;


        }

        return null;
    }


//    public char[] getSome(){
//        char[] send = new char[size];
//        Node searcher = head;
//        for(int x = 0; x<size; x++){
//
//
//
//        }
//
//    }
//

    public void check(){
        Node searcher = head;

        while (searcher.hasNeighbor()){
            System.out.print(searcher.getData()+", ");

            searcher = searcher.getNeighbor();


        }
        System.out.println();
    }

    public Node add(char c){
        size++;
        if(head.getData()=='\0'){
            head.setData(c);
            return head;
        }

        Node newNode = new Node(c, this);
        Node searcher=head;

        while(searcher.hasNeighbor()){
            searcher = searcher.getNeighbor();
        }
        searcher.setNeighbor(newNode);
        return newNode;
    }

}
