package gui;

import model.Edge;
import model.Flow;
import model.FlowMatrix;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by vviital on 19/09/15.
 */
public class GuiLab3 {

    private JFrame mainframe;

    private JTextField number;

    private JPanel leftPanel;

    private JPanel rightPanel;

    private int width = 1200;

    private int height = 600;

    private int n;

    private java.util.List<Component> left;

    private java.util.List<Component> right;

    private Flow flows;

    private int ly;

    private int ry;

    public GuiLab3(){
        this.mainframe = new JFrame("MaxFlow");
        this.mainframe.setLayout(null);
        this.mainframe.setSize(width, height);
        this.mainframe.setVisible(true);
        this.mainframe.setResizable(false);

        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(null);
        this.leftPanel.setBounds(0, 0, width / 2, height);

        this.rightPanel = new JPanel();
        this.rightPanel.setLayout(null);
        this.rightPanel.setBounds(width / 2, 0, width / 2, height);

        this.mainframe.add(leftPanel);
        this.mainframe.add(rightPanel);

        int x = 20, y = 20;
        addLabel(this.leftPanel, x, y, 100, 20, "Number: "); x += 105;

        this.number = new JTextField();
        this.number.setText("0");
        this.number.setBounds(x, y, 100, 20); x += 105;
        this.leftPanel.add(number);

        this.left = new ArrayList();
        this.right = new ArrayList();

        JButton okButton = new JButton("go!!!");
        okButton.setBounds(x, y, 100, 20);
        okButton.addActionListener((event) -> {
            try{
                this.n = Integer.parseInt(this.number.getText());
                removeComponent(leftPanel, left);
                this.addMatrix();
            } catch (Exception e){

            }
        });
        leftPanel.add(okButton);

        leftPanel.repaint();

    }

    private JLabel addLabel(JPanel panel, int x, int y, int w, int h, String text){
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        panel.add(label);
        return label;
    }

    private void addRemovableLabel(JPanel panel, int x, int y, int w, int h, String text, List<Component> mem){
        mem.add(addLabel(panel, x, y, w, h, text));
    }

    private void removeComponent(JPanel panel, List<Component> mem){
        for(Component x : mem){
            panel.remove(x);
        }
        mem.clear();
    }

    private void addRemovableTextField(int row, int col, JPanel panel, int x, int y, int w, int h, String text, List<Component> mem){
        JTextField field = new TextBox(text, x, y, w, h, row, col);
        field.setBounds(x, y, w, h);
        panel.add(field);
        mem.add(field);
    }

    private void addMatrix(){
        int x = 20, y = 50;
        addRemovableLabel(this.leftPanel, x, y, 200, 20, "Enter adjacency matrix", this.left); y += 30;
        for(int i = 0; i < n; ++i) {
            int temp = x;
            for (int j = 0; j < n; j++) {
                addRemovableTextField(i + 1, j + 1, this.leftPanel, x, y, 40, 20, "0", this.left);
                x += 50;
            }
            x = temp;
            y += 30;
        }

        JButton goButton = new JButton("Flow!!!");
        goButton.setBounds(x, y, 100, 20); y += 30;
        this.ly = y;
        goButton.addActionListener((event) -> {
            try {
                flow();
            } catch (Exception e) {

            }
        });
        this.left.add(goButton);
        this.leftPanel.add(goButton);
        this.leftPanel.repaint();
    }

    private void flow(){
        List<Edge> edges = new ArrayList();
        this.left.stream().filter(x -> x instanceof TextBox).forEach(x -> {
            TextBox box = (TextBox) x;
            int i = box.getRow();
            int j = box.getCol();
            int value = Integer.parseInt(box.getText());
            edges.add(new Edge(i, j, value, 0));
        });
        edges.add(new Edge(0, 1, Long.MAX_VALUE, 0));
        edges.add(new Edge(this.n, n + 1, Long.MAX_VALUE, 0));

        this.flows = new Flow(edges, this.n + 2, 0, n + 1);
        long f = this.flows.getFlow();

        List<Integer> leftPart = this.flows.minimumCutLeft();
        List<Integer> rightPart = this.flows.minimumCutRight();

        int x = 20, y = ly;

        this.addRemovableLabel(this.leftPanel, x, y, 100, 20, "Total flow: " + Long.toString(f), left); y += 30;
        this.addRemovableLabel(this.leftPanel, x, y, 100, 20, "Minimum cut: ", left); y += 30;
        this.addRemovableLabel(this.leftPanel, x, y, 100, 20, "Left part:", left); y += 30;
        this.addRemovableLabel(this.leftPanel, x, y, 400, 20, leftPart.toString(), left); y += 30;
        this.addRemovableLabel(this.leftPanel, x, y, 100, 20, "Right part:", left); y += 30;
        this.addRemovableLabel(this.leftPanel, x, y, 400, 20, rightPart.toString(), left); y += 30;
        this.leftPanel.repaint();
        System.out.println("f = " + f);
        this.curstep = 0;
        this.makeRightPanel();
    }


    private int curstep = 0;

    private void makeRightPanel(){
        removeComponent(this.rightPanel, this.right);
        int x = 20, y = 20;

        addRemovableLabel(this.rightPanel, x, y, 200, 20, "Current step : " + Integer.toString(curstep + 1), this.right); y += 30;

        y = drawMatrix(y);

        JButton prevButton = new JButton("Previous step");
        prevButton.setBounds(x, y, 200, 20);
        prevButton.addActionListener((event) -> {
            if (curstep != 0) {
                --curstep;
                makeRightPanel();
            }
        });
        this.right.add(prevButton);
        this.rightPanel.add(prevButton);

        x += 250;

        JButton nextButton = new JButton("Next step");
        nextButton.setBounds(x, y, 200, 20);
        nextButton.addActionListener((event) -> {
            if (curstep != this.flows.getStepNumber() - 1){
                ++curstep;
                this.makeRightPanel();
            }
        });
        this.right.add(nextButton);
        this.rightPanel.add(nextButton);

        this.rightPanel.repaint();

    }

    private int drawMatrix(int cy){
        int x = 20, y = cy;
        FlowMatrix matrix = this.flows.getStep(curstep);
        addRemovableLabel(this.rightPanel, x, y, 200, 20, "Current flow: " + matrix.getFlow(), right); y += 30;

        for(int i = 0; i < matrix.size(); ++i){
            int temp = x;
            for(int j = 0; j < matrix.size(); ++j){
                addRemovableLabel(this.rightPanel, x, y, 40, 40, Long.toString(matrix.getFlow(i, j)), right);
                x += 50;
            }
            x = temp;
            y += 50;
        }
        addRemovableLabel(this.rightPanel, x, y, 200, 20, "Layered network:", right); y += 30;
        for(int i = 0; i < matrix.size(); ++i){
            this.addRemovableLabel(this.rightPanel, x, y, 40, 40, Integer.toString(matrix.getDist(i)), right);
            x += 50;
        }
        y += 30;

        return y;
    }




}
