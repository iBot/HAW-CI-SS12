/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;
import java.util.HashMap;
import prak3.descr.AbstractDescr;
import static prak2.CodeGenerator.*;

/**
 *
 * @author Tobi
 */
public class RepeatStatementNode extends AbstractNode implements Node {
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
        String start = getLabel();
        writeln(start+":");
        statements.compile(symbolTable);
        expression.compile(symbolTable);
        writeln("BF, "+start);
        return null;
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
