import java.util.*;

public class GraphTraversal {
    private ArrayList<Character>[] list;
    Map<Character,Integer> letterMap;
    Map<Character,Boolean> visitedMap;
    GraphTraversal(){
        //empty constructor.
        queue = new LinkedList<Character>();
        letterMap = new HashMap<>();
        visitedMap = new HashMap<>();
    }

    public void setVisitedMap(){
        //method to set visitedMap to false
        for(char value : letterMap.keySet()){
            visitedMap.put(value,false);
        }

    }
    //constructor to create an adjacency list based on the number of vertices
    Queue<Character> queue; //used to store neighbors in breadth first search

    GraphTraversal(int vertices){
       list = new ArrayList[vertices];
       //initializing each array list in the array to avoid null pointer exceptions
       for(int i = 0; i < vertices; i++){
           list[i] = new ArrayList<>();
       }
       queue = new LinkedList<Character>();
   }
   GraphTraversal(ArrayList<Character>[] list){
        this.list = list;
        queue = new LinkedList<Character>();
   }

   public void mapConstruct(char[] letters){
        for(int i = 0; i < letters.length; i++){
            letterMap.put(letters[i],i);
        }
   }

   //time complexity of bfs is O(n+e) where n is the number of vertices and e is the number of edges
   //the while loop
   public void breadthFirstSearch(ArrayList<Character>[] list, char startVertex){
        queue.offer(startVertex); //insert the first vertex into the list.
        Map<Character,Boolean> visitedMap = new HashMap<>();
        for(char value : letterMap.keySet()){
            visitedMap.put(value,false);
        }
        visitedMap.put(startVertex,true);

        //We want to keep traversing the graph till the queue is empty, since that is when we would have visited all vertices
        while(!queue.isEmpty()){ //this queue will in total have n vertices
            char vertexToVisit = queue.poll();
            System.out.println(vertexToVisit);
            int indexOfVisitedVertex = letterMap.get(vertexToVisit);
            Collections.sort(list[indexOfVisitedVertex]);//to break ties
            for(char neighbour : list[indexOfVisitedVertex]){ // this will check all neighbours for this particular vertex
                if(visitedMap.get(neighbour) == false){
                    visitedMap.put(neighbour,true);
                    queue.offer(neighbour);
                }
            }

        }
   }

   //time complexity is O(n+e) where n is the number of vertices and e is the number of edges
   public void depthFirstSearch(ArrayList<Character>[] list, char startVertex){
        visitedMap.put(startVertex, true);
        System.out.println(startVertex);
        int indexOfVisitedVertex = letterMap.get(startVertex);
        Collections.sort(list[indexOfVisitedVertex]);
        for(char neighbour : list[indexOfVisitedVertex]){
            if(visitedMap.get(neighbour) == false){
                depthFirstSearch(list,neighbour);
            }
        }

   }

    public static void main(String[] args) {
        GraphTraversal graphTraversal = new GraphTraversal();
        char[] vertices = {'A','B','C','D','E','F','G','H','I','J'}; //gets mapped as 0,1,2,3,4......9
        graphTraversal.mapConstruct(vertices);
        ArrayList<Character>[] list = new ArrayList[10];
        for(int i = 0; i < list.length; i++){
            list[i] = new ArrayList<>();
        }
        list[0].add('B');
        list[0].add('D');
        list[0].add('I');
        list[1].add('A');
        list[1].add('C');
        list[1].add('D');
        list[1].add('E');
        list[2].add('B');
        list[2].add('E');
        list[2].add('F');
        list[3].add('A');
        list[3].add('B');
        list[3].add('E');
        list[3].add('G');
        list[4].add('B');
        list[4].add('C');
        list[4].add('D');
        list[4].add('F');
        list[4].add('G');
        list[4].add('H');
        list[5].add('C');
        list[5].add('E');
        list[5].add('H');
        list[6].add('D');
        list[6].add('E');
        list[6].add('H');
        list[6].add('I');
        list[6].add('J');
        list[7].add('E');
        list[7].add('F');
        list[7].add('G');
        list[7].add('J');
        list[8].add('A');
        list[8].add('G');
        list[8].add('J');
        list[9].add('G');
        list[9].add('H');
        list[9].add('I');
        System.out.println("BFS: ");
        graphTraversal.breadthFirstSearch(list,'A');
        graphTraversal.setVisitedMap();
        System.out.println("DFS: ");
        graphTraversal.depthFirstSearch(list,'A');




    }
}
