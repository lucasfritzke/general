CREATE TABLE address (
                         id SERIAL PRIMARY KEY,
                         street VARCHAR(255) NOT NULL,
                         number VARCHAR(50) NOT NULL,
                         neighborhood VARCHAR(100) NOT NULL,
                         city VARCHAR(100) NOT NULL,
                         state VARCHAR(50) NOT NULL,
                         country VARCHAR(50) DEFAULT 'Brasil' NOT NULL,
                         zipcode VARCHAR(20) NOT NULL,
                         complement VARCHAR(255),
                         latitude DOUBLE PRECISION,
                         longitude DOUBLE PRECISION
);
