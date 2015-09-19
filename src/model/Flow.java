package model;

import java.util.*;

/**
 * Created by vviital on 19/09/15.
 */
public class Flow {

    private List<Edge> edges;

    private List<Integer>[] indexes;

    private int[] dist;

    private int[] ptr;

    private int n;

    private int from;

    private int sink;

    private List<FlowMatrix> memory;

    private Flow(){

    }


    private void addEdge(int from, int to, long cap, long cost){
        this.indexes[from].add(this.edges.size());
        this.edges.add(new Edge(from, to, cap, cost));
        this.indexes[to].add(this.edges.size());
        this.edges.add(new Edge(to, from, 0, -cost));
        this.memory = new ArrayList();
    }

    public Flow(List<Edge> e, int n, int from, int sink){
        this.n = n;
        this.indexes = new ArrayList[n + 1];
        this.edges = new ArrayList();
        for(int i = 0; i < n + 1; ++i) this.indexes[i] = new ArrayList();
        for(Edge x : e){
            addEdge(x.getFrom(), x.getTo(), x.getCap(), x.getCost());
        }
        this.ptr = new int[n + 1];
        this.dist = new int[n + 1];
        this.from = from;
        this.sink = sink;
    }

    private boolean bfs(int from, int sink){
        Queue<Integer> queue = new LinkedList();
        for(int i = 0; i < this.dist.length; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[from] = 0;
        queue.add(from);
        while(queue.size() != 0){
            int v = queue.element();
            queue.remove();
            for(int x : indexes[v]){
                Edge e = this.edges.get(x);
                int to = e.getTo();
                if (dist[to] == Integer.MAX_VALUE && e.getFlow() < e.getCap()){
                    queue.add(to);
                    dist[to] = dist[v] + 1;
                }
            }
        }
        return dist[sink] != Integer.MAX_VALUE;
    }

    private long dinic(int from, int sink){
        long flow = 0;
        while(bfs(from, sink)){
            for(int i = 0; i < this.ptr.length; ++i) ptr[i] = 0;
            while(true){
                long push = dfs(from, sink, Long.MAX_VALUE);
                if (push == 0) break;
                flow += push;
            }
        }
        return flow;
    }

    private long dfs(int v, int sink, long flow){
        System.out.println("flow = " + flow);
        if (flow == 0) return 0;
        if (v == sink) return flow;
        for(; ptr[v] < indexes[v].size(); ++ptr[v]){
            int index = indexes[v].get(ptr[v]);
            Edge e = this.edges.get(index);
            int to = e.getTo();
            if (dist[v] != dist[to] - 1) continue;
            long can = e.getCap() - e.getFlow();
            long push = dfs(to, sink, Math.min(flow, can));
            if (push != 0){
                this.edges.get(index).addFlow(push);
                this.edges.get(index ^ 1).addFlow(-push);
                return push;
            }
        }
        return 0;
    }

    public long getFlow(){
        return dinic(from, sink);
    }

    private void dfs(int cur, List<Integer> array){
        dist[cur] = 1;
        array.add(cur);
        for(int x : indexes[cur]){
            Edge e = this.edges.get(x);
            int to = e.getTo();
            if (dist[to] == 1 || e.getFlow() == e.getCap()) continue;
            dfs(to, array);
        }
    }

    // Return left part indexes
    public List<Integer> minimumCut(){
        List<Integer> ans = new ArrayList();
        for(int i = 0; i < this.dist.length; ++i){
            dist[i] = 0;
        }
        dfs(from, ans);
        return ans;
    }

    private void persist(){
        FlowMatrix matrix = new FlowMatrix(this.n);
        for(Edge x : this.edges){
            int f = x.getFrom();
            int t = x.getTo();
            long flow = x.getFlow();
            matrix.addFlow(f, t, flow);
        }
        memory.add(matrix);
    }

    public FlowMatrix getStep(int i){
        return this.memory.get(i);
    }

    public int getStepNumber(){
        return this.memory.size();
    }



}
