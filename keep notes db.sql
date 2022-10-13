CREATE database keep_notes ;
USE keep_notes ;
CREATE TABLE user(user_id INT not null AUTO_INCREMENT PRIMARY KEY , first_name CHAR(20) NOT NULL , last_name CHAR(20) NOT NULL, email CHAR(150) NOT NULL , password CHAR(100) NOT NULL);
CREATE TABLE note(note_id INT not null AUTO_INCREMENT PRIMARY KEY, title CHAR(100) NOT NULL, content TEXT  , creation_date DATE , user_id INT);
ALTER TABLE note MODIFY creation_date DATETIME DEFAULT now();
ALTER TABLE note ADD CONSTRAINT FOREIGN KEY(user_id) REFERENCES user(user_id);
CREATE TABLE attachment(attachment_id INT not null AUTO_INCREMENT PRIMARY KEY , attachment_name CHAR(40) NOT NULL , note_id INT);
ALTER TABLE attachment ADD CONSTRAINT FOREIGN KEY(note_id) REFERENCES note(note_id);


-- insert into note (title,content,creation_date,user_id) values ("2 note for lena ","content",'2022-04-16 23:18:17',2)
-- insert into note (title,content,creation_date,user_id) values ("lena","content",'2020-09-14 23:18:17',1)
-- select * from note
-- select * from note where user_id =1