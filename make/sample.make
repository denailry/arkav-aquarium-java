JC = javac
LIB = lib/junit-4.12.jar
SOURCES = \
	helper/Tester.java \
	src/Sample/Sample.java \
	src/Sample/SampleTest.java
TARGET = bin

default:
	$(JC) -cp $(LIB) $(SOURCES) -d $(TARGET)