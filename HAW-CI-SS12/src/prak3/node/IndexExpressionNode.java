package prak3.node;

import java.util.HashMap;
import prak2.CodeGenerator;

import prak3.descr.AbstractDescr;
import prak3.descr.IntConstDescr;

public class IndexExpressionNode extends AbstractNode  implements Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

        private final AbstractNode innerNode;

        public IndexExpressionNode(String name, int line, int column, AbstractNode innerNode){
            super(name, line, column);
            if (!(innerNode instanceof ConstIdentNode) && !(innerNode instanceof IntegerNode)){
                throw new IllegalArgumentException(String.format("InnerNodeParameter has to be instanceof ConstIdentNode or IntegerNode, but it is %s",innerNode.getClass().toString()));
            }
            this.innerNode = innerNode;

        }
        
        @Override
        public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
            AbstractDescr compile = innerNode.compile(symbolTable);
            IntConstDescr icd = (IntConstDescr)compile;
            int value = icd.getIntVal();
            CodeGenerator.writeln("PUSHI, "+value);
            return null;
        }

        @Override
        public void print() {
            indent();
            System.out.println(String.format("IndexExpressionIdentNode (l:%d c:%d)",line,column));
            innerNode.print();
            unindent();
        }

}
