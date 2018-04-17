JC = javac
LIB = ;lib/junit-4.12.jar
SOURCES = \
	src/Tester.java \
	src/Tick/Tick.java \
	src/Space/Space.java \
	src/Entity/Entity.java \
	src/Entity/EntityType.java \
	src/Item/Item.java \
	src/Food/Food.java \
	src/Coin/Coin.java \
	src/Element/Element.java \
	src/LinkedList/LinkedList.java \
	src/Fish/Fish.java \
	src/Guppy/Guppy.java \
	src/Piranha/Piranha.java \
	src/Aquarium/Aquarium.java \
	src/Aquarium/AquariumTest.java
TARGET = bin

default:
	$(JC) -cp .$(LIB) $(SOURCES) -d $(TARGET)