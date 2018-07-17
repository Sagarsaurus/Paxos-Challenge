var mongoose	= require('mongoose');
var Schema       = mongoose.Schema;

const MessageSchema   = new Schema({
    _id: String,
    message: String
});

module.exports = mongoose.model('Message', MessageSchema);