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
public class ConstIdentNode extends AbstractNode implements Node {
    
	private static final long serialVersionUID = 1L;
    private final IdentNode ident;

    public ConstIdentNode(String name, int line, int column, IdentNode ident){
        super(name, line, column);
        this.ident = ident;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("ConstIdentNode (l:%d c:%d)",line,column));
        ident.print();
        unindent();
    }
    
}
