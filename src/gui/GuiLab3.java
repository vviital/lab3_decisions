package gui;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
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

    public GuiLab3(){
        this.mainframe = new JFrame("MaxFlow");
        this.mainframe.setLayout(null);
        this.mainframe.setSize(width, height);
        this.mainframe.setVisible(true);
        this.mainframe.setResizable(false);

        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(null);
        this.leftPanel.setBounds(0, 0, width / 2, height);

        this.rightPanel = new JPanel(null);
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
        panel.repaint();
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

    private void addRemovableTextField(JPanel panel, int x, int y, int w, int h, String text, List<Component> mem){
        JTextField field = new JTextField(text);
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
                addRemovableTextField(this.leftPanel, x, y, 40, 20, "0", this.left);
                x += 50;
            }
            x = temp;
            y += 30;
        }

        JButton goButton = new JButton("Flow!!!");
        goButton.setBounds(x, y, 100, 20);
        goButton.addActionListener((event) -> {
            try{
                flow();
            } catch (Exception e){

            }
        });
        this.left.add(goButton);
        this.leftPanel.add(goButton);
        this.leftPanel.repaint();
    }

    private void flow(){

    }




}
