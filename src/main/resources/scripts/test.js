/**
 * Example of scripting system
 */

var metadata = {
    "help": "this is a test script",
};

var helpText = {
    "test": "prints a test message from a script",
    "respond": "keeps track of the number of times called",
    "respond": "echo the user's message back",
};

var actions = {
    "hello": hello,
    "respond": respond,
    "test": test
};

function test() {
    return "Hello, from script file!";
}

var called = 0;

function hello(){
    called++;
    return "I have been called "+called+" times!";
}

function respond(message){
    called++;
    return "You said: "+message.getArgument(2);
}