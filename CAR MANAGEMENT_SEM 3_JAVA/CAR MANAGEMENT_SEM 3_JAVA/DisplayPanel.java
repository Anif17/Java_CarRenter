import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class DisplayPanel extends JPanel {
    private JList jcomp1;

    public DisplayPanel(String[] car) {
        //construct components
        jcomp1 = new JList (car);

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 571));
        setLayout (null);

        //add components
        add (jcomp1);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (70, 45, 810, 445);
    }
}