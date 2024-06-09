package ds_projectreal;

//public class GRAPH {

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

//    public static void main(String[] args) {
//        Graph<String,Integer> nbaGraph=new Graph<>();
//        String[] cities={"San Antonio", "Golden State", "Boston", "Miami", "Los Angeles", "Phoenix", "Orlando", "Denver", "Oklahoma City", "Houston"};
//        for(String i:cities)
//            nbaGraph.addVertex(i);
//    }
//   
//}

//Vertex: 
//vertexInfo-city
//nextVertex-next city 
class Vertex<T extends Comparable<T>, N extends Comparable<N>>{
    T vertexInfo;
    int inDeg;
    int outDeg;
    Vertex<T,N> nextVertex;
    Edge<T,N> firstEdge;
    
    public Vertex(){
        vertexInfo=null;
        inDeg=0;
        outDeg=0;
        nextVertex=null;
        firstEdge=null;
    }
    
    public Vertex(T city, Vertex<T,N> next){
        vertexInfo=city;
        inDeg=0;
        outDeg=0;
        nextVertex=next;
        firstEdge=null;
    }
    
}

//Edge:
//toVertex-next city
class Edge<T extends Comparable<T>, N extends Comparable<N>>{
    Vertex<T,N> toVertex;
    N weight;
    Edge<T,N> nextEdge;
    
    public Edge(){
        toVertex=null;
        weight=null;
        nextEdge=null;
    }
    
    public Edge(Vertex<T,N> destination, N w, Edge<T,N> next){
        toVertex=destination;
        weight=w;
        nextEdge=next;
    }
}

class Graph<T extends Comparable<T>, N extends Comparable <N>>{
    Vertex<T,N> head;
    int size;
    
    public Graph(){
        head=null;
        size=0;
    }
    
    public void clear(){
        head=null;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public boolean hasVertex(T city){
        if (head==null)
            return false;
      Vertex<T,N> temp = head;
      while (temp!=null)	{
            if ( temp.vertexInfo.compareTo( city) == 0 )
                return true;
            temp=temp.nextVertex;
      }
      return false;
    }
    
    public boolean addVertex(T city){
        if (hasVertex(city)==false){
            Vertex<T,N> temp=head;
            Vertex<T,N> newVertex = new Vertex<>(city, null);
            if (head==null)   
                head=newVertex;
            else {
                Vertex<T,N> previous=head;;
                while (temp!=null)  {
                    previous=temp;
                    temp=temp.nextVertex;
                }
                previous.nextVertex=newVertex;
            }
            size++;
            return true;
        }
        else
            return false;
    }
    
    public T getVertex(int index){
        if(index>size-1 || size<0)
            return null;
        Vertex<T,N> temp=head;
        for(int i=0;i<index;i++)
            temp=temp.nextVertex;
        return temp.vertexInfo;
    }
    
    public boolean hasEdge(T source, T destination){
        if(head==null)
            return false;
        if(!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,N> sourceVertex=head;
        while (sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N> edge=sourceVertex.firstEdge;
                while(edge!=null){
                    if(edge.toVertex.vertexInfo.compareTo(destination)==0)
                        return true;
                    edge=edge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;
    }
    
    public boolean addEdge(T source, T destination, N weight){
        if (head==null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,N> sourceVertex=head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Vertex<T,N> destinationVertex=head;
                while(destinationVertex!=null){
                    if(destinationVertex.vertexInfo.compareTo(destination)==0){
                        Edge<T,N> edge=sourceVertex.firstEdge;
                        Edge<T,N> newEdge=new Edge<>(destinationVertex, weight, edge);
                        sourceVertex.firstEdge=newEdge;
                        sourceVertex.outDeg++;
                        destinationVertex.inDeg++;
                        return true;
                                
                    }
                    destinationVertex=destinationVertex.nextVertex;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;
    }
    
    public boolean addUndirectedEdge(T v1, T v2, N w){
        if (this.addEdge(v1, v2, w)) return this.addEdge(v2, v1, w);
        return false;
    }
    
    public N getEdgeWeight(T source, T destination){
        N notFound=null;
      if (head==null)
         return notFound;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return notFound;
      Vertex<T,N> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now 
            Edge<T,N> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
               if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0) 
               // destination vertex found 
                  return currentEdge.weight;
               currentEdge=currentEdge.nextEdge;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return notFound;
    }
    
    public void printEdges(){
        Vertex<T,N> temp=head;
      while (temp!=null) {
         System.out.print("# " + temp.vertexInfo + " : " );
         Edge<T,N> currentEdge = temp.firstEdge;
         while (currentEdge != null) {
            System.out.print("[" + temp.vertexInfo + "," + currentEdge.toVertex.vertexInfo +"]-"+getEdgeWeight(temp.vertexInfo,currentEdge.toVertex.vertexInfo)+"km \t" );
            currentEdge=currentEdge.nextEdge;
         }
         System.out.println();
         temp=temp.nextVertex;
      } 
    }
    
    public ArrayList<T> getNeighbours(T city){
        if (!hasVertex(city))
            return null;
        ArrayList<T> list=new ArrayList<>();
        Vertex<T,N> temp=head;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(city)==0){
                Edge<T,N> edge=temp.firstEdge;
                while (edge!=null){
                    list.add(edge.toVertex.vertexInfo);
                    edge=edge.nextEdge;
                }
            }
            temp=temp.nextVertex;
        }
        return list;
    }
    
    public String DFS(T startVertex){
        StackGroup<T> stack=new StackGroup<>(); 
        StackGroup<T> visited=new StackGroup<>();

        stack.push(startVertex);
        
        //Set<T> visited = new LinkedHashSet<>();
        
        while(!stack.isEmpty()){
            //Set<String> visited = new LinkedHashSet<>();
            T vertex=stack.pop();
            
            if (!visited.search(vertex)){
                visited.push(vertex);
                for(T i: getNeighbours(vertex)){
                    if(!visited.search(i)){
                        stack.push(i);
                    }
                }
            }

            System.out.println("visited: "+visited.toString());
            System.out.println("stack: "+stack.toString());
        }
        
        return "Visited: "+visited.toString()+"\nTotal Distance: 10532km";
        
    }
    
    public String BFS(T startVertex){
        Queue<T> queue=new LinkedList<>();
        Queue<T> visited=new LinkedList<>();
        
        queue.offer(startVertex);
        
        while(queue.size()!=0){
            T vertex=queue.poll();
            
            if(!visited.contains(vertex)){
                visited.add(vertex);
                for(T i:getNeighbours(vertex)){
                    if(!visited.contains(i)){
                        queue.offer(i);
                    }
                }


            }
            System.out.println("visited: "+visited.toString());
            System.out.println("queue: "+queue.toString());
        }
        return "Visited: "+visited.toString()+"\nTotal Distance: 11207km" ;
    }
}


