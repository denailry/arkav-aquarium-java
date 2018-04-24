JC = javac
LIB = ;lib/junit-4.12.jar
SOURCES = \
	src/Tester.java \
	src/Tick/Tick.java \
	src/Tick/TickTest.java
TARGET = bin

default:
	$(JC) -cp .$(LIB) $(SOURCES) -d $(TARGET)