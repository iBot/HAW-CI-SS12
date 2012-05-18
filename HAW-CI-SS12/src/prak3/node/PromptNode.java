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
public class PromptNode extends AbstractNode{

	private static final long serialVersionUID = 1L;
    private final StringNode string;
    
    public PromptNode(String name, int line, int column, StringNode string){
        super(name, line, column);
        this.string = string;
    }
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        System.out.println(String.format("PromptNode (l:%d c:%d)",line,column));
        string.print();
    }
    
}
