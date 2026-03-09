import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        List<String> testsNames = List.of(
                "assignment", "assignment", "assignment",
                "initialization", "initialization", "initialization",
                "if", "if",
                "for", "for",
                "while", "while",
                "complex", "complex",
                "function", "class");

        List<String> testsInput = List.of(
                "\n x   =  \t  'f' ; ",
                "x = \n 113412   *  \t  0 +  - (  54   -  45 ) / 89 ; ",
                "\n x   =   (( \n a12 >= \t Njndjs3  ) == \n (jjj < o )\t) != (o > o) ; ",
                "  char \n x0   =  \t  'f' ; ",
                "  int x1 = \n 113412   *  \t  0 +   (  54  - \n  45 ) / 89  ;    ",
                "\n boolean x2   =    (( a12 \n >= Njndjs3 \t  ) ==  (jjj < o )\t) != (o > o) ; ",
                "   \n if \t ( \n true ) \n { \t }",
                "   \n if \t ( \n (( a12 >= Njndjs3  ) == (jjj < o )\t) != (o > o) ) \n { \t }",
                " for ( int \n x = 1 * 30  \n; x < a\t; x = x \t+ 1 ) { \n } ",
                " for ( \n; ; \t) {  } ",
                "while( \n true ) { \t }\n",
                "while\n(  \n (( a12 >= Njndjs3 \t  ) == \n (jjj < o )\t) != (o > o) \t ) { \t }\n",
                "\n x   =  \t  'f' ;   int x1 = \n 113412   *  \t  0 +   (  54  -  45 ) / 89   ;  \n if \t ( \n (( a12 >= Njndjs3  ) == (jjj < o )\t) != (o > o) ) \n { \t }  for ( int x = 1 * 30  \n; x < a\t; x = x + 1 ) {  } while\n(  \n (( a12 >= Njndjs3  ) == (jjj < o )\t) != (o > o)  ) { \t }\n",
                "    int x1 = \n -113412   *  \t  0 +   -(  54  -  45 ) / 89 ;    \n if \t ( \n (( a12 >= Njndjs3  ) == !(jjj < o )\t) != (o > o) ) \n { \n x   =  \t  'f' ; for ( int x = 1 * 30  \n; \t; x++ ) {  } \t }   while\n(  \n (!( a12 >= Njndjs3  ) == (jjj < o )\t) != (o > o)  ) { \t }\n",
                "int  \t   testFunc1\n(     char x \n, boolean      y, \t int z ) { \n \n x   =  \t  'f' ;  for ( \n; ; \t) { x++\n; }  return \t x; } \n char  \t   testFunc2\n(   \n ) { \n \n char x   =  \t  'f' ;  for (int i = 0 \n; i < x ; i++ \t) { x++\n; }  return \t x; }",
                "class \n testClass  \t { int \n    field1    =  \t   1 ; char \n field2 = \n 's' ; \t boolean field3 \n = true ; \n   int  \t   testFunc1\n(     char x \n, boolean      y, \t int z ) { \n \n x   =  \t  'f' ;  for ( \n; ; \t) { x++\n; }  return \t x; } \n char  \t   testFunc2\n(   \n ) { \n \n char x   =  \t  'f' ;  for (int i = 0 \n; i < x ; i++ \t) { x++\n; }  return \t x; } \n }");

        System.out.println();
        for (int i = 0; i < testsNames.size(); i++) {
            System.out.printf("Test #%d (%s test): %n", i + 1, testsNames.get(i));
            System.out.println();

            System.out.println("Input: ");
            System.out.println(testsInput.get(i));
            System.out.println();

            FormattingLexer lexer = new FormattingLexer(CharStreams.fromString(testsInput.get(i)));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            FormattingParser parser = new FormattingParser(tokens);

            parser.start();

            System.out.println("----------------------------------");

        }

    }
}