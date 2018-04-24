JC = javac
LIB = lib/junit-4.12.jar
SOURCES = \
	src/helper/Tester.java \
	src/Tick/Tick.java \
	src/Space/Space.java \
	src/Entity/Entity.java \
	src/Entity/EntityType.java \
	src/Fish/Fish.java \
	src/Fish/FishTest.java
TARGET = bin

default:
	$(JC) -cp $(LIB) $(SOURCES) -d $(TARGET)