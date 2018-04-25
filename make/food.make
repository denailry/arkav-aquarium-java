JC = javac
LIB = lib/junit-4.12.jar
SOURCES = \
	helper/Tester.java \
	src/Tick/Tick.java \
	src/Space/Space.java \
	src/Entity/Entity.java \
	src/Entity/EntityType.java \
	src/Element/Element.java \
	src/LinkedList/LinkedList.java \
	src/Aquarium/Aquarium.java \
	src/Snail/Snail.java \
	src/Fish/Fish.java \
	src/Guppy/Guppy.java \
	src/Piranha/Piranha.java \
	src/Item/Item.java \
	src/Coin/Coin.java \
	src/Food/Food.java \
	src/Food/FoodTest.java
TARGET = bin

default:
	$(JC) -cp $(LIB) $(SOURCES) -d $(TARGET)