CREATE TABLE courses(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  start Date NOT NULL,
  end Date NOT NULL,
  estimatedTime INT NOT NULL,
  facilitator VARCHAR(255)
);