package model;

/**
 * Created by vviital on 19/09/15.
 */
public class Edge implements Cloneable {

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public long getCap() {
        return cap;
    }

    public void setCap(long cap) {
        this.cap = cap;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getFlow() {
        return flow;
    }

    public void setFlow(long flow) {
        this.flow = flow;
    }

    private int from;
    private int to;
    private long cap;
    private long cost;
    private long flow;

    public Edge(){

    }

    public Edge(int from, int to, long cap, long cost){
        this.from = from;
        this.to = to;
        this.cap = cap;
        this.cost = cost;
    }

    public void addFlow(long value){
        this.flow += value;
    }

    @Override
    public Edge clone() throws CloneNotSupportedException{
        Edge obj = new Edge(from, to, cap, cost);
        obj.setFlow(flow);
        return obj;
    }

}
