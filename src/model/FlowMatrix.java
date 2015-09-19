package model;

/**
 * Created by vviital on 19/09/15.
 */
public class FlowMatrix {

    private long[][] arr;

    private FlowMatrix(){

    }

    public FlowMatrix(int n){
        this.arr = new long[n][n];
    }

    public long getFlow(int i, int j){
        return this.arr[i][j];
    }

    public void addFlow(int i, int j, long value){
        this.arr[i][j] += value;
    }
}
