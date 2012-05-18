/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;

import cip.base.AbstractDescr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Tobi
 */
public class ActualParametersNode extends AbstractNode{

    private static final long serialVersionUID = 1L;
    private final List<ExpressionNode> expressions;
    
    public ActualParametersNode(String name, int line, int column, List<ExpressionNode> expressions){
        super(name, line, column);
        this.expressions=expressions;
        if (expressions.size()<1) {
            throw new Error(String.format("At least one Element for Argumen expressions requiet. Curren size is %d",expressions.size()));
        }
    }
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("ActualParamterNode (l:%d c:%d)",line,column));
        for (ExpressionNode expressionNode : expressions) {
            expressionNode.print();
        }
        unindent();
    }
    
}
