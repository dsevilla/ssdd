CREATE SCHEMA IF NOT EXISTS videofaces;
USE videofaces;

CREATE TABLE IF NOT EXISTS users(
	id varchar(50),
       	email varchar(50),
	password_hash text,
       	name text,
	TOKEN text,
	visits int,
	PRIMARY KEY(id)
);

-- Para búsquedas con email
CREATE INDEX user_email_idx ON users (email);

-- CUIDADO!! AÑADO UN USUARIO PARA PROBAR, PASSWORD: "admin"
INSERT INTO users VALUES ("ddd", "dsevilla@um.es", "21232f297a57a5a743894a0e4a801fc3", "diego", "TOKEN", 0);

-- Vídeos
CREATE TABLE IF NOT EXISTS videos(
       	id varchar(50),
	userid varchar(50),
	date text,
	filename text,
	process_status int, -- 0: processing, 1: processed
	videodata LONGBLOB,

	PRIMARY KEY(id),
	FOREIGN KEY (userid) REFERENCES users(id)
);

-- Faces
CREATE TABLE IF NOT EXISTS faces(
       	id varchar(50),
	videoid varchar(50),
	imagedata BLOB, -- image data restricted to 64kb

	PRIMARY KEY(id),
	FOREIGN KEY (videoid) REFERENCES videos(id)
);


