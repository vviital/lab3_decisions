package model;

/**
 * Created by vviital on 19/09/15.
 */
public class Edge {

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

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    private int from;
    private int to;
    private int cap;
    private int cost;

    public Edge(){

    }

    public Edge(int from, int to, int cap, int cost){
        this.from = from;
        this.to = to;
        this.cap = cap;
        this.cost = cost;
    }

}
