create table section
(
    sid                 bigint auto_increment
        primary key,
    section_name        varchar(20)                        not null,
    section_description varchar(30)                        null,
    section_photo       varchar(50)                        null,
    section_datetime    datetime default CURRENT_TIMESTAMP null
);

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

create table posts
(
    pid               bigint auto_increment
        primary key,
    uid               bigint                             not null,
    posts_description varchar(1000)                      null,
    sid               bigint                             not null,
    posts_title       varchar(10)                        null,
    posts_dateTime    datetime default CURRENT_TIMESTAMP not null,
    likes             bigint   default 0                 not null,
    constraint SECTION_POSTS_FKEY
        foreign key (sid) references section (sid)
            on delete cascade,
    constraint UID_UID_FKEY
        foreign key (uid) references user (uid)
);

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

create table reply
(
    rid               bigint auto_increment
        primary key,
    uid               bigint                             not null,
    pid               bigint                             not null,
    reply_description varchar(1000)                      null,
    to_rid            bigint                             not null,
    reply_datetime    datetime default CURRENT_TIMESTAMP not null,
    likes             bigint   default 0                 not null,
    constraint POST_REPLY_FKEY
        foreign key (pid) references posts (pid)
            on delete cascade,
    constraint REPLY_USER_FKEY
        foreign key (uid) references user (uid)
);

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


