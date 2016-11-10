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
        Utility.debug("Finished reading screen text file: " + filetext);

        new RomanNumeralDemo();
    }

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

                JFrame frame = new JFrame("Roman Numeral Demo");

                frame.setBackground(Color.WHITE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                //frame.add(new PaintPane());
                frame.setSize(316,216);
                frame.setLocationRelativeTo(null);
               // frame.setVisible(true);
                JPanel description = new JPanel(new GridLayout(1,1));
                description.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
                /*
                ListIterator<String> screentxt = screentext.listIterator();
            while (screentxt.hasNext()){
                g2.drawString((String)screentxt.next(), 40, 20 * ln);
                ln++;
            }
                 */
                //JTextArea desc = new JTextArea(9,40);
                JLabel desc = new JLabel();
                desc.setPreferredSize(new Dimension(300,220));
                desc.setVerticalAlignment(SwingConstants.TOP);
                desc.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
                //desc.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                desc.setText(screentext.toString());
                desc.setFocusable(false);
                description.add(desc);
                frame.add(description, BorderLayout.NORTH);

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
        /*
                numberField.addActionListener(new ActionListener() {
                    @Override
                            public void actionPerformed(ActionEvent e){
                                Utility.debug(romanNumeralConverter.arabicToRoman(Integer.valueOf(numberField.getText())));
                    }
                });

                */
                frame.add(panel,BorderLayout.SOUTH);
                frame.pack();
                frame.setVisible(true);
            }

        });
    }

    protected class PaintPane extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            Utility.debug("Painting");
            /*
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Font font = new Font("Sans-Serif", Font.PLAIN, 14);
            g2.setFont(font);

            String line = null;

            ListIterator<String> screentxt = screentext.listIterator();
            while (screentxt.hasNext()){
                g2.drawString((String)screentxt.next(), 40, 20 * ln);
                ln++;
            }

            g2.dispose();
            */
        }
    }






}

/*
public class RomanNumeralDemo implements Runnable{

    private RomanNumeralConverter romanNumeralConverter;
    static Thread t1;


        static BufferedReader in;
        static int quit = 0;

        public void run() {
            String msg = null;
            while (true) {
                try {
                    msg = in.readLine();
                } catch (Exception e) {
                }

                if (msg.equals("Q")) {
                    quit = 1;
                    break;
                }
            }
        }




    public static void main(String args[]) throws Exception{
        in=new BufferedReader(new InputStreamReader(System.in));

        t1=new Thread(new RomanNumeralDemo());
        t1.start();

        print ("Welcome to the Roman Numeral Demonstration");

        print ("In this demonstration, you will learn how to formulate Roman Numerals properly.");
        print ("");
        print ("There are a few rules to formulating Roman Numerals that should be followed");
        print ("for consistency, so that anyone else reading your Roman Numerals can derive ");
        print ("the correct values.");


        System.out.println("\nType X THEN ENTER to Exit the Demo.");

        while(true){
            t1.sleep(10);
            if(quit==1) break;


        }
    }


    private static void print(String msg){
        //System.out.println(msg);
        for (char c : msg.toCharArray()){
            System.out.print(c);
            System.out.flush();
            try {
                t1.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.print("\n");
    }

}


*/