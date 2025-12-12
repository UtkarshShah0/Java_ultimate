const express = require('express');
const app = express();

app.get('/', (req, res) => {

	res.send("welcome to Express")


});

app.listen(3000, function(){
	console.log("Express is listening on port 3000")
});
