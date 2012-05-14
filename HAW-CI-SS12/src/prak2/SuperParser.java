package prak2;

import prak3.node.AbstractNode;

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

	static void factor() {

		indent();
		System.out.println(spaces + "factor: " + nextsymbol);
		if (nextsymbol == SuperScanner.lpar) {
			insymbol();
			simpleExpr();
			if (nextsymbol == SuperScanner.rpar)
				insymbol();
			else
				error(" ) expected");
		} else if (nextsymbol == SuperScanner.intconst) {
			outInt(SuperScanner.intval);
			insymbol();
		} else if (nextsymbol == SuperScanner.ident) {
			outStr(SuperScanner.strval);
                        insymbol();
                        selector();
		} else if (nextsymbol == SuperScanner.string){
                        
                    string();
                }
		unindent();
	}

	static void term() {
		int lsy;

		indent();
		System.out.println(spaces + "term: " + nextsymbol);

		factor();
		if ((nextsymbol == SuperScanner.multop)
				|| (nextsymbol == SuperScanner.divop)) {
			lsy = nextsymbol;
			insymbol();
			term();
			if (lsy == SuperScanner.multop)
				outOp("* ");
			else
				outOp("/ ");
		}
		unindent();
	}

	static void simpleExpr() {
		int lsy;

		indent();
		System.out.println(spaces + "simpleExpr: " + nextsymbol);

		term();
		if ((nextsymbol == SuperScanner.addop)
				|| (nextsymbol == SuperScanner.subop)) {
			lsy = nextsymbol;
			insymbol();
			simpleExpr();
			if (lsy == SuperScanner.addop)
				outOp("+ ");
			else
				outOp("- ");
		}
		unindent();
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
	
	public static void indexExpression(){
		indent();
		System.out.println(spaces + "IndexExpresion: " + nextsymbol);
		if(nextsymbol==SuperScanner.ident){
			constIdent();
		} else if(nextsymbol==SuperScanner.intconst){
			insymbol();
		} else {
			error("ConstIdent or Integer expected");
		}
		unindent();
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
	
	public static void assignment(String ident){
		indent();
		System.out.println(spaces + "Assignment: " + nextsymbol);
		
		//ident
		//TODO: ident in Node einf�gen
		
		//Selector
		selector();
		
		//:=
		if(nextsymbol==SuperScanner.decl){
			insymbol(); 
		} else {
			error("Declaration-Symbol ':=' expected");
		}
		
		//Expression
		expression();
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		
//		insymbol();
		unindent();
	}
	
	public static void actualParameters(){
		indent();
		System.out.println(spaces + "ActualParameters: " + nextsymbol);
		
		//Expression
		expression();
		
		//{','
		while(nextsymbol == SuperScanner.comma){
			insymbol();
			
			//Expression
			expression();
			
		//}
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		
//		insymbol();
		unindent();
	}
	
	public static void procedureCall(String ident){
		indent();
		System.out.println(spaces + "ProcedureCall: " + nextsymbol);
		
		//ident
		//TODO: Identifikator in Node einf�gen
		
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
			actualParameters();
			
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
	}
	
	public static void ifStatement(){
		indent();
		System.out.println(spaces + "IfStatement: " + nextsymbol);
		
		//'IF'
		if(nextsymbol==SuperScanner.iff){
			insymbol(); 
		} else {
			error("IF expected");
		}
		
		//Expression
		expression();
		
		//'THEN'
		if(nextsymbol==SuperScanner.thenn){
			insymbol(); 
		} else {
			error("THEN expected");
		}
		
		//StatementSequence
		statementSequence();
		
		//{'ELSIF'
		while(nextsymbol == SuperScanner.elsif){
			insymbol();
			
			//Expression
			expression();
			
			//'THEN'
			if(nextsymbol==SuperScanner.thenn){
				insymbol(); 
			} else {
				error("THEN expected");
			}
			
			//StatementSequence
			statementSequence();
			
		//}
		}
		
		//'ELSE'
		if(nextsymbol==SuperScanner.elsee){
			insymbol(); 

			//StatementSequence
			statementSequence();			
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
	}
	
	public static void whileStatement(){
		indent();
		System.out.println(spaces + "WhileStatement: " + nextsymbol);
		
		//'WHILE'
		if(nextsymbol==SuperScanner.whilee){
			insymbol(); 
		} else {
			error("WHILE expected");
		}
		
		//EXPRESSION
		expression();
		
		//'DO'
		if(nextsymbol==SuperScanner.doo){
			insymbol(); 
		} else {
			error("DO expected");
		}
		
		//StatementSequence
		statementSequence();
		
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
	}
	
	public static void repeatStatement(){
		indent();
		System.out.println(spaces + "RepeatStatement: " + nextsymbol);
		
		//'REPEAT'
		if(nextsymbol==SuperScanner.repeatt){
			insymbol(); 
		} else {
			error("REPEAT expected");
		}
		
		//StatementSequence
		statementSequence();
		
		//'UNTIL'
		if(nextsymbol==SuperScanner.untill){
			insymbol(); 
		} else {
			error("UNTIL expected");
		}
		
		//Expression
		expression();
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
	}
	
	public static void statement(){
		indent();
		System.out.println(spaces + "Statement: " + nextsymbol);
		
		//IfStatement
		if(nextsymbol==SuperScanner.iff){
			ifStatement();
			
		//WhileStatement
		} else if(nextsymbol==SuperScanner.whilee) {
			whileStatement();
			
		//RepeateStatement
		}else if(nextsymbol==SuperScanner.repeatt){
			repeatStatement();
		
		//'PRINT'	
		}else if(nextsymbol==SuperScanner.printt){
			insymbol();
			
			//Expression
			expression();
			
		}else if (nextsymbol==SuperScanner.ident){
			
			String ident = SuperScanner.strval;
			insymbol();
			if (nextsymbol==SuperScanner.lpar) {
				procedureCall(ident);
			} else {
				assignment(ident);
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
	}
	
	public static void statementSequence(){
		indent();
		System.out.println(spaces + "StatementSequence: " + nextsymbol);
		
		//Statement
		statement();
		
		//{';'
		while(nextsymbol == SuperScanner.semicolon){
			insymbol();
			
			//Statement
			statement();
			
		//}
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
	}
	
	public static void string(){
		indent();
		System.out.println(spaces + "String: " + nextsymbol);
		
		if (nextsymbol==SuperScanner.string) {
			insymbol();
		} else {
			error("String erwartet");
		}

		
		unindent();
	}
	
	public static void selector(){
		indent();
		System.out.println(spaces + "Selector: " + nextsymbol);
		
		//{'.' | '['
		if(nextsymbol == SuperScanner.dot || nextsymbol == SuperScanner.lsquarebraket){
			while(nextsymbol == SuperScanner.dot || nextsymbol == SuperScanner.lsquarebraket){
				if(nextsymbol==SuperScanner.dot){
					insymbol(); 
					
					//ident
					if(nextsymbol==SuperScanner.ident){
						insymbol(); 
					} 
				} else if(nextsymbol == SuperScanner.lsquarebraket){
					insymbol();
					
					//Expression
					expression();
					
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
	}
	
	public static void read(){
		indent();
		System.out.println(spaces + "Read: " + nextsymbol);
		
		//'READ'
		if(nextsymbol==SuperScanner.read){
			insymbol(); 
			
			//Prompt
			//TODO: Wie k�nnen wir auf String pr�fen? Ident ist nicht wirklich = String, oder?
			if (nextsymbol==SuperScanner.string) {
				prompt();
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
	}
	
	public static void prompt(){
		indent();
		System.out.println(spaces + "Promt: " + nextsymbol);
		
		//String
		//TODO: Wie k�nnen wir auf String pr�fen? Ident ist nicht wirklich = String, oder?
		if(nextsymbol==SuperScanner.string){
			string(); 
		} else {
			error("String expected");
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
	}

	
	public static void expression(){
		indent();
		System.out.println(spaces + "Expression: " + nextsymbol);
		
		//SimpleExpression
		simpleExpr();
		
		if (nextsymbol == SuperScanner.equal ||
			nextsymbol == SuperScanner.sharp ||
			nextsymbol == SuperScanner.less ||
			nextsymbol == SuperScanner.less_equal ||
			nextsymbol == SuperScanner.greater ||
			nextsymbol == SuperScanner.greater_equal) {
			
			insymbol();
			
			//SimpleExpression
			simpleExpr();
		}
		
		//.
//		if(!(nextsymbol==SuperScanner.dot)){
//			error("Dot expected");
//		}
//		insymbol();
		unindent();
	}
	
	public static void constIdent(){
		indent();
		System.out.println(spaces + "ConstIdent: " + nextsymbol);
		
		//ident
		if (nextsymbol == SuperScanner.ident){
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
	}
	
	
	


}
