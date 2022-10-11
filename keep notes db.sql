CREATE hmc_keep database;
USE hmc_keep;
CREATE TABLE user(user_id INT PRIMARY KEY , first_name CHAR(20) NOT NULL , last_name CHAR(20) NOT NULL, email CHAR(150) NOT NULL , password CHAR(40) NOT NULL);
CREATE TABLE note(note_id INT PRIMARY KEY, title CHAR(100) NOT NULL, content TEXT  , creation_date DATE , user_id INT);
ALTER TABLE note MODIFY creation_date DATETIME DEFAULT now();
ALTER TABLE note ADD CONSTRAINT FOREIGN KEY(user_id) REFERENCES user(user_id);
CREATE TABLE attachment(attachment_id INT PRIMARY KEY , attachment_name CHAR(40) NOT NULL , note_id INT);
ALTER TABLE attachment ADD CONSTRAINT FOREIGN KEY(note_id) REFERENCES note(note_id);

-- to describe tables by name

DESCRIBE user;
DESCRIBE note;
DESCRIBE attachment;
