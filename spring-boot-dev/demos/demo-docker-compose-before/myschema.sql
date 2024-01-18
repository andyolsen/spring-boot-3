-- Create myschema schema.
CREATE SCHEMA myschema;

CREATE TABLE myschema.employees (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL,
    region VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

-- Populate tables.
USE myschema;

-- Populate employees table.
INSERT INTO myschema.employees (name, salary, region) VALUES
    ('Andy',   25000.00, 'South Wales'),
    ('Claire', 37000.00, 'Kent'),
    ('Mary',   42000.00, 'London'),
    ('Mungo',  47000.00, 'Cumbria'),
    ('Midge',  72000.00, 'Scotland'),
    ('Hayley', 69000.00, 'Northern Ireland'),
    ('Nicki',  22000.00, 'Kent'),
    ('Sara',   11000.00, 'Kent'),
    ('Fiona',  88000.00, 'Kent');
