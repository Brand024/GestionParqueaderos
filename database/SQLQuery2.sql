create database gestionParqueadero;
go 

use gestionParqueadero; 
go

Create table TipoVehiculo(
	id_tipo int primary key,
	nombre varchar(50) not null,
	tarifa_minuto decimal(10,2) not null
);

Create table Vehiculo(
	placa varchar(20) primary key,
	id_tipo int not null,
	modelo varchar(50) not null,
	color varchar(20) not null,
	foreign key (id_tipo) references TipoVehiculo(id_tipo)
);

Create table EspacioParqueadero(
	id_espacio int primary key,
	numero int not null,
	piso int not null,
	tipo_celda varchar(20) not null,
	estado bit not null
);

Create table Mensualidad(
	id_mensualidad int primary key,
	placa varchar(20) not null,
	fecha_inicio date not null,
	fecha_fin date not null,
	pagado bit not null,
	foreign key (placa) references Vehiculo(placa)
);

Create table Ticket (
	id_ticket int primary key,
	placa varchar(20) not null,
	id_espacio int not null,
	hora_entrada datetime not null,
	hora_salida datetime,
	valor_total decimal(10,2),
	foreign key (placa) references Vehiculo(placa),
	foreign key (id_espacio) references EspacioParqueadero(id_espacio)
);

---- insert
Insert TipoVehiculo values
(1, 'auto', 50),
(2, 'motocicleta', 30),
(1, 'auto', 50);

insert Vehiculo values
('ABC123', 1, 'corolla', 'Blanco'),
('DEF45G', 2, 'YZF', 'negro'),
('HIJ678', 1, 'spark', 'gris');

insert EspacioParqueadero values
(1, 101, 1, 'auto', 1),
(2, 105, 2, 'motocicleta', 1),
(3, 103, 3, 'auto', 1);

insert Mensualidad values
(01, 'ABC123', '2026-04-07', '2026-04-08', 1),
(02, 'DEF45G', '2024-09-11', '2024-09-11', 1),
(03, 'HIJ678', '2026-08-02', '2026-08-03', 0);

insert Ticket values 
(1, 'ABC123', 1, '08:30:00', '10:12:00', 78000),
(5, 'DEF45G', 2, '12:20:00', '16:22:09',7200 ),
(6, 'HIJ678', 3, '20:15:00', '08:50:00', 39000);
