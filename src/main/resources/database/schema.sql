DROP TABLE IF EXISTS ingredient_types;

CREATE TABLE ingredient_types(
    ingredient_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    quantity_unit VARCHAR(100) NOT NULL
) ENGINE InnoDb;

DROP TABLE IF EXISTS ingredients;

CREATE TABLE ingredients(
    ingredient_id INT PRIMARY KEY AUTO_INCREMENT,
    ingredient_type_id INT,
    quantity DOUBLE(7, 2) UNSIGNED NOT NULL,
    CONSTRAINT fk_ingredient_type
        FOREIGN KEY (ingredient_type_id) REFERENCES ingredient_types(ingredient_type_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE InnoDb;