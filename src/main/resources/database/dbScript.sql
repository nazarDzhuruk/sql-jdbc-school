DROP TABLE IF EXISTS groups CASCADE;
CREATE TABLE groups
(
    group_id   INTEGER,
    group_name VARCHAR(50)
);
DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students
(
    student_id INTEGER NOT NULL PRIMARY KEY,
    first_name VARCHAR,
    last_name  VARCHAR,
    group_id   INTEGER
);
DROP TABLE IF EXISTS courses CASCADE;
CREATE TABLE courses
(
    course_id          INTEGER NOT NULL,
    course_name        VARCHAR(200)PRIMARY KEY ,
    course_description VARCHAR(200)
);
INSERT INTO courses (course_id, course_name, course_description) VALUES
 ('1', 'Biology' , 'Concepts and principles common to all living organisms'),
 ('2', 'Math' , 'Algebra, geometry'),
 ('3', 'Physics' , 'Mechanics, heat, light, sound, electricity and magnetism'),
 ('4', 'PE' , 'Physical education'),
 ('5', 'Economics' , 'Deals with consumers, firms, markets and income distribution'),
 ('6', 'IT' , 'Information technology'),
 ('7', 'English' , 'Reading, writing, speaking, listening, thinking, viewing and presenting'),
 ('8', 'Russian' , 'Reading, writing, speaking, listening, thinking, viewing and presenting'),
 ('9', 'French' , 'Reading, writing, speaking, listening, thinking, viewing and presenting'),
 ('10', 'Philosophy' , 'Human rights, social justice, and applications to selected problems');
DROP TABLE IF EXISTS student_courses CASCADE;
CREATE TABLE student_courses(
    student_id INTEGER NOT NULL,
    course VARCHAR,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course) REFERENCES courses(course_name)
);
commit;