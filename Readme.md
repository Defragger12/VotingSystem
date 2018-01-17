API:

GET http://localhost:8080/restaurants/
get all restaurants (with voteCounts, sorted by VoteCount)

GET http://localhost:8080/restaurants/{id}
get restaurant with specific id

DELETE http://localhost:8080/admin/restaurants/{id}
delete restaurant with specific id

PUT http://localhost:8080/admin/restaurants/{id}
update restaurant with specific id

POST http://localhost:8080/admin/restaurants
create new restaurant

GET http://localhost:8080/restaurants/{id}/menu
get menu of a restaurant with specific id

POST http://localhost:8080/admin/restaurants/{id}/menu
refresh menu of a restaurant with specific id

PUT http://localhost:8080/restaurants/{id}/vote
perform vote operation for a restaurant with specific id

TEST commands:

>User & Admin commands

#### get All Restaurants (with voteCounts, sorted by VoteCount)
curl -s http://localhost:8080/restaurants/ --user User:user123

#### get Restaurant 100001
curl -s http://localhost:8080/restaurants/100001 --user User:user123

#### get menu of Restaurant 100001
curl -s http://localhost:8080/restaurants/100001/menu --user User:user123

#### vote for Restaurant 100001
curl -s -X PUT http://localhost:8080/restaurants/100001/vote --user User:user123

>Admin commands only

#### delete Restaurant 100001 (with menu)
curl -s -X DELETE http://localhost:8080/admin/restaurants/100001 --user Admin:admin123

#### update Restaurant 100001
curl -s -X PUT -d '{"name":"updated name"}' -H 'Content-Type: application/json' http://localhost:8080/admin/restaurants/100001 --user Admin:admin123

#### create Restaurant
curl -s -X POST -d '{"name":"new name"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/admin/restaurants --user Admin:admin123

#### refresh menu (replace with new one) of Restaurant 100001
curl -s -X POST -d '[{"name":"food1", "price":"500"}, {"name":"food2", "price":"300"}]' -H 'Content-Type: application/json' http://localhost:8080/admin/restaurants/100001/menu --user Admin:admin123

#### delete menu of Restaurant 100000
curl -s -X DELETE http://localhost:8080/admin/restaurants/100000/menu --user Admin:admin123






