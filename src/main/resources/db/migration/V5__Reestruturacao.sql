DROP TABLE job_application CASCADE;
DROP TABLE job CASCADE;
DROP TABLE caregiver CASCADE;
DROP TABLE adviser CASCADE;
DROP TABLE address CASCADE;


-- Extensão necessária para UUID no PostgreSQL
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

-- Criar tabela de usuários
CREATE TABLE users
(
    id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email    VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(20)         NOT NULL
);

-- Criar tabela de endereços
CREATE TABLE address
(
    id           serial primary key,
    street       VARCHAR(255) NOT NULL,
    number       VARCHAR(20)  NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        VARCHAR(50)  NOT NULL,
    country      VARCHAR(50)  NOT NULL DEFAULT 'Brasil',
    zipcode      VARCHAR(20)  NOT NULL,
    complement   VARCHAR(255),
    latitude     DOUBLE PRECISION,
    longitude    DOUBLE PRECISION
);

-- Criar tabela de cuidadores
CREATE TABLE caregiver
(
    id                   UUID PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    name                 VARCHAR(100)       NOT NULL,
    cpf                  VARCHAR(11) UNIQUE NOT NULL,
    qualification        VARCHAR(255)       NOT NULL,
    hourly_rate          NUMERIC(10, 2)     NOT NULL,
    personal_description TEXT,
    phone                VARCHAR(20),
    coverage_radius      DOUBLE PRECISION,
    address_id           integer               REFERENCES address (id)
);

-- Criar tabela de anunciantes
CREATE TABLE adviser
(
    id         UUID PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    name       VARCHAR(100)       NOT NULL,
    cpf        VARCHAR(11) UNIQUE NOT NULL,
    phone      VARCHAR(20),
    address_id integer               REFERENCES address (id)
);

-- Criar tabela de trabalhos
CREATE TABLE job
(
    id              UUID PRIMARY KEY     DEFAULT uuid_generate_v4(),
    job_description TEXT        NOT NULL,
    job_type        VARCHAR(50) NOT NULL CHECK (job_type IN ('TEMPORARY', 'PERMANENT')),
    start_date      TIMESTAMP   NOT NULL,
    end_date        TIMESTAMP   NOT NULL,
    hours_per_day   INTEGER     NOT NULL CHECK (hours_per_day > 0),
    adviser_id      UUID        NOT NULL REFERENCES adviser (id) ON DELETE CASCADE,
    address_id      integer        NOT NULL REFERENCES address (id),
    status          VARCHAR(50) NOT NULL
);

-- Criar tabela de candidaturas
CREATE TABLE job_application
(
    id               UUID PRIMARY KEY     DEFAULT uuid_generate_v4(),
    job_id           UUID        NOT NULL REFERENCES job (id) ON DELETE CASCADE,
    caregiver_id     UUID        NOT NULL REFERENCES caregiver (id) ON DELETE CASCADE,
    application_date TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    status           VARCHAR(50) NOT NULL
);
