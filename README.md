# news-subscription
simple application to receive news from subscribed channels

### Tech stack
* It is Java 8/Spring boot application
* Used Spring data JPA to persist data
* Used MQTT for external news publish/subscription
* Used Web sockets to publish news to Web client

### Execution flow
* This single application is responsible for
** Hardcoded user and his subscription
** 1. Publish dummy news message every 30 seconds
** 2. Same application subscribes to the news message which is publish above
** 3. Save every news back into our DB
** 4. Publish the news message back to Web client using Web socket

### Assumptions
* Going for monolithic approach for this sample application 
* Using H2 db
* On application start, I create User and User news subscriptions
* For now, UI/Web client is receives the news after every 30 seconds
* No User registration 
* No security considerations

### If time given, I would design as following
* News Stream Service
** Pull users who is subscribed to incoming news channel/topic
** Send the news to all the Users(Web socket)
** Use **News Repository SDK** to persist news
** Use **User Repository SDK** to pull users who are subscribed to channel/topic
* News Persistent Service
** Only responsibility is to persist the news using **News Repository SDK** 
* User Repository SDK
** user repo as sdk is responsible for User and User Subscription db operations
* News Repository SDK
** News repo as sdk is responsible for news db operations
* News Web backend
** Exposes REST apis
* News Web client
** Web client/ GUI for end users
** This communicates with the **News Web backend**

### How to run application 
*

### Question 1: How would you design such a system
If I get a enough time, my design goes as follows
Please section  **If I given time, I would design as following**

### Question 2: JPA and DB: given that the information is stored in an SQL database.
For database transactions there are different isolation levels? What is the reasoning behind it. Why would you choose one over the other. What would be a typical use case given the scenario above? 
My understanding on isolation is : it is like handling concurrency at DB level. There are multiple levels, READ UNCOMMITTED, READ COMMITTED, REPEATABLE READ
In this current application scenario, once the news received by our application, to send to all appropriate users, 
we need to fetch all users subscribed by news channel based on incoming news
Here we should use READ COMMITTED so that we can protect against update User subscription. 

What is an optimistic lock exception in JPA? 
This exception comes when we are trying to update the record which is not the latest opt lock version object in DB.

What is a stale object, what is a detached object? 
Transient: An entity is transient if it has just been instantiated using the new operator, and it is not associated with a EntityManager.
Persistent: An entity that is associated with Entitymanager
Detached: Persisted entity but not associated to Entitymanager


### Question 3: As the business grows, there are many more users. How could you scale such a system? Where would you store the user session data?
I would suggest we do not use session at all. Maintaining a session would cause hard time with the scalability to manage sessions across the clusters! 
Instead we can use JWT(JSON Web token) to transmit the user state in every request

### Question 4:  Since news will not change after a while, how would you handle with news that are older than 1 year to control the capacity of the database? How does this affect your persistence logic?
