/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.9.4
 */

package schedule.builder.gui;

//https://github.com/abarcomb-work/ENSF380-WS2023/blob/main/Lesson23_GUI/04_GUIPetID/GUIPetID.java
import java.awt.BorderLayout;
import javax.swing.*;

import schedule.builder.data.DataImport;

import java.awt.event.*;
import java.awt.FlowLayout;

public class MainGui extends JFrame implements ActionListener, MouseListener {

    private DataImport dataImport = new DataImport();
    private String username;
    private String password;

    private JLabel instructions;
    private JLabel userLabel;
    private JLabel passLabel;

    private JTextField userInput;
    private JTextField passInput;

    public MainGui() {
        super("Please login into database");
        loginGui();
        setSize(500, 300);
        setLocation(730, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void loginGui() {
        // TODO Fix from previous version of GUI
        instructions = new JLabel("Please login into database.");
        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        userInput = new JTextField("oop", 15);
        passInput = new JTextField("password", 15);

        userInput.addMouseListener(this);
        passInput.addMouseListener(this);

        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(instructions);
        clientPanel.add(userLabel);
        clientPanel.add(userInput);
        clientPanel.add(passLabel);
        clientPanel.add(passInput);
        submitPanel.add(submitInfo);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent event) {
        username = userInput.getText();
        password = passInput.getText();
        System.out.println(username);
        System.out.println(password);
        if (dataImport.createConnection(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            // getTables();
            this.setVisible(false);
            new GenerateGui(username, password).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Login failed!");
        }
    }

    public void mouseClicked(MouseEvent event) {

        if (event.getSource().equals(userInput)) {
            userInput.setText("");
        }
        if (event.getSource().equals(passInput)) {
            passInput.setText("");
        }

    }

    public void mouseEntered(MouseEvent event) {
        return;
    }

    public void mouseExited(MouseEvent event) {
        return;
    }

    public void mousePressed(MouseEvent event) {
        return;
    }

    public void mouseReleased(MouseEvent event) {
        return;
    }
}
// calls a gui
// public void schedule
// public void getTables() {
// try {
// dataImport.getAnimalsTable();
// dataImport.getTasksTable();
// dataImport.getTreatmentsTable();
// } catch (Exception e) {
// System.out.println(e);
// }
// // System.out.println(animals);
// // System.out.println(tasks);
// // System.out.println(treatments);
// }
// // public static void main(String[] args) {
// // EventQueue.invokeLater(() -> {
// // new GuiScheduler().setVisible(true);
// // });
// // }
// }
