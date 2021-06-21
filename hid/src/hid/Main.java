package hid;

public class Main {

    public static void main(String[] args) {
	    switch(args[0]) {
            case "init":
                Repository.createRepository();
                break;
            case "hide":
                try {
                    String fileName = args[1];
                    Repository.hide(fileName);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "recover":
                try {
                    String fileName = args[1];
                    Repository.recover(fileName);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                break;
        }
    }
}
