# sakila-springboot-rest

ENDPOINTS

/films - get/ post/put
/films/{filmID}	- get/delete
/films/actors/{actorId} -	get films by actor
/films/categories/{categoryId} -	get films by category
/films/search - get films by title

/actors	- get/post/put
/actors/{actorId} - get/delete
/actors/films/{filmId} - get actors by film
/actors/search – get actors by first name/ last name

/customers – get/post/put (post, put – if address id is null, create new)
/customers/{customerId} – get/delete
/customers/search – get customers by first name/last name

/inventory – get/post/put
/inventory/{inventoryId} – get/delete

/rentals – get/post/put
/rentals/{rentalId} – get/delete
/rentals/customers/{customerId} – get rentals by customer id
/payments – get/post/put
/payments /{rentalId} – get/delete

/staff – get/post/put
/staff/{staffId} – get/delete
/stores – get/post/put
/stores/{storeId} – get/delete
