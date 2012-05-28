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
public class WhileStatementNode extends AbstractNode implements Node {
    
    private static final long serialVersionUID = 1L;
    private final ExpressionNode expression;
    private final StatementSequenceNode statements;
    
    public WhileStatementNode(String name, int line, int column, ExpressionNode expression, StatementSequenceNode statements){
        super(name, line, column);
        this.expression=expression;
        this.statements=statements;
    }
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("WhileStatementNode (l:%d c:%d)",line,column));
        expression.print();
        statements.print();
        unindent();
    }
}
