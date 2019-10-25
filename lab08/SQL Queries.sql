--Exercise 8.1

--(a)
-- SELECT * 
-- FROM Game
-- ORDER BY time ASC;

--(b)
-- SELECT * 
-- FROM Game
-- WHERE time > '2018-10-18 4:30';

--(c)
-- SELECT * 
-- FROM Player
-- WHERE name IS NOT NULL;

--(d)
-- SELECT playerID 
-- FROM PlayerGame
-- score > 2000;

--(e)
-- SELECT * 
-- FROM Player
-- WHERE emailAddress LIKE '%@gmail.com%';

--Exercise 8.2

--(a)
-- SELECT PlayerGame.score
-- FROM PlayerGame, Player
-- WHERE PlayerGame.playerID = Player.ID
-- AND Player.name LIKE '%The King%'
-- ORDER BY PlayerGame.score DESC;

--(b)
-- SELECT Player.name
-- FROM PlayerGame, Player, Game
-- WHERE PlayerGame.playerID = Player.ID
-- AND PlayerGame.gameID = Game.ID
-- AND Game.time = '2006-06-28 13:20:00'
-- ORDER BY PlayerGame.score DESC
-- LIMIT 1;

--(c)
-- It makes sure that the two players are different. 

--(d)
-- We could join a table to itself to find all previous games played
