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
public class TermNode extends AbstractNode implements Node {
     private static final long serialVersionUID = 1L;
    private final FactorNode firstFactor;
    private final List<Character> operators;
    private final List<FactorNode> factors;


    public TermNode(String name, int line, int column, FactorNode firstFactor, List<Character> operators, List<FactorNode> factors){
        super(name, line, column);
        this.firstFactor=firstFactor;
        this.operators=operators;
        this.factors=factors;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        firstFactor.compile(symbolTable);
        for (int i = 0; i < operators.size(); i++) {
            factors.get(i).compile(symbolTable);
            char op = operators.get(i);
            if (op=='*') {
                writeln("MUL");
            } else if (op=='/'){
                writeln("DIV");
            } else {
                throw new Error("Wrong operator. Expected: * or /. Result: "+op);
            }
        }
        return null;
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("TermNode (l:%d c:%d)",line,column));
        firstFactor.print();
        for (int i = 0; i<operators.size();i++) {
            System.out.println(operators.get(i));
            factors.get(i).print();
        }
        unindent();
    }
}
