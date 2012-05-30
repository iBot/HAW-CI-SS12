/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;

import prak3.descr.AbstractDescr;
import java.util.HashMap;

/**
 *
 * @author Tobi
 */
public class StringNode extends AbstractNode implements Node {
    
	private static final long serialVersionUID = 1L;
    private String value;

    public StringNode(String name, int line, int column){
        super(name, line, column);
        this.value =name;
    }
    
    public String getValue(){
        return value;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        //TODO:
        throw new Error("Wie wirde ein String compiliert? Ich kann ihn ja nicht komplett auf den Stack schreiben, oder?");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("StringNode (l:%d c:%d)",line,column));
        System.out.println(value);
        unindent();
    }
    
}
