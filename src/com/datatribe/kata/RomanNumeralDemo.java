package com.datatribe.kata;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by datatribe on 11/4/2016.
 */

public class RomanNumeralDemo extends JPanel{

    private static FileInputStream fis;
    private static BufferedReader br;
    private static String[] screentext;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 14);
        g2.setFont(font);

        String line = null;
        int ln = 1;
        for (String s : screentext) {
            g2.drawString(s, 40, 40 * ln);
            ln++;
        }
    }
    public static void main(String[] args) {

        ;
        try {
            File dir = new File(".");
            File fil = new File(dir.getCanonicalPath() + File.separator + "demo.txt");
            readFile(fil);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame f = new JFrame();
        f.getContentPane().add(new RomanNumeralDemo());
        f.setSize(600, 400);
        f.setVisible(true);
    }

    private static void readFile(File fin) throws IOException {
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line + "\r");
        }

        screentext = sb.toString().split("\r");

        br.close();
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