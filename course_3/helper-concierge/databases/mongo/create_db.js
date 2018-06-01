var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/concierge');
 
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {});
 
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

/*--------------------users--------------------*/
 
var UserSchema = new Schema({
    name: String,
    age: Number,
    family: {
        spouse: {
	        name: String,
	        status: String,
	        relationships: String
	    	},
		children: [ {
			name: String,
			age: Number,
			relationships: String
	    	} ],
		mother: {
			name: String,
			relationships: String
	    	},
		father: {
			name: String,
			relationships: String
	    	}
    },
    profession: String,
    reservations: [ {
    	place: {type: Schema.Types.ObjectId, ref: 'Place'},
		time: Date,
		number_of_persons: Number
    } ],
    ordering_food: [ {
    	place: {type: Schema.Types.ObjectId, ref: 'Place'},
		time: Date,
		address: String,
		dish: String
    } ],
    alarm_clock: [ Date ],
    timetable: [ {
		time: Date,
		action: String,
		importance: Number
    } ]
});
var User = mongoose.model('User', UserSchema);

/*-----------------reservations-----------------*/
 
var PlaceSchema = new Schema({
    name: String,
    address: String,
    phone: String,
    rating: Number
});
var Place = mongoose.model('Place', PlaceSchema);
 
/*-------------------inserts-------------------*/

var place = new Place();
place.name = "dodo-pizza";
place.address = "moskovskaya, 16";
place.phone = "9602603026";
place.rating = 4.5;
place.save();

var user = new User();
user.name = 'sonya';
user.age = 21;
user.profession = "president";
user.reservations.push({ place: place._id, time: new Date(2017, 10, 10), number_of_persons: 3});
user.reservations.push({ place: place._id, time: new Date(2018, 11, 11), number_of_persons: 20});
user.save();

