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

INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1001, 200001, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consequat imperdiet est, sit amet semper nulla convallis eu.', 200002, '随机标题1', '2024-04-14 13:01:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1002, 200002, 'Fusce commodo metus a tortor feugiat, id pellentesque dolor laoreet.', 200003, '随机标题2', '2024-04-14 12:59:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1003, 200003, 'Integer vitae nisl aliquam, aliquam libero a, fermentum erat. Maecenas pretium sapien nec dapibus ultrices.', 200004, '随机标题3', '2024-04-14 12:02:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1004, 200004, 'Curabitur vel lacus cursus, consequat ex in, aliquam lectus.', 200005, '随机标题4', '2024-04-14 12:03:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1005, 200005, 'Donec ac augue eget elit volutpat facilisis. Sed at risus nec mauris eleifend dapibus.', 200006, '随机标题5', '2024-04-14 12:04:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1006, 200006, 'Quisque consectetur elit a est cursus, vel placerat erat iaculis.', 200007, '随机标题6', '2024-04-14 12:05:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1007, 200007, 'Sed sollicitudin sem sed ligula lacinia, in rhoncus leo lacinia.', 200008, '随机标题7', '2024-04-14 12:06:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1008, 200008, 'Phasellus vel diam in leo tristique cursus vitae a augue.', 200009, '随机标题8', '2024-04-14 12:06:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1009, 200009, 'Aliquam venenatis justo nec enim tempor, id interdum enim feugiat.', 200010, '随机标题9', '2024-04-14 12:08:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1010, 200010, 'Vestibulum lacinia odio at risus sagittis sagittis. Ut vitae consectetur leo.', 200011, '随机标题10', '2024-04-14 12:11:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1011, 200001, '这是第一个帖子的内容。', 200002, '第一个帖子', '2024-04-05 23:15:27', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1012, 200002, '大家有什么好听的音乐推荐吗？', 200003, '音乐推荐', '2024-04-06 11:40:54', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1013, 200003, '分享一下你们最近看的好电影吧！', 200004, '最近看的电影', '2024-04-07 16:28:20', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1014, 200004, '大家都在玩什么游戏？推荐一下！', 200005, '喜爱的游戏', '2024-04-08 10:53:38', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1015, 200005, '分享一些最近的科技新闻吧！', 200006, '最新科技新闻', '2024-04-09 16:03:11', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1016, 200006, '和大家分享一下我的健身心得。', 200007, '健身心得', '2024-04-10 19:48:21', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1017, 200007, '有没有好吃的菜谱推荐？', 200008, '美食推荐', '2024-04-11 12:35:45', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1018, 200008, '刚刚去了一个美丽的旅行地，推荐给大家！', 200009, '旅行分享', '2024-04-12 17:23:56', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1019, 200009, '大家最喜欢的文学作品是什么？一起交流讨论吧！', 200010, '最喜欢的文学作品', '2024-04-13 10:02:08', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1020, 200010, '分享一些摄影技巧和经验。', 200011, '摄影技巧分享', '2024-04-14 19:16:34', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1021, 200014, '小时候最快的一集，20/10/28开始，24/4/16结束，时隔1266天，差不多3.4年，顺便贴一下20年的我(图4)，当初我以为爱瑠吧不可能那么多人，我甚至不玩贴吧了，倒数都没数完，没想到后面人越来越多了，还很多人坚持去数，大家真的好温柔，我哭死
谢谢大家了，所以现在开始数负数吧，一直数到负无穷
顺便说一句小吧的0被抢了，太搞笑了，乐死我了', 200002, '我的青春结束啦', '2024-04-21 20:54:27', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1022, 200014, '阿斯顿撒大苏打
啊实打实大苏打
阿斯顿撒大苏打
啊实打实dasd', 200002, '按着呵呵', '2024-04-21 21:01:13', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1025, 200014, '应当会在mujica里有回收
1. 第一话大祥老师拆团的那个雨天，应该是发生于c团初次live，大祥老师看手机大惊失色之后有一段时
了。那么下雨那天到底发生了什么，让大祥老师不惜冒雨前来拆团呢？mujica里可能会以大祥老师第一人称
的回忆视角交待。（当然，也不排除单纯只是用雨天烘托气氛）
2.灯决定对大祥老师敞开心扉但被其拒绝，初华从天文馆赶赴mujica彩排现场时遇到灯。这两个情节都有爱
音在场。这两个情节的安排明显代表着c团，mygo，mujica众人之间剪不断理还乱的关系，而同时安排爱音
在场，应当是重要伏笔，预示着爱音这个局外人要在清情节推动中起大的作用。再想到爱音与灯这一对官配
以及种种纠葛都算是灯的心结，那么让爱音来救赎也是合理。（圣爱音）
3.mujica上场之前大祥老师的中二台词。“一丘之貉”，那么明显说明这五个人各自有各自的心结与问题，
换言之都有要“反抗”的东西，下一季全员大爆几乎成定局。睦头比较明显，就是来自富裕但压抑的家庭的
压力；大祥老师众说纷纭，但归根结底也是富裕阶层的烦恼。初华和喵梦一个是带偶像，一个是带V，这俩
倒还没看出来。海铃这个最强雇佣兵，会不会是因为要贴补家用才这么丧心病狂地接单呢？至少有一点，这
五个人在结成mujica前社会阶层并不是平衡的。这就牵涉出mujica这个乐队的核心矛盾，虽然寄托着以大祥
老师为主、其余四人多少都沾点的“反抗”理想，但它归根结底是个商业乐队（连大祥老师自己都不讳言、
并且乐队海报也有丰川物产的赞助）。这种理想与现实的矛盾，在乐队层面，在乐队成员层面（你一个财阀
千金要反抗的东西，我一个普通人理解不了，只会觉得何不食肉糜），以及在大祥老师内心都有可能成为引
爆一切的导火索。

但话说回来，这毕竟是邦邦世界观，难道真要搞这么现实且沉重的东西吗……但至少从mygo里铺开的摊子来
看，想要收得圆满着实是大考验。', 200002, '啊啊啊啊', '2024-04-21 21:05:57', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1026, 200014, '是这样的，但是我们怎么知道一些
事情，也就那样阿里', 200002, '啊啊啊啊', '2024-04-21 21:15:57', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1027, 200014, '确实可以', 200002, '我觉得', '2024-04-22 19:52:43', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1028, 200014, '这样吗', 200002, '再来一次', '2024-04-22 19:53:03', 0);
INSERT INTO forums_db.posts (pid, uid, posts_description, sid, posts_title, posts_dateTime, likes) VALUES (1029, 200014, '阿扎
艾米弄死
啊实打实的
~~~~', 200012, '这是贴子', '2024-04-26 19:06:07', 0);
