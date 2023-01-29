db.users.createIndex(
	{
		"email": 1
	},
	{
		unique: true
	})

db.users.createIndex(
	{
		"id": 1
	},
	{
		unique: true
	})


db.users.insertOne({id: "dsevilla", 
	email: "dsevilla@um.es",
	password_hash: "21232f297a57a5a743894a0e4a801fc3",
	name: "diego",
	token: "TOKEN",
	visits: 0
	});
