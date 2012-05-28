/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import prak3.descr.AbstractDescr;
import prak3.node.AbstractNode;
import prak3.node.IndexExpressionNode;
import prak3.node.Node;
import prak3.node.SimpleExpressionNode;

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
    
    public static void generateCode(Node startnode){
        CodeGenerator.startnode = startnode;
        symboltable = new HashMap<String, AbstractDescr>();
        try {
            fstream = new FileWriter(targetFile);
            out = new BufferedWriter(fstream);
            ((IndexExpressionNode)startnode).compile(symboltable);
            out.close();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    
    public static void writeln(String codeline){
        try {
            out.write(codeline);
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    
    
}
