import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    Map<Integer, MenuRow> menu = new HashMap<>();

    public Menu(MenuRow[] rows) {
        int i = 0;
        for (MenuRow row : rows) {
            menu.put(i++, new MenuRow(row.getTitle(), row.getAction()));
        }
    }

    public MenuRow get(int i) {
        return menu.get(i);
    }

    public void print() {
        for (Map.Entry<Integer, MenuRow> pair : menu.entrySet()) {
            System.out.println(pair.getKey() + " 🧪 " + pair.getValue().getTitle());
        }
    }

    public static void main(String[] args) {
        Driver.main(args);
    }

}

class MenuRow {
    String title;
    Runnable action;

    // receives a string for the name and a runnable for the action
    public MenuRow(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return this.title;
    }

    public Runnable getAction() {
        return this.action;
    }

    public void run() {
        action.run(); // is run when user selects the row
    }
}

class Driver {
    public static void main(String[] args) {
        MenuRow[] rows = new MenuRow[] {
                new MenuRow("Sorts", () -> Sorts.main(null)),
                new MenuRow("Reverse Queue", () -> ReverseQueue.main(null)),
                new MenuRow("Queue", () -> QueueTester.main(null)),
                new MenuRow("Calculator", () -> Calculator.main(null)),
                new MenuRow("Merge Queue", () -> QueueMerge.main(null)),
                // new MenuRow("Matrix", () -> Matrix.main(null)),
                // new MenuRow("Swap", () -> IntByReference.main(null)),
                new MenuRow("Exit", () -> main(null)),
        };

        Menu menu = new Menu(rows);

        while (true) {
            menu.print();
            try {
                Scanner sc = new Scanner(System.in);
                int selection = sc.nextInt();
                try {
                    MenuRow row = menu.get(selection);
                    if (row.getTitle().equals("Exit"))
                        return;
                    row.run();
                } catch (Exception e) {
                    System.out.printf("Invalid selection %d\n", selection);
                }
            } catch (Exception e) {
                System.out.println("Not a number");
            }
        }
    }
}