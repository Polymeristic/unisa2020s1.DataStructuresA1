package pack;

public class Main {

    public static void main(String[] args) {
	    LinkedList list = new LinkedList();
	    LinkedList listB = new LinkedList();

	    list.add("01", "02", "03", "04", "05", "06");
	    System.out.println(list);

        list.remove(2);
        System.out.println(list);

        list.add(3, "07");
        System.out.println(list);

        list.add(4, "08");
        System.out.println(list);

        list.add(0, "09");
        System.out.println(list);

        listB.add("0A");
        System.out.println(listB);

        listB.remove(0);
        System.out.println(listB);

        listB.add("0B");
        System.out.println(listB);

        listB.add(0, "0C");
        System.out.println(listB);

        listB.add("0D");
        System.out.println(listB);

        System.out.println("Index of 06 is " + list.indexOf("06"));
    }
}
