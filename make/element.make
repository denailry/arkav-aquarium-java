JC = javac
LIB = ;lib/junit-4.12.jar
SOURCES = \
	src/Element/Element.java \
	src/Element/ElementTest.java
TARGET = bin

default:
	$(JC) -cp .$(LIB) $(SOURCES) -d $(TARGET)