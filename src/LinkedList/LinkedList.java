public class LinkedList<E> {
  private int neff;

  private Element<E> first;

  public LinkedList() {
    this.first = null;
    this.neff = 0;
  }

  public LinkedList(LinkedList<E> linkedList) {
    this.neff = 0;
    this.add(linkedList);
  }

  public boolean isEmpty() {
    return first == null;
  }

  public E get(int index) {
    Element<E> P = first;
      for (int i = 0; i < index; ++i) {
        if (P == null) {
          return null;
        }
        P = P.getNext();
      }
      return P.getInfo();
  }

  public boolean remove(int index) {
    if (index < 0 || isEmpty()) {
      return false;
    } else if (index == 0) {
      this.first = this.first.getNext();
    } else {
      index--;
      Element<E> elmt = this.first;
      while (index > 0 && elmt != null) {
        elmt = elmt.getNext();
        index--;
      }
      if (elmt == null || elmt.getNext() == null) {
        return false;
      }
      Element<E> oldElmt = elmt.getNext();
      elmt.setNext(oldElmt.getNext());
    }
    this.neff--;
    return true;
  }

  public void add(E info) {
    if (isEmpty()) {
      this.first = new Element <E>();
      this.first.setInfo(info);
      this.first.setNext(null);
    } else {
      Element<E> elmt = this.first;
      while (elmt.getNext() != null) {
        elmt = elmt.getNext();
      }
      Element<E> newElement = new Element <E>();
      elmt.setNext(newElement);
      elmt.getNext().setInfo(info);
      elmt.getNext().setNext(null);
    }
    this.neff++;
  }

  public void add(LinkedList<E> linkedList) {
    Element<E> elmt = linkedList.first;
    while (elmt.getNext() != null) {
      this.add(elmt.getInfo());
      elmt = elmt.getNext();
    }
    this.add(elmt.getInfo());
  }

  public Element<E> getFirst() {
    return this.first;
  }

  public int size() {
    return this.neff;
  }
}
