import java.util.HashMap;
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
   private boolean needsMap = false;
   private boolean needsEntry = false;
   private boolean insideFunc = false;
   private boolean needsarrayToListStringsAuxFunc = false;
   private HashMap<String, String> convertion = new HashMap<>();
   private HashMap<String, String> idToTmpVar = new HashMap<>();
   private HashMap<String, Double> realValueOfId = new HashMap<>();
   private HashMap<String, String> funcParams = new HashMap<>();
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
      convertion.put("AND", "&&");
      convertion.put("OR", "||");
      convertion.put("==", "==");
      convertion.put("!=", "!=");
      convertion.put(">", ">");
      convertion.put("<", "<");
   }

   // COMPLETED
   @Override public ST visitProgram(QuizParser.ProgramContext ctx) {
      ST module = templates.getInstanceOf("module");
      module.add("name", file);
      for (QuizParser.BlockContext block : ctx.block()) {
         module.add("stat", visit(block).render());
      }
      if (numVars > 0 || needsMap) module.add("hasVars", numVars);

      if (hasScanner) module.add("hasScanner", "");

      if (numWriters > 0) module.add("hasPrintWriter", "");

      if (hasList) module.add("hasList", "");

      if (needsEntry) module.add("needsEntry", "");

      if (needsarrayToListStringsAuxFunc) module.add("needsarrayToListStringsAuxFunc", "");

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
      insideFunc = true;
      needsEntry = true;
      ST func = templates.getInstanceOf("function");
      func.add("name", ctx.name.getText());
      for (QuizParser.ParamsContext type : ctx.params()) {
         String[] splitted = visit(type).render().split(" ");
         func.add("paramsID", "\""+splitted[1]+"\", ");
         func.add("param", convertion.get(splitted[0])+" "+splitted[1]+", ");
         funcParams.put(splitted[1], splitted[0]);
      }

      if (ctx.type != null) {
         func.add("paramsID", "\""+ctx.ID(0)+"\"");
         func.add("param", convertion.get(ctx.type.getText())+" "+ctx.ID(0));
         funcParams.put(ctx.ID(0).getText(), ctx.type.getText());
      }

      for (QuizParser.ContentContext content : ctx.content()) {
         func.add("stat", visit(content).render());
      }

      String ret = "";
      if (ctx.expr() != null || ctx.bool() != null) {
         if (ctx.bool() != null) {
            ret = visit(ctx.bool()).render();
            func.add("ret", convertion.get(ret));
         }
         else {
            ret = visit(ctx.expr()).render();
            func.add("building_return", ret);
            func.add("ret", ctx.expr().varx);
         }
         String[] words = ret.split(" ");
         for (String word : words) {
            if (word.equals("double")) {
               func.add("return_type", "double");
               break;
            }
            else if (word.equals("boolean") || word.equals("TRUE") || word.equals("FALSE")) {
               func.add("return_type", "boolean");
               break;
            }
            else {
               func.add("return_type", "String");
               break;
            }
         }
      }
      else {
         func.add("return_type", "void");
      }

      insideFunc = false;
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

   //IN PROGRESS
   @Override public ST visitMapQuestion(QuizParser.MapQuestionContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("getQuestions", "");
      format.add("type", convertion.get(ctx.type.getText()));
      format.add("var", ctx.ID().getText());
      format.add("path", ctx.TEXT().getText());
      return format;
   }
   
   //COMPLETED
   @Override public ST visitMapNums(QuizParser.MapNumsContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("type", convertion.get(ctx.type.getText()).replace("d", "D"));
      format.add("var", ctx.ID().getText());
      return format;
   }

   //COMPLETED
   @Override public ST visitMapText(QuizParser.MapTextContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("type", convertion.get(ctx.type.getText()));
      format.add("var", ctx.ID().getText());
      return format;
   }

   //COMPLETED
   @Override public ST visitMapBoolean(QuizParser.MapBooleanContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("type", convertion.get(ctx.type.getText()).replace("b", "B"));
      format.add("var", ctx.ID().getText());
      return format;
   }

   // IN PROGRESS
   @Override public ST visitVarText(QuizParser.VarTextContext ctx) {
      ST atrib = templates.getInstanceOf("atrib");
      if (insideFunc) atrib.add("insideFunc", "");
      ctx.varx = newVar();
      atrib.add("type", "String");
      atrib.add("var", ctx.varx);
      if (ctx.singlestring != null) {
         atrib.add("value", ctx.singlestring);
         atrib.add("needComma", "");
      }
      else if (ctx.ID().size() > 1) {
         String index = ctx.index.getText();
         String finalIndex = idToTmpVar.containsKey(index) ? idToTmpVar.get(index) : index;
         atrib.add("value", ctx.ID(1).getText()+".get("+finalIndex+")");
         atrib.add("needComma", "");
      }
      else if (ctx.callfunction() != null)
         atrib.add("value", visit(ctx.callfunction()).render());
      else if (ctx.finalstring != null) {
         String str, finalStr;
         for (QuizParser.StringsContext s : ctx.strings()) {
            str = visit(s).render();
            finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
            atrib.add("value", finalStr+"+");
         }
         str = ctx.finalstring.getText();
         finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
         atrib.add("value", finalStr);
         atrib.add("needComma", "");
      }
      else if (ctx.questionFetchTitle() != null) {
         atrib.add("needComma", "");
         atrib.add("value", visit(ctx.questionFetchTitle()).render());
      }
      else if (ctx.questionFetchDiff() != null) {
         atrib.add("needComma", "");
         atrib.add("value", visit(ctx.questionFetchDiff()).render());
      }
      else if (ctx.questionFetchType() != null) {
         atrib.add("needComma", "");
         atrib.add("value", visit(ctx.questionFetchType()).render());
      }
      atrib.add("id", ctx.ID(0).getText());
      idToTmpVar.put(ctx.ID(0).getText(), ctx.varx);
      varTypes.put(ctx.ID(0).getText(), "String");
      return atrib;
   }

   @Override public ST visitStrings(QuizParser.StringsContext ctx) {
      ST val = templates.getInstanceOf("return_plain_val");
      if (ctx.TEXT() != null) val.add("val", ctx.TEXT().getText());
      else val.add("val", ctx.ID().getText());
      return val;
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
      ctx.varx = newVar();
      ST visit;
      if (ctx.expr() != null) {
         visit = visit(ctx.expr());
         idToTmpVar.put(tmpId, ctx.expr().varx);
      }
      else if (ctx.callfunction() != null) {
         visit = templates.getInstanceOf("atrib_double");
         visit.add("type", "double");
         visit.add("var", ctx.varx);
         visit.add("value", visit(ctx.callfunction()).render());
         visit.add("id", tmpId);
         if (insideFunc) visit.add("insideFunc", "");
      }
      else if (ctx.ID().size() > 1) {
         visit = templates.getInstanceOf("atrib_double");
         visit.add("type", "double");
         visit.add("var", ctx.varx);
         String index = ctx.index.getText();
         String finalIndex = idToTmpVar.containsKey(index) ? idToTmpVar.get(index) : index;
         visit.add("value", ctx.ID(1).getText()+".get("+finalIndex+")");
         visit.add("id", tmpId);
         idToTmpVar.put(tmpId, ctx.varx);
         visit.add("needComma", "");
         if (insideFunc) visit.add("insideFunc", "");
      }
      else {
         visit = null;
      }
      return visit;
   }

   @Override public ST visitVarBoolean(QuizParser.VarBooleanContext ctx) {
      ST atrib = templates.getInstanceOf("atrib_boolean");
      if (insideFunc) atrib.add("insideFunc", "");
      ctx.varx = newVar();
      atrib.add("type", "boolean");
      atrib.add("var", ctx.varx);
      if (ctx.bool() != null) {
         atrib.add("value", convertion.get(visit(ctx.bool()).render()));
         atrib.add("needComma", "");
      }
      else if (ctx.ID().size() > 1) {
         String index = ctx.index.getText();
         String finalIndex = idToTmpVar.containsKey(index) ? idToTmpVar.get(index) : index;
         atrib.add("value", ctx.ID(1).getText()+".get("+finalIndex+")");
         atrib.add("needComma", "");
      }
      else if (ctx.callfunction() != null) atrib.add("value", visit(ctx.callfunction()).render());
      atrib.add("id", ctx.ID(0).getText());
      idToTmpVar.put(ctx.ID(0).getText(), ctx.varx);
      varTypes.put(ctx.ID(0).getText(), "boolean");
      return atrib;
   }

   @Override public ST visitVarQuestion(QuizParser.VarQuestionContext ctx) {
      ST atrib = templates.getInstanceOf("atrib");
      if (insideFunc) atrib.add("insideFunc", "");
      ctx.varx = newVar();
      atrib.add("type", "Question");
      atrib.add("var", ctx.varx);
      atrib.add("id", ctx.ID().getText());
      idToTmpVar.put(ctx.ID().getText(), ctx.varx);
      varTypes.put(ctx.ID().getText(), "Question");
      return atrib;
   }

   @Override public ST visitVarListAdd(QuizParser.VarListAddContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarListRemove(QuizParser.VarListRemoveContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitVarListSplit(QuizParser.VarListSplitContext ctx) {
      needsarrayToListStringsAuxFunc = true;
      ST split = templates.getInstanceOf("list_split");
      // this means you are splitting a literal string
      split.add("var", ctx.ID(0).getText());
      if (ctx.TEXT().size() > 1) {
         split.add("val", ctx.TEXT(0).getText());
         split.add("split", ctx.TEXT(1).getText());
      }
      else {
         String val = ctx.ID(1).getText();
         String finalVal = idToTmpVar.containsKey(val) ? idToTmpVar.get(val) : val;
         split.add("val", finalVal);
         split.add("split", ctx.TEXT(0).getText());
      }
      return split;
   }

   @Override public ST visitVarMapGet(QuizParser.VarMapGetContext ctx) {
      needsMap = true;
      ST get = templates.getInstanceOf("hashMap_get");
      get.add("var", ctx.ID(1).getText());
      get.add("getter", ctx.ID(0).getText());
      if(ctx.TEXT() != null){
         get.add("key", ctx.TEXT().getText());
      }
      else{
         get.add("key", visit(ctx.questionFetch()).render());
      }
      return visitChildren(ctx);
   }

   @Override public ST visitVarMapPut(QuizParser.VarMapPutContext ctx) {
      needsMap = true;
      ST put = templates;

      if(ctx.questionFetch() != null){
         //TEXT(0)
      }
      else{
         // if size txt > 1 --> 2 txt
         //else
      }
      return visitChildren(ctx);
   }

   @Override public ST visitVarMapRemove(QuizParser.VarMapRemoveContext ctx) {
      needsMap = true;
      return visitChildren(ctx);
   }

   @Override public ST visitVarMapClear(QuizParser.VarMapClearContext ctx) {
      needsMap = true;
      ST clear = templates.getInstanceOf("hashMap_clear");
      System.out.println(ctx.ID().getText());
      clear.add("var", ctx.ID().getText());
      return clear;
   }

   @Override public ST visitAdd(QuizParser.AddContext ctx) {
      return visitChildren(ctx);
   }

   // @Override public ST visitQuestionTitle(QuizParser.QuestionTitleContext ctx) {
   //    return visitChildren(ctx);
   // }

   // @Override public ST visitQuestionAnsRight(QuizParser.QuestionAnsRightContext ctx) {
   //    return visitChildren(ctx);
   // }

   // @Override public ST visitQuestionAnsWrong(QuizParser.QuestionAnsWrongContext ctx) {
   //    return visitChildren(ctx);
   // }

   // @Override public ST visitQuestionDifficulty(QuizParser.QuestionDifficultyContext ctx) {
   //    return visitChildren(ctx);
   // }

   // @Override public ST visitQuestionType(QuizParser.QuestionTypeContext ctx) {
   //    return visitChildren(ctx);
   // }

   // @Override public ST visitQuestionTries(QuizParser.QuestionTriesContext ctx) {
   //    return visitChildren(ctx);
   // }

   // @Override public ST visitQuestionTime(QuizParser.QuestionTimeContext ctx) {
   //    return visitChildren(ctx);
   // }

   // @Override public ST visitQuestionPoints(QuizParser.QuestionPointsContext ctx) {
   //    return visitChildren(ctx);
   // }

   // COMPLETED
   @Override public ST visitQuestionFetch(QuizParser.QuestionFetchContext ctx) {
      if (ctx.questionFetchTitle() != null) return visit(ctx.questionFetchTitle());
      if (ctx.questionFetchAnsRight() != null) return visit(ctx.questionFetchAnsRight());
      if (ctx.questionFetchAnsWrong() != null) return visit(ctx.questionFetchAnsWrong());
      if (ctx.questionFetchDiff() != null) return visit(ctx.questionFetchDiff());
      if (ctx.questionFetchType() != null) return visit(ctx.questionFetchType());
      if (ctx.questionFetchTries() != null) return visit(ctx.questionFetchTries());
      if (ctx.questionFetchTime() != null) return visit(ctx.questionFetchTime());
      if (ctx.questionFetchPoints() != null) return visit(ctx.questionFetchPoints());
      return null;
   }

   // IN PROGRESS
   @Override public ST visitQuestionFetchTitle(QuizParser.QuestionFetchTitleContext ctx) {
      ST questionFetch = templates.getInstanceOf("question_fetch");
      questionFetch.add("id", ctx.ID().getText());
      questionFetch.add("type", "getTitle()");
      return questionFetch;
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
      if (ctx.finalstring != null) {
         String str, finalStr;
         for (QuizParser.StringsContext s : ctx.strings()) {
            str = visit(s).render();
            finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
            print.add("text", finalStr+"+");
         }
         str = ctx.finalstring.getText();
         finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
         print.add("text", finalStr);
      }
      else if (ctx.TEXT() != null) {
         print.add("text", ctx.TEXT().getText());
      }
      else if (ctx.expr() != null) {
         print.add("stat", visit(ctx.expr()).render());
         print.add("text", ctx.expr().varx);
      }
      else if (ctx.questionFetch() != null) {
         print.add("text", visit(ctx.questionFetch()).render());
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
      writer.add("var", newVarWriter());
      if (ctx.finalstring != null) {
         String str, finalStr;
         for (QuizParser.StringsContext s : ctx.strings()) {
            str = visit(s).render();
            finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
            writer.add("string", finalStr+"+");
         }
         str = ctx.finalstring.getText();
         finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
         writer.add("string", finalStr);
      }
      else if (ctx.TEXT().size() > 1) {
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
      switch (ctx.type.getText()) {
         case "number":
            forLoop.add("double", "");
            break;
         case "boolean":
            forLoop.add("boolean", "");
            break;
      }
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
         String par = visit(p).render();
         call.add("param", par+", ");
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
      String val = ctx.val.getText();
      String valFinal = idToTmpVar.containsKey(val) ? idToTmpVar.get(val) : val;
      callp.add("val", valFinal);
      return callp;
   }

   // COMPLETED
   @Override public ST visitConditional(QuizParser.ConditionalContext ctx) {
      ST cond = templates.getInstanceOf("conditional");
      if (ctx.id != null) {
         String[] content = ctx.getText().split("T");
         // then there is a NOT
         if (content.length > 1) {
            cond.add("not", "!");
         }
         cond.add("id", idToTmpVar.get(ctx.ID(0).getText()));
      }
      else if (ctx.field1 != null) {
         String f1 = ctx.field1.getText();
         String f2 = ctx.field2.getText();

         if (!isNumber(ctx.field1.getText()))
            f1 = idToTmpVar.get(ctx.field1.getText());

         if (!isNumber(ctx.field2.getText()))
            f2 = idToTmpVar.get(ctx.field2.getText());

         cond.add("s1", f1);
         cond.add("s2", f2);
         cond.add("op", convertion.get(ctx.op.getText()));
      }
      else {
         if (ctx.op.getText().equals("!=")) cond.add("not", "!");
         String f3 = ctx.field3.getText();
         String f4 = ctx.field4.getText();
         String finalf3 = idToTmpVar.containsKey(f3) ? idToTmpVar.get(f3) : f3;
         String finalf4 = idToTmpVar.containsKey(f4) ? idToTmpVar.get(f4) : f4;
         cond.add("s1", finalf3);
         cond.add("s2", finalf4);
         cond.add("equals", ".equals");
      }
      return cond;
   }

   // COMPLETED
   @Override public ST visitVarManipPlus(QuizParser.VarManipPlusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_front");
      if (insideFunc) manip.add("insideFunc", "");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "++");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipMinus(QuizParser.VarManipMinusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_front");
      if (insideFunc) manip.add("insideFunc", "");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "--");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipPrePlus(QuizParser.VarManipPrePlusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_back");
      if (insideFunc) manip.add("insideFunc", "");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "++");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipPreMinus(QuizParser.VarManipPreMinusContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_back");
      if (insideFunc) manip.add("insideFunc", "");
      manip.add("var", idToTmpVar.get(ctx.ID().getText()));
      manip.add("action", "--");
      manip.add("id", ctx.ID().getText());
      return manip;
   }

   // COMPLETED
   @Override public ST visitVarManipPlusEquals(QuizParser.VarManipPlusEqualsContext ctx) {
      ST manip = templates.getInstanceOf("varmanip_number");
      if (insideFunc) manip.add("insideFunc", "");
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
      if (insideFunc) manip.add("insideFunc", "");
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
      if (insideFunc) manip.add("insideFunc", "");
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
      if (insideFunc) manip.add("insideFunc", "");
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
      if (insideFunc) op.add("insideFunc", "");
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
      if (insideFunc) res.add("insideFunc", "");
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
      res.add("needComma", "");
      return res;
   }

   // COMPLETED
   @Override public ST visitExprId(QuizParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();
      ST res;
      ctx.varx = newVar();
      if (!funcParams.containsKey(id)) {
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
               case "boolean":
                  ctx.type = "boolean";
                  res.add("boolean", "");
                  break;
            }
         }
         else {
            System.out.println("Can't get from map because varTypes doesn't contain the key "+id);
         }
      }
      else {
         res = templates.getInstanceOf("handle_func_param");
         String type = funcParams.get(id);
         switch (type) {
            case "number":
               res.add("double", "");
               break;
            case "boolean":
               res.add("boolean", "");
               break;
         }
         res.add("type", convertion.get(type));
         res.add("var", ctx.varx);
         res.add("value", id);
      }
      return res;
   }

   // COMPLETED
   @Override public ST visitExprMultDivMod(QuizParser.ExprMultDivModContext ctx) {
      ST op = templates.getInstanceOf("binary_operation");
      if (insideFunc) op.add("insideFunc", "");
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
      if (ctx.map() != null) return visit(ctx.map());
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
