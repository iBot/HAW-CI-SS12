/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;
import java.util.List;
import static prak2.CodeGenerator.*;

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
        String end = getLabel();
        String nextCase = getLabel();
        expressions.get(0).compile(symbolTable);
        writeln("BF, "+nextCase);
        statements.get(0).compile(symbolTable);
        writeln("JUMP, "+end);
        for (int i = 1; i < expressions.size(); i++){
            writeln(nextCase+":");
            nextCase = getLabel();
            expressions.get(i).compile(symbolTable);
            writeln("BF, "+nextCase);
            statements.get(i).compile(symbolTable);
            writeln("JUMP, "+end);
        }
        if (statements.size()+1==expressions.size()){
            writeln(nextCase+":");
            nextCase = end;
            statements.get(statements.size()-1).compile(symbolTable);
        }
        writeln(nextCase+":");
        writeln(end+":");
        return null;
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("IfStatementNode (l:%d c:%d)",line,column));
        System.out.println("IF");
        expressions.get(0).print();
        System.out.println("THEN");
        statements.get(0).print();
        for (int i = 1; i < expressions.size(); i++) {
            System.out.println("ELSEIF");
            expressions.get(i).print();
            System.out.println("THEN");
            statements.get(i).print();
        }
        if (statements.size()+1==expressions.size()){
            System.out.println("ELSE");
            statements.get(statements.size()-1).print();
        }
        unindent();
    }
}
