import java.io.PrintWriter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.ST;

public class QuizMain {
   public static void main(String[] args) throws Exception {
      // check for file name
      if (args.length < 1) {
         System.out.println("RUN: java CalculatorMain filename");
         System.exit(1);
      }

      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
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
         String fileName = args[0];
         QuizCompiler compiler = new QuizCompiler(fileName);
         PrintWriter writer = new PrintWriter("outputs/"+fileName+".java");
         ST res = compiler.visit(tree);
         writer.write(res.render());
         writer.close();
      }
   }
}
