package model;

/**
 * Created by vviital on 19/09/15.
 */
public class FlowMatrix {

    private long[][] arr;

    private int[] dist;

    private FlowMatrix(){

    }

    public FlowMatrix(int n){
        this.arr = new long[n][n];
        this.dist = new int[n];
    }

    public long getFlow(int i, int j){
        return this.arr[i][j];
    }

    public void addFlow(int i, int j, long value){
        this.arr[i][j] += value;
    }

    public int getDist(int index){
        return this.dist[index];
    }

    public int getDistLength(){
        return this.dist.length;
    }

    public void setDist(int index, int value){
        this.dist[index] = value;
    }
}
