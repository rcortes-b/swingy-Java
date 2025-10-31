
CREATE TABLE IF NOT EXISTS heroes (
	name VARCHAR(15) UNIQUE NOT NULL,
	classType VARCHAR(7),
	level INT,
	experience INT,
	attackDmg INT,
	armorDefense INT,
	helmHP INT
);

INSERT INTO heroes (name, classType, level, experience, attackDmg, armorDefense, helmHP)
VALUES	('defaultWizard', 'Wizard', 1, 0, 15, 7, 15),
		('defaultWarrior', 'Warrior', 1, 0, 10, 10, 18),
		('defaultHealer', 'Healer', 1, 0, 7, 5, 25);

/*
	Villains and Artifacts will scale based in hero level, so their default level is 1, no need to specify
*/

CREATE TABLE IF NOT EXISTS villains (
	name VARCHAR(14) UNIQUE NOT NULL,
	classType VARCHAR(7),
	attackDmg INT,
	armorDefense INT,
	helmHP INT
);

INSERT INTO villains (name, classType, attackDmg, armorDefense, helmHP)
VALUES	('Vicious Goblin', 'Goblins', 5, 1, 9),
		('Filthy Orc', 'Orcs', 12, 5, 12),
		('Rotten Troll', 'Trolls', 20, 2, 20);

CREATE TABLE IF NOT EXISTS artifacts (
	name VARCHAR(25) UNIQUE,
	type VARCHAR(6),
	value INT,
	description TEXT
);

INSERT INTO artifacts (name, type, value, description)
VALUES	('Amulet of the Stone Heart', 'Helm', 13,
		'A heavy, obsidian pendant that increases the wearers overall resilience, allowing them to endure more damage.'),

		('Cinderblade Fragment', 'Weapon', 9,
		'A jagged piece of metal pulsing with heat. Purely focused on increasing offensive output.'),

		('Wardens Gauntlets', 'Armor', 9,
		'Reinforced steel gloves designed to deflect incoming blows, maximizing physical damage mitigation.'),

		('Phoenix Feather Cloak', 'Helm', 8,
		'A light mantle woven with bright threads. Its warmth provides slight healing and enhances vitality.'),

		('The Shifting Sigil', 'Weapon', 5,
		'A complex silver symbol that enhances focus, providing a moderate offense boost.'),

		('Oaken Buckler', 'Armor', 5,
		'A small, sturdy wooden shield bound in bronze, offering a reliable protection');
