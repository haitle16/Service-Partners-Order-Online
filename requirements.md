# Software Requirements

## Vision

### Project Vision: 
This project is a prototype ordering site for Service Partners branch 2007. With it, customers will be able to place orders remotely, and without the assistance of a sales representative. They will also be able to browse the items available to them.

### Pain Points:
This product will save the sales reps time, by allowing customers to place orders independently and prevent backlog when there are more orders being placed than sales reps available. It will also serve as a way to track that orders are being entered correctly by providing a confirmation of exactly what the customer input for their order.

### Why It Matters:
The ordering process can be cumbersome for customers and salespeople. Without official record keeping customers and reps are unable to keep track of orders. Additionally, not having a digital system is very time and resource intensive for the employees who have to take orders over the phone and manually enter/track them.

## Scope (In/Out)
### IN - 
- The web app will provide a way to keep track of all orders a customer makes in a database.
- The web app will allow users to place orders independently, without the need for inside sales assistance.
- The web app will allow customers to select individual items from order and edit their orders.
- The web app will allow customers to place order at any time (even outside of business hours)
- The web app will email customers a record of their order for confirmation.

### OUT - 
This app will never perform payment transactions.

### Minimum Viable Product
What will your MVP functionality be?
- A customer can create an account and log in (require a customer id#)
- A customer can add an item to an order or “cart”
- A customer can update an item in their cart (update qty)
- A customer can remove an item from their cart
- A customer can ‘checkout’
- About us page

What are your stretch goals?
- Web responsive design (mobile first) 
- A customer can share their items in the cart 
- A customer can add things to a wishlist (update, delete etc) 
- An admin can log in
- An admin can add item codes
- An admin can edit and delete item codes
- An admin can update who is receiving orders (can add additional email recipients, remove email recipients, etc.)
- Style using bootStrap
- Customer details page where they can update their address etc
- Customer order history page 
- Customer order history page has reorder feature 
- Search for items by code or keyword
- Drop down menu of item categories 
- Filter search results by price or other item parameter
- Adding an item to the cart multiple times updates the order count instead of duplicating the entire item entry in the cart
- A customer can add/save a credit card
- A sales rep can see the customer credit card to charge
- A search engine or radio box that list all the elbows for downspouts
- Import/Export order data through excel
- Upon checkout their order (items and quantities) will be send in an email to a salesperson


## Stretch
What stretch goals are you going to aim for?
- Upon checkout their order (items and quantities) will be send in an email to a salesperson
- Web responsive design (mobile first) 
- Style using bootStrap
- An admin can log in
- An admin can add item codes
- An admin can edit and delete item codes
- A search engine or radio box that list all the elbows for downspouts

## Functional Requirements
- A customer can make an account and login/logout
- A customer can add, update and delete items in a cart


### Data Flow
Once the user arrives at the site they will be prompted to log in or sign up. Once either event occurs they will be rerouted to the /products page. All items will be displayed on the products page, the user can then click on individual items to see it's details. Once they select an individual item they will be rerouted to the /products/item/{id} where they can see specific details about the item, and add it to their cart + select a quantity. The item will be added to the cart when the form is submitted and it will be saved into the database to an item_ordered table with a relational key to the order table and the order table will contain a key to the customers table. The user will not be re-routed from their current page. Once a customer is finished adding items they can then click 'checkout' which will reroute them to their cart. There they will be asked to review their order. Once they have reviewed the order and clicked submit, they will be rerouted to a /thankyou page which thanks them for their order. They can then log out of the application, which will take them back to the landing page.


## Non-Functional Requirements
Security - Our app will maintain customer security by preventing SQL injection (we will use a series of checks when requests are sent to the server). We will also make sure passwords are hidden (using the html password input type). As a stretch goal we will have requirements for password length when a customer first creates an account. It is important especially when making an online ordering site that the customer feels like you are protecting their information.

Usability - A customer will be able to easily find the items they are looking for, for MVP we will accomplish this by only showing a minimal number of items. As a stretch goal we will implement search and styling features that make it visually directive for the customer on where they need to go. Customers want to be able to go straight to the materials they need and place an order quickly and efficiently.

