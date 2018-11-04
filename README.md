# YELP DATABASE [![Build Status](https://travis-ci.com/adesouky/Yelp-Database.svg?branch=master)](https://travis-ci.com/adesouky/Yelp-Database) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/6daa4114453346529a5c478e441a2201)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=CPEN-221/f17-mp5-megan-and-anas&amp;utm_campaign=Badge_Grade)



Restaurants, Queries and Statistical Learning
===

This machine problem is designed to allow you to explore multiple aspects of software construction:
+ managing complex ADTs;
+ multithreading and the client-server pattern;
+ query parsing and execution.

In addition to these aspects, the problem also touches upon rudimentary methods for statistical inference and learning.

### Background

You will use the dataset to create and maintain a simple in-memory database with restaurants, users and reviews. (Since the Yelp Academic Dataset does not contain details of business near UBC we are using information for restaurants near UC Berkeley or UCB!)


### Part I: A Database as a Datatype

The first part of this machine problem is to build a datatype (`YelpDB`) that represents Yelp's restaurant dataset.

You should design this datatype to support a variety of useful operations. You have to decide on the representation for this datatype keeping extensibility (the ability to add new features and operations) in mind.

### Part II: Statistical Learning

In this part of the machine problem you will implement two approaches to statistical machine learning: one is an instance of unsupervised learning and the second is an instance of supervised learning. Statistical learning is an exciting area for computing today!

The two operations that you have to support are also part of the `MP5Db` interface.

#### k-means Clustering

Suppose you are given a set of (x, y) coordinates, you may sometimes want to group the points into _k_ clusters such that no point is closer to the center point (centroid) of a cluster other than the one to which it is assigned. In the case of restaurants, this approach may allow us to group restaurants that are in the same neighbourhood even without knowing anything about the neighbourhoods in a city. _A similar approach is used to group similar products on online shopping services such as Amazon._


#### Least Squares Regression

As an instance of supervised learning, you will implement an algorithm for predicting the rating that a user may give to a restaurant.

By analyzing a user's past ratings, we can try to predict what rating the user might give to a new restaurant.


### Part III: A YelpDB Server

In the next part of this machine problem, you should implement a multi-threaded server application, `YelpDBServer` that wraps a `YelpDB` instance.


### Part IV: Handling Simple Requests

The server should be able to handle some simple requests from a client that connects to it.


### Part V: Structured Queries

The final part of this machine problem is to support structured queries over the database you have constructed. The request-response pattern will be handled by the `RestaurantDBServer` as was the case with "simple" requests earlier.

We would like to process queries such as "list all restaurants in a neighbourhood that serve Chinese food and have moderate ($$) price."

