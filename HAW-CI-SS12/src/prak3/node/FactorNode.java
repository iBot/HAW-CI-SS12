/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;

import cip.base.AbstractDescr;
import java.util.HashMap;

/**
 *
 * @author Tobi
 */
public class FactorNode extends AbstractNode{
    private static final long serialVersionUID = 1L;
    private final AbstractNode fNode;
    private final IdentNode ident;

    public FactorNode(String name, int line, int column,AbstractNode fNode, IdentNode ident){
        super(name, line, column);
        this.fNode=fNode;
        this.ident=ident;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        System.out.println(String.format("FactorNode (l:%d c:%d)",line,column));
        if (ident!=null) ident.print();
        fNode.print();
    }
}
