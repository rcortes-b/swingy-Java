
# Development Notes

## TO DO
- Rethink the % of villains in the map, borders CANNOT have villains so loop start as y = 1 and ends as map_size - 1
- NEXT STEP IS IN GAME.JAVA LINE 74

## Next Step
	- Implement the game logic
	- Implement the battles
	- Implement the Console / GUI view

## To Add
	- en GUIBuilder -> in buildHeroListing() -> add the if inGame that are commented
	- en Console_View -> createHero() -> add the game redirect (last line before the throw)
	- En Game -> SimulateBattle -> displayResult()
	- En Game -> engGame -> showVictory() && showDefeat()

## To Fix

## To Refactor
	- GuiBuilder is ugly code, I can improve something 100% (Some functions are not intuitive due to its multi-use)!!!
	- getGUI() && getConsole() change for getViewModel()