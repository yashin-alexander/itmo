var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost/concierge')
mongoose.connection.once('connected', () => {
        mongoose.connection.db.dropDatabase();
});

