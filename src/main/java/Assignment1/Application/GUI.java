package Assignment1.Application;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;


public class GUI {

    public GUI() throws IOException {
        Backend backend = new Backend();
        JFrame frame = new JFrame("WikiSuggestion");

        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,255,238));

        //Frame Stuff
        frame.setSize(800, 300);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //url input
        JLabel urlLabel = new JLabel("Enter Wiki URL:  ");
        JTextField urlField = new JTextField(40);

        //Output labels
        JLabel output1 = new JLabel(" ");
        JLabel output2 = new JLabel(" ");
        JLabel output3 = new JLabel(" ");
        JLabel output4 = new JLabel(" ");
        JLabel output5 = new JLabel(" ");

        JLabel sugestedLabel = new JLabel("                       ==== Recommended Pages ====  ");

        //Buttons
        JButton searchButton = new JButton("Search");
        JButton closeButton = new JButton("Close");

        searchButton.addActionListener(ae -> {
                Scanner urlScan = new Scanner(urlField.getText());
            if (urlScan.hasNext() & urlField.getText().contains("https://en.wikipedia.org") | urlField.getText().contains("https://simple.wikipedia.org")) {
                // Put link to url list array list
                try {

                        JOptionPane.showMessageDialog(null, "Please wait 1-5 minutes depending on the size of the page");

                        backend.inputUrl(urlField.getText());
                        output1.setText(backend.getResult(1)); output1.setForeground(new Color(204,0,0));
                        output2.setText(backend.getResult(2)); output2.setForeground(new Color(204,0,0));
                        output3.setText(backend.getResult(3)); output3.setForeground(new Color(204,0,0));
                        output4.setText(backend.getResult(4)); output4.setForeground(new Color(204,0,0));
                        output5.setText(backend.getResult(5)); output5.setForeground(new Color(204,0,0));

                        urlLabel.setText(" ");
                        sugestedLabel.setVisible(false);
                        urlField.setVisible(false);
                        searchButton.setVisible(false);
                        sugestedLabel.setVisible(true);
                        closeButton.setVisible(true);

                        frame.revalidate();

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Invalid Wiki Link");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter A Valid Wiki Link");
                }
            });

        closeButton.addActionListener(ae -> {
           System.exit(0);
        });



        Box box = Box.createVerticalBox();
        panel.add(box);

        urlLabel.setAlignmentX(JLabel.CENTER);         box.add(urlLabel);
        urlField.setAlignmentX(JLabel.CENTER);         box.add(urlField);

        sugestedLabel.setHorizontalAlignment(JLabel.CENTER); box.add(sugestedLabel);

        searchButton.setAlignmentY(JLabel.CENTER);     box.add(searchButton);

        //labels
        output1.setHorizontalAlignment(JLabel.CENTER); box.add(output1);
        output2.setHorizontalAlignment(JLabel.CENTER); box.add(output2);
        output3.setHorizontalAlignment(JLabel.CENTER); box.add(output3);
        output4.setHorizontalAlignment(JLabel.CENTER); box.add(output4);
        output5.setHorizontalAlignment(JLabel.CENTER); box.add(output5);

        closeButton.setAlignmentX(JLabel.CENTER);      box.add(closeButton);

        closeButton.setVisible(false);
        sugestedLabel.setVisible(false);



        frame.add(panel);
        frame.revalidate();
    }



}

