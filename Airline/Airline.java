import java.util.*;
import java.io.*;

public class Airline {
    private static EdgeWeightedDigraph graph;
    private static String cities[];
    private static ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>();
    private static String fname;

    public static void main(String args[]) throws FileNotFoundException{

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter file name: ");
        fname = scan.nextLine();
        File file = new File(fname);
        Scanner read = new Scanner(file);
        int numCities = read.nextInt();
        cities = new String[numCities+1];
        graph = new EdgeWeightedDigraph(numCities+1);
        cities[0] = "placeholder";
        read.nextLine();
        for(int i = 1; i<numCities+1; i++){
            cities[i]=read.nextLine();
            System.out.println(i+cities[i]);
        }

        int v1, v2, dist;
        double price;
        DirectedEdge e;
        while(read.hasNext()){
            v1=read.nextInt();
            v2=read.nextInt();
            dist=read.nextInt();
            price=read.nextDouble();
            e = new DirectedEdge(v1, v2, dist, price);
            graph.addEdge(e);
            edges.add(e);
            e = new DirectedEdge(v2, v1, dist, price);
            graph.addEdge(e);
        }

        boolean quit = false;
        while(!quit) {

            printMenu();
            int selec = scan.nextInt();

            switch (selec) {
                case 1:
                    allRoutes();
                    break;
                case 2:
                    mst();
                    break;
                case 3:
                    price();
                    break;
                case 4:
                    distance();
                    break;
                case 5:
                    hops();
                    break;
                case 6:
                    underPrice();
                    break;
                case 7:
                    add();
                    break;
                case 8:
                    remove();
                    break;
                case 9:
                    quit();
                    quit = true;
                    break;
            }
        }
    }



    private static void allRoutes(){
        System.out.println("Displaying all routes: ");
        System.out.println("(each route can go both ways)");
        System.out.println("-----------------------------");

        DirectedEdge e;
        for(int i = 0; i<edges.size(); i++){
            e = edges.get(i);
            System.out.println("Cities: "+cities[e.from()]+", "+cities[e.to()]+" Distance: "+e.weight()+" Price: "+e.weight2());
        }

    }

    private static void mst(){
        PrimMST prim = new PrimMST(graph);
        Iterator<DirectedEdge> it = prim.edges().iterator();
        System.out.println("City1, City2, distance, price");
        System.out.println("--------------------------");

        while(it.hasNext()){
            DirectedEdge e = it.next();
            System.out.println(cities[e.from()]+", "+ cities[e.to()]+", "+e.weight()+", "+e.weight2());
        }
    }

    private static void price() {
        printCities();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter first city (name): ");
        String s = scan.nextLine();
        int dep=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) dep = i;
        }
        System.out.print("Enter second city (name): ");
        s=scan.nextLine();
        int arr=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) arr = i;
        }

        DijkstraSP d = new DijkstraSP(graph, dep, 1);
        Iterator<DirectedEdge> stack = d.pathTo2(arr).iterator();
        DirectedEdge p = stack.next();

        ArrayList<String> a = new ArrayList<String>();
        DirectedEdge q;
        while(stack.hasNext()){
            q=stack.next();
            a.add(cities[q.to()]);
            //System.out.println(cities[q.to()]);
            a.add(Double.toString(q.weight2()));
            //System.out.println(Double.toString(q.weight2()));
        }
        System.out.println("Route with prices: ");
        System.out.print("From "+cities[dep]);
        for(int i = 0; i<a.size(); i+=2){
            System.out.println(" to "+a.get(i) + "   Cost: " + a.get(i + 1));
            //System.out.print(a.get(i));
        }
        System.out.println("TOTAL PRICE: "+p.weight2());



    }

    private static void distance(){
        printCities();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter first city (name): ");
        String s = scan.nextLine();
        int dep=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) dep = i;
        }
        System.out.print("Enter second city (name): ");
        s=scan.nextLine();
        int arr=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) arr = i;
        }
        DijkstraSP d = new DijkstraSP(graph, dep, 0);
        Iterator<DirectedEdge> stack = d.pathTo(arr).iterator();
        DirectedEdge p = stack.next();

        ArrayList<String> a = new ArrayList<String>();
        DirectedEdge q;
        while(stack.hasNext()){
            q=stack.next();
            a.add(cities[q.to()]);
            //System.out.println(cities[q.to()]);
            a.add(Integer.toString(q.weight()));
            //System.out.println(Integer.toString(q.weight()));
        }
        System.out.println("Route with distances: ");
        System.out.print("From "+cities[dep]);
        for(int i = 0; i<a.size(); i+=2){
            System.out.println(" to "+a.get(i) + "   Distance: " + a.get(i + 1));
            //System.out.print(a.get(i));
        }
        System.out.println("TOTAL DISTANCE: "+p.weight());



    }

    private static void hops(){
        printCities();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter first city (name): ");
        String s = scan.nextLine();
        int dep=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) dep = i;
        }
        System.out.print("Enter second city (name): ");
        s=scan.nextLine();
        int arr=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) arr = i;
        }

        BreadthFirstPaths g = new BreadthFirstPaths(graph, dep);
        Iterator<Integer> bfs = g.pathTo(arr).iterator();

        System.out.println("Route: ");
        while(bfs.hasNext()){
            System.out.println(cities[bfs.next()]);
        }

    }

    private static void underPrice(){

    }

    private static void add(){
        printCities();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter first city (name): ");
        String s = scan.nextLine();
        int dep=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) dep = i;
        }
        System.out.print("Enter second city (name): ");
        s=scan.nextLine();
        int arr=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) arr = i;
        }

        System.out.print("Enter distance: ");
        int dis = scan.nextInt();
        System.out.print("Enter price: ");
        double pr = scan.nextDouble();
        DirectedEdge e = new DirectedEdge(dep, arr, dis, pr);
        graph.addEdge(e);
        edges.add(e);
        graph.addEdge(new DirectedEdge(arr, dep, dis, pr));

    }

    private static void remove(){
        printCities();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter first city (name): ");
        String s = scan.nextLine();
        int dep=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) dep = i;
        }
        System.out.print("Enter second city (name): ");
        s=scan.nextLine();
        int arr=0;
        for(int i =0; i<cities.length; i++){
            if(cities[i].equalsIgnoreCase(s)) arr = i;
        }
        graph.removeEdge(dep, arr);
        graph.removeEdge(arr, dep);
        DirectedEdge e;
        for(int i = 0; i<edges.size(); i++){
            e = edges.get(i);
            if(e.to()==arr && e.from()==dep)edges.remove(i);
        }

    }

    private static void quit() throws FileNotFoundException{
        File file = new File(fname);
        PrintWriter writer = new PrintWriter(file);
        writer.write(Integer.toString(cities.length-1)+"\n");
        for(int i = 1; i<cities.length;i++){
            writer.write(cities[i]+"\n");
        }
        DirectedEdge e;
        for(int i = 0; i<edges.size();i++){
            e=edges.get(i);
            writer.write(e.from()+" "+e.to()+" "+e.weight()+" "+e.weight2()+"\n");
        }
        writer.close();
    }

    private static void printMenu(){
        System.out.println("---------------");
        System.out.println("   MAIN MENU");
        System.out.println("---------------");
        System.out.println("1. Display all routes");
        System.out.println("2. Display MST");
        System.out.println("3. Search by lowest price");
        System.out.println("4. Search by shortest distance");
        System.out.println("5. Search by least number of hops");
        System.out.println("6. All routes under a price");
        System.out.println("7. Add a route");
        System.out.println("8. Remove a route");
        System.out.println("9. Quit");
        System.out.println("\nEnter selection: ");

    }

    private static void printCities(){
        for(int i = 1; i<cities.length; i++){
            System.out.println(i+". "+cities[i]);
        }
    }

}
