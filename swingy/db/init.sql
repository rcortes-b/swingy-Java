
CREATE TABLE IF NOT EXISTS heroes (
	name VARCHAR(15) UNIQUE NOT NULL,
	classType VARCHAR(7),
	level INT,
	experience INT,
	attack INT,
	defense INT,
	hp INT
);