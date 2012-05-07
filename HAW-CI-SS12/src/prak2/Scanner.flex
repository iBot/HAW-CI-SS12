package prak2;
%%
%public
%standalone
%class SuperScanner
%line
%column
%{
	
	public static final int whitespace 	= 256;
	public static final int ident 		= 257;
	public static final int intconst	= 258;
	public static final int lpar		= 259;
	public static final int rpar		= 260;
	public static final int addop		= 261;
	public static final int subop		= 262;
	public static final int multop		= 263;
	public static final int divop		= 264;
	public static final int beginsy		= 268;
	public static final int endsy		= 269;
	public static final int decl		= 270;
	public static final int equal		= 271;
	public static final int read		= 272;
	public static final int whilee		= 273;
	public static final int untill		= 274;
	public static final int repeatt		= 275;
	public static final int array		= 276;
	public static final int record		= 277;
	public static final int constt		= 278;
	public static final int type		= 279;
	public static final int of			= 280;
	public static final int doo			= 281;
	public static final int iff			= 282;
	public static final int thenn		= 283;
	public static final int elsif		= 284;
	public static final int elsee		= 285;
	public static final int printt		= 286;
	public static final int var			= 287;
	public static final int procedure	= 288;
	public static final int module		= 289;
	public static final int less		= 290;
	public static final int greater		= 291;
	public static final int sharp		= 292;
	public static final int double_dot	= 293;
	public static final int lsquarebraket	= 294;
	public static final int rsquarebraket	= 295;
	public static final int semicolon	= 296;
	public static final int dot			= 297;
	public static final int comma		= 298;
	public static final int less_equal		= 299;
	public static final int greater_equal = 300;
	public static final int string		= 301;
	
	
	public static final int errorsy		= 255;
	public static int intval; 
	public static String strval;
	
	public static int line = 1;
%}

BLANK=[ \t\r]
DIGIT=[0-9]
ALPHA=[a-zA-Z]
INTEGER={DIGIT}+

%%
{BLANK}*	{return whitespace;}
{INTEGER} 	{intval = Integer.parseInt(yytext());
             return intconst;}

[Bb][Ee][Gg][Ii][Nn]					{return beginsy;}
[Ee][Nn][Dd]							{return endsy;}
[Rr][Ee][Aa][Dd]						{return read;}
[Ww][Hh][Ii][Ll][Ee]					{return whilee;}
[Rr][Ee][Pp][Ee][Aa][Tt]				{return repeatt;}
[Uu][Nn][Tt][Ii][Ll]					{return untill;}
[Aa][Rr][Rr][Aa][Yy]					{return array;}
[Rr][Ee][Cc][Oo][Rr][Dd]				{return record;}
[Cc][Oo][Nn][Ss][Tt]					{return constt;}
[Tt][Yy][Pp][Ee]						{return type;}
[Oo][Ff]								{return of;}
[Dd][Oo]								{return doo;}
[Ii][Ff]								{return iff;}
[Tt][Hh][Ee][Nn]						{return thenn;}
[Ee][Ll][Ss][Ii][Ff] 					{return elsif;}
[Ee][Ll][Ss][Ee]						{return elsee;}
[Pp][Rr][Ii][Nn][Tt]					{return printt;}
[Vv][Aa][Rr]							{return var;}
[Pp][Rr][Oo][Cc][Ee][Dd][Uu][Rr][Ee]	{return procedure;}
[Mm][Oo][Dd][Uu][Ll][Ee]				{return module;}
\"(![\"\n])*\"							{strval = new String(yytext());
										 return string;}

{ALPHA}({ALPHA}|{DIGIT}|{BLANK})*	
				{strval = new String(yytext());
				 return ident;}

\(				{return lpar;}
\)				{return rpar;}
\+				{return addop;}
\*				{return multop;}
\-				{return subop;}
\/				{return divop;}
[:][=]			{return decl;}
\=				{return equal;}
[<][=]			{return less_equal;}
[>][=]			{return greater_equal;}
\<				{return less;}
\>				{return greater;}
\#				{return sharp;}
\:				{return double_dot;}
\[				{return lsquarebraket;}
\]				{return rsquarebraket;}
\;				{return semicolon;}
\.				{return dot;}
\,				{return comma;}
\n				{line=line+1;}
.				{return errorsy;}
