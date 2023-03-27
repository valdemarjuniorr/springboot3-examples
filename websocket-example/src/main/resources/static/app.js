var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    // same as set in websocket.stomp-endpoint property in application.yml
    var socket = new SockJS('/websocket-server');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        // same as set in @SendTo annotation in NotificationController.notify method.
        stompClient.subscribe('/notify/message', function (notification) {
            showNotification(JSON.parse(notification.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send('/app/topic', {}, JSON.stringify({'name': $("#name").val(), 'message': $("#message").val()}));
}

function showNotification(notification) {
    $("#messages").append("<tr><td>" + notification.name + "</td><td>" + notification.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});