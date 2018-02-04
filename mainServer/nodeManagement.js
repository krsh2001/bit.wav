// file ID server

// imports

var fs = require('fs');
var http = require('http');
var https = require('https');
var express = require('express');
var mysql = require('mysql');

var options = {key: fs.readFileSync('credentials/key.pem', 'utf8'), cert: fs.readFileSync('credentials/cert.pem', 'utf8'), passphrase: 'bassword'};
var app = express();
var router = express.Router();

// restful API setup

// setup mysql
var connection = mysql.createConnection({
	host:'127.0.0.1',
	user:'webapp',
	password:'bassword',
	database:'bitwav'
});

connection.connect(function(err) {
	if (err) {
		console.error('error connecting: ' + err.stack);
		return;
	}

	console.log('connected as id ' + connection.threadId);
});

// setup routing

app.use(function(req, res, next) {
	console.log('Connection made.');
	next();
});

router.get('/file/:fileinfo', function(req, res) {
	connection.query('select * from fileSummaries where name = \'' + req.params.fileinfo + '\'', function(error, result) {
		if (error) {
			throw error;
		}

		res.send(result);
	});
});

router.get('/ips/:file', function(rec, res) {
	connection.query('select ip from activeips where id = \'' + rec.params.file + '\'', function(error, result) {
		if (error) {
			throw error;
		}

		res.send(result);
	});
});

app.use('/', router);

var httpServer = http.createServer(app);
var httpsServer = https.createServer(options, app);

httpServer.listen(8999);
httpsServer.listen(8443);
