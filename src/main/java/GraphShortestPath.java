import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GraphShortestPath {

    public static void main(String args[])
    {
        int v = 8;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(v);

        for(int i=0 ; i <v ; i++){
                adj.add(new ArrayList<>());
        }

        addEdge(adj,0,1);
        addEdge(adj,0,3);
        addEdge(adj,1,2);
        addEdge(adj,3,4);
        addEdge(adj,3,7);
        addEdge(adj,4,5);
        addEdge(adj,4,6);
        addEdge(adj,5,6);
        addEdge(adj,6,7);

        System.out.print(adj);

        int src = 0 , dst = 7 ;
        findShortestPath(adj,src,dst,v);
    }

    private static void findShortestPath(ArrayList<ArrayList<Integer>> adj, int src, int dst, int v) {
        int prev [] = new int[v];
        int dist [] = new int[v];
        if(BFS(adj,src, dst,v,prev,dist) == false){
            System.out.print("src and dst are not connected");
            return;
        }

        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dst;
        while(prev[crawl] != -1){
                path.add(prev[crawl]);
                crawl = prev[crawl];
         }

        System.out.println("Shortest path length is: " + dist[dst]);
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }

    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dst, int v, int[] prev, int[] dist) {
        LinkedList<Integer> queue =new LinkedList<>();
        boolean visited [] = new boolean[v];

        for(int i=0;i<v;i++){
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while(!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                final int node = adj.get(u).get(i);
                if (visited[node] == false) {
                    visited[node] = true;
                    dist[node] = dist[u] + 1;
                    prev[node] = u;
                    queue.add(node);
                }
                if (node == dst)
                    return true;
            }
        }
        return false;
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }


}
