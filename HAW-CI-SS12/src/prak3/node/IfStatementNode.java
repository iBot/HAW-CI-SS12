/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Tobi
 */
public class IfStatementNode extends AbstractNode implements Node {
    
    private static final long serialVersionUID = 1L;
    private final List<ExpressionNode> expressions;
    private final List<StatementSequenceNode> statements;

    public IfStatementNode(String name, int line, int column, List<ExpressionNode> expressions, List<StatementSequenceNode> statements){
        super(name, line, column);
        this.expressions = expressions;
        this.statements = statements;
    }
    
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        System.err.println("Bllaaa");
        return null;
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("IfStatementNode (l:%d c:%d)",line,column));
        System.out.println("IF");
        expressions.get(0);
        statements.get(0);
        for (int i = 1; i < expressions.size(); i++) {
            System.out.println("ELSEIF");
            expressions.get(i);
            statements.get(i);
        }
        if (statements.size()+1==expressions.size()){
            System.out.println("ELSE");
            statements.get(statements.size()-1);
        }
        unindent();
    }
}
