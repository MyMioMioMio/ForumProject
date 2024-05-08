create table posts_likes
(
    plid bigint auto_increment
        primary key,
    uid  bigint null,
    pid  bigint null,
    constraint PL_PID_FK
        foreign key (pid) references posts (pid),
    constraint PL_UID_FK
        foreign key (uid) references user (uid)
);

