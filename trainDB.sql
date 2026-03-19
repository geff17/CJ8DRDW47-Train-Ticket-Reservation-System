CREATE DATABASE trainDB;
USE trainDB;
CREATE TABLE Tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    passenger_name VARCHAR(100),
    destination VARCHAR(100),
    ticket_class VARCHAR(50),
    price DOUBLE
)
SELECT * FROM trainDB.Tickets;
TRUNCATE TABLE trainDB.Tickets;