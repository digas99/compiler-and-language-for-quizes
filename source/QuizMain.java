import java.io.PrintWriter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.ST;
import java.io.*;

public class QuizMain {
   public static void main(String[] args) throws Exception {
      // check for file name
      if (args.length != 3) {
         System.out.println("Incorrect number of arguments!");
         System.out.println("java CalculatorMain <questionsFileName> <quizFileName> <outputFileName>");
         System.out.println("You need to have the files in the propor directory");
         System.out.println("You need to write the files with extension");
         System.exit(1);
      }

      String quizFile = "../quiz/" + args[1];

      String output = args[2];
      PrintWriter writer = new PrintWriter(output + ".java");

      String questionFile = "../questions/" + args[0];
      QuestionsMain.main(questionFile);

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
