create table IF NOT EXISTS beer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    beer_name VARCHAR(255) ,
    beer_style VARCHAR(255),
    upc VARCHAR(255),
    quantity_on_hand INT ,
    price decimal(19,2) ,
    created_date TIMESTAMP ,
    last_modified_date TIMESTAMP
);

create table IF NOT EXISTS customer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) ,
    created_date TIMESTAMP ,
    last_modified_date TIMESTAMP
);
