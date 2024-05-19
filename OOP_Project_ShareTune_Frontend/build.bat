@echo off
javac --module-path "C:\Program Files (x86)\Java\javafx-sdk-22\lib" --add-modules javafx.controls,javafx.fxml,javafx.media src/*.java
cd src
java --module-path "C:\Program Files (x86)\Java\javafx-sdk-22\lib" --add-modules javafx.controls,javafx.fxml,javafx.media Main