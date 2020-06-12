import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class QuestionsMain {
   public static void main(String fileName) throws Exception {
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromFileName(fileName);
      // create a lexer that feeds off of input CharStream:
      QuestionsLexer lexer = new QuestionsLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      QuestionsParser parser = new QuestionsParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at prog rule:
      ParseTree tree = parser.prog();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         QuestionInterpreter visitor0 = new QuestionInterpreter();
         visitor0.visit(tree);
      }
   }
}
