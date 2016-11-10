package com.datatribe.kata;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.datatribe.util.*;
/**
 * Created by datatribe on 11/4/2016.
 *
 * A UI to demonstrate the RomanNumeralConverter Library.  Provides a brief concept introduction
 * by way of the demo.txt file and two input fields for entering Arabic or Roman numerals to convert
 * to either format.
 */

public class RomanNumeralDemo extends JPanel{

    private static FileInputStream fis;
    private static BufferedReader br;
    private static StringBuffer screentext;
    private static RomanNumeralConverter romanNumeralConverter;

    private static JFrame frame;
    private static JLabel inputLabel;

    private static JPanel appPanel;

    private int ln = 1;

    /**
     * main - loads our display text and instantiates our product object, then kicks off the ui generation.
     * @param args (none needed or used at present)
     */
    public static void main(String[] args){
        romanNumeralConverter = new RomanNumeralConverter();
        List<String> filetext = new LinkedList<String>();
        Utility.debug("Reading demo.txt");
        filetext = Utility.readFile("demo.txt");
        //screentext = filetext;
        screentext = new StringBuffer();
        Iterator fileit = filetext.listIterator();
        while(fileit.hasNext()){
            screentext.append(fileit.next()+"\n");
        }

        new RomanNumeralDemo();
    }

    /**
     *  Constructor - create a thread to run the UI under.
     */
    public RomanNumeralDemo(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                Utility.debug("Running");
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(ClassNotFoundException ex){
                } catch(InstantiationException ex){
                } catch(IllegalAccessException ex){
                } catch(UnsupportedLookAndFeelException ex){

                }

                // set up frame
                JFrame frame = new JFrame("Roman Numeral Demo");
                frame.setBackground(Color.WHITE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setSize(316,216);
                frame.setLocationRelativeTo(null);

                // create description holder panel
                JPanel description = new JPanel(new GridLayout(1,1));
                description.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
                // add a label to support HTML rendering
                JLabel desc = new JLabel();
                desc.setPreferredSize(new Dimension(300,220));
                desc.setVerticalAlignment(SwingConstants.TOP);
                desc.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
                desc.setText(screentext.toString());
                desc.setFocusable(false);
                description.add(desc);
                frame.add(description, BorderLayout.NORTH);

                // add our input fields and associated labels and add them ot a panel at the bottom of the frame.
                JTextField numberField = new JTextField(4);
                numberField.setName("arabic");
                JTextField romanField = new JTextField(30);
                romanField.setName("roman");
                romanField.setFocusable(true);
                JPanel panel = new JPanel(new GridLayout(2,2));
                panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
                panel.setBackground(Color.WHITE);
                panel.add(new JLabel("Number: "));

                panel.add(numberField);
                panel.add(new JLabel("Roman numeral: "));
                panel.add(romanField);
                numberField.setFocusable(true);
                numberField.requestFocus();
                // key listeners allow us to capture input - used instead of event listener for flexibility
                KeyListener numberKeyListener = new KeyListener() {
                    public void keyPressed(KeyEvent keyEvent){}
                    public void keyTyped(KeyEvent keyEvent){}

                    public void keyReleased(KeyEvent keyEvent) {
                        if(keyEvent.getKeyCode()==KeyEvent.VK_ENTER) {
                            int input = 0;
                            try {
                                input = Integer.parseInt(numberField.getText());
                                String output = romanNumeralConverter.arabicToRoman(input);
                                romanField.setText(output);
                            } catch (Exception e) {
                                numberField.setText(numberField.getText().substring(0, numberField.getText().length() - 1));
                            }
                        }
                    }

                };
                numberField.addKeyListener(numberKeyListener);
                KeyListener romanKeyListener = new KeyListener(){
                    public void keyPressed(KeyEvent keyEvent){}
                    public void keyTyped(KeyEvent keyEvent){}
                    public void keyReleased(KeyEvent keyEvent) {
                        if (keyEvent.getKeyCode()==KeyEvent.VK_ENTER) {
                            if (romanField.getText().contains("Out of Range")) {
                                romanField.setText(romanField.getText().replace("Out of Range", ""));
                            }
                            String input;
                            try {
                                input = romanField.getText();
                                String validroman;
                                int output = romanNumeralConverter.romanToArabic(input);
                                validroman = romanNumeralConverter.arabicToRoman(output);
                                numberField.setText(String.valueOf(output));
                                romanField.setText(validroman);
                            } catch (Exception e) {
                                numberField.setText(numberField.getText().substring(0, numberField.getText().length() - 1));
                            }
                        }
                    }
                };
                romanField.addKeyListener(romanKeyListener);
                frame.add(panel,BorderLayout.SOUTH);

                // finalize and display the composition
                frame.pack();
                frame.setVisible(true);
            }

        });
    }

}

