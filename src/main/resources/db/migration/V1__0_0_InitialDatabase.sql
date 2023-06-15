CREATE TABLE Receipe
(
	id serial primary key,

	name varchar(256) not null unique,
	ingredients varchar(1024) not null,
	serving integer not null,
	instructions varchar(1024) not null,
	is_vegan boolean not null
);

INSERT INTO Receipe(id, name, serving, ingredients, instructions, is_vegan) Values
(1, 'Hamburger', 1, 'Bread, Meat, Onions', 'Cook meat on the plate for 5 minutes. Cut onions. Combine the cooked meat and onions in between the breads', false),
(2, 'Cheeseburger', 1, 'Bread, Meat, Onions, Cheese', 'Cook meat on the plate for 5 minutes. Cut onions. Combine the cooked meat and onions and the slice of cheese in between the breads', false),
(3, 'Vega Sandwich', 2, '4 slices of bread, Mozarella, Cucumber', 'Cut mozarella and cucumber into slices. Per serving, use 2 slices of bread and put mozarella and cucumber slices in between', true),
(4, 'Tomatoe Soup', 2, '6 tomatoes, 2L water', 'Boil tomatoe and water for half an hour', true);