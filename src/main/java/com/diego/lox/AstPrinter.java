package com.diego.lox;

public class AstPrinter implements Expr.Visitor<String> {

    // just for test
    public static void main(String[] args) {
        Expr leftExpression = new Expr.Unary(
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(123)
        );
        Token operator = new Token(TokenType.STAR, "*", null, 1);
        Expr rightExpression =  new Expr.Grouping(new Expr.Literal(45.67));
        Expr expression = new Expr.Binary(leftExpression, operator, rightExpression);

        System.out.println(new AstPrinter().print(expression));
        // (* (- 123) (group 45.67))
    }


    public String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for(var expr : exprs) {
            builder.append(" ").append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
}
