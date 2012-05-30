/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;
import static prak2.CodeGenerator.*;

/**
 *
 * @author Tobi
 */
public class AssignmentNode extends AbstractNode implements Node {

	private static final long serialVersionUID = 1L;
    private final IdentNode ident;
    private final SelectorNode selector;
    private final ExpressionNode expression;
    public AssignmentNode(String name, int line, int column, IdentNode ident,SelectorNode selector ,ExpressionNode expression){
        super(name, line, column);
        this.ident= ident;
        this.selector = selector;
        this.expression = expression;
    }
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        expression.compile(symbolTable);
        int address; //TODO:
        if (true) throw new Error("Ident-Adresse muss an address zugewiesen werden");
        ident.compile(symbolTable);
        writeln("ASSIGN, 1");
        
        return null;
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("AssignementNode (l:%d c:%d)",line,column));
        ident.print();
        selector.print();
        System.out.println(":=");
        expression.print();
        unindent();
    }
    
}
