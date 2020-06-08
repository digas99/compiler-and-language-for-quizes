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
   private int numWriters = 0;
   private boolean hasScanner = false;
   private boolean hasList = false;
   private HashMap<String, String> convertion = new HashMap<>();
   private HashMap<String, String> idToTmpVar = new HashMap<>();
   private HashMap<String, Double> realValueOfId = new HashMap<>();
   private List<String> funcParamsNames = new ArrayList<>();
   private HashMap<String, String> varTypes = new HashMap<>();
   private String newVar() {
      numVars++;
      return "var"+numVars;
   }
   private String newVarWriter() {
      numWriters++;
      return "writer"+numWriters;
   }
   private String file;

   public QuizCompiler(String file) {
      this.file = file;
      convertion.put("number", "double");
      convertion.put("text", "String");
      convertion.put("boolean", "boolean");
      convertion.put("question", "Question");
      convertion.put("TRUE", "true");
      convertion.put("FALSE", "false");
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

      if (numWriters > 0) module.add("hasPrintWriter", "");

      if (hasList) module.add("hasList", "");
      
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

   // COMPLETED
   @Override public ST visitFunction(QuizParser.FunctionContext ctx) {
      ST func = templates.getInstanceOf("function");
      func.add("name", ctx.name.getText());

      for (QuizParser.ParamsContext type : ctx.params()) {
         String[] splitted = visit(type).render().split(" ");
         func.add("param", convertion.get(splitted[0])+" "+splitted[1]+", ");
      }

      if (ctx.type != null) func.add("param", convertion.get(ctx.type.getText())+" "+ctx.ID(0));
      
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

   @Override public ST visitParams(QuizParser.ParamsContext ctx) {
      ST types = templates.getInstanceOf("types_formater");
      if (ctx.type != null) {
         types.add("type", ctx.type.getText());
      }
      types.add("id", ctx.ID().getText());
      return types;
   }

   @Override public ST visitListQuestion(QuizParser.ListQuestionContext ctx) {
      return visitChildren(ctx);
   }

   // COMPLETED
   @Override public ST visitListNums(QuizParser.ListNumsContext ctx) {
      ST list = templates.getInstanceOf("list_double");
      hasList = true;
      list.add("var", ctx.ID().getText());
      if (ctx.listFormatNumber() != null) list.add("val", visit(ctx.listFormatNumber()).render());
      list.add("tmpvar", newVar());
      list.add("tmpvar2", newVar());
      if (ctx.init != null) list.add("init", "");
      return list;
   }

   // COMPLETED
   @Override public ST visitListText(QuizParser.ListTextContext ctx) {
      ST list = templates.getInstanceOf("list_string");
      hasList = true;
      list.add("var", ctx.ID().getText());
      if (ctx.listFormatText() != null) list.add("val", visit(ctx.listFormatText()).render());
      list.add("tmpvar", newVar());
      list.add("tmpvar2", newVar());
      if (ctx.init != null) list.add("init", "");
      return list;
   }

   // COMPLETED
   @Override public ST visitListBoolean(QuizParser.ListBooleanContext ctx) {
      ST list = templates.getInstanceOf("list_boolean");
      hasList = true;
      list.add("var", ctx.ID().getText());
      if (ctx.listFormatBool() != null) list.add("val", visit(ctx.listFormatBool()).render());
      list.add("tmpvar", newVar());
      list.add("tmpvar2", newVar());
      if (ctx.init != null) list.add("init", "");
      return list;
   }

   // COMPLETED
   @Override public ST visitListFormatNumber(QuizParser.ListFormatNumberContext ctx) {
      ST format = templates.getInstanceOf("return_plain_val");
      String buildVals = "";
      for (TerminalNode tm : ctx.NUMBER()) {
         buildVals+=tm.getText()+", ";
      }
      format.add("val", buildVals.substring(0, buildVals.length()-2));
      return format;
   }

   // COMPLETED
   @Override public ST visitListFormatText(QuizParser.ListFormatTextContext ctx) {
      ST format = templates.getInstanceOf("return_plain_val");
      String buildVals = "";
      for (TerminalNode tm : ctx.TEXT()) {
         buildVals+=tm.getText()+", ";
      }
      format.add("val", buildVals.substring(0, buildVals.length()-2));
      return format;
   }

   // COMPLETED
   @Override public ST visitListFormatBool(QuizParser.ListFormatBoolContext ctx) {
      ST format = templates.getInstanceOf("return_plain_val");
      String buildVals = "";
      for (QuizParser.BoolContext b : ctx.bool()) {
         buildVals+=convertion.get(visit(b).render())+", ";
      }
      format.add("val", buildVals.substring(0, buildVals.length()-2));
      return format;
   }
   
   // COMPLETED
   @Override public ST visitBool(QuizParser.BoolContext ctx) {
      ST val = templates.getInstanceOf("return_plain_val");
      val.add("val", ctx.getText());
      return val;
   }

   // IN PROGRESS
   @Override public ST visitVarText(QuizParser.VarTextContext ctx) {
      ST atrib = templates.getInstanceOf("atrib");
      ctx.varx = newVar();
      atrib.add("type", "String");
      atrib.add("var", ctx.varx);
      if (ctx.TEXT() != null) atrib.add("value", ctx.TEXT().getText());
      atrib.add("id", ctx.ID().getText());
      idToTmpVar.put(ctx.ID().getText(), ctx.varx);
      varTypes.put(ctx.ID().getText(), "String");
      return atrib;
   }

   // COMPLETED
   @Override public ST visitVarTextRead(QuizParser.VarTextReadContext ctx) {
      ST read = templates.getInstanceOf("read");
      String id = ctx.ID().getText();
      ctx.varx = newVar();
      hasScanner = true;
      if (ctx.TEXT() == null) read.add("file", "System.in");
      else read.add("file", ctx.TEXT().getText());
      read.add("type", "String");
      read.add("var", ctx.varx);
      read.add("id", id);
      varTypes.put(id, "String");
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

   // COMPLETE
   @Override public ST visitWriteFile(QuizParser.WriteFileContext ctx) {
      ST writer = templates.getInstanceOf("write");
      writer.add("file", ctx.TEXT(0).getText());
      // TEXT as something more other than the file name
      writer.add("var", newVarWriter());
      if (ctx.TEXT().size() > 1) {
         writer.add("string", ctx.TEXT(1));
      }
      else {
         if (ctx.expr() != null) {
            writer.add("stat", visit(ctx.expr()).render());
            if (ctx.expr().type.equals("String"))
               writer.add("string", ctx.expr().varx);
            else
               writer.add("double", ctx.expr().varx);
         }
      }
      return writer;
   }

   // IN PROGRESS
   @Override public ST visitForIn(QuizParser.ForInContext ctx) {
      ST forLoop = templates.getInstanceOf("for_in");
      ctx.varx = newVar();
      forLoop.add("var", ctx.varx);
      forLoop.add("type", convertion.get(ctx.type.getText()));
      String id = ctx.ID(0).getText();
      varTypes.put(id, "String");
      idToTmpVar.put(id, ctx.varx);
      forLoop.add("var1", id);
      forLoop.add("var2", ctx.ID(1).getText());
      for (QuizParser.ContentContext content : ctx.content()) {
         forLoop.add("stat", visit(content).render());
      }
      return forLoop;
   }

   // COMPLETED
   @Override public ST visitForTo(QuizParser.ForToContext ctx) {
      ST forLoop = templates.getInstanceOf("for_loop");
      ctx.varx = newVar();
      forLoop.add("var", ctx.varx);
      
      String id = ctx.ID(0).getText();
      forLoop.add("id", id);
      varTypes.put(id, "String");
      idToTmpVar.put(id, ctx.varx);
      
      for (QuizParser.ContentContext content : ctx.content()) {
         forLoop.add("stat", visit(content).render());
      }

      String start = ctx.start.getText();
      String end = ctx.end.getText();
      
      if (isNumber(start)) forLoop.add("start", start);
      else forLoop.add("start", idToTmpVar.get(start));

      if (isNumber(end)) forLoop.add("end", end);
      else forLoop.add("end", idToTmpVar.get(end));

      double startInt, endInt;
      try {
         startInt = Integer.parseInt(start);
      } catch(NumberFormatException e) {
         startInt = realValueOfId.get(start);
      }

      try {
         endInt = Integer.parseInt(end);
      } catch(NumberFormatException e) {
         endInt = realValueOfId.get(end);
      }

      double res = startInt - endInt;

      if (res < 0)
         forLoop.add("comp", "<");
      else forLoop.add("comp", ">");

      if (ctx.incr != null) {
         forLoop.add("increment", ctx.incr.getText());
         forLoop.add("op", ctx.op.getText());
      }
      else {
         forLoop.add("increment", 1);
         forLoop.add("op", "+");
      }
      
      return forLoop; 
   }

   // COMPLETED
   @Override public ST visitDoaslong(QuizParser.DoaslongContext ctx) {
      ST doLoop = templates.getInstanceOf("do_while");
      doLoop.add("condition", visit(ctx.conditional()).render());
      for (QuizParser.ContentContext cont : ctx.content()) {
         doLoop.add("stat", visit(cont).render());
      }
      return doLoop;
   }

   // COMPLETED
   @Override public ST visitAslong(QuizParser.AslongContext ctx) {
      ST whileLoop = templates.getInstanceOf("while");
      whileLoop.add("condition", visit(ctx.conditional()).render());
      for (QuizParser.ContentContext cont : ctx.content()) {
         whileLoop.add("stat", visit(cont).render());
      }
      return whileLoop;
   }

   // COMPLETED
   @Override public ST visitIfCond(QuizParser.IfCondContext ctx) {
      ST ifcond = templates.getInstanceOf("ifstat");
      ifcond.add("condition", visit(ctx.conditional()).render());
      for (QuizParser.ContentContext cont : ctx.content()) {
         ifcond.add("stat", visit(cont).render());
      }
      if (ctx.finalCond() != null) {
         ifcond.add("final", visit(ctx.finalCond()).render());
      }
      if (ctx.elsif().size() > 0) {
         int size = ctx.elsif().size();
         for (int i = 0; i < size-1; i++) {
            ifcond.add("elseifstat", visit(ctx.elsif(i)).render()+"\n");
         }
         ifcond.add("elseifstat", visit(ctx.elsif(size-1)).render());
      }
      return ifcond;
   }

   // COMPLETED
   @Override public ST visitElsif(QuizParser.ElsifContext ctx) {
      ST elseif = templates.getInstanceOf("elsifstat");
      elseif.add("condition", visit(ctx.conditional()).render());
      for (QuizParser.ContentContext cont : ctx.content()) {
         elseif.add("stat", visit(cont).render());
      }
      return elseif;
   }

   // COMPLETED
   @Override public ST visitFinalCond(QuizParser.FinalCondContext ctx) {
      ST ifcond = templates.getInstanceOf("return_plain_val");
      int size = ctx.content().size();
      for (int i = 0; i < size-1; i++) {
         ifcond.add("val", visit(ctx.content(i)).render()+"\n");
      }
      ifcond.add("val", visit(ctx.content(size-1)).render());
      return ifcond;
   }

   // COMPLETED
   @Override public ST visitCallfunction(QuizParser.CallfunctionContext ctx) {
      ST call = templates.getInstanceOf("callfunc");
      call.add("name", ctx.ID(0).getText());
      for (QuizParser.CallParamsContext p : ctx.callParams()) {
         call.add("param", visit(p).render()+", ");
      }
      if (ctx.last != null) {
         // if last is an id
         if (ctx.ID(1) != null) {
            call.add("param", idToTmpVar.get(ctx.last.getText()));   
         }
         else {
            call.add("param", ctx.last.getText());
         }
      }
      return call;
   }

   // COMPLETED
   @Override public ST visitCallParams(QuizParser.CallParamsContext ctx) {
      ST callp = templates.getInstanceOf("return_plain_val");
      callp.add("val", ctx.val.getText());
      return callp;
   }

   // IN PROGRESS
   @Override public ST visitConditional(QuizParser.ConditionalContext ctx) {
      ST cond = templates.getInstanceOf("conditional");
      if (ctx.ID() != null) {
         String[] content = ctx.getText().split("T");
         // then there is a NOT
         if (content.length > 1) {
            cond.add("not", "!");
         }
         cond.add("id", idToTmpVar.get(ctx.ID().getText()));
      }
      else if (ctx.expr().size() > 0) {
         System.out.println("expr");
      }
      else {
         if (ctx.op.getText().equals("!=")) cond.add("not", "!");
         cond.add("s1", ctx.TEXT(0).getText());
         cond.add("s2", ctx.TEXT(1).getText());
      }
      return cond;
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
      ctx.type = "double";
      op.add("stat", visit(ctx.expr(0)).render());
      op.add("stat", visit(ctx.expr(1)).render());
      if (!tmpId.equals("")) {
         op.add("id", tmpId);
         varTypes.put(tmpId, "double");
      }
      op.add("type", "double");
      op.add("var", ctx.varx);
      op.add("e1", ctx.expr(0).varx);
      op.add("op", ctx.op.getText());
      op.add("e2", ctx.expr(1).varx);
      return op;
   }

   @Override public ST visitExprParens(QuizParser.ExprParensContext ctx) {
      ST parens = visit(ctx.expr());
      ctx.varx= ctx.expr().varx;
      ctx.type = "double";
      return parens;
   }

   //COMPLETED
   @Override public ST visitExprUnary(QuizParser.ExprUnaryContext ctx) {
      ST unary = templates.getInstanceOf("atrib_unary");
      ctx.varx= newVar(); 
      ctx.type = "double";
      unary.add("stat", visit(ctx.expr()).render());
      if (!tmpId.equals("")) {
         unary.add("id", tmpId);
         varTypes.put(tmpId, "double");
      }
      unary.add("type","double");
      unary.add("var",ctx.varx);
      unary.add("op",ctx.op.getText());
      unary.add("e1",ctx.expr().varx);
      return unary;
   }

   // COMPLETED
   @Override public ST visitExprNumber(QuizParser.ExprNumberContext ctx) {
      ST res = templates.getInstanceOf("atrib_double");
      ctx.varx = newVar();
      realValueOfId.put(tmpId, Double.parseDouble(ctx.NUMBER().getText()));
      ctx.type = "double";
      if (!tmpId.equals("")) {
         res.add("id", tmpId);
         varTypes.put(tmpId, "double");
      }
      res.add("type", "double");
      res.add("var", ctx.varx);
      res.add("value", ctx.NUMBER().getText());
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
         if (varTypes.containsKey(id)) {
            switch(varTypes.get(id)) {
               case "String":
                  ctx.type = "String";
                  res.add("string", "");
                  break;
               case "double":
                  ctx.type = "double";
                  res.add("double", "");
                  break;
            }
         }
         else {
            System.out.println("Can't get from map because varTypes doesn't contain the key "+id);
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
      ctx.type = "double";
      op.add("stat", visit(ctx.expr(0)).render());
      op.add("stat", visit(ctx.expr(1)).render());
      if (!tmpId.equals("")) {
         op.add("id", tmpId);
         varTypes.put(tmpId, "double");
      }
      op.add("type", "double");
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

   private String getKeyOfValue(String value, HashMap<String, String> map) {
      for (Entry<String, String> entry : map.entrySet()) {
         if (entry.getValue().equals(value)) return entry.getKey();
      }
      return null;
   }

   private boolean isNumber(String n) {
      try {
         Integer.parseInt(n);
      } catch(NumberFormatException e) {
         return false;
      }
      return true;
   }
}
