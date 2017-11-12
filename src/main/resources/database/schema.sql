DROP TABLE IF EXISTS ingredient_types;

CREATE TABLE ingredient_types(
    ingredient_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    quantity_unit VARCHAR(100) NOT NULL
) ENGINE InnoDb;