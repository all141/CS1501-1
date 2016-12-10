/**
 * Created by Lenny on 2/6/16.
 */
public class DLB {
    LinkedList root;

    public DLB(){
        root = new LinkedList();
    }



    public void add(String s){
        s=s.toLowerCase();
        LinkedList SList = root;
        boolean ending = false;
        for(int x=0; x<s.length(); x++){
            if(x==s.length()-1) ending = true;
            SList=add(s.charAt(x), SList, ending);
        }


    }

    public LinkedList add(char c, LinkedList list, boolean ending){
        Node search = list.contains(c);

        if(search!=null){    //if list contains c

            if(search.hasChild()) {  //if node containing c has a child

                if(ending){
                    search.setEnd(true);
                    return null;
                }

                return search.getChild().getMyList();
            }

            else{ // if node containing c does NOT have a child

                if(!ending){     //if there are more chars to be added to this word
                    LinkedList back = new LinkedList();
                    search.setChild(back.head);
                    return back;
                }
                else{
                    search.setEnd(true);
                    return null;
                }
            }
        }

        else{  //if list does NOT contain c

            Node added = list.add(c);
            if(!ending){     //if there are more chars to be added to this word
                LinkedList back = new LinkedList();
                added.setChild(back.head);
                return back;
            }
            else{
                added.setEnd(true);
                return null;
            }
        }

    }


    public String getNext(String sub, String whole){
        String goodChars = "bcdefghjklmnopqrstuvwxyz02356789!@$^_*";
        LinkedList list;
        int y = 0;

        char[] done = new char[5];
        char[] word = sub.toCharArray();
        for(int x =0; x<word.length; x++){

            done[x]=word[x];
            y=x;
        }
        y++;

        Node search = root.head;

        for(int x = 0; x<sub.length();x++){
            search = search.getMyList().contains(sub.charAt(x));
            //if(search==null)return null;
            if(x<sub.length()-1) search = search.getChild();
        }
        //System.out.println(search.getData());

        while(y<5) {
            //System.out.println("WHILE TIME : "+y);
            list = search.getChild().getMyList();       //get list of next char
            char imm = whole.charAt(y);      //get next char to look for
            //System.out.println("imm: "+imm);
//            if (imm == 'a') imm = 'b';
//            if (imm == 'i') imm = 'j';
//            if (imm == '1') imm = '2';
//            if (imm == '4') imm = '5';

            int look = goodChars.indexOf(imm)+1;       //get index of that char in goodChars
            if (look == goodChars.length()) look = 0;


            search = list.contains(goodChars.charAt(look));
            while (search == null) {
                look++;
                if (look == goodChars.length()) look = 0;
                search = list.contains(goodChars.charAt(look));

            }

            done[y] = search.getData();
            //System.out.println("DONE THING "+done[y]);
            y++;
        }
        String finish = new String(done);
        return finish;
    }

    public boolean contains(String find){

        Node search = root.head;

        for(int x = 0; x<find.length();x++){
            search = search.getMyList().contains(find.charAt(x));
            if(search==null)return false;
            if(x<find.length()-1) search = search.getChild();

        }
        return search.isEnd();

    }

    public int prefix(String find){

        Node search = root.head;

        for(int x = 0; x<find.length();x++){
            search = search.getMyList().contains(find.charAt(x));
            if(search==null)return 0;      //returns 0 if find is neither a word or prefix
            if(x<find.length()-1) search = search.getChild();

        }

        if(search.isEnd() && search.hasChild()) return 3;   // returns 3 if find is word and prefix
        if(!search.isEnd() && search.hasChild()) return 2;  // returns 2 if find is not word but is a prefix
        if(search.isEnd() && !search.hasChild()) return 1;  // returns 1 if find is a word but not a prefix
        return 0;
    }


}
