import java.io.*;
import java.util.*;

public class pw_check {

    static int inCount=0;

    public static void main(String[] args) throws FileNotFoundException{


        DLB dict = new DLB();

        if(args.length>0){
            if(args[0].equalsIgnoreCase("-g")) generate(dict);
            else{
                System.out.println("Run with \"-g\" argument to generate list of good passwords, ");
                System.out.println("or with no arguments to enter and check passwords");
                System.exit(0);
            }
        }

        else {

            File passwords = new File("good_passwords.txt");

            File dictionary = new File("my_dictionary.txt");



                if(passwords.length() == 0 || dictionary.length() == 0){

                System.out.println("Either good_passwords.txt or my_dictionary was not found. Please run 'java pw_check -g' to generate these files. ");

                return;

                }

            while (true) {
                String userWords = readPasswords();
                char[] word = new char[5];
                for (int x = 0; x < userWords.length(); x++) {
                    char c = userWords.charAt(x);
                    word[x] = c;
                    if (c == '7') word[x] = 't';
                    if (c == '0') word[x] = 'o';
                    if (c == '3') word[x] = 'e';
                    if (c == '$') word[x] = 's';
                }
                userWords = String.valueOf(word);


                //int x = 0;
                //while(userWords[x] != null){

                fillForAlts(dict, userWords.charAt(0));
                System.out.println("DLB loaded");

                if (dict.contains(userWords))
                    System.out.println(userWords + " is a GOOD password. What a fantastic choice!");
                else {
                    System.out.println(userWords + " is a BAD password. Here are some great alternatives: ");
                    String[] out = genAltPasswords(userWords, dict);
                    for (int y = 0; y < 10; y++) {
                        System.out.println(out[y]);
                    }
                    //pass alts
                }
                //x++;
                // }


//            for(int x = 0; userWords[x] != null; x++)
//            System.out.println(userWords[x]+x);


            }

        }
    }

    public static void generate(DLB dictionary) throws FileNotFoundException{
        genMyDictionary(dictionary);
        goodPasswords(dictionary);

    }


    public static String[] genAltPasswords(String bad, DLB dictionary) throws FileNotFoundException{

        String[] good = new String[10];
        String toAdd=null;
        String goodChars = "bcdefghjklmnopqrstuvwxyz02356789!@$^_*";

        char[] word = new char[5];
        for(int x=0; x<bad.length();x++){
            char c = bad.charAt(x);
            word[x] = c;
            if(c=='7') word[x] = 't';
            if(c=='0') word[x] = 'o';
            if(c=='3') word[x] = 'e';
            if(c=='$') word[x] = 's';
        }
        bad = String.valueOf(word);
//        int x = 0;
//
//        String goodChars = "bcdefghjklmnopqrstuvwxyz02356789!@$^_*";
//        char[] password = bad.toCharArray();
//
//        boolean first0=true;
//        boolean first1=true;
//        boolean first2=true;
//        boolean first3=true;
//        boolean first4=true;
//        bad=bad.replace('a', 'b');
//        bad=bad.replace('i', 'j');
//        bad=bad.replace('1', '2');
//        bad=bad.replace('4', '5');
//
//        System.out.println(bad);
//        for(int p0=goodChars.indexOf(bad.substring(0,1)); p0!=goodChars.indexOf(bad.substring(0,1))||first0;p0++){
//            for(int p1=goodChars.indexOf(bad.substring(1,2)); p1!=goodChars.indexOf(bad.substring(1,2))||first1;p1++) {
//                if(dictionary.prefix(bad.substring(0,2))==2){
//                for (int p2 = goodChars.indexOf(bad.substring(2, 3)); p2 != goodChars.indexOf(bad.substring(2, 3)) || first2; p2++) {
//                    if (dictionary.prefix(bad.substring(0, 3)) == 2) {
//                        for (int p3 = goodChars.indexOf(bad.substring(3, 4)); p3 != goodChars.indexOf(bad.substring(3, 4)) || first3; p3++) {
//                            if(dictionary.prefix(bad.substring(0,4))==2){
//                                for (int p4 = goodChars.indexOf(bad.substring(4, 5)); p4 != goodChars.indexOf(bad.substring(4, 5)) || first4; p4++) {
//
//                                if (p0 == goodChars.length() - 1 && !first0) p0 = 0;
//                                if (p1 == goodChars.length() - 1 && !first1) p1 = 0;
//                                if (p2 == goodChars.length() - 1 && !first2) p2 = 0;
//                                if (p3 == goodChars.length() - 1 && !first3) p3 = 0;
//                                if (p4 == goodChars.length() - 1 && !first4) p4 = 0;
//
//                                password[0] = goodChars.charAt(p0);
//                                password[1] = goodChars.charAt(p1);
//                                password[2] = goodChars.charAt(p2);
//                                password[3] = goodChars.charAt(p3);
//                                password[4] = goodChars.charAt(p4);
//
//                                String pass = new String(password);
//
//                                if (dictionary.contains(pass)) {
//                                    good[x] = pass;
//                                    x++;
//                                    if (x == 10) return good;
//                                }
//
//                                first4 = false;
//                            }}
//                            first3 = false;
//                        }}
//                        first2 = false;
//                    }
//                }
//                first1 = false;
//
//            }
//            first0=false;
//        }
//        return good;
        int y = 4;
        for(int x =0; x<10; x++) {
            //int y = 4;
            boolean found = false;
            while(!found) {
                //System.out.println("Y: "+y);
                String sub = bad.substring(0, y);
                if (dictionary.prefix(sub) == 2){
                    //System.out.println("BAD: "+bad);



                    toAdd=dictionary.getNext(sub, bad);
//SWITCH WHERE A IS TO BE



                    found=true;
                } else y--;
            }
            boolean alreadyIn = false;
            //System.out.println(x);
            if(x!=0) {
                for (int p = 1; p <= x; p++) {
                    if (good[p-1].equalsIgnoreCase(toAdd)) alreadyIn = true;
                }
            }


            if(!alreadyIn) {
                y=4;
                good[x] = toAdd;
                //System.out.println("TO ADD: "+toAdd);
                bad = good[x];
            }
            else {
                y--;
                x--;
            }

        }
        return good;
    }



    public static String readPasswords(){
        Scanner scan = new Scanner(System.in);
        String userWords = new String();

        System.out.println("Enter password (Type \"stop\" to finish): ");


            String s = scan.nextLine();
            s=s.toLowerCase();
            if(!s.equalsIgnoreCase("stop")){
                userWords =  s;
            }
            else System.exit(0);


        return userWords;

    }


    public static void goodPasswords(DLB dictionary)throws FileNotFoundException{
        int goodCount=0;
        int b4Check=0;
        PrintWriter out = new PrintWriter("good_passwords.txt");
        String goodChars = "bcdefghjklmnopqrstuvwxyz02356789!@$^_*";
        char[] password = new char[5];

        for(int p0=0; p0<goodChars.length();p0++){
            for(int p1=0; p1<goodChars.length();p1++){
                for(int p2=0; p2<goodChars.length();p2++){
                    for(int p3=0; p3<goodChars.length();p3++){
                        for(int p4=0; p4<goodChars.length();p4++){

                            password[0] = goodChars.charAt(p0);
                            password[1] = goodChars.charAt(p1);
                            password[2] = goodChars.charAt(p2);
                            password[3] = goodChars.charAt(p3);
                            password[4] = goodChars.charAt(p4);

                            String pass = new String(password);
                            b4Check++;
                            if(isGoodPassword(pass, dictionary)){
                                out.println(pass);
                                goodCount++;
                            }

                        }
                    }
                }
            }
        }
        System.out.println("Before check: " +b4Check);
        System.out.println("non dic: "+inCount);
    System.out.println("Confirmed good: "+goodCount);
    out.close();
    }

    public static boolean isGoodPassword(String s, DLB dict){
        int numCount=0;
        int charCount=0;
        int symCount=0;

        if(s.length()>5) return false;

        for(int x=0; x<s.length();x++){
            char c = s.charAt(x);

            if(c>=48 && c<=57) numCount++;
            else if(c>=97 && c<=122) charCount++;
            else if(c==33 || c==36 || c==64 || c==94 || c==95 || c==42) symCount++;

            if(numCount>2 || charCount>3 || symCount>2) return false;
        }

        if(numCount>2 || charCount>3 || symCount>2 || numCount<1 || charCount<1 || symCount<1) return false;

        inCount++;

        char[] word = new char[5];
        for(int x=0; x<s.length();x++){
            char c = s.charAt(x);
            word[x] = c;
            if(c=='7') word[x] = 't';
            if(c=='0') word[x] = 'o';
            if(c=='3') word[x] = 'e';
            if(c=='$') word[x] = 's';
        }
        s = String.valueOf(word);

        String shorter;
        int y;
        int result;



        for(int x=0; x<s.length()-1;x++){
            y = x+1;
            if(s.charAt(x)>=97 && s.charAt(x)<=122 && s.charAt(y)>=97 && s.charAt(y)<=122){

                shorter = s.substring(x, y + 1);
                result = dict.prefix(shorter);
                while(result==2 && x < 4 && y<s.length()-1){
                    y++;
                    if(s.charAt(y)>=97 && s.charAt(y)<=122){
                        shorter = s.substring(x, y+1);
                        result = dict.prefix(shorter);
                    }
                    else{
                        break;
                    }
                }
                if(result==1 || result==3) return false;

            }


        }


    return true;
    }






    public static void genMyDictionary(DLB dict) throws FileNotFoundException{

        File infile = new File("dictionary.txt");
        PrintWriter outfile = new PrintWriter("my_dictionary.txt");
        PrintWriter other = new PrintWriter("my_other_dict.txt");
        Scanner scan = new Scanner(infile);
        String word;

        while(scan.hasNext()) {
            word = scan.nextLine();
            if(word.length()<=5) {
                dict.add(word);
                outfile.println(word);
                other.println(word);

//                if(word.contains("a") ||word.contains("s")||word.contains("e")||word.contains("t")||word.contains("i")||word.contains("l")||word.contains("o")){
//
//                    String posReps = "74031$";
//                    String[] posRepsA = new String[5];
//                    for(int p=0; p<5; p++){
//                        posRepsA[p] = "74031$";
//                    }
//
//
//
//                    String toSwap = "asetilo";
//                    int[] loc = new int[word.length()];
//                    int x = 0;
//                    for( int y =0; y<word.length();y++){
//
//                        char[] to = new char[1];
//                        to[0]=posReps.charAt(y);
//                        String s = new String(to);
//
//                        if(toSwap.contains(s)) {
//                            loc[x] = y;
//                            posRepsA[x]=posRepsA[x].concat(s);
//                            x++;
//                        }
//                    }
//                    x--;      // x = num of vals in loc
//                    //loc[] contains index of word to cycle
//
//                    char[] theWord = word.toCharArray();
//
//                    for(int z0=0; z0<posRepsA[0].length(); z0++){
//                        for(int z1=0; z1<posRepsA[1].length(); z1++){
//                            for(int z2=0; z2<posRepsA[2].length(); z2++){
//                                for(int z3=0; z3<posRepsA[3].length(); z3++){
//                                    for(int z4=0; z4<posRepsA[4].length(); z4++){
//
//                                    if(x<=1) theWord[loc[0]] = posRepsA[0].charAt(z0);
//                                    if(x<=2) theWord[loc[1]] = posRepsA[1].charAt(z1);
//                                    if(x<=3) theWord[loc[2]] = posRepsA[2].charAt(z2);
//                                    if(x<=4) theWord[loc[3]] = posRepsA[3].charAt(z3);
//                                    if(x<=5) theWord[loc[4]] = posRepsA[4].charAt(z4);
//
//                                   char[] toCheck = new char[5];
//
//                                    for(int t = 0; t<5;t++){
//
//                                        toCheck[t] = theWord[t];
//
//
//                                        if(theWord[t]=='7') toCheck[t] = 't';
//                                        if(theWord[t]=='0') toCheck[t] = 'o';
//                                        if(theWord[t]=='3') toCheck[t] = 'e';
//                                        if(theWord[t]=='$') toCheck[t] = 's';
//                                        if(theWord[t]=='4') toCheck[t] = 'a';
//                                        if(theWord[t]=='1'){
//                                            toCheck[t] = 'i';
//                                        }
//
//                                    }
//                                    System.out.println(word+" "+ toCheck.toString());
//                                     if(word.equals(toCheck.toString())) other.println(theWord.toString());
//
//
//
//
//
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
            }
        }
    outfile.close();
        other.close();
    }

    public static void fillForAlts(DLB dict, char c)throws FileNotFoundException{
        File infile = new File("good_passwords.txt");
        Scanner scan = new Scanner(infile);
        String word;
        int found = 0;
        while(scan.hasNext()) {
            word = scan.nextLine();
            if(word.charAt(0)==c) {
                found=1;
                dict.add(word);
            }
            else{
                if(found==1) break;
            }
        }


    }

}
