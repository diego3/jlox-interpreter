package com.diego.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Lox {
    static boolean hadError = false;
    static boolean hadRuntimeError = false;
    private static final Interpreter interpreter = new Interpreter();

    public static void main(String[] args) throws IOException {
        runPrompt();
    }

    private static void runPrompt() throws IOException {
        var input = new InputStreamReader(System.in);
        var reader = new BufferedReader(input);
        for(;;) {
            if (hadError) System.exit(65);
            //if (hadRuntimeError) System.exit(70); // TODO should exit only if from file

            System.out.print("> ");
            var line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
            hadRuntimeError = false;
        }
    }

    private static String runFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Path.of(fileName)));
    }

    private static void run(String source) {
        var scanner = new Scanner(source);
        var tokens = scanner.scanTokens();
        //tokens.forEach((t) -> System.out.println(t.toString()));
        var parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        if (hadError) return;

        //System.out.println(new AstPrinter().print(expression));

        interpreter.interpret(statements);
    }

    public static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '"+token.lexeme+ "'", message);
        }
    }

    public static void report(int line, String where, String message) {
        System.err.printf("[line %d] Error %s : %s\n", line, where, message);
        hadError = true;
    }

    public static void runtimeError(RuntimeError e) {
        hadRuntimeError = true;
        error(e.getToken(), e.getMessage());
    }
}
