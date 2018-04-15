public class Element <E> {
  //attribute
  private E *info;
  private Element<E> *next;
  //method
  public Element() {
    this.info = null;
    this.next = null;
  }

  public Element(E *info) {
    this.info = info;
    this.next = null;
  }

  public E* getInfo() {
    return this.info;
  }

  public Element<E>* getNext() {
    return this.next;
  }

  public void setInfo(E *info) {
    this.info = info;
  }

  public void setNext(Element<E> *next) {
    this.next = next;
  }
}
