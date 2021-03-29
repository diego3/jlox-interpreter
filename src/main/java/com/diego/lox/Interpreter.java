package com.diego.lox;

public class Interpreter implements Expr.Visitor<Object> {


    public void interpret(Expr expression) {

    }

    private String stringify(Object object) {

        return "";
    }

    private Object evaluate(Expr expression) {


        return null;
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return null;
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return null;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        return null;
    }
}
