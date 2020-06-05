import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.*;

public class QuizCompiler extends QuizBaseVisitor<ST> {
   private STGroup templates = new STGroupFile("string_templates.stg");
   private String tmpId = "";
   private int numVars = 0;
   private boolean hasScanner = false;
   private HashMap<String, String> types = new HashMap<>();
   private HashMap<String, String> idToTmpVar = new HashMap<>();
   private List<String> funcParamsNames = new ArrayList<>();
   private HashMap<String, String> varTypes = new HashMap<>();
   private String newVar() {
      numVars++;
      return "var"+numVars;
   }
   private String file;

   public QuizCompiler(String file) {
      this.file = file;
      types.put("number", "double");
      types.put("text", "String");
      types.put("boolean", "boolean");
      types.put("question", "Question");
   }

   // COMPLETED
   @Override public ST visitProgram(QuizParser.ProgramContext ctx) {
      ST module = templates.getInstanceOf("module");
      module.add("name", file);
      for (QuizParser.BlockContext block : ctx.block()) {
         module.add("stat", visit(block).render());
      }
      if (numVars > 0) module.add("hasVars", numVars);

      if (hasScanner) module.add("hasScanner", "");
      
      return module;
   }

   // COMPLETED
   @Override public ST visitBlock(QuizParser.BlockContext ctx) {
      if (ctx.function() != null) return visit(ctx.function());
      if (ctx.main() != null) return visit(ctx.main());
      return null;
   }

   // COMPLETED
   @Override public ST visitMain(QuizParser.MainContext ctx) {
      ST main = templates.getInstanceOf("main");
      for (QuizParser.ContentContext content : ctx.content()) {
         main.add("stat", visit(content).render());
      }
      return main;
   }

   // IN PROGRESS
   @Override public ST visitFunction(QuizParser.FunctionContext ctx) {
      ST func = templates.getInstanceOf("function");
      func.add("name", ctx.name.getText());
      List<TerminalNode> paramsTypes = ctx.getTokens(7);

         if (paramsTypes.size() > 0) {
         // take all TerminalNodes but the last one (which is the func name)
         List<TerminalNode> paramsNames = new ArrayList<>();
         for (int i = 0; i < ctx.ID().size()-1; i++) {
            funcParamsNames.add(ctx.ID(i).getText());
            paramsNames.add(ctx.ID(i));
         }

         if (paramsTypes.size() > 1) {
            for (int j = 0; j < paramsTypes.size()-1; j++) {
               func.add("param", types.get(paramsTypes.get(j).getText()) + " " + paramsNames.get(j) + ", ");
            }
         }
         func.add("param", types.get(paramsTypes.get(paramsTypes.size()-1).getText()) + " " + paramsNames.get(paramsNames.size()-1));
      }

      for (QuizParser.ContentContext content : ctx.content()) {
         func.add("stat", visit(content).render());
      }

      String expr = "";
      if (ctx.expr() != null) {
         expr = visit(ctx.expr()).render();
         func.add("building_return", expr);
         func.add("ret", ctx.expr().varx);
         String[] words = expr.split(" ");
         boolean isInt = false;
         for (String word : words) {
            if (word.equals("double")) {
               func.add("return_type", "double");
               isInt = true;
               break;
            }
         }
         if (!isInt) func.add("return_type", "String");
      }
      else {
         func.add("return_type", "void");
      }

      return func;
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

   // COMPLETED
   @Override public ST visitVarTextRead(QuizParser.VarTextReadContext ctx) {
      ST read = templates.getInstanceOf("read");
      ctx.varx = newVar();
      hasScanner = true;
      if (ctx.TEXT() == null) read.add("file", "System.in");
      else read.add("file", ctx.TEXT().getText());
      read.add("type", "String");
      read.add("var", ctx.varx);
      read.add("id", ctx.ID().getText());
      varTypes.put(ctx.ID().getText(), "String");
      return read;
   }

   // IN PROGRESS
   @Override public ST visitVarNumber(QuizParser.VarNumberContext ctx) {
      tmpId = ctx.ID(0).getText();
      ST visit = visit(ctx.expr());
      idToTmpVar.put(tmpId, ctx.expr().varx);
      return visit;
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

   // COMPLETED
   @Override public ST visitCallfunction(QuizParser.CallfunctionContext ctx) {
      ST call = templates.getInstanceOf("callfunc");
      String paramsBuild = "";
      boolean hasAtLeastOneParam = false;
      int i;
      call.add("name", ctx.ID(0).getText());
      // if there is another ID other than the name of the function
      if (ctx.ID().size() > 1) {
         hasAtLeastOneParam = true;
         for (i = 1; i < ctx.ID().size(); i++) {
            String id = ctx.ID(i).getText();
            String getTemp = idToTmpVar.containsKey(id) ? idToTmpVar.get(id) : id;
            paramsBuild += getTemp + ", ";
         }
      }
      if (ctx.TEXT().size() > 0) {
         hasAtLeastOneParam = true;
         for (i = 0; i < ctx.TEXT().size(); i++) {
            paramsBuild += "\"" + ctx.TEXT(i).getText() + "\", ";
         }
      }
      if (ctx.NUMBER().size() > 0) {
         hasAtLeastOneParam = true;
         for (i = 0; i < ctx.NUMBER().size(); i++) {
            paramsBuild += ctx.NUMBER(i).getText() + ", ";
         }
      }
      
      if (hasAtLeastOneParam) {
         // remove last comma and space   
         call.add("param", paramsBuild.substring(0, paramsBuild.length()-2));
      }

      return call;
   }

   @Override public ST visitConditional(QuizParser.ConditionalContext ctx) {
      return visitChildren(ctx);
   }

   // COMPLETED
   @Override public ST visitVarManipPlus(QuizParser.VarManipPlusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_front");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "++");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipMinus(QuizParser.VarManipMinusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_front");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "--");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipPrePlus(QuizParser.VarManipPrePlusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_back");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "++");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipPreMinus(QuizParser.VarManipPreMinusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_back");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "--");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipPlusEquals(QuizParser.VarManipPlusEqualsContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_number");
      if (ctx.ID(1) != null) {
         ctx.varx = newVar();
         manip.add("newvar", ctx.varx);
         manip.add("mod_var", ctx.ID(1).getText());
      }
      if (ctx.NUMBER() != null) {
         manip.add("number", ctx.NUMBER().getText());
      }
      manip.add("var", idToTmpVar.get(ctx.ID(0).getText()));
      manip.add("action", "+=");
      manip.add("id", ctx.ID(0).getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipMinusEquals(QuizParser.VarManipMinusEqualsContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_number");
      if (ctx.ID(1) != null) {
         ctx.varx = newVar();
         manip.add("newvar", ctx.varx);
         manip.add("mod_var", ctx.ID(1).getText());
      }
      if (ctx.NUMBER() != null) {
         manip.add("number", ctx.NUMBER().getText());
      }
      manip.add("var", idToTmpVar.get(ctx.ID(0).getText()));
      manip.add("action", "-=");
      manip.add("id", ctx.ID(0).getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipTimesEquals(QuizParser.VarManipTimesEqualsContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_number");
      if (ctx.ID(1) != null) {
         ctx.varx = newVar();
         manip.add("newvar", ctx.varx);
         manip.add("mod_var", ctx.ID(1).getText());
      }
      if (ctx.NUMBER() != null) {
         manip.add("number", ctx.NUMBER().getText());
      }
      manip.add("var", idToTmpVar.get(ctx.ID(0).getText()));
      manip.add("action", "*=");
      manip.add("id", ctx.ID(0).getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipDivideEquals(QuizParser.VarManipDivideEqualsContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_number");
      if (ctx.ID(1) != null) {
         ctx.varx = newVar();
         manip.add("newvar", ctx.varx);
         manip.add("mod_var", ctx.ID(1).getText());
      }
      if (ctx.NUMBER() != null) {
         manip.add("number", ctx.NUMBER().getText());
      }
      manip.add("var", idToTmpVar.get(ctx.ID(0).getText()));
      manip.add("action", "/=");
      manip.add("id", ctx.ID(0).getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitExprAddSub(QuizParser.ExprAddSubContext ctx) {
      ST op = templates.getInstanceOf("binary_operation");
      ctx.varx = newVar();
      op.add("stat", visit(ctx.expr(0)).render());
      op.add("stat", visit(ctx.expr(1)).render());
      op.add("id", tmpId);
      op.add("type", "double");
      op.add("var", ctx.varx);
      op.add("e1", ctx.expr(0).varx);
      op.add("op", ctx.op.getText());
      op.add("e2", ctx.expr(1).varx);
      varTypes.put(tmpId, "double");
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
      ST res = templates.getInstanceOf("atrib_double");
      ctx.varx = newVar();
      res.add("id", tmpId);
      res.add("type", "double");
      res.add("var", ctx.varx);
      res.add("value", ctx.NUMBER().getText());
      varTypes.put(tmpId, "double");
      return res;
   }

   // COMPLETED
   @Override public ST visitExprId(QuizParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();
      ST res;
      ctx.varx = newVar();
      if (!funcParamsNames.contains(id)) {
         res = templates.getInstanceOf("get_from_map");
         res.add("id", id);
         res.add("var", ctx.varx);
         String type = varTypes.containsKey(id) ? varTypes.get(id) : null;
         switch(type) {
            case "String":
               res.add("string", "");
               break;
            case "double":
               res.add("double", "");
               break;
         }
      }
      else {
         res = templates.getInstanceOf("handle_func_param");
         res.add("type", "double");
         res.add("var", ctx.varx);
         res.add("value", id);
      }
      return res;
   }

   // COMPLETED
   @Override public ST visitExprMultDivMod(QuizParser.ExprMultDivModContext ctx) {
      ST op = templates.getInstanceOf("binary_operation");
      ctx.varx = newVar();
      op.add("stat", visit(ctx.expr(0)).render());
      op.add("stat", visit(ctx.expr(1)).render());
      op.add("id", tmpId);
      op.add("type", "double");
      op.add("var", ctx.varx);
      op.add("e1", ctx.expr(0).varx);
      op.add("op", ctx.op.getText());
      op.add("e2", ctx.expr(1).varx);
      varTypes.put(tmpId, "double");
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

   private String getKeyOfValue(String value, HashMap<String, String> map) {
      for (Entry<String, String> entry : map.entrySet()) {
         if (entry.getValue().equals(value)) return entry.getKey();
      }
      return null;
   }
}
