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

Insert into TipoVehiculo (id_tipo, nombre, tarifa_minuto) values
(1, 'Auto', 50.00),
(2, 'Motocicleta', 30.00);

-- 2. Insertar Vehículos
insert into Vehiculo (placa, id_tipo, modelo, color) values
('ABC123', 1, 'Corolla', 'Blanco'),
('DEF45G', 2, 'YZF', 'Negro'),
('HIJ678', 1, 'Spark', 'Gris');

-- 3. Insertar Espacios
insert into EspacioParqueadero (id_espacio, numero, piso, tipo_celda, estado) values
(1, 101, 1, 'Auto', 1),
(2, 105, 2, 'Motocicleta', 1),
(3, 103, 3, 'Auto', 1);

-- 4. Insertar Mensualidades
insert into Mensualidad (id_mensualidad, placa, fecha_inicio, fecha_fin, pagado) values
(1, 'ABC123', '2026-04-01', '2026-05-01', 1),
(2, 'DEF45G', '2026-04-01', '2026-05-01', 1),
(3, 'HIJ678', '2026-04-01', '2026-05-01', 0);

-- 5. Insertar Tickets
insert into Ticket (id_ticket, placa, id_espacio, hora_entrada, hora_salida, valor_total) values
(1, 'ABC123', 1, '2026-04-08 08:30:00', '2026-04-08 10:12:00', 5100), -- 102 min * $50
(2, 'DEF45G', 2, '2026-04-08 12:20:00', '2026-04-08 16:22:00', 7260), -- 242 min * $30
(3, 'HIJ678', 3, '2026-04-08 20:15:00', '2026-04-09 08:50:00', 37750); -- Ejemplo amanecida