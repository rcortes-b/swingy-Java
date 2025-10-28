
CREATE TABLE IF NOT EXISTS heroes (
	name VARCHAR(15) UNIQUE,
	classType VARCHAR(10),
	level INTEGER,
	experience INT,
	attackDmg INT,
	armorDefense INT,
	helmHP INT
);

INSERT INTO heroes (name, classType, level, experience, attackDmg, armorDefense, helmHP)
VALUES	('defaultWizard', 'Wizard', 1, 300, 15, 7, 15),
		('defaultWarrior', 'Warrior', 1, 300, 10, 10, 18),
		('defaultHealer', 'Healer', 1, 300, 7, 5, 25);

/*CREATE TABLE IF NOT EXISTS villains (
	name VARCHAR(15) UNIQUE,
	classType VARCHAR(10),
	level INTEGER,
	experience INT,
	attackDmg INT,
	armorDefense INT,
	helmHP INT
);

CREATE TABLE IF NOT EXISTS artifacts (
	name VARCHAR(15) UNIQUE,
	classType VARCHAR(10),
	level INTEGER,
	experience INT,
	attackDmg INT,
	armorDefense INT,
	helmHP INT
);*/