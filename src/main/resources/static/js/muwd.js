jQuery(function($, undefined) {

    var MAX_COMMAND_LENGTH = 1024;

    var term = $('#termDemo').terminal(function(command, term) {
        if (command !== '') {
            if(command.length > MAX_COMMAND_LENGTH) {
                command = command.substring(0, MAX_COMMAND_LENGTH);
            }

            stompClient.send('/app/command', {}, JSON.stringify({'command': command}));
        } else {
            term.echo('');
        }
    }, {
        greetings: 'Welcome to the Multi User Web Dungeon. Connecting to server...',
        name: 'MUWD',
        height: 600,
        width: 1024,
        prompt: '> '
    });

    var socket = new SockJS('/command');
    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        echoRaw('<span style="color: green;">Connected!</span><br/>');

        //global topic
        stompClient.subscribe('/topic/message', function(message) {
            echoContent(message);
        });

        //user specific topic
        stompClient.subscribe('/user/topic/message', function(message){
            echoContent(message);
        });
    });

    var getResponseContent = function(message) {
        return JSON.parse(message.body).content;
    };

    var echoContent = function(message) {
        var content = getResponseContent(message);
        echoRaw(content);
    };

    var echoRaw = function(message) {
        term.echo(message, { raw: true });
    }
});