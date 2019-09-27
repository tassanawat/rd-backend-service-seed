create table hibernate_sequence (
	sequence_name varchar(255) primary key,
  next_val integer not null
);

insert into hibernate_sequence(sequence_name,next_val) VALUES ('SEQ_GEN', 1);

CREATE TABLE monitoring (
	id INTEGER PRIMARY KEY UNIQUE NOT NULL,
	system_cpu DOUBLE,
	process_cpu DOUBLE,
	memory_used_heap DOUBLE,
	memory_used_non_heap DOUBLE,
	requests_per_minute DOUBLE,
	threads_live INTEGER,
	threads_daemon INTEGER,
	TIMESTAMP DATETIME
);

CREATE TABLE "teams" (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	"key" varchar NOT NULL, "title" varchar NOT NULL,
	"title2" varchar,
	"code" varchar,
	"synonyms" varchar,
	"country_id" integer NOT NULL,
	"city_id" integer,
	"club" boolean DEFAULT 'f' NOT NULL,
	"since" integer,
	"address" varchar,
	"web" varchar,
	"assoc_id" integer,
	"national" boolean DEFAULT 'f' NOT NULL,
  "file" longvarchar,
  "json" clob,
	"created_at" datetime NOT NULL,
	"updated_at" datetime NOT NULL
);

-- insert into teams(id, key, title, title2, code, synonyms, country_id, city_id, club, since, address, web, assoc_id, national, file,json, created_at, updated_at) VALUES(null,'alg', 'Algeria', null,'ALG',null, '27',null, 'f',null,null,null,null,null,'f','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRJZCI6ImY5YmM1MWQ2LTA3NGYtNGNmYS1hYmU1LWU0MTJmYTM1NWMyYSIsInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE1NjExMzc5NjgsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiI2ZmJhMDFjOS01NDVmLTRhMTgtODY4Yy03YjZjNjA0YjI5YmIiLCJjbGllbnRfaWQiOiJmOWJjNTFkNi0wNzRmLTRjZmEtYWJlNS1lNDEyZmEzNTVjMmEifQ.8rg35PAk704szhiiE9OmvfM_u4m7eD9XVHTA4hHu2-8','{"clientId":"f9bc51d6-074f-4cfa-abe5-e412fa355c2a","user_name":"admin","scope":["read"],"exp":1561137968,"authorities":["ROLE_USER"],"jti":"6fba01c9-545f-4a18-868c-7b6c604b29bb","client_id":"f9bc51d6-074f-4cfa-abe5-e412fa355c2a"}','2018-03-25 12:24:21.414739','2018-03-25 12:24:21.414739');