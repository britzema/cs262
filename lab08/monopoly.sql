--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @version Summer, 2015
-- Updated by Brad Ritzema on 10/18/2019

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;

-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	score integer
	);

CREATE TABLE Property (
	ID integer PRIMARY KEY,
	name varchar(50) NOT NULL
	);    

CREATE TABLE SaveGame (
	gameID integer REFERENCES Game(ID),
    playerID integer REFERENCES Player(ID)
	propertyID integer REFERENCES Property(ID),
	houses integer, 
	hotels integer,
	);    

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON Property TO PUBLIC;
GRANT SELECT ON SaveGame TO PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 0.00);
INSERT INTO PlayerGame VALUES (1, 2, 0.00);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00);
INSERT INTO PlayerGame VALUES (2, 2, 0.00);
INSERT INTO PlayerGame VALUES (2, 3, 500.00);
INSERT INTO PlayerGame VALUES (3, 2, 0.00);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00);

INSERT INTO Property VALUES (1, 'Pikes Palace');
INSERT INTO Property VALUES (2, 'Hoo Doo Dojo');
INSERT INTO Property VALUES (3, 'Taco Takeout');
INSERT INTO Property VALUES (4, 'Short Line');
INSERT INTO Property VALUES (5, 'Waterslide Wonderland');
INSERT INTO Property VALUES (6, 'Hotel California');
INSERT INTO Property VALUES (7, 'Calvin University ');
INSERT INTO Property VALUES (8, 'Staples Arena');
INSERT INTO Property VALUES (9, 'Wowser Trousers');
INSERT INTO Property VALUES (10, 'Meme Arena');

INSERT INTO SaveGame VALUES (1, 1, 3, 0, 0);
INSERT INTO SaveGame VALUES (1, 2, 6, 1, 1);
INSERT INTO SaveGame VALUES (2, 3, 10, 5, 3);