package ui;

import sun.tools.jps.Jps;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalInterface extends JFrame implements ActionListener {

    // main page
    public GraphicalInterface() {
        super("FitPet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JSplitPane splitPane = setSplitLayout();
        add(splitPane);





    }

    public JSplitPane setSplitLayout() {
        JSplitPane splitPane = new JSplitPane();
        add(splitPane);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(500);
        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(botPanel);

        return splitPane;
    }











    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
