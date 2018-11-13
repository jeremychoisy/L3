var express = require('express');

var app = express();

app.get('/', function(req, res) {
    res.render('form.ejs');
});

app.listen(8080);
