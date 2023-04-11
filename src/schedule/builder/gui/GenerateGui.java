/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.8.3
 */
package schedule.builder.gui;

import java.awt.BorderLayout;
import javax.swing.*;

import schedule.builder.ScheduleBuilder;

import java.awt.event.*;
import java.awt.FlowLayout;

public class GenerateGui extends JFrame implements ActionListener {

    private String username;
    private String password;

    public GenerateGui(String username, String password) {
        super("Schedule Builder");
        this.username = username;
        this.password = password;
        generateGui();
        setSize(500, 300);
        setLocation(730, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Creates gui layout for the generate schedule button
    public void generateGui() {
        JButton genButton = new JButton("Generate Schedule");
        genButton.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        headerPanel.add(genButton);

        this.add(headerPanel, BorderLayout.CENTER);
    }

    // Creates new ScheduleBuilder gui and hides this gui
    public void actionPerformed(ActionEvent event) {
        new ScheduleBuilder(username, password).setVisible(true);
        this.setVisible(false);

    }

}
