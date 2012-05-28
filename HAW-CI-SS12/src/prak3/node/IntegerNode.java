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
public class IntegerNode extends AbstractNode implements Node {
	private static final long serialVersionUID = 1L;
    private final int value;

    public IntegerNode(String name, int line, int column, int value){
        super(name, line, column);
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("IntegerNode (l:%d c:%d)",line,column));
        System.out.println(value);
        unindent();
    }
    
}
