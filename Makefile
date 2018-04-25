JC = javac
LIB = lib/junit-4.12.jar
SOURCES = \
	helper/Tester.java \
	helper/Runner.java \
	helper/Checkstyler.java \
	src/Tick/Tick.java \
	src/Space/Space.java \
	src/Entity/Entity.java \
	src/Entity/EntityTest.java \
	src/Entity/EntityType.java \
	src/Entity/DummyEntity.java \
	src/Item/Item.java \
	src/Food/Food.java \
	src/Coin/Coin.java \
	src/Coin/CoinTest.java \
	src/Element/Element.java \
	src/Element/ElementTest.java \
	src/LinkedList/LinkedList.java \
	src/LinkedList/LinkedListTest.java \
	src/Fish/Fish.java \
	src/Fish/FishTest.java \
	src/Guppy/Guppy.java \
	src/Guppy/GuppyTest.java \
	src/Piranha/Piranha.java \
	src/Piranha/PiranhaTest.java \
	src/Aquarium/Aquarium.java \
	src/Aquarium/AquariumTest.java \
	src/Aquarium/ShopItem.java \
	src/Snail/Snail.java \
	src/Snail/SnailTest.java \
	src/Area/Area.java \
	src/Screen/Screen.java \
	src/Frame/Frame.java \
	src/Main.java
TARGET = bin

default:
	$(JC) -cp $(LIB) $(SOURCES) -d $(TARGET)