@echo off

set CD=%~dp0
set javafxHome=%CD%lib\javafx-sdk-17.0.0.1\lib
set mainPath=%CD%src\com\company
set logPath=%CD%lib\logback

set GraphChessBoard=-d bin %mainPath%\GraphChessBoard.java
set Game=-d bin %mainPath%\Game.java
set AngularField=-d bin %mainPath%\Fields\AngularField.java
set CommonField=-d bin %mainPath%\Fields\CommonField.java
set Field=-d bin %mainPath%\Fields\Field.java
set pieces=-d bin %mainPath%\figures\*.java
set Figure=-d bin %mainPath%\figures\Figure.java
set Main=-d bin %mainPath%\Main.java


javac --module-path "%javafxHome%" --upgrade-module-path "%logPath%" --add-modules ALL-MODULE-PATH %Figure% %pieces% %Field% %CommonField% %AngularField% %GraphChessBoard% %Game% %Main%