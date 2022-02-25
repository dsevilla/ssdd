CREATE SCHEMA IF NOT EXISTS videofaces;
USE videofaces;

CREATE TABLE IF NOT EXISTS users(id int PRIMARY KEY, email text, password_hash text, name text);
