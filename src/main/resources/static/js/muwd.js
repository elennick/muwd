jQuery(function($, undefined) {

    var MAX_COMMAND_LENGTH = 1024;
    var clientUUID;

    var term = $('#termDemo').terminal(function(command, term) {
        if (command !== '') {
            if(command.length > MAX_COMMAND_LENGTH) {
                command = command.substring(0, MAX_COMMAND_LENGTH);
            }

            if(clientUUID) {
                stompClient.send('/app/command', {}, JSON.stringify({'command': command}));
            }
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
        term.echo('<span style="color: green;">Connected!</span><br/>', { raw: true });
        term.echo('Logging in....');

        stompClient.subscribe('/topic/message', function(message) {
            var content = getResponseContent(message);
            term.echo(content, { raw: true });
        });

        stompClient.subscribe('/topic/loginresponse', function(message) {
            clientUUID = getResponseContent(message);
            console.log("client UUID set -> " + clientUUID);
            term.echo('<span style="color: green;">Logged in!</span><br/>', { raw: true });
        });

        stompClient.send('/app/login', {}, {});
    });

    var getResponseContent = function(message) {
        return JSON.parse(message.body).content;
    }
});