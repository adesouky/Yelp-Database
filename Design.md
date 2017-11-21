Design Meeting Review

Methods:
	-create: creates an entry in the database 
	-update: updates an existing entry in the database
	-delete: deletes an entry in the database
	-read: reads an entry from the database
	
The Data Type we will use is a map
Each restaurant will be mapped to a list of reviews
Restaurant -> Reviews 

Reviews will have a field called user
	
Restaurant, Review, and User will each have its own class, as well as the database having its own class.
	
Restaurant fields will be: longitude, latitude, photoID,  neighborhood, open or closed, yelp link, business ID, city, review count, name, phone number, overall stars, type of food category, price range
 
Review fields will be: votes, ReviewID, text, date, stars, user
	
User fields will be: URL, votes, average stars, name, userID, photoID, location, list of all reviews, #friends, #reviews, #photos, Eliete ‘17(a category classifying a specific type of user on yelp)
