JC = javac
LIB = lib/junit-4.12.jar
SOURCES = \
	src/helper/Tester.java \
	src/Tick/Tick.java \
	src/Space/Space.java \
	src/Entity/EntityType.java \
	src/Entity/Entity.java \
	src/Entity/EntityTest.java
TARGET = bin

default:
	$(JC) -cp $(LIB) $(SOURCES) -d $(TARGET)