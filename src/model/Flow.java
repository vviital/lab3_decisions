package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vviital on 19/09/15.
 */
public class Flow {

    private List<Edge> edges;

    private List<List<Integer> > indexes;

    private void addEdge(int from, int to, int cap, int cost){
        indexes.get(from).add(edges.size());
        edges.add(new Edge(from, to, cap, cost));
        indexes.get(to).add(edges.size());
        edges.add(new Edge(to, from, 0, cost));
    }

    private Flow(){

    }

    public Flow(List<Edge> edges, int n){
        this.edges = new ArrayList();
        this.indexes = new ArrayList();
        for(int i = 0; i < n; i++)
            this.indexes.add(new ArrayList());
        for(Edge edge : edges){
            addEdge(edge.getFrom(), edge.getTo(), edge.getCap(), edge.getCost());
        }
    }

    public int getMaxFlow(){
        return dinic();
    }

    private int dinic(){
        return 1;
    }
}
