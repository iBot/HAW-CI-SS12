package prak2;

import java.util.ArrayList;
import java.util.List;
import prak3.node.*;

public class SuperParser {

	static int nextsymbol = 0;
	static int labcnt = 0;
	static String infile;
	static SuperScanner scanner = null;

	static String spaces = "";

	/*
	 * Hilfsroutinen
	 */

	public static void indent() {
		spaces = spaces + "  ";
	}

	public static void unindent() {
		spaces = spaces.substring(2);
	}

	public static void compile(String str) {
		System.out.println(spaces + " -- " + str);
	}

	public static void outStr(String str) {
		System.out.println(spaces + " -- " + str + " ");
	}

	public static void outInt(int i) {
		System.out.println(spaces + " -- " + i + " ");
	}

	public static void outOp(String op) {
		System.out.println(spaces + " -- " + op);
	}

	public static void error(String str) {
		System.out.println("==> Error: "+"Zeile " + SuperScanner.yyline+" Spalte: "+SuperScanner.yycolumn+" " + str);
		System.exit(0);
	}

	public static void insymbol() {
		try {
			while ((nextsymbol = scanner.yylex()) == SuperScanner.whitespace)
				;
			//
			System.out.println(spaces + "insymbol: " + nextsymbol);
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + infile + "\"");
		} catch (java.io.IOException e) {
			System.out.println("IO error scanning file \"" + infile + "\"");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}
	}

	/*
	 * Parserroutinen
	 */

	/******************* Ausdrücke ******************/

	public static FactorNode factor() {

		indent();
		System.out.println(spaces + "factor: " + nextsymbol);
                
                int fL = SuperScanner.yyline;
                int fC = SuperScanner.yycolumn;
                
                IdentNode ident = null;
                AbstractNode fNode = null;
                
		if (nextsymbol == SuperScanner.lpar) {
			insymbol();
			fNode=expression();
			if (nextsymbol == SuperScanner.rpar)
				insymbol();
			else
				error(" ) expected");
		} else if (nextsymbol == SuperScanner.intconst) {
			outInt(SuperScanner.intval);
                        fNode = new IntegerNode("ThisIsTheNameOfAnIntegerNode", fL, fC, SuperScanner.intval);
			insymbol();
		} else if (nextsymbol == SuperScanner.ident) {
			outStr(SuperScanner.strval);
                        ident = new IdentNode(SuperScanner.strval, fL, fC);
                        insymbol();
                        fNode=selector();
		} else if (nextsymbol == SuperScanner.string){
                        
                        fNode=string();
                } else if (nextsymbol == SuperScanner.read){
                        insymbol();
                        fNode = read();
                }
		unindent();
                return new FactorNode("ThisIsTheNameOfAFactorNode", fL, fC, fNode, ident);
	}

	public static TermNode term() {
		indent();
		System.out.println(spaces + "term: " + nextsymbol);

                int tL = SuperScanner.yyline;
                int tC = SuperScanner.yycolumn;
                
                FactorNode firstFactor=factor();
                List<Character> operators = new ArrayList<>();
                List<FactorNode> factors = new ArrayList<>();
                
		while ((nextsymbol == SuperScanner.multop)
				|| (nextsymbol == SuperScanner.divop)) {
                        if (nextsymbol == SuperScanner.multop){
                            operators.add('*');
                        } else {
                            operators.add('/');
                        }
			insymbol();
			factors.add(factor());
		}
		unindent();
                return new TermNode("ThisIsTheNameOfATermNode", tL, tC, firstFactor, operators, factors);
	}

	public static SimpleExpressionNode simpleExpr() {
		

                int sEL = SuperScanner.yyline;
                int sEC = SuperScanner.yycolumn;
                List<Character> operators= new ArrayList<>();
                List<TermNode> terms= new ArrayList<>();
                
                boolean isNegative = false;
                
		indent();
		System.out.println(spaces + "simpleExpr: " + nextsymbol);

                if(nextsymbol == SuperScanner.subop){
                    isNegative=true;
                    insymbol();
                }
		TermNode firstTerm=term();
		while ((nextsymbol == SuperScanner.addop)
				|| (nextsymbol == SuperScanner.subop)) {
                        if (nextsymbol == SuperScanner.addop) {
                            operators.add('+');
                        } else {
                            operators.add('-');
                        }
			insymbol();
			terms.add(term());
		}
		unindent();
                return new SimpleExpressionNode("ThisIsTheNameOfASimpleExpressionNode", sEL, sEC, isNegative, firstTerm, operators, terms);
	}

	static void sexprSeq() {

		indent();
		System.out.println(spaces + "sexprSeq: " + nextsymbol);

		if (nextsymbol == SuperScanner.beginsy)
			insymbol();
		else
			error("BEGIN expected\n");
		while ((nextsymbol == SuperScanner.lpar)
				|| (nextsymbol == SuperScanner.ident)
				|| (nextsymbol == SuperScanner.intconst)) {
			simpleExpr();
		}
		if (nextsymbol == SuperScanner.endsy)
			insymbol();
		else
			error("END expected\n");
		unindent();
	}

	static void program() {

		indent();
		System.out.println(spaces + "program: " + nextsymbol);

		while (nextsymbol == SuperScanner.beginsy) {
			sexprSeq();
			//identList();
		}
		unindent();
	}
	
	
	/*
	 * ---------------------------------------
	 */
	
	public static void identList(){
		indent();
		System.out.println(spaces + "IdentList: " + nextsymbol);
		if(nextsymbol==SuperScanner.ident){
			insymbol(); 
		} else {
			error("Indent expected");
		}
		while(nextsymbol == SuperScanner.comma){
			insymbol();
			if(nextsymbol==SuperScanner.ident){
				insymbol(); 
			} else {
				error("Indent expected");
			}
		}
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
	}
	
	/**
	 * TODO | ArrayType | RecordType. 
	 * . ?????
	 */
	public static void type(){
		indent();
		System.out.println(spaces + "Type: " + nextsymbol);
		if(!(nextsymbol==SuperScanner.ident)){
			error("Ident expected");
		}
		insymbol();
//		if(!(nextsymbol==SuperScanner.dot)){
//			error(". erwartet");
//		}
//		insymbol();
		unindent();
	}
	
	public static void arrayType(){
		indent();
		System.out.println(spaces + "ArrayType: " + nextsymbol);
		//ARRAY
		if(!(nextsymbol==SuperScanner.array)){
			error("ARRAY expected");
		}
		insymbol();
		//[
		if(!(nextsymbol==SuperScanner.lsquarebraket)){
			error("[ expected");
		}
		insymbol();
		indexExpression();
		//]
		if(!(nextsymbol==SuperScanner.rsquarebraket)){
			error("] expected");
		}
		insymbol();
		//OF
		if(!(nextsymbol==SuperScanner.of)){
			error("OF expected");
		}
		insymbol();
		type();
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error(". expected");
//		}
//		insymbol();
		unindent();
	}
	
	public static IndexExpressionNode indexExpression(){
		indent();
                int ieL = SuperScanner.yyline;
                int ieC = SuperScanner.yycolumn;
                AbstractNode innerNode = null;
                
		System.out.println(spaces + "IndexExpresion: " + nextsymbol);
		if(nextsymbol==SuperScanner.ident){
			innerNode=constIdent();
		} else if(nextsymbol==SuperScanner.intconst){
                    
                        innerNode = new IntegerNode("ThisIsTheNameOfAnIntegerNode", ieL, ieC, SuperScanner.intval);
			insymbol();
		} else {
			error("ConstIdent or Integer expected");
		}
		unindent();
                return new IndexExpressionNode("ThisIsTheNameOfAnIndexExpressionNode", ieL, ieC, innerNode);
	}
	
	public static void fieldList(){
		indent();
		System.out.println(spaces + "FieldList: " + nextsymbol);
		if(nextsymbol==SuperScanner.ident){
			identList();
			// :
			if(!(nextsymbol==SuperScanner.double_dot)){
				error(": erwartet");
			}
			insymbol();
			type();
			//]
//			if(!(nextsymbol==SuperScanner.rsquarebraket)){
//				error("[ erwartet");
//			}
			//.
	//		if(!(nextsymbol==SuperScanner.dot)){
	//			error(". erwartet");
	//		}
	//		insymbol();
		}
		unindent();
	}
	
	public static void recordType(){
		indent();
		System.out.println(spaces + "RecordType: " + nextsymbol);
		if(!(nextsymbol==SuperScanner.record)){
			error("RECORD erwartet");
		}
		insymbol();
		fieldList();
		while(nextsymbol==SuperScanner.semicolon){
			insymbol();
			fieldList();
		}
		//END
		if(!(nextsymbol==SuperScanner.endsy)){
			error("END erwartet");
		}
		insymbol();
//		if(!(nextsymbol==SuperScanner.dot)){
//			error(". erwartet");
//		}
//		insymbol();
//		unindent();
	}
	
	public static void fpSection(){
		indent();
		System.out.println(spaces + "FPSection: " + nextsymbol);
		if((nextsymbol==SuperScanner.var)){
			insymbol();
		}
		identList();
		//:
		if(!(nextsymbol == SuperScanner.double_dot)){
			error(": erwartet");
		}
		insymbol();
		type();
//		if(!(nextsymbol == SuperScanner.dot)){
//			error(". erwartet");
//		}
//		insymbol();
		unindent();
	}
	
	public static void formalParameters(){
		indent();
		System.out.println(spaces + "FormalParameters: " + nextsymbol);
		fpSection();
		while(nextsymbol==SuperScanner.semicolon){
			insymbol();
			fpSection();
		}
		//.
//		if(!(nextsymbol == SuperScanner.dot)){
//			error(". erwartet");
//		}
//		insymbol();
		unindent();
	}
	
	public static void procedureHeading(){
		indent();
		System.out.println(spaces + "ProcedureHeading: " + nextsymbol);
		if(!(nextsymbol==SuperScanner.procedure)){
			error("PROCEDURE erwartet");
		}
		insymbol();
		if(!(nextsymbol==SuperScanner.ident)){
			error("Ident erwartet");
		}
		insymbol();
		if(!(nextsymbol==SuperScanner.lpar)){
			error("( erwartet");
		}
		insymbol();
		if(!(nextsymbol==SuperScanner.lpar)){
			formalParameters();
		}
		//)
		if(!(nextsymbol==SuperScanner.rpar)){
			error(") erwartet");
		}
		insymbol();
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error(". erwartet");
//		}
//		insymbol();
		unindent();
	}
	
	public static void procedureBody(){
		indent();
		System.out.println(spaces + "ProcedureBody: " + nextsymbol);
		declarations();
		if(!(nextsymbol==SuperScanner.beginsy)){
			error("Begin erwartet");
		}
		insymbol();
		statementSequence();
		if(!(nextsymbol==SuperScanner.endsy)){
			error("end erwartet");
		}
		insymbol();
		unindent();
	}
	
	public static void procedureDeclarations(){
		indent();
		System.out.println(spaces + "ProcedureDeclarations: " + nextsymbol);
		procedureHeading();
		if(!(nextsymbol==SuperScanner.semicolon)){
			error("; erwartet");
		}
		insymbol();
		procedureBody();
		if(!(nextsymbol==SuperScanner.ident)){
			error("ident erwartet");
		}
		insymbol();
		unindent();
	}
	
	public static void declarations(){
		indent();
		System.out.println(spaces + "Declarations: " + nextsymbol);
		if(nextsymbol==SuperScanner.constt){
			insymbol();
			if(!(nextsymbol==SuperScanner.ident)){
				error("ident erwartet");
			}
			insymbol();
			if(!(nextsymbol==SuperScanner.equal)){
				error("= erwartet");
			}
			insymbol();
			expression();
			if(!(nextsymbol==SuperScanner.semicolon)){
				error("; erwartet");
			}
			insymbol();
			while(nextsymbol==SuperScanner.ident){
				insymbol();
				if(!(nextsymbol==SuperScanner.equal)){
					error("= erwartet");
				}
				insymbol();
				expression();
				if(!(nextsymbol==SuperScanner.semicolon)){
					error("; erwartet");
				}
				insymbol();
			}
		}
		else if(nextsymbol==SuperScanner.type){
			insymbol();
			if(!(nextsymbol==SuperScanner.ident)){
				error("ident erwartet");
			}
			insymbol();
			if(!(nextsymbol==SuperScanner.equal)){
				error("= erwartet");
			}
			insymbol();
			type();
			if(!(nextsymbol==SuperScanner.semicolon)){
				error("; erwartet");
			}
			insymbol();
			while(nextsymbol==SuperScanner.ident){
				insymbol();
				if(!(nextsymbol==SuperScanner.equal)){
					error("= erwartet");
				}
				insymbol();
				type();
				if(!(nextsymbol==SuperScanner.semicolon)){
					error("; erwartet");
				}
				insymbol();
			}
		}
		else if(nextsymbol==SuperScanner.var){
			insymbol();
			identList();
			if(!(nextsymbol==SuperScanner.double_dot)){
				error(": erwartet");
			}
			insymbol();
			type();
			if(!(nextsymbol==SuperScanner.semicolon)){
				error("; erwartet");
			}
			insymbol();
			while(nextsymbol==SuperScanner.ident){
				identList();
				if(!(nextsymbol==SuperScanner.double_dot)){
					error(": erwartet");
				}
				insymbol();
				type();
				if(!(nextsymbol==SuperScanner.semicolon)){
					error("; erwartet");
				}
				insymbol();
			}
		}
		while(nextsymbol==SuperScanner.procedure){
			procedureDeclarations();
			if(!(nextsymbol==SuperScanner.semicolon)){
				error("; erwartet");
			}
			insymbol();
		}
//		if(!(nextsymbol==SuperScanner.dot)){
//			error(". erwartet");
//		}
//		insymbol();
		unindent();
	}
	
	public static void module(){
		indent();
		System.out.println(spaces + "Module: " + nextsymbol);
		//'MODULE'
		if (nextsymbol == SuperScanner.module){
			insymbol();
		} else {
			error("MODULE expected");
		}
		
		//ident
		if (nextsymbol == SuperScanner.ident){
			insymbol();
		} else {
			error("ident expected");
		}
		
		//';'
		if (nextsymbol == SuperScanner.semicolon){
			insymbol();
		} else {
			error("; expected");
		}
		
		//Declaration
		declarations();
		
		//'BEGIN'
		if (nextsymbol == SuperScanner.beginsy){
			insymbol();
		} else {
			error("BEGIN expected");
		}
		
		//Declaration
		statementSequence();
		
		//'END'
		if (nextsymbol == SuperScanner.endsy){
			insymbol();
		} else {
			error("END expected");
		}
		
		//ident
		if (nextsymbol == SuperScanner.ident){
			insymbol();
		} else {
			error("ident expected");
		}
		
		//'.'
		if (nextsymbol == SuperScanner.dot){
			insymbol();
		} else {
			error(". expected");
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		
//		insymbol();
		unindent();
	}
	
	
	
	/*
	 * main
	 */

	public static void main(String[] argv) {
		System.out.println("SimpleExpression Version 0.0");

		if (argv.length == 0) {
			System.out.println("Usage : java MySexprParserMain1 <inputfile>");
		} else {
			for (int i = 0; i < argv.length; i++) {
				try {
					// Hier geht es los:
					//	        	
					infile = argv[i];
					scanner = new SuperScanner(new java.io.FileReader(infile));
					insymbol();
					while (nextsymbol > 0) {
						//program();
						//identList();
						//arrayType();
						//fieldList();
						//recordType();
						//fpSection();
						//formalParameters();
						//procedureHeading();
						//procedureBody();
						//procedureDeclarations();
						//declarations();
						//module();
						//expression();
						// System.out.println(nextsymbol);
						//constIdent();	//OK
						//indexExpression(); //OK
						//term(); //OK
						//simpleExpr(); //OK
						//expression(); //OK
						//selector();   //OK
						//factor();     //OK
						//ifStatement();    //OK
                                                //whileStatement();   //OK
                                                //repeatStatement();  //OK
						//statement();  //OK
                                                //statementSequence();    //OK
						//string(); //OK
						//prompt(); //OK
						//read();   //OK
						//assignment("Bla"); //OK
						//actualParameters(); //OK
						//procedureCall("Bla"); //OK
                                            indexExpression().print();
					}
					//	          
					// Und hier ist Schluss
				} catch (java.io.FileNotFoundException e) {
					System.out.println("File not found : \"" + argv[i] + "\"");
				} catch (Exception e) {
					System.out.println("Unexpected exception:");
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AssignmentNode assignment(IdentNode ident){
		indent();
                int line = SuperScanner.yyline;
                int column = SuperScanner.yycolumn;
		System.out.println(spaces + "Assignment: " + nextsymbol);
		
		
		//Selector
		SelectorNode selector= selector();
		
		//:=
		if(nextsymbol==SuperScanner.decl){
			insymbol(); 
		} else {
			error("Declaration-Symbol ':=' expected");
		}
		
		//Expression
		ExpressionNode expression = expression();
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		
//		insymbol();
		unindent();
                return new AssignmentNode("ThisIsTheName", line, column, ident,selector , expression);
	}
	
	public static ActualParametersNode actualParameters(){
		indent();
                
                int apL = SuperScanner.yyline;
                int apC = SuperScanner.yycolumn;
                List<ExpressionNode> expressions = new ArrayList<>();
                
		System.out.println(spaces + "ActualParameters: " + nextsymbol);
		
		//Expression
		expressions.add(expression());
		
		//{','
		while(nextsymbol == SuperScanner.comma){
			insymbol();
			
			//Expression
			expressions.add(expression());
			
		//}
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		
//		insymbol();
		unindent();
                return new ActualParametersNode("ThisIsANameOfAnActualParametersList", apL, apC, expressions);
	}
	
	public static ProcedureCallNode procedureCall(IdentNode ident){
		indent();
		System.out.println(spaces + "ProcedureCall: " + nextsymbol);
                
                int pcL = SuperScanner.yyline;
                int pcC = SuperScanner.yycolumn;
                ActualParametersNode ap = null;
                		
		
		//'('
		if(nextsymbol==SuperScanner.lpar){
			insymbol(); 
		} else {
			error("( expected");
		}
		
		
		//')'
		if(nextsymbol==SuperScanner.rpar){
			insymbol(); 
		} else {
			//[ActualParameters]
			ap = actualParameters();
			
			//')'
			if(nextsymbol==SuperScanner.rpar){
				insymbol(); 
			} else {
				error(") expected");
			}
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//				
//		
//		insymbol();
		unindent();
                return new ProcedureCallNode("ThisIsTheNameOfAProcedureCallNode", pcL, pcC, ident, ap);
	}
	
	public static IfStatementNode ifStatement(){
		indent();
		System.out.println(spaces + "IfStatement: " + nextsymbol);
		int ifL = SuperScanner.yyline;
                int ifC = SuperScanner.yycolumn;
                List<ExpressionNode> expr = new ArrayList<>();
                List<StatementSequenceNode> stSeq = new ArrayList<>();
                
		//'IF'
		if(nextsymbol==SuperScanner.iff){
			insymbol(); 
		} else {
			error("IF expected");
		}
		
		//Expression
		expr.add(expression());
		
		//'THEN'
		if(nextsymbol==SuperScanner.thenn){
			insymbol(); 
		} else {
			error("THEN expected");
		}
		
		//StatementSequence
		stSeq.add(statementSequence());
		
		//{'ELSIF'
		while(nextsymbol == SuperScanner.elsif){
			insymbol();
			
			//Expression
			expr.add(expression());
			
			//'THEN'
			if(nextsymbol==SuperScanner.thenn){
				insymbol(); 
			} else {
				error("THEN expected");
			}
			
			//StatementSequence
			stSeq.add(statementSequence());
			
		//}
		}
		
		//'ELSE'
		if(nextsymbol==SuperScanner.elsee){
			insymbol(); 

			//StatementSequence
			stSeq.add(statementSequence());
		} 
		
		//'END'
		if(nextsymbol==SuperScanner.endsy){
			insymbol(); 
		} else {
			error("END expected");
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new IfStatementNode("ThisIsTheNameOfAIfStatementNode", ifL, ifC, expr, stSeq);
	}
	
	public static WhileStatementNode whileStatement(){
		indent();
		System.out.println(spaces + "WhileStatement: " + nextsymbol);
		
                int wL = SuperScanner.yyline;
                int wC = SuperScanner.yycolumn;
                
		//'WHILE'
		if(nextsymbol==SuperScanner.whilee){
			insymbol(); 
		} else {
			error("WHILE expected");
		}
		
		//EXPRESSION
		ExpressionNode expression = expression();
		
		//'DO'
		if(nextsymbol==SuperScanner.doo){
			insymbol(); 
		} else {
			error("DO expected");
		}
		
		//StatementSequence
		StatementSequenceNode statements = statementSequence();
		
		//'END'
		if(nextsymbol==SuperScanner.endsy){
			insymbol(); 
		} else {
			error("END expected");
		}
		
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		
//		insymbol();
		unindent();
                return new WhileStatementNode("ThisIsTheNameOfAWhileStatementNode", wL, wC, expression, statements);
	}
	
	public static RepeatStatementNode repeatStatement(){
		indent();
		System.out.println(spaces + "RepeatStatement: " + nextsymbol);
		
                int rL = SuperScanner.yyline;
                int rC = SuperScanner.yycolumn;
                
		//'REPEAT'
		if(nextsymbol==SuperScanner.repeatt){
			insymbol(); 
		} else {
			error("REPEAT expected");
		}
		
		//StatementSequence
		StatementSequenceNode statements= statementSequence();
		
		//'UNTIL'
		if(nextsymbol==SuperScanner.untill){
			insymbol(); 
		} else {
			error("UNTIL expected");
		}
		
		//Expression
		ExpressionNode expression = expression();
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new RepeatStatementNode("ThisIsTheNameOfARepeatStatementNode", rL, rC, statements, expression);
	}
	
	public static StatementNode statement(){
		indent();

		System.out.println(spaces + "Statement: " + nextsymbol);
                
                int sL = SuperScanner.yyline;
                int sC = SuperScanner.yycolumn;
                AbstractNode statement = null;
		//IfStatement
		if(nextsymbol==SuperScanner.iff){
			statement = ifStatement();
			
		//WhileStatement
		} else if(nextsymbol==SuperScanner.whilee) {
			statement = whileStatement();
			
		//RepeateStatement
		}else if(nextsymbol==SuperScanner.repeatt){
			statement = repeatStatement();
		
		//'PRINT'	
		}else if(nextsymbol==SuperScanner.printt){
			insymbol();
			
			//Expression
			statement = expression();
			
		}else if (nextsymbol==SuperScanner.ident){
			
			String ident = SuperScanner.strval;
                        int identL = SuperScanner.yyline;
                        int identC = SuperScanner.yycolumn;
                        IdentNode identNode = new IdentNode(ident, identL, identC);
			insymbol();
			if (nextsymbol==SuperScanner.lpar) {
				statement = procedureCall(identNode);
			} else {
				statement = assignment(identNode);
			}
			
		}
//		else {
//			error("Assignment | ProcedureCall | IfStatement | 'PRINT' | WhileStatement | RepeatStatement expected");
//		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new StatementNode("ThisIsTheNameOfAStatementNode", sL, sC, statement);
	}
	
	public static StatementSequenceNode statementSequence(){
		indent();
		System.out.println(spaces + "StatementSequence: " + nextsymbol);
		
                int sSL = SuperScanner.yyline;
                int sSC = SuperScanner.yycolumn;
                List<StatementNode> additionalStatements = new ArrayList<>();
                
		//Statement
		StatementNode statement = statement();
		
		//{';'
		while(nextsymbol == SuperScanner.semicolon){
			insymbol();
			
			//Statement
			additionalStatements.add(statement());
			
		//}
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new StatementSequenceNode("ThisIsTheNameOfAStatementSequence", sSL, sSC, statement, additionalStatements);
	}
	
	public static StringNode string(){
		indent();
		System.out.println(spaces + "String: " + nextsymbol);
		int stringL=SuperScanner.yyline;
                int stringC=SuperScanner.yycolumn;
                String string = null;
		if (nextsymbol==SuperScanner.string) {
			string = SuperScanner.strval;
                        insymbol();
		} else {
			error("String erwartet");
		}

		
		unindent();
                return new StringNode(string, stringL, stringC);
	}
	
	public static SelectorNode selector(){
		indent();
		System.out.println(spaces + "Selector: " + nextsymbol);
                
                int selectorL=SuperScanner.yyline;
                int selectorC=SuperScanner.yycolumn;
                List<AbstractNode> nodes = new ArrayList<>();
		
		//{'.' | '['
		if(nextsymbol == SuperScanner.dot || nextsymbol == SuperScanner.lsquarebraket){
			while(nextsymbol == SuperScanner.dot || nextsymbol == SuperScanner.lsquarebraket){
				if(nextsymbol==SuperScanner.dot){
					insymbol(); 
					
					//ident
					if(nextsymbol==SuperScanner.ident){
                                                nodes.add(new IdentNode(SuperScanner.strval, SuperScanner.yyline,SuperScanner.yycolumn));
						insymbol(); 
					} 
				} else if(nextsymbol == SuperScanner.lsquarebraket){
					insymbol();
					
					//Expression
					nodes.add(expression());
					
					//']'
					if(nextsymbol==SuperScanner.rsquarebraket){
						insymbol(); 
					} else {
						error("] expected");
					}		
				}	
				
			//}
			}
		} 
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		
//		insymbol();
		unindent();
                
                return new SelectorNode("ThisIsTheNameOfASelectorNode", selectorL, selectorC, nodes);
	}
	
	public static ReadNode read(){
		indent();
		System.out.println(spaces + "Read: " + nextsymbol);
                int readL = SuperScanner.yyline;
                int readC = SuperScanner.yycolumn;
                PromptNode prompt = null;
		//'READ'
		if(nextsymbol==SuperScanner.read){
			insymbol(); 
			
			//Prompt
			if (nextsymbol==SuperScanner.string) {
				prompt = prompt();
			}
		} else {
			error("READ expected");
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new ReadNode("ThisIsTheNameOfAReadNode", readL, readC, prompt);
	}
	
	public static PromptNode prompt(){
		indent();
		System.out.println(spaces + "Promt: " + nextsymbol);
		
                int promptL = SuperScanner.yyline;
                int promptC = SuperScanner.yycolumn;
              
		//String
		StringNode string = null;
		if(nextsymbol==SuperScanner.string){
			string=string(); 
		} else {
			error("String expected");
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new PromptNode("ThisIsANameOfAPromptNode", promptL, promptC, string);
	}

	
	public static ExpressionNode expression(){
		indent();
		System.out.println(spaces + "Expression: " + nextsymbol);
		int expL = SuperScanner.yyline;
                int expC = SuperScanner.yycolumn;
                String operator = null;
                SimpleExpressionNode secondSE = null;
                
		//SimpleExpression
		SimpleExpressionNode firstSE = simpleExpr();
		
                
		if (nextsymbol == SuperScanner.equal) {
                    insymbol();
                    operator="=";
                    insymbol();
                    //SimpleExpression
                    secondSE=simpleExpr();
                } else if (nextsymbol == SuperScanner.sharp){
                    insymbol();
                    operator="#";
                    insymbol();
                    //SimpleExpression
                    secondSE=simpleExpr();
                } else if (nextsymbol == SuperScanner.less) {
                    insymbol();
                    operator="<";
                    insymbol();
                    //SimpleExpression
                    secondSE=simpleExpr();
                } else if (nextsymbol == SuperScanner.less_equal) {
                    insymbol();
                    operator="<=";
                    insymbol();
                    //SimpleExpression
                    secondSE=simpleExpr();
                } else if (nextsymbol == SuperScanner.greater){
                    insymbol();
                    operator=">";
                    insymbol();
                    //SimpleExpression
                    secondSE=simpleExpr();
                } else if (nextsymbol == SuperScanner.greater_equal){
                    insymbol();
                    operator=">=";
                    insymbol();
                    //SimpleExpression
                    secondSE=simpleExpr();
                }
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new ExpressionNode("ThisIsTheNameOfAnExpressionNode", expL, expC, firstSE, operator, secondSE);
	}
	
	public static ConstIdentNode constIdent(){
		indent();
		System.out.println(spaces + "ConstIdent: " + nextsymbol);
                IdentNode ident = null;
		int constIdentL = SuperScanner.yyline;
		int constIdentC = SuperScanner.yycolumn;
		//ident
		if (nextsymbol == SuperScanner.ident){
                    ident = new IdentNode(SuperScanner.strval, constIdentL, constIdentC);
                    insymbol();
		} else {
			error("ident expected");
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
                return new ConstIdentNode("ThisIsANameOfAConstIdentNode", constIdentL, constIdentC, ident);
	}
	
	
	


}
