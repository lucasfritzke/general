-- Criando a tabela Caregiver
CREATE TABLE caregiver (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           cpf VARCHAR(11) UNIQUE NOT NULL,
                           birth_date DATE NOT NULL,
                           id_address INT NOT NULL,
                           qualification VARCHAR(50) NOT NULL,
                           hourly_rate DECIMAL(10,2) NOT NULL,
                           personal_description VARCHAR(300),
                           email VARCHAR(100) UNIQUE NOT NULL,
                           phone VARCHAR(20),
                           coverage_radius DOUBLE PRECISION,
                           status BOOLEAN DEFAULT TRUE,
                           CONSTRAINT fk_caregiver_address FOREIGN KEY (id_address) REFERENCES address(id) ON DELETE CASCADE
);
