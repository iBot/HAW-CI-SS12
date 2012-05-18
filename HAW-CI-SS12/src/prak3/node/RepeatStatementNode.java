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
public class RepeatStatementNode extends AbstractNode{
    private static final long serialVersionUID = 1L;
    private final ExpressionNode expression;
    private final StatementSequenceNode statements;
    
    public RepeatStatementNode(String name, int line, int column, StatementSequenceNode statements, ExpressionNode expression){
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
        System.out.println(String.format("RepeatStatementNode (l:%d c:%d)",line,column));
        statements.print();
        expression.print();
        unindent();
    }
}
