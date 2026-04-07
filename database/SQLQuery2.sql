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