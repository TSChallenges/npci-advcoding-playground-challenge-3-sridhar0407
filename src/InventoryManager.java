import java.io.*;
import java.util.*;

public class InventoryManager {
    private static final String INVENTORY_FILE = "src/inventory.txt";
    public static void main(String[] args) {
        // Entry point for the program
        // TODO: Implement menu-driven program for inventory management
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Item");
            System.out.println("3. Update Item");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    readInventory(INVENTORY_FILE);
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String newItem = scanner.nextLine();
                    System.out.print("Enter item count: ");
                    int newCount = scanner.nextInt();
                    addItem(INVENTORY_FILE, newItem, newCount);
                    break;
                case 3:
                    System.out.print("Enter item name to update: ");
                    String updateItem = scanner.nextLine();
                    System.out.print("Enter new item count: ");
                    int updateCount = scanner.nextInt();
                    updateItem(INVENTORY_FILE, updateItem, updateCount);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }


    public static void readInventory(String fileName) {
        // TODO: Read and display inventory from file
        File file = new File(INVENTORY_FILE);
        System.out.println("Checking file existence: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("Inventory file not found. No items to display.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\nCurrent Inventory:");
            while ((line = br.readLine()) != null) {
                System.out.println("Read line: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }

    public static void addItem(String fileName, String itemName, int itemCount) {
        // TODO: Add a new item to the inventory
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INVENTORY_FILE, true))) {
            bw.write(itemName + "," + itemCount);
            bw.newLine();
            System.out.println("Item added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    public static void updateItem(String fileName, String itemName, int itemCount) {
        // TODO: Update the count of an existing item
        File file = new File(INVENTORY_FILE);
        if (!file.exists()) {
            System.out.println("Inventory file not found.");
            return;
        }
        List<String> lines = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(itemName)) {
                    line = itemName + "," + itemCount;
                    found = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
            return;
        }
        if (!found) {
            System.out.println("Item not found. Use add option.");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Item updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating inventory: " + e.getMessage());
        }
    }
}
