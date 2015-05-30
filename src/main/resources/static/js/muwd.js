jQuery(function($, undefined) {

    var term = $('#termDemo').terminal(function(command, term) {
        if (command !== '') {
            stompClient.send("/app/command", {}, JSON.stringify({'text': command}));
        } else {
            term.echo('');
        }
    }, {
        greetings: 'Welcome to the Multi User Web Dungeon.',
        name: 'MUWD',
        height: 600,
        width: 1024,
        prompt: '> '
    });

    var socket = new SockJS('/command');
    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
//        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/message', function(message) {
//            console.log(JSON.parse(message.body).content);
            var content = JSON.parse(message.body).content;
            term.echo(content);
        });
    });
});