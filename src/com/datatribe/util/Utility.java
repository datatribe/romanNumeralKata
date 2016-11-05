package com.datatribe.util;

/**
 * Created by datatribe on 10/31/2016.
 */

import org.apache.log4j.Logger;

import java.io.*;


public class Utility {
    public static Logger logger = Logger.getLogger(Utility.class.getName());


    public static String readFile(File fin) throws IOException {
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\r");
        }

        //


        br.close();
        return sb.toString();
    }

    public static void debug(String msg){
        logger.debug(msg);
    }
}
