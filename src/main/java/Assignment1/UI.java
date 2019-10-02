package Assignment1;


import java.awt.*;
import javax.swing.*;

import java.io.IOException;
import java.util.Scanner;


public class UI {

    static String urlTxtBox = "";
    static boolean isGoButtonClicked = false;

    public UI() {
        JFrame frame = new JFrame("Wiki-Parser");
        // Panel
        JPanel panel = new JPanel();

        //Frame Stuff
        frame.setSize(800, 300);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //url stuff
        JLabel urlLabel = new JLabel("Enter Wiki URL:  ");
        JTextField urlField = new JTextField(40);

        //Output stuff
        JLabel output1 = new JLabel("Output 1 ");
        JLabel output2 = new JLabel("Output 2 ");
        JLabel output3 = new JLabel("Output 3 ");
        JLabel output4 = new JLabel("Output 4 ");
        JLabel output5 = new JLabel("Output 5 ");

        //gobutton Stuff
        JButton goButton = new JButton("Search");
        goButton.addActionListener(ae -> {
            urlTxtBox = urlField.getText();
            Scanner urlScan = new Scanner(urlTxtBox);
            if (urlScan.hasNext()) {

                            urlTxtBox = urlField.getText();
            } else {
                JOptionPane.showMessageDialog(null, "Enter A Valid Link");
            }
        });






    /*    //empty label stuff
        JLabel emptyLabel = new JLabel("   ");


        //GridBagLayout
        //frame.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();


        //urlLabel locations
        gc.gridy = 0; // row 0
        gc.gridx = 0; // column 0
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(urlLabel, gc);


        //Field(textbox) locations
        gc.gridy = 0; // row 0
        gc.gridx = 0; // column 1
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(urlField, gc);

        //GoButton locations
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(goButton, gc);

        //empty label location
        gc.gridy = 3;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(emptyLabel, gc);



*/



        Box box = Box.createVerticalBox();
        panel.add( box );

        urlLabel.setAlignmentX(JLabel.CENTER);
        box.add(urlLabel);

        urlField.setAlignmentX(JLabel.CENTER);
        box.add(urlField);

        goButton.setAlignmentY(JLabel.CENTER);
        box.add(goButton );

        output1.setHorizontalAlignment(JLabel.CENTER);
        box.add(output1);

        output2.setHorizontalAlignment(JLabel.CENTER);
        box.add(output2);

        output3.setHorizontalAlignment(JLabel.CENTER);
        box.add(output3);

        output4.setHorizontalAlignment(JLabel.CENTER);
        box.add(output4);

        output5.setHorizontalAlignment(JLabel.CENTER);
        box.add(output5);

        frame.add(panel);
        frame.revalidate();
    }



    public static String getURLTxtBox() {
        System.out.println(urlTxtBox);
        return urlTxtBox;
    }

    public static boolean getGoButtonState() {
        System.out.println(" is button clicked " + isGoButtonClicked);
        return isGoButtonClicked;
    }


}

