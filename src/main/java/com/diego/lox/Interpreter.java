package com.diego.lox;

//https://craftinginterpreters.com/evaluating-expressions.html#evaluating-unary-expressions
public class Interpreter implements Expr.Visitor<Object> {


    public void interpret(Expr expression) {

    }

    private String stringify(Object object) {

        return "";
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        return null;
    }
}
