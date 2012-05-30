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
public class StatementNode extends AbstractNode implements Node {
    
    private static final long serialVersionUID = 1L;
    private AbstractNode statement;

    public StatementNode(String name, int line, int column, AbstractNode statement){
        super(name, line, column);
        this.statement=statement;
        if ( (statement!=null) &&
             !(statement instanceof AssignmentNode) &&
             !(statement instanceof ProcedureCallNode) &&
             !(statement instanceof IfStatementNode) &&
             !(statement instanceof ExpressionNode) &&
             !(statement instanceof WhileStatementNode) &&
             !(statement instanceof RepeatStatementNode)
                ){
              throw new IllegalArgumentException(String.format("AbstractNode Parameter has to be a Statement, but it is %s",statement.getClass().toString()));
        }
            
    }
    
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        if (statement instanceof ExpressionNode){
            throw new Error("Wie lautet der zu erzeugende Code für 'PRINT'");
        }
        statement.compile(symbolTable);
        return null;
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("StatementNode (l:%d c:%d)",line,column));
        if (statement!=null) statement.print();
        unindent();
    }
}
