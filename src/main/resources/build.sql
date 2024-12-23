create table books
(
    id             int auto_increment
        primary key,
    title          varchar(200) not null,
    author         varchar(100) not null,
    published_date date         not null
);

create table students
(
    id    int auto_increment
        primary key,
    name  varchar(200) null,
    email varchar(200) null,
    dob   datetime     null
);

create table rents
(
    id         int auto_increment
        primary key,
    student_id int  null,
    book_id    int  null,
    rent_date  date null,
    constraint rents_ibfk_1
        foreign key (student_id) references students (id)
            on delete cascade,
    constraint rents_ibfk_2
        foreign key (book_id) references books (id)
            on delete cascade
);

create index book_id
    on rents (book_id);

create index student_id
    on rents (student_id);



-- Dữ liệu mẫu cho students ---------------------------------------------------------------
INSERT INTO students (name, email, dob)
VALUES
    ('Nguyễn Văn A', 'nguyenvana@example.com', '2005-01-15'),
    ('Trần Thị B', 'tranthib@example.com', '2005-03-22'),
    ('Lê Minh C', 'leminhc@example.com', '2005-05-10'),
    ('Phạm Thị D', 'phamthid@example.com', '2005-07-30'),
    ('Đinh Quang E', 'dinhquange@example.com', '2005-09-05'),
    ('Vũ Thị F', 'vuthif@example.com', '2005-02-14'),
    ('Bùi Anh G', 'buianhg@example.com', '2005-04-18'),
    ('Ngô Tuấn H', 'ngotuanh@example.com', '2005-06-23'),
    ('Hoàng Thị I', 'hoangthi@example.com', '2005-08-17'),
    ('Mai Hoàng J', 'maikhoangj@example.com', '2005-10-01'),
    ('Cao Đức K', 'caoduck@example.com', '2005-11-20'),
    ('Phan Khánh L', 'phankhanhl@example.com', '2005-12-25'),
    ('Lý Bình M', 'lybinhm@example.com', '2005-01-09'),
    ('Dương Tiến N', 'duongtienn@example.com', '2005-02-21'),
    ('Trương Đặng O', 'truongdango@example.com', '2005-03-04'),
    ('Nguyễn Lan P', 'nguyenlanp@example.com', '2005-04-28'),
    ('Lâm Khôi Q', 'lamkhoiq@example.com', '2005-05-11'),
    ('Hoàng Lan R', 'hoanglanr@example.com', '2005-06-15'),
    ('Phạm Mai S', 'phammais@example.com', '2005-07-01'),
    ('Bùi Hải T', 'buihait@example.com', '2005-08-13'),
    ('Vũ Đức U', 'vuducu@example.com', '2005-09-29'),
    ('Trần Tuấn V', 'trantuanv@example.com', '2005-10-09'),
    ('Nguyễn Quang W', 'nguyenquangw@example.com', '2005-11-02'),
    ('Lê Anh X', 'leanhx@example.com', '2005-12-04'),
    ('Phạm Tuấn Y', 'phamtuany@example.com', '2005-01-30'),
    ('Đinh Khánh Z', 'dinhkhanhz@example.com', '2005-03-25'),
    ('Mai Lan AA', 'mailanaa@example.com', '2005-05-17'),
    ('Ngô Bình AB', 'ngobinhab@example.com', '2005-07-22'),
    ('Cao Minh AC', 'caominhac@example.com', '2005-08-08'),
    ('Vũ Thảo AD', 'vuthaoad@example.com', '2005-09-30'),
    ('Lý Quang AE', 'lyquangae@example.com', '2005-10-23'),
    ('Trương Hải AF', 'truonghaif@example.com', '2005-11-18'),
    ('Dương Anh AG', 'duonganhag@example.com', '2005-12-14'),
    ('Nguyễn Lan AH', 'nguyenlanah@example.com', '2005-05-30'),
    ('Trần Minh AI', 'tranminhai@example.com', '2005-06-14'),
    ('Phạm Bình AJ', 'phambinha@example.com', '2005-07-10'),
    ('Bùi Hoàng AK', 'buihoangak@example.com', '2005-08-24'),
    ('Lê Tiến AL', 'letienal@example.com', '2005-09-19'),
    ('Vũ Dương AM', 'vuduongam@example.com', '2005-10-18'),
    ('Ngô Thị AN', 'ngothian@example.com', '2005-11-11'),
    ('Mai Khôi AO', 'maikhoiao@example.com', '2005-12-06');



-- Dữ liệu mẫu cho books ---------------------------------------------------------------
INSERT INTO books (title, author, published_date)
VALUES
    ('Mastering Java Programming', 'John Doe', '2010-06-15'),
    ('Advanced Python Techniques', 'Jane Smith', '2015-02-23'),
    ('The Art of C++', 'Alice Johnson', '2018-03-12'),
    ('Data Structures and Algorithms in Java', 'Robert Brown', '2012-09-05'),
    ('Beginning JavaScript', 'William White', '2014-07-01'),
    ('Python for Data Science', 'Michael Green', '2016-11-15'),
    ('Learning SQL', 'Chris Black', '2013-05-30'),
    ('Web Development with React', 'Sarah Miller', '2020-01-10'),
    ('Java for Beginners', 'David Clark', '2011-04-20'),
    ('Effective Java', 'Joshua Bloch', '2008-09-18'),
    ('Database Design and SQL', 'Emily King', '2019-03-27'),
    ('Understanding Algorithms', 'Daniel Lewis', '2017-06-14'),
    ('Building Web Apps with Angular', 'Megan Scott', '2021-08-03'),
    ('Introduction to Machine Learning with Python', 'Benjamin Adams', '2020-02-10'),
    ('C++ for Competitive Programming', 'Kevin Harris', '2016-09-25'),
    ('Building RESTful APIs with Spring Boot', 'Olivia Thomas', '2019-07-12'),
    ('Learning Go Programming', 'Joshua Hall', '2022-05-19'),
    ('Docker for Developers', 'Sophia Martinez', '2021-12-01'),
    ('JavaScript Design Patterns', 'Ethan Walker', '2014-06-17'),
    ('Data Science from Scratch', 'Mason Lee', '2018-10-30'),
    ('Android Development with Kotlin', 'Charlotte Young', '2021-04-02'),
    ('Deep Learning with TensorFlow', 'Jack Robinson', '2020-09-21'),
    ('Learning Vue.js', 'Daniel Perez', '2022-02-14'),
    ('Agile Software Development', 'Lucas Harris', '2017-11-08'),
    ('Node.js for Beginners', 'Samantha Walker', '2020-07-26'),
    ('Java in Depth', 'Amelia Lewis', '2015-08-13'),
    ('Functional Programming in Scala', 'Henry Carter', '2014-02-05'),
    ('The Pragmatic Programmer', 'Dave Thomas', '2004-09-01'),
    ('Introduction to Swift Programming', 'James Taylor', '2018-03-04'),
    ('The Complete React Developer', 'Rachel Adams', '2021-06-25'),
    ('Machine Learning with R', 'Victor Clark', '2017-01-14'),
    ('Advanced SQL Queries', 'Madison Allen', '2016-10-11'),
    ('Learning Java 8', 'Sophia Evans', '2015-05-09'),
    ('Building Scalable Systems', 'Ethan Collins', '2019-11-04'),
    ('Artificial Intelligence in Python', 'Grace Walker', '2021-03-17'),
    ('Exploring Docker and Kubernetes', 'Ryan Mitchell', '2020-04-29'),
    ('Hands-on Data Science', 'Nathan White', '2022-06-08'),
    ('Mastering Web Development', 'Jackie Brown', '2020-02-05'),
    ('C# Programming for Beginners', 'Mason Green', '2018-01-13'),
    ('Rust Programming for Systems', 'Amos Harris', '2021-10-19'),
    ('Programming in Python 3', 'Lily Johnson', '2015-11-02'),
    ('Advanced Android Development', 'Chris Thompson', '2017-07-09'),
    ('HTML and CSS for Web Development', 'Zoe King', '2019-04-01'),
    ('Modern Web Development with React and Redux', 'Olivia Taylor', '2020-09-19'),
    ('Exploring Data Visualization', 'Aiden White', '2021-08-14'),
    ('Artificial Intelligence: A Modern Approach', 'Stella Clark', '2015-01-20'),
    ('Mastering Git and GitHub', 'Liam Harris', '2018-07-14'),
    ('Introduction to Web Security', 'Charlotte Brown', '2020-05-23'),
    ('Functional Programming with JavaScript', 'Evelyn Scott', '2016-12-16'),
    ('Computer Vision with OpenCV', 'Lucas Martin', '2022-04-01'),
    ('Programming Ruby', 'Harper Lee', '2008-05-15'),
    ('Android App Development with Kotlin', 'Madeline Garcia', '2019-09-28'),
    ('Deep Learning for Computer Vision', 'Ethan King', '2021-01-03'),
    ('Cloud Computing Essentials', 'James Adams', '2020-11-06'),
    ('Building a Node.js API', 'Grace Evans', '2021-05-02'),
    ('Designing with Figma', 'Elena Harris', '2021-08-17'),
    ('PHP and MySQL Web Development', 'Matthew Walker', '2018-03-30'),
    ('Practical Data Engineering', 'Lily Johnson', '2022-01-19'),
    ('Programming in Dart', 'William Brown', '2020-02-23'),
    ('C++ Programming for Beginners', 'Nina Scott', '2017-08-28'),
    ('Introduction to Artificial Intelligence', 'Lucas Lee', '2019-06-10'),
    ('Building Real-World Web Apps', 'Ella Taylor', '2021-04-20'),
    ('Introduction to Data Science with R', 'Victoria Walker', '2021-07-08'),
    ('Complete Guide to Java Programming', 'Sam Wilson', '2014-08-17'),
    ('Effective SQL', 'Henry Johnson', '2015-04-09'),
    ('Hands-on Cloud Development with AWS', 'Liam Scott', '2020-11-01'),
    ('Web Scraping with Python', 'Sophia White', '2019-02-11'),
    ('Advanced Swift Programming', 'Benjamin King', '2021-09-02'),
    ('Introduction to TensorFlow', 'Amos Taylor', '2020-10-03'),
    ('Programming in Objective-C', 'Charlotte Lee', '2016-06-17'),
    ('Blockchain for Developers', 'Nathan Green', '2021-03-25'),
    ('Machine Learning and AI for Business', 'Emily Collins', '2022-01-17'),
    ('Building Progressive Web Apps', 'Madison White', '2020-09-30'),
    ('Introduction to WebAssembly', 'Daniel Scott', '2021-12-15'),
    ('Exploring Vue.js', 'Hannah Taylor', '2019-11-04'),
    ('C++ Programming in Practice', 'Olivia Adams', '2017-09-12'),
    ('Learning JavaScript with ES6', 'Max Taylor', '2016-12-01'),
    ('Building Real-time Applications with Node.js', 'Julia Walker', '2021-06-18'),
    ('Introduction to Django', 'William Harris', '2018-02-28'),
    ('Practical Data Science', 'Evelyn Mitchell', '2022-02-22'),
    ('JavaScript for Modern Web Development', 'Amelia King', '2021-10-04'),
    ('Docker for Beginners', 'Samuel Brown', '2019-06-06'),
    ('Understanding Cloud Computing', 'Grace White', '2021-03-11'),
    ('Building Scalable Web Applications', 'Sophie Green', '2018-07-02'),
    ('Functional JavaScript', 'Lucas Scott', '2019-04-14'),
    ('Python Programming for Data Analysis', 'Mason Harris', '2017-09-23'),
    ('React Native for Mobile Development', 'Zoe King', '2020-08-21'),
    ('The Complete Developer’s Guide to SQL', 'Noah Evans', '2021-05-17'),
    ('Algorithm Design Manual', 'Emma Collins', '2015-07-22'),
    ('Mastering PostgreSQL', 'Liam Walker', '2021-11-19'),
    ('Java Design Patterns', 'Lucas Johnson', '2016-10-03'),
    ('Introduction to Algorithms', 'Megan Brown', '2018-09-10');
