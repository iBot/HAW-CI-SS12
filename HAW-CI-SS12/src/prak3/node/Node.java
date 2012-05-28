/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;

import java.util.HashMap;
import prak3.descr.AbstractDescr;

/**
 *
 * @author Tobi
 */
public interface Node {
        public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable);

	public void print();

}
