create table reply_likes
(
    lid bigint auto_increment
        primary key,
    uid bigint null,
    rid bigint null,
    constraint RID_FK
        foreign key (rid) references reply (rid),
    constraint UID_FK
        foreign key (uid) references user (uid)
);

INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (1, 200005, 5);
INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (2, 200014, 177);
INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (3, 200014, 175);
INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (4, 200014, 185);
INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (5, 200014, 184);
INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (6, 200014, 183);
INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (7, 200014, 182);
INSERT INTO forums_db.reply_likes (lid, uid, rid) VALUES (8, 200014, 181);
