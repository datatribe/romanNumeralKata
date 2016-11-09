package com.datatribe.util;

/**
 * Created by datatribe on 10/31/2016.
 */

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;



public class Utility {
    public static Logger logger = Logger.getLogger(Utility.class.getName());


    public static List<String> readFile(String filename) {
    try {
        File dir = new File(".");
        File fil = new File(dir.getCanonicalPath() + File.separator + filename);
        FileInputStream fis = new FileInputStream(fil);
        String output = "";
        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line ="";
       List<String> lines = new LinkedList<String>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        //

        //output = sb.toString();

        br.close();
        return lines;
    } catch (IOException ioex){
        Utility.logger.error(ioex.getMessage());
    }
        return null;
    }

    public static void debug(String msg){
        logger.debug(msg);
    }
}
