# Language for Quiz Compiler and Questions Interpreter
Project of **Linguagens Formais e Autómatos**, made by [Beatriz Rodrigues](https://github.com/bea-rodrigues), [Bruno Sá Lopes](https://github.com/Bruno-SLopes), [Daniel Correia](https://github.com/danielvcorreia), [Diogo Correia](https://github.com/digas99), [Lara Rodrigues](https://github.com/Lararodrigues1).
This project uses **ANTLR4** with **Java** to create an Interpreter and a Compiler.
## Interpreter
The Interpreter is used to fetch questions data from a file with the extention *.qst*. This data is meant to be used by the compiler to create a Quiz.
 
## Compiler
The Compiler builds a Quiz according to the *.quiz* file written and the questions fetched from the *.qst* file.

## Report and documentation [here](REPORT.pdf)

## Setup
Depending on what is the user's approach towards this project, different things can be done.

If the user wants to create a **Quiz**, then they should work with the Quiz oriented language, written on a *.quiz* file.

If the Quiz is already created and ready to be fed with some **Questions**, then the Questions oriented language, written on a *.qst* file, will allow a description of each question in terms of right and wrong answers, points, and more.

If both *.qst* and *.quiz* files already exist, then follow the steps below.

It is important that the files for both languages are inside the correct directories, **/questions** for the *.qst* and **/quiz** for the *.quiz*, and that the **Compiler** and the **Interpreter** are both inside the directory **/source**.

To run a *.quiz* file you should write the following commands in the Terminal:
- To compile the Quiz file:
```
java QuizMain namefile.quiz NameForOutputJavaClass
```
- To compile the Java file that resulted from previous compilation:
```
javac NameForOutputJavaClass.java
```
- To run the Java file which contains the final version of the Quiz:
```
java NameForOutputJavaClass
```
