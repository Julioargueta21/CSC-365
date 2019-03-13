package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static BackEnd.HTMLParser.*;

public class UI {

    private static String scanJTextField = "";

    public UI() {

        JFrame frame = new JFrame( "Wiki-Parser" );
        // Panel
        JPanel panel = new JPanel();

        //Frame Stuff
        frame.setSize( 400, 200 );
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
        panel.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


        //url stuff
        JLabel urlLabel = new JLabel( "Search using URL:  " );
        JTextField urlField = new JTextField( 20 );


        //goButton Stuff
        JButton goButton = new JButton( "Go" );
        goButton.addActionListener( ae -> {
            scanJTextField = urlField.getText();
            Scanner urlScan = new Scanner( scanJTextField );
            if (urlScan.hasNext()) {
                try {
                    grabWebPage( true );
                    recommendPages();
                    Desktop.getDesktop().open(new File( "Recommendations.txt" ));
                    JOptionPane.showMessageDialog( null, "Success, showing related pages " );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog( null, "Enter A Valid Link" );
            }
        } );

        //fileButton Stuff
        JButton fileButton = new JButton( "Search using control file" );
        fileButton.addActionListener( ae -> {
            clearFiles();
            try {
                grabWebPage( false );
                JOptionPane.showMessageDialog( null, "Success, check your directory " );

            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        //empty label stuff
        JLabel emptyLabel = new JLabel( "   " );


        //GridBagLayout
        GridBagConstraints gc = new GridBagConstraints();


        //urlLabel locations
        gc.gridy = 0; // row 0
        gc.gridx = 0; // column 0
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add( urlLabel, gc );

        //Field(textbox) locations
        gc.gridy = 0; // row 0
        gc.gridx = 1; // column 1
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add( urlField, gc );

        //GoButton locations
        gc.gridy = 1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add( goButton, gc );

        //empty label location
        gc.gridy = 3;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add( emptyLabel, gc );


        //Search using txt file
        gc.gridy = 4;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add( fileButton, gc );

        frame.add( panel );
    }


    public static String getURLTxtBox() {
        System.out.println( scanJTextField );
        return scanJTextField;
    }


}
