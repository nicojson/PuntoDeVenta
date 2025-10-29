-- Tabla para categorizar los productos.
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            icon VARCHAR(20)
);

-- Tabla para almacenar todos los productos disponibles.
CREATE TABLE products (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          description TEXT,
                          price DECIMAL(10, 2) NOT NULL,
                          quantity SMALLINT NOT NULL DEFAULT 0,
                          color VARCHAR(20),
                          image VARCHAR(255),  -- Aumentado para rutas de imagen más largas
                          category_id INT,
                          FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

-- Tabla para la información de los clientes.
CREATE TABLE customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           first_name VARCHAR(50) NOT NULL,
                           last_name VARCHAR(50) NOT NULL,
                           email VARCHAR(50) UNIQUE,
                           phone VARCHAR(50),
                           address VARCHAR(100),
                           gender ENUM('male', 'female')
);

CREATE TABLE Orden (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       id_custumer INT NOT NULL,
                       estatus ENUM('pagada', 'pendiente', 'cancelada') NOT NULL DEFAULT 'pendiente',
                       fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Llave foránea que vincula la orden con el cliente
                       FOREIGN KEY (id_custumer) REFERENCES customers(id)
);

CREATE TABLE Detalle (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         id_orden INT NOT NULL,
                         id_product INT NOT NULL,
                         cantidad INT NOT NULL,
                         precio_venta DECIMAL(10, 2) NOT NULL,

    -- Llave foránea que vincula a la tabla Detalle con Orden y Products
                         FOREIGN KEY (id_orden) REFERENCES Orden(id),
                         FOREIGN KEY (id_product) REFERENCES Products(id)
);