
-- Insertar roles
INSERT INTO roles (nombre) VALUES ('ADMINISTRADOR');
INSERT INTO roles (nombre) VALUES ('ACOMODADOR');
INSERT INTO roles (nombre) VALUES ('CLIENTE');

-- Insertar usuarios iniciales
INSERT INTO usuarios (username, password, nombre, rol_id)
VALUES ('camilo', '$2a$10$yOodhv9wKIdUSBVlqNalo.Hu5ndVu3sTGR5LyNWRRzkOXGA9tNA9G', 'Administrador', 1);
INSERT INTO usuarios (username, password, nombre, rol_id)
VALUES ('francisco', '$2a$10$mL2fYxWT9i9L9N05GUf91Oioj44QpdBYwjh.O4WRURG8LEMKKx6JO', 'Acomodador Principal', 2);
INSERT INTO usuarios (username, password, nombre, rol_id)
VALUES ('ronaldo', '$2a$10$o2yeQMAYnN0V70diXS.zWOexR4u3jojdzu8yqArLVte1jhwtEJyVq', 'Cliente Regular', 3);

-- Insertar tipos de vehículos
INSERT INTO tipos_vehiculos (nombre, descripcion) VALUES ('Automóvil', 'Vehículos de cuatro ruedas para pasajeros');
INSERT INTO tipos_vehiculos (nombre, descripcion) VALUES ('Motocicleta', 'Vehículos de dos ruedas');
INSERT INTO tipos_vehiculos (nombre, descripcion) VALUES ('Camioneta', 'Vehículos tipo SUV o pickup');
INSERT INTO tipos_vehiculos (nombre, descripcion) VALUES ('Camión', 'Vehículos de carga');
INSERT INTO tipos_vehiculos (nombre, descripcion) VALUES ('Bicicleta', 'Vehículos no motorizados de dos ruedas');