/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import prak3.descr.AbstractDescr;
import prak3.node.Node;

/**
 *
 * @author Tobi
 */
public class CodeGenerator {
    
    private static HashMap<String, AbstractDescr> symboltable;
    private static Node startnode;
    public static String targetFile = "assembler.txt";
    private static FileWriter fstream;
    private static BufferedWriter out;
    private static int addressCounter;
    private static int labelId;
    
    public static void generateCode(Node startnode){
        CodeGenerator.labelId = 0;
        CodeGenerator.addressCounter = 0;
        CodeGenerator.startnode = startnode;
        symboltable = new HashMap<String, AbstractDescr>();
        try {
            fstream = new FileWriter(targetFile);
            out = new BufferedWriter(fstream);
            startnode.compile(symboltable);
            out.close();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    
    public static int getCurrentAddress(){
        return addressCounter;
    }
    
    public static int increaseAddressBy(int size){
        addressCounter+=size;
        return addressCounter;
    }
    
    public static void writeln(String codeline){
        try {
            out.write(codeline);
            out.write("\n");
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    
    public static String getLabel(){
        return String.format("LBL%d", labelId++);
    }
    
}
