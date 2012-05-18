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
public class ProcedureCallNode extends AbstractNode{

    private static final long serialVersionUID = 1L;
    private final ActualParametersNode actualParameters;
    private final IdentNode ident;
    
    public ProcedureCallNode(String name, int line, int column, IdentNode ident, ActualParametersNode actualParameters){
        super(name, line, column);
        this.ident = ident;
        this.actualParameters = actualParameters;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("ActualParametersNode (l:%d c:%d)",line,column));
        ident.print();
        if (actualParameters!=null) actualParameters.print();
        unindent();
    }
    
}
