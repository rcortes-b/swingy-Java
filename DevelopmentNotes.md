
In create hero:
	- In the "acceptButton" the user will be redirected (if not PopUp is shown) to heroMenu or to the Game (with the Hero selected to the game!!!)
	- In the "cancelButton" the user will be redirected to heroMenu or gameMenu
	** Both depends of the current GameStatus (if IN_HERO_MENU or IN_GAME_MENU)

In list Hero --> create a flag wich is a boolean and it adds 1 to the columns (if true) and if flag is True add a new Button which selects the hero depending of the index (CREATE AN ARRAY OF JButtons[])



**TO ADD:
	- en GUIBuilder -> in buildHeroListing() -> add the if inGame that are commented
	- en Console_View -> createHero() -> add the game redirect (last line before the throw)


**TO REFACTOR / FIX:
	- GuiBuilder is ugly code, I can improve something 100% (Some functions are not intuitive due to its multi-use)!!!