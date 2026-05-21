CREATE DATABASE gestionParqueadero;
GO

USE gestionParqueadero;
GO

-- 3. Tabla TipoVehiculo (¡UNA SOLA y con tipo decimal estándar!)
CREATE TABLE [dbo].[TipoVehiculo](
	[id_tipo] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[tarifa_minuto] [decimal](10, 2) NOT NULL,
    PRIMARY KEY ([id_tipo])
);
GO

-- 4. Tabla Vehiculo
CREATE TABLE [dbo].[Vehiculo](
	[placa] [varchar](20) NOT NULL,
	[id_tipo] [int] NOT NULL,
	[modelo] [varchar](50) NOT NULL,
	[color] [varchar](20) NOT NULL,
    PRIMARY KEY ([placa]),
    FOREIGN KEY ([id_tipo]) REFERENCES [dbo].[TipoVehiculo] ([id_tipo])
);
GO

-- 5. Tabla EspacioParqueadero
CREATE TABLE [dbo].[EspacioParqueadero](
	[id_espacio] [int] NOT NULL,
	[numero] [int] NOT NULL,
	[piso] [int] NOT NULL,
	[tipo_celda] [varchar](20) NOT NULL,
	[estado] [bit] NOT NULL,
    PRIMARY KEY ([id_espacio])
);
GO

-- 6. Tabla Mensualidad (¡Con la columna valor unificada!)
CREATE TABLE [dbo].[Mensualidad](
	[id_mensualidad] [int] NOT NULL,
	[placa] [varchar](20) NOT NULL,
	[fecha_inicio] [date] NOT NULL,
	[fecha_fin] [date] NOT NULL,
	[pagado] [bit] NOT NULL,
	[valor] [decimal](10, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY ([id_mensualidad]),
    FOREIGN KEY ([placa]) REFERENCES [dbo].[Vehiculo] ([placa])
);
GO

-- 7. Tabla Ticket
CREATE TABLE [dbo].[Ticket](
	[id_ticket] [int] NOT NULL,
	[placa] [varchar](20) NOT NULL,
	[id_espacio] [int] NOT NULL,
	[hora_entrada] [datetime] NOT NULL,
	[hora_salida] [datetime] NULL,
	[valor_total] [decimal](10, 2) NULL,
    PRIMARY KEY ([id_ticket]),
    FOREIGN KEY ([id_espacio]) REFERENCES [dbo].[EspacioParqueadero] ([id_espacio]),
    FOREIGN KEY ([placa]) REFERENCES [dbo].[Vehiculo] ([placa])
);
GO

-- =======================================================
-- 8. INSERTAR DATOS DE PRUEBA REALES PARA VER EN TU TABLA
-- =======================================================

INSERT INTO TipoVehiculo (id_tipo, nombre, tarifa_minuto) VALUES
(1, 'Auto', 50.00),
(2, 'Motocicleta', 30.00);

INSERT INTO Vehiculo (placa, id_tipo, modelo, color) VALUES
('ABC123', 1, 'Corolla', 'Blanco'),
('DEF45G', 2, 'YZF', 'Negro'),
('HIJ678', 1, 'Spark', 'Gris');

INSERT INTO EspacioParqueadero (id_espacio, numero, piso, tipo_celda, estado) VALUES
(1, 101, 1, 'Auto', 1),
(2, 105, 2, 'Motocicleta', 1),
(3, 103, 3, 'Auto', 1);

INSERT INTO Mensualidad (id_mensualidad, placa, fecha_inicio, fecha_fin, pagado, valor) VALUES
(1, 'ABC123', '2026-05-01', '2026-06-01', 1, 120000.00),
(2, 'DEF45G', '2026-05-01', '2026-06-01', 1, 80000.00),
(3, 'HIJ678', '2026-05-01', '2026-06-01', 0, 110000.00);
GO

ALTER TABLE Vehiculo
ADD CONSTRAINT FK_Vehiculo_TipoVehiculo
FOREIGN KEY (id_tipo) REFERENCES TipoVehiculo(id_tipo);