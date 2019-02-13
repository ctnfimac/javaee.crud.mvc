create database if not exists dbz;

use dbz;

create table personaje(
	id int AUTO_INCREMENT,
	imagen varchar(100) not null,
	nombre varchar(30) not null,
	descripcion varchar(100)  default 'sin descripcion',
	ataque varchar(30) default 'desconocido',
	primary key (id)
);

INSERT INTO personaje (imagen, nombre, descripcion, ataque) values 
('img/goku.jpg','Goku','guerrero saiyan personaje principal','kame-hame-ha'),
('img/vegeta.jpg','Vegeta','guerrero saiyan principe del planeta vegeta','big-bang'),
('img/freezer.jpg','Freezer','Malvado emperador del mal','hakay'),
('img/picoro.jpg','Picoro','guerrero Namekusei','Makancosapo'),
('img/roshi.jpg','Maestro Roshi','Maestro de Son Goku','Mafuba');