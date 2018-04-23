--Файл скриптов создания Бд в postgresql

CREATE DATABASE "BullsAndCows"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;
	   
CREATE TABLE "User"
(
  login character(20),
  password character(20),
  user_id bigint NOT NULL DEFAULT nextval('"Users_user_id_seq"'::regclass),
  CONSTRAINT user_id PRIMARY KEY (user_id),
  CONSTRAINT login UNIQUE (login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "User"
  OWNER TO postgres;
  
  
CREATE TABLE "Rating"
(
  steps integer,
  user_id integer,
  rating_id bigserial NOT NULL,
  CONSTRAINT rating_id PRIMARY KEY (rating_id),
  CONSTRAINT user_user_id FOREIGN KEY (user_id)
      REFERENCES "User" (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Rating"
  OWNER TO postgres;

-- Index: fki_user_user_id

-- DROP INDEX fki_user_user_id;

CREATE INDEX fki_user_user_id
  ON "Rating"
  USING btree
  (user_id);