### TODO
1. Add method "int size()" in LinkedList that count how many object it has.
2. Add method copy constructor to LinkedList
3. Add method "bool add(LinkedList)" in LinkedList

### NOTES
1. Entity's derivation can no longer get Entity's space instance. 
2. Entity's derivation can use "boolean move(double x, double y)" to move itself. Return true if success.
3. Entity's derivation can use "void remove()" to remove itself from the space;
4. Entity's derivation can use "bool isExist()" to check its existence.
5. Type of Entity can be retrieved from enum EntityType. Just call EntityType.COIN, EntityType.FOOD, etc...
6. Entity's constructor no longer need width and height. Use setWidth(double width) and setHeight(double height) instead.