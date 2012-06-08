/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.unitycoders.pircbotx.commands;

import java.util.Stack;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 *
 * @author webpigeon
 */
public class CalcCommand extends ListenerAdapter<PircBotX> {
    private static final Integer OP_TOKEN = 1;
    private static final Integer NUM_TOKEN = 2;

    private int doExpr(int left, char op, int right){
        switch(op){
            case '*':
                return left * right;

            case '/':
                return left / right;

            case '+':
                return left + right;

            case '-':
                return left - right;

            case '%':
                return left % right;
        }

        return -1;
    }

    private int doNum(Token num){
        return Integer.parseInt(num.data);
    }

    private char doOp(Token op){
        return op.data.charAt(0);
    }

    private int doStmt(Stack<Token> input){
        Token t = input.pop();
        System.out.println("token = "+t.data);
        if(t.type == NUM_TOKEN){
            return doNum(t);
        }

        if(t.type == OP_TOKEN){
            char op = doOp(t);
            int right = doStmt(input);
            int left = doStmt(input);
            return doExpr(left, op, right);
        }

        return -1;
    }

    private Stack<Token> tokenise(String input){
        String[] tokens = input.split(" ");
        Stack<Token> stack = new Stack<Token>();

        for(int i=0; i<tokens.length; i++){
            Token t = new Token();
            t.data = tokens[i];

            if(tokens[i].matches("\\d+")){
                t.type = NUM_TOKEN;
            }

            if(tokens[i].matches("[+*/%-]")){
                t.type = OP_TOKEN;
            }

            if(t.type == null){
                throw new RuntimeException("illegal token "+tokens[i]);
            }

            stack.push(t);
        }

        return stack;
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event) throws Exception{
        String msg = event.getMessage();
        if(msg.startsWith("!calc ")){
            try{
                msg = msg.substring(6);
                event.respond(msg+" = "+parse(msg));
            }catch(Exception ex){
                event.respond(ex.getLocalizedMessage());
            }
        }
    }

    public int parse(String input){
        Stack<Token> tokens = tokenise(input);
        return doStmt(tokens);
    }

    class Token{
        Integer type;
        String data;
    }

}