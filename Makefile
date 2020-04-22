.PHONY = make jar runjar test clean

# replace with path to your javac,java,jar,javafx installations
JC =  C:\Program Files\AdoptOpenJDK\jdk-11.0.5.10-hotspot\bin\javac.exe
JAR = C:\Zhiwei_Song\Wisconsin\My_Eclipse\Proudly_Cheese\executable.jar
JAVA = C:\Program Files\AdoptOpenJDK\jdk-11.0.5.10-hotspot\bin\javaw.exe
MP = --module-path C:\Users\jerry\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.fxml #-Dfile.encoding=UTF-8 
CP = -classpath "C:\Program Files\AdoptOpenJDK\jdk-11.0.5.10-hotspot\bin\javaw.exe" --module-path "C:\Users\jerry\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml -Dfile.encoding=UTF-8 -classpath "C:\Zhiwei_Song\Wisconsin\My_Eclipse\Proudly_Cheese;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx.base.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx.controls.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx.fxml.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx.graphics.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx.media.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx.swing.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx.web.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\javafx-swt.jar;C:\Users\jerry\javafx-sdk-11.0.2\lib\src.zip" application.Main 
APP = application.Main

#CLASSPATH = .:junit-platform-console-standalone-1.5.2.jar:json-simple-1.1.1.jar

make: 
	$(JC) $(MP) $(CP) -d . application/*.java

run:
	$(JAVA) $(MP) $(CP) application.Main

fx: 
	$(JC) $(MP) $(CP) -d . application/*.java

fxrun:
	$(JAVA) $(MP) $(CP) $(APP)

jar: 
	$(JAR) cvmf manifest.txt executable.jar .

runjar:
	java $(MP) -jar executable.jar

zip:
	zip team.zip application/* *

test: 
	javac $(MP) -cp $(CLASSPATH) *.java
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path $(CLASSPATH) -p ""

clean:
	\rm application/*.class
	\rm executable.jar
