@echo off

set CD=%~dp0
set mainPath=com\company
set binPath=%CD%bin

set GraphChessBoard=%mainPath%\GraphChessBoard.class
set Game=%mainPath%\Game.class
set AngularField=%mainPath%\Fields\AngularField.class
set CommonField=%mainPath%\Fields\CommonField.class
set Field=%mainPath%\Fields\Field.class
set pieces=%mainPath%\figures\*.class
set Main=%mainPath%\Main.class



cd %binPath%
jar cvfe ../Task_3_1.jar com.company.Main %pieces% %Field% %CommonField% %AngularField% %GraphChessBoard% %Game% %Main%