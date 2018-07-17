var express = require('express');
var shajs = require('sha.js');

var router = express.Router();

var Message = require('../models/message');

/* POST to create messages. */
router.post('/', function(req, res, next) {

  var postedMessage = req.body.message;

  var newMessage = new Message();

  var digest = shajs('sha256').update(postedMessage).digest("hex");

  newMessage._id = digest;
  newMessage.message = postedMessage

  newMessage.save(function(err) {
    
    if (err) {
    	if(err.code === 11000) {
			res.json({"error" : "That message already exists"});
    	} else {
    		res.send(err);
    	}
    }

    else
    	res.json({ "digest": digest });
   });

});

/* GET messages. */
router.get('/:digest', function(req, res, next) {
  var digest = req.params.digest;

  Message.findById(digest, {message : 1, _id : 0}, function(message, err) {

	if (err) {
		res.send(err);
	}
	else {

		if(message) {
			res.json(message);
		}

		else {
			res.status(404).send({"err_msg" : "Message Not Found"});
		}
	}
  });
});

module.exports = router;