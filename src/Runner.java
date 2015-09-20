import gui.GuiLab3;
import model.Edge;
import model.Flow;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vviital on 19/09/15.
 */
public class Runner {

    public static void main(String[] args) {

        new GuiLab3();
//        Scanner in = new Scanner(System.in);
//        PrintWriter out = new PrintWriter(System.out);
//        new Solver().solve(in, out);
//        in.close();
//        out.close();
    }
}

class Solver{

    void solve(Scanner in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        List<Edge> edgeList = new ArrayList(m);
        for(int i = 0; i < m; ++i){
            Edge e = new Edge();
            e.setFrom(in.nextInt());
            e.setTo(in.nextInt());
            e.setCap(in.nextInt());
            edgeList.add(e);
        }
        edgeList.add(new Edge(0, 1, Long.MAX_VALUE, 0));
        edgeList.add(new Edge(n, n + 1, Long.MAX_VALUE, 0));
        Flow f = new Flow(edgeList, n + 2, 0, n + 1);
        long ans = f.getFlow();
        out.println(ans);
        List<Integer> leftPart = f.minimumCutLeft();
        for(int x : leftPart){
            System.out.println("x = " + x);
        }
    }
}

