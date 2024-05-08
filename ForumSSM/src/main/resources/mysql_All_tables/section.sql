create table section
(
    sid                 bigint auto_increment
        primary key,
    section_name        varchar(20)                        not null,
    section_description varchar(30)                        null,
    section_photo       varchar(50)                        null,
    section_datetime    datetime default CURRENT_TIMESTAMP null
);

INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200002, 'Test吧', '第一个吧', null, '2024-04-05 22:07:18');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200003, '音乐吧', '音乐相关话题', null, '2024-04-06 10:32:45');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200004, '电影吧', '最新电影讨论', null, '2024-04-07 15:20:11');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200005, '游戏吧', '热门游戏分享', null, '2024-04-08 09:45:29');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200006, '科技吧', '科技前沿话题', null, '2024-04-09 14:55:02');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200007, '健身吧', '健康生活指南', null, '2024-04-10 18:40:12');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200008, '美食吧', '美味食谱分享', null, '2024-04-11 11:27:36');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200009, '旅游吧', '精彩旅行经验', null, '2024-04-12 16:15:47');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200010, '文学吧', '文学作品交流', null, '2024-04-13 09:53:59');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200011, '摄影吧', '摄影技巧分享', null, '2024-04-13 19:08:25');
INSERT INTO forums_db.section (sid, section_name, section_description, section_photo, section_datetime) VALUES (200012, 'Test22吧', '这是更新过的Test2吧', null, '2024-04-26 19:05:23');
