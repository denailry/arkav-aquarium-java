JC = javac
LIB = ;lib/junit-4.12.jar
SOURCES = \
	src/Tick/Tick.java \
	src/Space/Space.java \
	src/Entity/Entity.java \
	src/Entity/EntityType.java \
	src/Item/Item.java \
	src/Coin/Coin.java \
	src/Element/Element.java \
	src/LinkedList/LinkedList.java \
	src/Snail/Snail.java \
	src/Snail/SnailTest.java
TARGET = bin

default:
	$(JC) -cp .$(LIB) $(SOURCES) -d $(TARGET)