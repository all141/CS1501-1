import java.util.*;

/**
 * Created by Lenny on 3/19/16.
 */
public class PQNode implements Comparable<PQNode> {

    int pos;
    int val;

    PQNode(int p, int v){
        pos=p;
        val=v;
    }

    @Override
    public int compareTo(PQNode pqNode) {
        int otherVal = pqNode.val;
        int comp = val-otherVal;
        if(comp>0) return 1;
        if(comp<0) return -1;
        if(comp==0) return 0;
        else return 0;
    }
}
