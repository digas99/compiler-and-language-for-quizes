import java.util.HashMap;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.*;

public class QuizCompiler extends QuizBaseVisitor<ST> {
   private STGroup templates = new STGroupFile("string_templates.stg");
   private String tmpId = "";
   private int numVars = 0;
   private int numWriters = 0;
   private int numScanners = 0;
   private boolean hasList = false;
   private boolean needsMap = false;
   private boolean needsEntry = false;
   private boolean insideFunc = false;
   private boolean needsArrays = false;
   private HashMap<String, String> convertion = new HashMap<>();
   private HashMap<String, String> idToTmpVar = new HashMap<>();
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
   private String newVarScanner() {
      numScanners++;
      return "rd"+numScanners;
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
      convertion.put(">=", ">=");
      convertion.put("<=", "<=");
   }

   
   @Override public ST visitProgram(QuizParser.ProgramContext ctx) {
      ST module = templates.getInstanceOf("module");
      module.add("name", file);
      for (QuizParser.BlockContext block : ctx.block()) {
         module.add("stat", visit(block).render());
      }
      if (numVars > 0 || needsMap) module.add("hasVars", numVars);

      if (numScanners > 0) module.add("hasScanner", "");

      if (numWriters > 0) module.add("hasPrintWriter", "");

      if (hasList) module.add("hasList", "");

      if (needsEntry) module.add("needsEntry", "");

      if (needsArrays) module.add("needsArrays", "");

      return module;
   }

   
   @Override public ST visitBlock(QuizParser.BlockContext ctx) {
      if (ctx.function() != null) return visit(ctx.function());
      if (ctx.main() != null) return visit(ctx.main());
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
      ST list = templates.getInstanceOf("list_question");
      hasList = true;
      list.add("var", ctx.ID(0).getText());
      list.add("newVar", newVar());
      if (ctx.imp != null) {
         if (ctx.TEXT() != null) list.add("file",ctx.TEXT().getText());
         else list.add("file", idToTmpVar.get(ctx.ID(1).getText()));
      }
      else if (ctx.clone != null) {
         list = templates.getInstanceOf("list_clone");
         list.add("type", "Question");
         list.add("id", ctx.ID(0).getText());
         list.add("target", ctx.ID(1).getText());
      }
      
      if (ctx.init != null && ctx.imp == null) {
         list.add("justInitialize", "");
      }
      else if (ctx.init == null && ctx.imp != null) {
         list.add("justAtrib", "");
      }

      return list;
   }

   
   @Override public ST visitListNums(QuizParser.ListNumsContext ctx) {
      ST list = templates.getInstanceOf("list_double");
      hasList = true;
      list.add("var", ctx.ID(0).getText());
      if (ctx.listFormatNumber() != null) list.add("val", visit(ctx.listFormatNumber()).render());
      list.add("tmpvar", newVar());
      list.add("tmpvar2", newVar());
      if (ctx.init != null) list.add("init", "");
      if (ctx.clone != null) {
         list = templates.getInstanceOf("list_clone");
         list.add("type", "double");
         list.add("id", ctx.ID(0).getText());
         list.add("target", ctx.ID(1).getText());
      }
      return list;
   }

   
   @Override public ST visitListText(QuizParser.ListTextContext ctx) {
      ST list = templates.getInstanceOf("list_string");
      hasList = true;
      list.add("var", ctx.ID(0).getText());
      list.add("tmpvar", newVar());
      list.add("tmpvar2", newVar());
      if (ctx.listFormatText() != null) list.add("val", visit(ctx.listFormatText()).render());
      if (ctx.questionFetchAnsRight() != null) list.add("questionFetch", visit(ctx.questionFetchAnsRight()).render());
      if (ctx.questionFetchAnsWrong() != null) list.add("questionFetch", visit(ctx.questionFetchAnsWrong()).render());
      if (ctx.init != null && ctx.questionFetchAnsRight() == null && ctx.questionFetchAnsWrong() == null) list.add("init", "");
      if (ctx.clone != null) {
         list = templates.getInstanceOf("list_clone");
         list.add("type", "String");
         list.add("id", ctx.ID(0).getText());
         list.add("target", ctx.ID(1).getText());
      }
      return list;
   }

   
   @Override public ST visitListBoolean(QuizParser.ListBooleanContext ctx) {
      ST list = templates.getInstanceOf("list_boolean");
      hasList = true;
      list.add("var", ctx.ID(0).getText());
      if (ctx.listFormatBool() != null) list.add("val", visit(ctx.listFormatBool()).render());
      list.add("tmpvar", newVar());
      list.add("tmpvar2", newVar());
      if (ctx.init != null) list.add("init", "");
      if (ctx.clone != null) {
         list = templates.getInstanceOf("list_clone");
         list.add("type", "boolean");
         list.add("id", ctx.ID(0).getText());
         list.add("target", ctx.ID(1).getText());
      }
      return list;
   }

   
   @Override public ST visitListFormatNumber(QuizParser.ListFormatNumberContext ctx) {
      ST format = templates.getInstanceOf("return_plain_val");
      String buildVals = "";
      for (TerminalNode tm : ctx.NUMBER()) {
         buildVals+=tm.getText()+", ";
      }
      format.add("val", buildVals.substring(0, buildVals.length()-2));
      return format;
   }

   
   @Override public ST visitListFormatText(QuizParser.ListFormatTextContext ctx) {
      ST format = templates.getInstanceOf("return_plain_val");
      String buildVals = "";
      for (TerminalNode tm : ctx.TEXT()) {
         buildVals+=tm.getText()+", ";
      }
      format.add("val", buildVals.substring(0, buildVals.length()-2));
      return format;
   }

   
   @Override public ST visitListFormatBool(QuizParser.ListFormatBoolContext ctx) {
      ST format = templates.getInstanceOf("return_plain_val");
      String buildVals = "";
      for (QuizParser.BoolContext b : ctx.bool()) {
         buildVals+=convertion.get(visit(b).render())+", ";
      }
      format.add("val", buildVals.substring(0, buildVals.length()-2));
      return format;
   }
   
   
   @Override public ST visitBool(QuizParser.BoolContext ctx) {
      ST val = templates.getInstanceOf("return_plain_val");
      val.add("val", ctx.getText());
      return val;
   }

   @Override public ST visitMapQuestion(QuizParser.MapQuestionContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("getQuestions", "");
      format.add("type", convertion.get(ctx.type.getText()));
      format.add("var", ctx.ID(0).getText());
      if (ctx.TEXT() != null)  format.add("path", ctx.TEXT().getText());
      else format.add("path", idToTmpVar.get(ctx.ID(1).getText()));
      return format;
   }
   
   @Override public ST visitMapNums(QuizParser.MapNumsContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("type", convertion.get(ctx.type.getText()).replace("d", "D"));
      format.add("var", ctx.ID().getText());
      return format;
   }

   @Override public ST visitMapText(QuizParser.MapTextContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("type", convertion.get(ctx.type.getText()));
      format.add("var", ctx.ID().getText());
      return format;
   }

   @Override public ST visitMapBoolean(QuizParser.MapBooleanContext ctx) {
      needsMap = true;
      ST format = templates.getInstanceOf("hashMap");
      format.add("type", convertion.get(ctx.type.getText()).replace("b", "B"));
      format.add("var", ctx.ID().getText());
      return format;
   }

   @Override public ST visitVarText(QuizParser.VarTextContext ctx) {
      ST atrib = templates.getInstanceOf("atrib");
      if (insideFunc) atrib.add("insideFunc", "");
      ctx.varx = newVar();
      atrib.add("type", "String");
      atrib.add("var", ctx.varx);
      if (ctx.strings().size() > 0) {
         for (QuizParser.StringsContext s : ctx.strings()) {
            String str = visit(s).render();
            String finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
            atrib.add("value", finalStr+"+");
         }
      }
      if (ctx.singlestring != null) {
         atrib.add("value", ctx.singlestring);
         atrib.add("needComma", "");
      }
      else if (ctx.ID().size() > 1) {
         if (ctx.index != null) {
            String index = ctx.index.getText();
            String finalIndex = idToTmpVar.containsKey(index) ? idToTmpVar.get(index) : index;
            atrib.add("value", ctx.ID(1).getText()+".get("+"(int)"+finalIndex+")");
            atrib.add("needComma", "");
         }
         else if (ctx.id != null) {
            String type = varTypes.get(ctx.ID(0).getText());
            if (type == null) type = varTypes.get(ctx.id.getText());
            if (type.equals("double")) {
               atrib = templates.getInstanceOf("atrib_double");
               atrib.add("type", "double");
               atrib.add("var", ctx.varx);
               atrib.add("value", idToTmpVar.get(ctx.id.getText()));
               atrib.add("needComma", "");
            }
            else if (type.equals("boolean")) {
               atrib = templates.getInstanceOf("atrib_boolean");
               atrib.add("type", "boolean");
               atrib.add("var", ctx.varx);
               atrib.add("value", idToTmpVar.get(ctx.id.getText()));
               atrib.add("needComma", "");
            }
            else {
               atrib.add("value", idToTmpVar.get(ctx.id.getText()));
               atrib.add("needComma", "");
            }
         }
         else {
            atrib.add("value", ctx.ID(1).getText()+".get("+visit(ctx.random()).render()+")");
            atrib.add("needComma", "");
         }
      }
      else if (ctx.callfunction() != null)
         atrib.add("value", visit(ctx.callfunction()).render());
      else if (ctx.finalstring != null) {
         String str = ctx.finalstring.getText();
         String finalStr = idToTmpVar.containsKey(str) ? idToTmpVar.get(str) : str;
         atrib.add("value", finalStr);
         atrib.add("needComma", "");
      }
      else if (ctx.questionFetch() != null) {
         atrib.add("needComma", "");
         atrib.add("value", visit(ctx.questionFetch()).render());
      }
      else {
         atrib.add("needComma", "");
         atrib.add("isInitialization", "");
      }
      if (insideFunc) atrib.add("insideFunc", "");
      atrib.add("id", ctx.ID(0).getText());
      idToTmpVar.put(ctx.ID(0).getText(), ctx.varx);
      varTypes.put(ctx.ID(0).getText(), "String");
      return atrib;
   }

   @Override public ST visitStrings(QuizParser.StringsContext ctx) {
      ST val = templates.getInstanceOf("return_plain_val");
      if (ctx.TEXT() != null) val.add("val", ctx.TEXT().getText());
      else if (ctx.ID()!= null) val.add("val", ctx.ID().getText());
      else val.add("val", visit(ctx.questionFetch()).render());
      return val;
   }

   @Override public ST visitVarTextRead(QuizParser.VarTextReadContext ctx) {
      ST read = templates.getInstanceOf("read");
      String id = ctx.ID().getText();
      String newScanner = newVarScanner();
      ctx.varx = newVar();
      if (ctx.TEXT() == null) read.add("file", "System.in");
      else read.add("file", ctx.TEXT().getText());
      read.add("type", "String");
      read.add("var", ctx.varx);
      read.add("id", id);
      read.add("varScanner", newScanner);
      varTypes.put(id, "String");
      if (insideFunc) read.add("insideFunc", "");
      idToTmpVar.put(id, ctx.varx);
      return read;
   }

   @Override public ST visitVarNumber(QuizParser.VarNumberContext ctx) {
      tmpId = ctx.ID(0).getText();
      ctx.varx = newVar();
      ST visit = templates.getInstanceOf("atrib_double");
      if (ctx.expr() != null) {
         visit = visit(ctx.expr());
         idToTmpVar.put(tmpId, ctx.expr().varx);
      }
      else if (ctx.callfunction() != null) {
         visit.add("type", "double");
         visit.add("var", ctx.varx);
         visit.add("value", visit(ctx.callfunction()).render());
         visit.add("id", tmpId);
         if (insideFunc) visit.add("insideFunc", "");
      }
      else if (ctx.ID().size() > 1 && ctx.size == null) {
         visit.add("type", "double");
         visit.add("var", ctx.varx);
         if (ctx.index != null) {
            String index = ctx.index.getText();
            String finalIndex = idToTmpVar.containsKey(index) ? idToTmpVar.get(index) : index;
            visit.add("value", ctx.ID(1).getText()+".get("+"(int)"+finalIndex+")");
         }
         else visit.add("value", ctx.ID(1).getText()+".get("+visit(ctx.random()).render()+")");
         visit.add("needComma", "");
         visit.add("id", tmpId);
         idToTmpVar.put(tmpId, ctx.varx);
         if (insideFunc) visit.add("insideFunc", "");
      }
      else if (ctx.random() != null && ctx.ID().size() < 2) {
         visit.add("type", "double");
         visit.add("var", ctx.varx);
         visit.add("value", visit(ctx.random()).render());
         visit.add("id", tmpId);
         if (insideFunc) visit.add("insideFunc", "");
         visit.add("needComma", "");
         idToTmpVar.put(tmpId, ctx.varx);
      }
      else if (ctx.numberFetches() != null) {
         visit.add("type", "double");
         visit.add("var", ctx.varx);
         visit.add("needComma", "");
         visit.add("value", visit(ctx.numberFetches()).render());
         visit.add("id", tmpId);
         idToTmpVar.put(tmpId, ctx.varx);
      }
      else if (ctx.size != null) {
         visit.add("type", "double");
         visit.add("var", ctx.varx);
         visit.add("value", ctx.ID(1).getText()+".size()");
         visit.add("id", tmpId);
         if (insideFunc) visit.add("insideFunc", "");
         visit.add("needComma", "");
         idToTmpVar.put(tmpId, ctx.varx);
      }
      else {
         visit.add("type", "double");
         visit.add("var", tmpId);
         visit.add("needComma", "");
      }
      varTypes.put(tmpId, "double");
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
         if (ctx.index != null) {
            String index = ctx.index.getText();
            String finalIndex = idToTmpVar.containsKey(index) ? idToTmpVar.get(index) : index;
            atrib.add("value", ctx.ID(1).getText()+".get("+"(int)"+finalIndex+")");
            atrib.add("needComma", "");
         }
         else {
            atrib.add("value", ctx.ID(1).getText()+".get("+visit(ctx.random()).render()+")");
            atrib.add("needComma", "");
         }
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
      atrib.add("type", "Question");
      atrib.add("var", ctx.ID(0).getText());
      if (ctx.ID().size() > 1) {
         if (ctx.index != null) {
            String index = ctx.index.getText();
            String finalIndex = idToTmpVar.containsKey(index) ? idToTmpVar.get(index) : index;
            atrib.add("value", ctx.ID(1).getText()+".get("+"(int)"+finalIndex+")");
         }
         else atrib.add("value", ctx.ID(1).getText()+".get("+visit(ctx.random()).render()+")");
      }
      else {
         atrib.add("isQuestion", "");
      }
      atrib.add("needComma", "");
      varTypes.put(ctx.ID(0).getText(), "Question");
      return atrib;
   }

   @Override public ST visitVarListAdd(QuizParser.VarListAddContext ctx) {
      ST list = templates.getInstanceOf("list_action");
      list.add("id",ctx.ID().getText());
      list.add("action",visit(ctx.add()).render());
      return list;
   }
   

   @Override public ST visitVarListRemove(QuizParser.VarListRemoveContext ctx) {
      ST list = templates.getInstanceOf("list_action");
      list.add("id",ctx.ID().getText());
      list.add("action",visit(ctx.remove()).render());
      return list;
   }

   @Override public ST visitVarListSplit(QuizParser.VarListSplitContext ctx) {
      needsArrays = true;
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
      get.add("getter", ctx.ID(0).getText());
      if(ctx.ID().size() > 1){
         if(ctx.ID().size() > 2){
            String id = ctx.ID(2).getText();
            String idFinal = idToTmpVar.containsKey(id) ? idToTmpVar.get(id) : id;
            get.add("var", idFinal);
            get.add("key", idToTmpVar.get(ctx.ID(1).getText()));
         }
         else{
            String id = ctx.ID(1).getText();
            String idFinal = idToTmpVar.containsKey(id) ? idToTmpVar.get(id) : id;
            get.add("var", idFinal);
            if(ctx.TEXT() != null){
               get.add("key", ctx.TEXT().getText());
            }
            else{
               get.add("key", visit(ctx.questionFetch()).render());
            }
         }
      }
      return get;
   }

   @Override public ST visitVarMapPut(QuizParser.VarMapPutContext ctx) {
      needsMap = true;
      ST put = templates.getInstanceOf("hashMap_put");
      if(ctx.ID().size() > 2){
         put.add("var", ctx.ID(0).getText());
         put.add("key", idToTmpVar.get(ctx.ID(1).getText()));
         String id = ctx.ID(2).getText();
         String idFinal = idToTmpVar.containsKey(id) ? idToTmpVar.get(id) : id;
         put.add("value", idFinal);
      }
      else{
         if(ctx.ID().size() > 1){
            put.add("var", ctx.ID(0).getText());
            if(ctx.questionFetch() != null){
               put.add("key", visit(ctx.questionFetch()).render());
               put.add("value", idToTmpVar.get(ctx.ID(1).getText()));
            }
            else if(ctx.text1 != null){
               put.add("key", ctx.text1.getText());
               put.add("value", idToTmpVar.get(ctx.ID(1).getText()));
            }
            else{
               put.add("key", idToTmpVar.get(ctx.ID(1).getText()));
               if(ctx.NUMBER() != null){
                  put.add("isDouble", "");
                  put.add("value", ctx.NUMBER().getText());
               }
               else if(ctx.text2 != null){
                  put.add("value", ctx.text2.getText());
               }
               else{
                  put.add("value", convertion.get(visit(ctx.bool()).render()));
               }
            }
         }
         else{
            put.add("var", ctx.ID(0).getText());
            if(ctx.questionFetch() != null){
               put.add("key", visit(ctx.questionFetch()).render());
               if(ctx.NUMBER() != null){
                  put.add("isDouble", "");
                  put.add("value", ctx.NUMBER().getText());
               }
               else if(ctx.text2 != null){
                  put.add("value", ctx.text2.getText());
               }
               else{
                  put.add("value", convertion.get(visit(ctx.bool()).render()));
               }
            }
            else if(ctx.text1 != null){
               put.add("key", ctx.text1.getText());
               if(ctx.NUMBER() != null){
                  put.add("isDouble", "");
                  put.add("value", ctx.NUMBER().getText());
               }
               else if(ctx.text2 != null){
                  put.add("value", ctx.text2.getText());
               }
               else{
                  put.add("value", convertion.get(visit(ctx.bool()).render()));
               }
            }
         }
      }
      return put;
   }

   @Override public ST visitVarMapRemove(QuizParser.VarMapRemoveContext ctx) {
      needsMap = true;
      ST remo = templates.getInstanceOf("hashMap_remove");
      if(ctx.ID().size() > 1){
         remo.add("var", ctx.ID(0).getText());
         remo.add("key", idToTmpVar.get(ctx.ID(1).getText()));
      }
      else{
         remo.add("var", ctx.ID(0).getText());
         if(ctx.questionFetch() != null){
            remo.add("key", visit(ctx.questionFetch()).render());
         }
         else if (ctx.TEXT() != null){
            remo.add("key", ctx.TEXT().getText());
         }
      }
      return remo;
   }

   @Override public ST visitVarMapClear(QuizParser.VarMapClearContext ctx) {
      needsMap = true;
      ST clear = templates.getInstanceOf("hashMap_clear");
      clear.add("var", ctx.ID().getText());
      return clear;
   }

   @Override public ST visitAddQuestion(QuizParser.AddQuestionContext ctx) {
      ST add = templates.getInstanceOf("add_atrib");
      add.add("var",visit(ctx.questionFetch()).render());
      add.add("isDouble","");
      return add;
   }

   @Override public ST visitAddText(QuizParser.AddTextContext ctx) {
      ST add = templates.getInstanceOf("add_atrib");
      add.add("var",ctx.TEXT().getText());
      return add;
   } 

   @Override public ST visitAddID(QuizParser.AddIDContext ctx) {
      ST add = templates.getInstanceOf("add_atrib");
      add.add("var",idToTmpVar.get(ctx.ID().getText()));
      return add;   
   } 

   @Override public ST visitAddNumber(QuizParser.AddNumberContext ctx) {
      ST add = templates.getInstanceOf("add_atrib");
      add.add("isDouble","");
      add.add("var",ctx.NUMBER().getText());
      return add;   
   }

   @Override public ST visitRandom(QuizParser.RandomContext ctx) {
      ST rand = templates.getInstanceOf("get_random");
      String min = ctx.min.getText();
      String max = ctx.max.getText();
      String minFinal = idToTmpVar.containsKey(min) ? idToTmpVar.get(min) : min;
      String maxFinal = idToTmpVar.containsKey(max) ? idToTmpVar.get(max) : max;
      rand.add("min", minFinal);
      rand.add("max", maxFinal);
      return rand;
   }

   @Override public ST visitRemoveNumber(QuizParser.RemoveNumberContext ctx) {
      ST remove = templates.getInstanceOf("remove_atrib");
      remove.add("var",ctx.NUMBER().getText());
      return remove;     
   }

   @Override public ST visitRemoveText(QuizParser.RemoveTextContext ctx) {
      ST remove = templates.getInstanceOf("remove_atrib");
      remove.add("var",ctx.TEXT().getText());
      return remove;     
   }

   @Override public ST visitRemoveID(QuizParser.RemoveIDContext ctx) {
      ST remove = templates.getInstanceOf("remove_atrib");
      remove.add("var", "(int)"+idToTmpVar.get(ctx.ID().getText()));
      return remove;    
   } 

   @Override public ST visitQuestionFetch(QuizParser.QuestionFetchContext ctx) {
      if (ctx.questionFetchTitle() != null) return visit(ctx.questionFetchTitle());
      if (ctx.questionFetchAnsRight() != null) return visit(ctx.questionFetchAnsRight());
      if (ctx.questionFetchAnsWrong() != null) return visit(ctx.questionFetchAnsWrong());
      if (ctx.questionFetchDiff() != null) return visit(ctx.questionFetchDiff());
      if (ctx.questionFetchType() != null) return visit(ctx.questionFetchType());
      if (ctx.questionFetchTries() != null) return visit(ctx.questionFetchTries());
      if (ctx.questionFetchPoints() != null) return visit(ctx.questionFetchPoints());
      return null;
   }

   @Override public ST visitQuestionFetchTitle(QuizParser.QuestionFetchTitleContext ctx) {
      return handleQuestionFetch("question_fetch", ctx.ID().getText(), "getTitle()");
   }

   @Override public ST visitQuestionFetchAnsRight(QuizParser.QuestionFetchAnsRightContext ctx) {
      needsArrays = true;
      return handleQuestionFetch("question_fetch", ctx.ID().getText(), "getRightAns()");
   }

   @Override public ST visitQuestionFetchAnsWrong(QuizParser.QuestionFetchAnsWrongContext ctx) {
      needsArrays = true;
      return handleQuestionFetch("question_fetch", ctx.ID().getText(), "getWrongAns()");
   }

   
   @Override public ST visitQuestionFetchDiff(QuizParser.QuestionFetchDiffContext ctx) {
      return handleQuestionFetch("question_fetch", ctx.ID().getText(), "getDifficulty()");
      
   }

   
   @Override public ST visitQuestionFetchType(QuizParser.QuestionFetchTypeContext ctx) {
      return handleQuestionFetch("question_fetch", ctx.ID().getText(), "getType()");
   }

   
   @Override public ST visitQuestionFetchTries(QuizParser.QuestionFetchTriesContext ctx) {
      return handleQuestionFetch("question_fetch", ctx.ID().getText(), "getTries()");
   }

   
   @Override public ST visitQuestionFetchPoints(QuizParser.QuestionFetchPointsContext ctx) {
      return handleQuestionFetch("question_fetch", ctx.ID().getText(), "getPoints()");
   }

   private ST handleQuestionFetch(String template, String id, String type) {
      ST questionFetch = templates.getInstanceOf(template);
      questionFetch.add("id", id);
      questionFetch.add("type", type);
      return questionFetch;
   }
   
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

      if (ctx.writetype.getText().equals("writeln")) print.add("ln", "ln");

      return print;
   }

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

   @Override public ST visitForIn(QuizParser.ForInContext ctx) {
      ST forLoop = templates.getInstanceOf("for_in");
      ctx.varx = newVar();
      String id = ctx.ID(0).getText();
      String idVar = ctx.type.getText().equals("question") ? id : ctx.varx;
      forLoop.add("var", idVar);
      forLoop.add("type", convertion.get(ctx.type.getText()));
      switch (ctx.type.getText()) {
         case "number":
            forLoop.add("double", "");
            break;
         case "boolean":
            forLoop.add("boolean", "");
            break;
         case "question":
            forLoop.add("question", "");
            break;
      }
      varTypes.put(id, "String");
      idToTmpVar.put(id, ctx.varx);
      forLoop.add("var1", id);
      forLoop.add("var2", ctx.ID(1).getText());
      for (QuizParser.ContentContext content : ctx.content()) {
         forLoop.add("stat", visit(content).render());
      }
      return forLoop;
   }
  
   @Override public ST visitForTo(QuizParser.ForToContext ctx) {
      ST forLoop = templates.getInstanceOf("for_loop");
      ctx.varx = newVar();
      forLoop.add("var", ctx.varx);
      
      String id = ctx.ID(0).getText();
      forLoop.add("id", id);
      varTypes.put(id, "double");
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

      forLoop.add("comp", ctx.direction.getText());

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
 
   @Override public ST visitDoaslong(QuizParser.DoaslongContext ctx) {
      ST doLoop = templates.getInstanceOf("do_while");
      for (QuizParser.ContentContext cont : ctx.content()) {
         doLoop.add("stat", visit(cont).render());
      }
      doLoop.add("condition", visit(ctx.conditional()).render());
      return doLoop;
   }

   @Override public ST visitAslong(QuizParser.AslongContext ctx) {
      ST whileLoop = templates.getInstanceOf("while");
      whileLoop.add("condition", visit(ctx.conditional()).render());
      for (QuizParser.ContentContext cont : ctx.content()) {
         whileLoop.add("stat", visit(cont).render());
      }
      return whileLoop;
   }

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

   @Override public ST visitElsif(QuizParser.ElsifContext ctx) {
      ST elseif = templates.getInstanceOf("elsifstat");
      elseif.add("condition", visit(ctx.conditional()).render());
      for (QuizParser.ContentContext cont : ctx.content()) {
         elseif.add("stat", visit(cont).render());
      }
      return elseif;
   }

   @Override public ST visitFinalCond(QuizParser.FinalCondContext ctx) {
      ST ifcond = templates.getInstanceOf("return_plain_val");
      int size = ctx.content().size();
      for (int i = 0; i < size-1; i++) {
         ifcond.add("val", visit(ctx.content(i)).render()+"\n");
      }
      ifcond.add("val", visit(ctx.content(size-1)).render());
      return ifcond;
   }

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

   
   @Override public ST visitCallParams(QuizParser.CallParamsContext ctx) {
      ST callp = templates.getInstanceOf("return_plain_val");
      String val = ctx.val.getText();
      String valFinal = idToTmpVar.containsKey(val) ? idToTmpVar.get(val) : val;
      callp.add("val", valFinal);
      return callp;
   }

   
   @Override public ST visitConditional(QuizParser.ConditionalContext ctx) {
      ST cond = templates.getInstanceOf("conditional");
      if (ctx.id != null) {
         String[] content = ctx.getText().split("T");
         // then there is a NOT
         if (content.length > 1) {
            cond.add("not", "!");
         }
         cond.add("id", "vars.get("+ctx.ID(0).getText()+")");
      }
      else if (ctx.field1 != null) {
         String f1 = ctx.field1.getText();
         String f2 = ctx.field2.getText();
         if (varTypes.get(f1).equals("String") && varTypes.get(f2).equals("String")) {
            cond.add("equals", ".equals");
         }
         else {
            cond.add("op", convertion.get(ctx.op.getText()));
         }
         if (!isNumber(ctx.field1.getText())) {
            if (varTypes.get(f1).equals("double"))
               f1 = "Double.parseDouble(vars.get(\""+ctx.field1.getText()+"\"))";
            else
               f1 = "vars.get(\""+ctx.field1.getText()+"\")";
         }
         if (!isNumber(ctx.field2.getText())) {
            if (varTypes.get(f2).equals("double"))
               f2 = "Double.parseDouble(vars.get(\""+ctx.field2.getText()+"\"))";
            else
               f2 = "vars.get(\""+ctx.field2.getText()+"\")";
         }
         cond.add("s1", f1);
         cond.add("s2", f2);
      }
      else {
         if (ctx.op.getText().equals("!=")) cond.add("not", "!");
         if (ctx.field3 != null) {
            String f3 = ctx.field3.getText();
            String finalf3 = idToTmpVar.containsKey(f3) ? "vars.get(\""+f3+"\")" : f3;
            cond.add("s1", finalf3);
         }
         else {
            cond.add("s1", visit(ctx.stringFetches(0)).render());
         }

         if (ctx.field4 != null) {
            String f4 = ctx.field4.getText();
            String finalf4 = idToTmpVar.containsKey(f4) ? "vars.get(\""+f4+"\")" : f4;
            cond.add("s2", finalf4);
         }
         else {
            if (ctx.stringFetches().size() > 1)
               cond.add("s2", visit(ctx.stringFetches(1)).render());
            else
               cond.add("s2", visit(ctx.stringFetches(0)).render());
         }

         cond.add("equals", ".equals");
      }
      return cond;
   }

   @Override public ST visitStringFetches(QuizParser.StringFetchesContext ctx) {
      if (ctx.questionFetchDiff() != null) return visit(ctx.questionFetchDiff());
      else if (ctx.questionFetchTitle() != null) return visit(ctx.questionFetchTitle());
      else return visit(ctx.questionFetchType());
   }

   @Override public ST visitNumberFetches(QuizParser.NumberFetchesContext ctx) {
      if (ctx.questionFetchTries() != null) return visit(ctx.questionFetchTries());
      else return visit(ctx.questionFetchPoints());
   }

   
   @Override public ST visitVarManipPlus(QuizParser.VarManipPlusContext ctx) {
      return handleVarManip("varmanip_front", "++", ctx.ID().getText());
   }

   
   @Override public ST visitVarManipMinus(QuizParser.VarManipMinusContext ctx) {
      return handleVarManip("varmanip_front", "--", ctx.ID().getText());
   }

   
   @Override public ST visitVarManipPrePlus(QuizParser.VarManipPrePlusContext ctx) {
      return handleVarManip("varmanip_back", "++", ctx.ID().getText());
   }

   
   @Override public ST visitVarManipPreMinus(QuizParser.VarManipPreMinusContext ctx) {
      return handleVarManip("varmanip_back", "--", ctx.ID().getText());
   }

   private ST handleVarManip(String template, String action, String id) {
      ST manip = templates.getInstanceOf(template);
      if (insideFunc) manip.add("insideFunc", "");
      manip.add("var", idToTmpVar.get(id));
      manip.add("action", action);
      manip.add("id", id);
      return manip;
   }

   
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

   
   @Override public ST visitExprNumber(QuizParser.ExprNumberContext ctx) {
      ST res = templates.getInstanceOf("atrib_double");
      if (insideFunc) res.add("insideFunc", "");
      ctx.varx = newVar();
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

   
   @Override public ST visitContent(QuizParser.ContentContext ctx) {
      if (ctx.list() != null) return visit(ctx.list());
      if (ctx.var() != null) return visit(ctx.var());
      if (ctx.write() != null) return visit(ctx.write());
      if (ctx.forLoop() != null) return visit(ctx.forLoop());
      if (ctx.ifCond() != null) return visit(ctx.ifCond());
      if (ctx.doaslong() != null) return visit(ctx.doaslong());
      if (ctx.aslong() != null) return visit(ctx.aslong());
      if (ctx.callfunction() != null) return visit(ctx.callfunction());
      if (ctx.varmanipulation() != null) return visit(ctx.varmanipulation());
      if (ctx.map() != null) return visit(ctx.map());
      if (ctx.getText().contains("BREAK")) {
         ST template = templates.getInstanceOf("return_plain_val");
         template.add("val","break;");
         return template;
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
