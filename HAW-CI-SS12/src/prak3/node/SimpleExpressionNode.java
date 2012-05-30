/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;

import java.util.HashMap;
import java.util.List;
import prak3.descr.AbstractDescr;
import static prak2.CodeGenerator.*;

/**
 *
 * @author Tobi
 */
public class SimpleExpressionNode extends AbstractNode implements Node {
    private static final long serialVersionUID = 1L;
    private final TermNode firstTerm;
    private final List<Character> operators;
    private final List<TermNode> terms;
    private final boolean firstIsNegative;
    
    public SimpleExpressionNode(String name, int line, int column, boolean firstIsNegative, TermNode firstTerm, List<Character> operators, List<TermNode> terms){
        super(name, line, column);
        this.firstIsNegative=firstIsNegative;
        this.firstTerm=firstTerm;
        this.operators=operators;
        this.terms=terms;       
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        firstTerm.compile(symbolTable);
        if (firstIsNegative){
            writeln("PUSHI, -1");
            writeln("MUL");
        }
        for (int i = 0; i<operators.size();i++) {
            terms.get(i).compile(symbolTable);
            char op = operators.get(i);
            if (op=='+') {
                writeln("ADD");
            } else if (op=='-'){
                writeln("SUB");
            }
        }
        return null;
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("SimpleExpressinNode (l:%d c:%d)",line,column));
        System.out.print((firstIsNegative ? "-\n" : ""));
        firstTerm.print();
        for (int i = 0; i<operators.size();i++) {
            System.out.println(operators.get(i));
            terms.get(i).print();
        }
        unindent();
    } 
}
