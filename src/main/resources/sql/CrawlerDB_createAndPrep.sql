CREATE TABLE CrawlerDB.alerts(
ID INT PRIMARY KEY NOT NULL auto_increment,
USER_ID int NOT NULL,
FOREIGN KEY (USER_ID) REFERENCES CrawlerDB.users(USER_ID) ON DELETE CASCADE,
AREA varchar(50) NOT NULL,
SUBMISSION_TIME_DATE DATETIME NOT NULL,
QUERY_NAME varchar(50) NOT NULL,
QUERY_ID varchar(50) NOT NULL,
NOTIFY_ADDRESS varchar(50) NOT NULL, 
ACTIVE BOOLEAN NOT NULL
);

CREATE TABLE CrawlerDB.users(
USER_ID INT PRIMARY KEY NOT NULL auto_increment,
USER_NAME varchar(50) NOT NULL,
USER_PASS varchar(50) NOT NULL
);
-----------------------------------------------------------------------------------

SELECT * FROM CrawlerDB.clicks;
SELECT * FROM CrawlerDB.clicks WHERE TODAY_DATE BETWEEN '2017-04-19' and '2017-04-29';
DELETE FROM CrawlerDB.clicks WHERE ID=1;
DROP TABLE CrawlerDB.clicks;
TRUNCATE CrawlerDB.clicks;

-- INSERT INTO usersO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(23,'2017-04-26');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(24,'2017-04-27');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(6,'2017-04-28');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(4,'2017-04-29');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(23,'2017-04-24');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(5,'2017-04-23');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(5,'2017-04-22');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(7,'2017-04-21');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(60,'2017-04-20');
INSERT INTO CrawlerDB.clicks(NUMBER_OF_CLICKS,TODAY_DATE)VALUES(62,'2017-04-19');

drop table CrawlerDB.alerts;

INSERT INTO CrawlerDB.users(USER_NAME,USER_PASS)VALUES('dude','143851');

INSERT INTO CrawlerDB.users(USER_NAME,USER_PASS)VALUES('user1','secret1');

select * from CrawlerDB.users;

select * from CrawlerDB.alerts order by SUBMISSION_TIME_DATE asc;

select * from CrawlerDB.users;
commit;
UPDATE CrawlerDB.users SET USERclicks_PASS='secret1' where USER_ID= 2;
commit;

SET GLOBAL log_output = 'TABLE';
SET GLOBAL general_log = 'ON';
select * from mysql.general_log;

UPDATE CrawlerDB.alerts SET ACTIVE=true WHERE QUERY_ID = 916468;
select * from CrawlerDB.alerts order by SUBMISSION_TIME_DATE desc;

select userrecord0_.user_id as user_id1_2_, userrecord0_.password as password2_2_, userrecord0_.user_name as user_nam3_2_ from users userrecord0_ 
where userrecord0_.user_name=dude;

select userrecord0_.user_id as user_id1_2_, userrecord0_.user_pass as user_pas2_2_, userrecord0_.user_name as user_nam3_2_ from users userrecord0_ where userrecord0_.user_name=?

