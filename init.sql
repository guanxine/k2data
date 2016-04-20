CREATE TABLE IF NOT EXISTS courses(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  start Date NOT NULL,
  end Date NOT NULL,
  estimatedTime INT NOT NULL,
  facilitator VARCHAR(255)
);
INSERT INTO courses (name,start,end,estimatedTime,facilitator) VALUES ("java","2016-4-10","2016-4-22",20,"shaonian");