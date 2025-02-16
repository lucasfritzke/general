CREATE TABLE job (
                     id SERIAL PRIMARY KEY,
                     job_description VARCHAR(500) NOT NULL,
                     job_type VARCHAR(20) NOT NULL CHECK (job_type IN ('TEMPORARY', 'PERMANENT')),
                     start_date TIMESTAMP NOT NULL,
                     end_date TIMESTAMP NOT NULL,
                     hours_per_day INT NOT NULL,
                     adviser_id INT NOT NULL,
                     address_id INT NOT NULL,
                     status VARCHAR(20) NOT NULL CHECK (status IN ('OPEN', 'IN_PROGRESS', 'COMPLETED')),
                     notify BOOLEAN DEFAULT FALSE,
                     FOREIGN KEY (adviser_id) REFERENCES adviser(id) ON DELETE CASCADE,
                     FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);
