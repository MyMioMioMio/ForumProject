create table user
(
    uid            bigint auto_increment
        primary key,
    username       varchar(12)                          not null,
    password       varchar(20)                          not null,
    gender         varchar(2) default '男'              null,
    user_signature varchar(30)                          null,
    user_datetime  datetime   default CURRENT_TIMESTAMP not null
);

INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200001, '沦为阶下囚QAQ', 'ljh3927322', '男', '第一个账号', '2024-04-05 20:14:36');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200002, '小明', 'abc123', '男', 'Hello, World!', '2024-04-06 09:28:12');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200003, '爱丽丝', 'password', '女', 'Life is beautiful', '2024-04-07 14:36:59');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200004, 'JohnDoe', 'secret', '男', 'Carpe Diem', '2024-04-08 18:52:45');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200005, 'JaneSmith', 'qwerty', '女', 'Live, Laugh, Love', '2024-04-09 11:07:23');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200006, '张三', '123456', '男', '人生如戏，全靠演技', '2024-04-10 16:43:10');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200007, 'Emily', 'password', '女', 'Dream big', '2024-04-11 20:59:47');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200008, 'Michael', 'abcdef', '男', 'Stay positive', '2024-04-12 13:14:55');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200009, '小红', '987654', '女', 'Carpe Diem', '2024-04-13 17:30:21');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200010, 'TomWilson', 'ilovecats', '男', 'Adventure awaits', '2024-04-13 21:45:38');
INSERT INTO forums_db.user (uid, username, password, gender, user_signature, user_datetime) VALUES (200014, 'QAQaaaa', '123456', '女', '现在写了。。。。', '2024-04-21 17:05:01');
