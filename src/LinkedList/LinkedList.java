public class LinkedList <E> {
  //attribute
  private Element <E> first;
  //method
  public LinkedList() {
    this.first = null;
  }

  public bool isEmpty() {
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

  public bool remove(int index) {
    if (index < 0 || isEmpty()) {
      return false;
    } else if (index == 0) {
      // Element<E> oldFirst = this.first;
      this.first = this.first.getNext();
      // delete oldFirst;
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
      // delete oldElmt;
    }
    return true;
  }

  public void add(E info) {
    if (isEmpty()) {
      this.first = new Element <E>();
      this.first.setInfo(info);
      this.first.setNext(null);
    } else {
      Element <E> elmt = this.first;
      while (elmt.getNext() != null) {
        elmt = elmt.getNext();
      }
      Element <E> newElement = new Element <E>();
      elmt.setNext(newElement);
      elmt.getNext().setInfo(info);
      elmt.getNext().setNext(null);
    }
  }

  public Element<E> getFirst() {
    return this.first;
  }
}
