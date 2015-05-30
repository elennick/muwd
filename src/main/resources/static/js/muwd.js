jQuery(function($, undefined) {
    $('#termDemo').terminal(function(command, term) {
        if (command !== '') {
            try {
                var result = window.eval(command);
                if (result !== undefined) {
                    term.echo(new String(result));
                }
            } catch(e) {
                term.error(new String(e));
            }
        } else {
            term.echo('');
        }
    }, {
        greetings: 'Welcome to the Multi User Web Dungeon.',
        name: 'MUWD',
        height: 480,
        width: 640,
        prompt: '> '
    });
});