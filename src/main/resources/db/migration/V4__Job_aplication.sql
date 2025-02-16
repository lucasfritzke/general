CREATE TABLE job_application (
                                 id SERIAL PRIMARY KEY,
                                 job_id INT NOT NULL,
                                 caregiver_id INT NOT NULL,
                                 status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED')),
                                 FOREIGN KEY (job_id) REFERENCES job(id) ON DELETE CASCADE,
                                 FOREIGN KEY (caregiver_id) REFERENCES caregiver(id) ON DELETE CASCADE
);
