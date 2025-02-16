-- Adicionar coluna birth_date na tabela caregiver
ALTER TABLE caregiver
    ADD COLUMN birth_date DATE NOT NULL;

-- Adicionar chave estrangeira corretamente para address_id na tabela caregiver
ALTER TABLE caregiver
    ADD CONSTRAINT fk_caregiver_address FOREIGN KEY (address_id) REFERENCES address(id);

-- Adicionar coluna birth_date na tabela adviser
ALTER TABLE adviser
    ADD COLUMN birth_date DATE NOT NULL;

-- Adicionar chave estrangeira corretamente para address_id na tabela adviser
ALTER TABLE adviser
    ADD CONSTRAINT fk_adviser_address FOREIGN KEY (address_id) REFERENCES address(id) ;