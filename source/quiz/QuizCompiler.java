import java.util.Iterator;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.*;

public class QuizCompiler extends QuizBaseVisitor<ST> {
   private STGroup templates = new STGroupFile("string_templates.stg");
   private String tmpId = "";
   private int numVars = 0;
   private String newVar() {
      numVars++;
      return "var"+numVars;
   }
   private String file;

   public QuizCompiler(String file) {
      this.file = file;
   }

   @Override public ST visitProgram(QuizParser.ProgramContext ctx) {
      ST module = templates.getInstanceOf("module");
      module.add("name", file);
      for (QuizParser.BlockContext block : ctx.block()) {
         module.add("stat", visit(block).render());
      }
      return module;
   }

   @Override public ST visitBlock(QuizParser.BlockContext ctx) {
      if (ctx.function(0) != null) return visit(ctx.function(0));
      if (ctx.main() != null) return visit(ctx.main());
      if (ctx.function(1) != null) return visit(ctx.function(1));
      return null;
   }

   @Override public ST visitMain(QuizParser.MainContext ctx) {
      ST main = templates.getInstanceOf("main");
      for (QuizParser.ContentContext content : ctx.content()) {
         main.add("stat", visit(content).render());
      }
      return main;
   }

   @Override public ST visitFunction(QuizParser.FunctionContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitListQuestion(QuizParser.ListQuestionContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitListNums(QuizParser.ListNumsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitListText(QuizParser.ListTextContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitListBoolean(QuizParser.ListBooleanContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarText(QuizParser.VarTextContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarTextRead(QuizParser.VarTextReadContext ctx) {
      return visitChildren(ctx);
   }

   // IN PROGRESS
   @Override public ST visitVarNumber(QuizParser.VarNumberContext ctx) {
      tmpId = ctx.ID(0).getText();
      return visit(ctx.expr());
   }

   @Override public ST visitVarBoolean(QuizParser.VarBooleanContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarQuestion(QuizParser.VarQuestionContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarListAdd(QuizParser.VarListAddContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarListRemove(QuizParser.VarListRemoveContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitAdd(QuizParser.AddContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionTitle(QuizParser.QuestionTitleContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionAnsRight(QuizParser.QuestionAnsRightContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionAnsWrong(QuizParser.QuestionAnsWrongContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionDifficulty(QuizParser.QuestionDifficultyContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionType(QuizParser.QuestionTypeContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionTries(QuizParser.QuestionTriesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionTime(QuizParser.QuestionTimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionPoints(QuizParser.QuestionPointsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetch(QuizParser.QuestionFetchContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchTitle(QuizParser.QuestionFetchTitleContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchAnsRight(QuizParser.QuestionFetchAnsRightContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchAnsWrong(QuizParser.QuestionFetchAnsWrongContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchDiff(QuizParser.QuestionFetchDiffContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchType(QuizParser.QuestionFetchTypeContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchTries(QuizParser.QuestionFetchTriesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchTime(QuizParser.QuestionFetchTimeContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitQuestionFetchPoints(QuizParser.QuestionFetchPointsContext ctx) {
      return visitChildren(ctx);
   }

   // COMPLETED
   @Override public ST visitWriteConsole(QuizParser.WriteConsoleContext ctx) {
      ST print = templates.getInstanceOf("print");
      if (ctx.TEXT() != null) print.add("text", ctx.TEXT().getText());
      else if (ctx.expr() != null) {
         print.add("stat", visit(ctx.expr()).render());
         print.add("text", ctx.expr().varx);
      }
      else {
         print.add("text", "\"ERROR: Not a valid value to print!\"");
      }
      return print;
   }

   @Override public ST visitWriteText(QuizParser.WriteTextContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitForLoop(QuizParser.ForLoopContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitDoaslong(QuizParser.DoaslongContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitAslong(QuizParser.AslongContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitIfCond(QuizParser.IfCondContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitElsif(QuizParser.ElsifContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitFinalCond(QuizParser.FinalCondContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitCallfunction(QuizParser.CallfunctionContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitConditional(QuizParser.ConditionalContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipPlus(QuizParser.VarManipPlusContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipMinus(QuizParser.VarManipMinusContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipPrePlus(QuizParser.VarManipPrePlusContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipPreMinus(QuizParser.VarManipPreMinusContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipPlusEquals(QuizParser.VarManipPlusEqualsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipMinusEquals(QuizParser.VarManipMinusEqualsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipTimesEquals(QuizParser.VarManipTimesEqualsContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarManipDivideEquals(QuizParser.VarManipDivideEqualsContext ctx) {
      return visitChildren(ctx);
   }

   // COMPLETED
   @Override public ST visitExprAddSub(QuizParser.ExprAddSubContext ctx) {
      ST op = templates.getInstanceOf("binary_operation");
      ctx.varx = newVar();
      op.add("stat", visit(ctx.expr(0)).render());
      op.add("stat", visit(ctx.expr(1)).render());
      op.add("id", tmpId);
      op.add("type", "int");
      op.add("var", ctx.varx);
      op.add("e1", ctx.expr(0).varx);
      op.add("op", ctx.op.getText());
      op.add("e2", ctx.expr(1).varx);
      return op;
   }

   @Override public ST visitExprParens(QuizParser.ExprParensContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprUnary(QuizParser.ExprUnaryContext ctx) {
      return visitChildren(ctx);
   }

   // COMPLETED
   @Override public ST visitExprNumber(QuizParser.ExprNumberContext ctx) {
      ST res = templates.getInstanceOf("atrib");
      ctx.varx = newVar();
      res.add("id", ctx.varx);
      res.add("type", "int");
      res.add("var", ctx.varx);
      res.add("value", ctx.NUMBER().getText());
      return res;
   }

   // COMPLETED
   @Override public ST visitExprId(QuizParser.ExprIdContext ctx) {
      ST fetch = templates.getInstanceOf("get_from_map");
      ctx.varx = newVar();
      fetch.add("id", ctx.ID().getText());
      fetch.add("var", ctx.varx);
      return fetch;
   }

   // COMPLETED
   @Override public ST visitExprMultDivMod(QuizParser.ExprMultDivModContext ctx) {
      ST op = templates.getInstanceOf("binary_operation");
      ctx.varx = newVar();
      op.add("stat", visit(ctx.expr(0)).render());
      op.add("stat", visit(ctx.expr(1)).render());
      op.add("id", tmpId);
      op.add("type", "int");
      op.add("var", ctx.varx);
      op.add("e1", ctx.expr(0).varx);
      op.add("op", ctx.op.getText());
      op.add("e2", ctx.expr(1).varx);
      return op;
   }

   // COMPLETED
   @Override public ST visitContent(QuizParser.ContentContext ctx) {
      if (ctx.list() != null) return visit(ctx.list());
      if (ctx.var() != null) return visit(ctx.var());
      if (ctx.write() != null) return visit(ctx.write());
      if (ctx.question() != null) return visit(ctx.question());
      if (ctx.forLoop() != null) return visit(ctx.forLoop());
      if (ctx.ifCond() != null) return visit(ctx.ifCond());
      if (ctx.doaslong() != null) return visit(ctx.doaslong());
      if (ctx.aslong() != null) return visit(ctx.aslong());
      if (ctx.callfunction() != null) return visit(ctx.callfunction());
      if (ctx.varmanipulation() != null) return visit(ctx.varmanipulation());
      return null;
   }
}
