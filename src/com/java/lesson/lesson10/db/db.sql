CREATE TABLE IF NOT EXISTS students (
  id int(10) NOT NULL AUTO_INCREMENT,
  fName varchar(50) DEFAULT NULL,
  sName varchar(50) DEFAULT NULL,
  course INT(10) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS subjects (
  id int(10) NOT NULL AUTO_INCREMENT,
  subject varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS marks (
  mark_id int(100) NOT NULL AUTO_INCREMENT,
  mark int(2) DEFAULT NULL,
  student_id int(10) DEFAULT NULL,
  subject_id int(10) DEFAULT NULL,
  PRIMARY KEY (mark_id),
  INDEX student_id (student_id),
  INDEX subject_id (subject_id),
  CONSTRAINT marks_ibfk_1 FOREIGN KEY (student_id)
  REFERENCES students (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT marks_ibfk_2 FOREIGN KEY (subject_id)
  REFERENCES subjects (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
INSERT INTO students(id, fName, sName, course) VALUES (1, 'Fill', 'Colins', 1);
INSERT INTO students(id, fName, sName, course) VALUES (2, 'Bob', 'Marlin', 2);
INSERT INTO students(id, fName, sName, course) VALUES (3, 'Tony', 'Pretty', 4);
INSERT INTO students(id, fName, sName, course) VALUES (4, 'Son', 'Yang', 2);
INSERT INTO students(id, fName, sName, course) VALUES (5, 'Dooly', 'Boom', 2);
INSERT INTO students(id, fName, sName, course) VALUES (6, 'Lesly', 'Knife', 3);
INSERT INTO students(id, fName, sName, course) VALUES (7, 'Dobru', 'Pog', 1);
INSERT INTO students(id, fName, sName, course) VALUES (8, 'Smith', 'Simon', 1);
INSERT INTO students(id, fName, sName, course) VALUES (9, 'Fleth', 'Mex', 2);
INSERT INTO students(id, fName, sName, course) VALUES (10, 'Terry', 'Stool', 3);

INSERT INTO subjects(id, subject) VALUES(1, 'Mathematics');
INSERT INTO subjects(id, subject) VALUES(2, 'History');
INSERT INTO subjects(id, subject) VALUES(3, 'Physics');
INSERT INTO subjects(id, subject) VALUES(4, 'Chemistry');
INSERT INTO subjects(id, subject) VALUES(5, 'Economics');
INSERT INTO subjects(id, subject) VALUES(6, 'Science');

INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(1, 1, 1, 5);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(2, 2, 3, 7);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(3, 3, 5, 8);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(4, 4, 5, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(5, 5, 6, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(6, 6, 5, 2);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(7, 7, 1, 6);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(8, 1, 2, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(9, 2, 3, 7);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(10, 10, 4, 10);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(11, 4, 6, 8);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(12, 6, 4, 10);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(13, 1, 5, 1);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(14, 2, 6, 4);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(15, 3, 2, 7);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(16, 5, 2, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(17, 6, 2, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(18, 7, 4, 6);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(19, 1, 4, 5);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(20, 3, 5, 5);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(21, 3, 6, 3);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(22, 6, 3, 4);