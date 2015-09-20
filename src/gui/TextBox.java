package gui;

import javax.swing.*;
import javax.xml.soap.Text;

/**
 * Created by vviital on 20/09/15.
 */
public class TextBox extends JTextField {

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    private int row;
    private int col;

    private TextBox(){

    }

    public TextBox(String text, int x, int y, int w, int h, int row, int col){
        this.setText(text);
        this.setBounds(x, y, w, h);
        this.row = row;
        this.col = col;
    }
}
