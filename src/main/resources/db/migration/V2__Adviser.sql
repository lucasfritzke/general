CREATE TABLE adviser (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         cpf VARCHAR(11) UNIQUE NOT NULL,
                         birth_date DATE NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         phone VARCHAR(20),
                         address_id INT NOT NULL,
                         FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);
