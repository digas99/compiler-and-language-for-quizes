import java.io.PrintWriter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.ST;
import java.io.*;

public class QuizMain {
   public static void main(String[] args) throws Exception {
      // check for file name
      if (args.length != 2) {
         System.out.println("Incorrect number of arguments!");
         System.out.println("java CalculatorMain <quizFileName> <outputFileName>");
         System.out.println("You need to have the files in the propor directory");
         System.exit(1);
      }

      String quizFile = "../quiz/" + args[0];

      String output = args[1];
      PrintWriter writer = new PrintWriter(output + ".java");

      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromFileName(quizFile);
      // create a lexer that feeds off of input CharStream:
      QuizLexer lexer = new QuizLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      QuizParser parser = new QuizParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at program rule:
      ParseTree tree = parser.program();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         QuizCompiler compiler = new QuizCompiler(output);
         ST res = compiler.visit(tree);
         writer.write(res.render());
         writer.close();
      }
   }
}
