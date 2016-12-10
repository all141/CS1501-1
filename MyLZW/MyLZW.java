/**
 * Created by Lenny on 2/22/16.
 */
import java.lang.*;


public class MyLZW {
    private static int R = 256;        // number of input chars
    private static int L = 512;       // number of codewords = 2^W
    private static int W = 9;         // codeword width

    public static void compressN() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF

        BinaryStdOut.write('n'); //write type of compression

        while (input.length() > 0) {

            if(code==L && W<16){
                //System.err.println("EXPANDING");
                W++;
                L = (int)Math.pow(2,W);
            }

            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void compressR() {


        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF

        BinaryStdOut.write('r'); //write type of compression

        while (input.length() > 0) {

            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);

            if(code==65536){  //reset
                //System.err.println("RESETTING");
                L = 512;
                W = 9;

                st = new TST<Integer>();
                for (int i = 0; i < R; i++)
                    st.put("" + (char) i, i);
                code = R+1;  // R is codeword for EOF
            }

            if(code==L && W<16){
                //System.err.println("EXPANDING");
                W++;
                L = (int)Math.pow(2,W);
            }


            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void compressM() {


        int in=0;
        int out=0;
        double oldRatio = 0;
        double newRatio = 0;
        double RatRatio = 0;

        String input = BinaryStdIn.readString();
        //System.err.println(input);
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF

        BinaryStdOut.write('m'); //write type of compression

        while (input.length() > 0) {

            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            //System.err.println(s);
            BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);

            if(code==65536) {  //reset

                in = in + (t * 8);
                out = out + W;
                newRatio = in / out;
                if (oldRatio == 0) oldRatio = newRatio;

                RatRatio = oldRatio / newRatio;

                if (RatRatio > 1.1) {



                    //System.err.println("RESETTING");
                    L = 512;
                    W = 9;

                    st = new TST<Integer>();
                    for (int i = 0; i < R; i++)
                        st.put("" + (char) i, i);
                    code = R + 1;  // R is codeword for EOF
                    in=0;
                    out=0;
                    oldRatio=0;
                }
            }

            if(code==L && W<16){
                //System.err.println("EXPANDING");
                W++;
                L = (int)Math.pow(2,W);
            }


            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expandN() {
        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        while (true) {

            if(i==L-1 && W<16){

                W++;
                L=(int)Math.pow(2,W);

                String[] temp = new String[L];
                for(int x = 0; x<st.length; x++){
                    temp[x]=st[x];
                }
                st=temp;
            }

            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            val = s;
        }
        BinaryStdOut.close();
    }



    public static void expandR() {
        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        while (true) {


            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L-1) st[i++] = val + s.charAt(0);

            if(i==L-1 && W<16){

                W++;
                L=(int)Math.pow(2,W);
                //System.err.println("EXPANDING "+L +" "+st.length);

                String[] temp = new String[L];
                for(int x = 0; x<st.length && x<temp.length; x++){
                    temp[x]=st[x];
                }
                st=temp;
            }


            val = s;


            if(i == 65535){



                //System.err.println("RESETTING");

                W=9;
                L=512;

                st = new String[L];

                for (i = 0; i < R; i++)
                    st[i] = "" + (char) i;
                st[i++] = "";                        // (unused) lookahead for EOF

                BinaryStdOut.write(val);

                codeword = BinaryStdIn.readInt(W);
                if (codeword == R) break;           // expanded message is empty string
                val = st[codeword];  // resetting val size
            }


        }
        BinaryStdOut.close();
    }



    public static void expandM() {
        int in=0;
        int out=0;
        double oldRatio = 0;
        double newRatio = 0;
        double RatRatio = 0;

        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        while (true) {


            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L-1) st[i++] = val + s.charAt(0);

            if(i==L-1 && W<16){

                W++;
                L=(int)Math.pow(2,W);
                //System.err.println("EXPANDING "+L +" "+st.length);

                String[] temp = new String[L];
                for(int x = 0; x<st.length && x<temp.length; x++){
                    temp[x]=st[x];
                }
                st=temp;
            }


            val = s;


            if(i == 65535){

                out = out + W;
                in = in + val.length()*8;
                newRatio = in / out;
                if (oldRatio == 0) oldRatio = newRatio;

                RatRatio = oldRatio / newRatio;

                if(RatRatio>1.1) {
                    //System.err.println("RESETTING");

                    W = 9;
                    L = 512;

                    st = new String[L];

                    for (i = 0; i < R; i++)
                        st[i] = "" + (char) i;
                    st[i++] = "";                        // (unused) lookahead for EOF

                    BinaryStdOut.write(val);

                    codeword = BinaryStdIn.readInt(W);
                    if (codeword == R) break;           // expanded message is empty string
                    val = st[codeword];  // resetting val size

                    in=0;
                    out=0;
                    oldRatio=0;
                }
            }


        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")){
            if(args[1].equals("n")) compressN();
            else if(args[1].equals("r")) compressR();
            else if(args[1].equals("m")) compressM();
        }
        else if (args[0].equals("+")){
            char c = BinaryStdIn.readChar();
            if(c=='n') expandN();
            else if(c=='r') expandR();
            else expandM();
        }
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}

