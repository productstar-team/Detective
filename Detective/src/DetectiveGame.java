import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureInventory {
    private static LinkedHashMap<String, Integer> inventory = new LinkedHashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Инвентарь приключенца!");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addItem();
                    break;
                case "2":
                    updateItemQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    findItem();
                    break;
                case "5":
                    showInventory();
                    break;
                case "6":
                    running = false;
                    System.out.println("Выход из программы. До свидания!");
                    break;
                default:
                    System.out.println("Неверный ввод. Повторите попытку.");
            }
        }
    }

    // Вывод меню
    private static void printMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1 - Добавить новый предмет");
        System.out.println("2 - Изменить количество предмета");
        System.out.println("3 - Удалить предмет");
        System.out.println("4 - Найти предмет по названию");
        System.out.println("5 - Показать весь инвентарь");
        System.out.println("6 - Выход");
        System.out.print("Ваш выбор: ");
    }

    // Добавление нового предмета
    private static void addItem() {
        System.out.print("Введите название предмета: ");
        String itemName = scanner.nextLine().trim();

        if (itemName.isEmpty()) {
            System.out.println("Название предмета не может быть пустым.");
            return;
        }

        System.out.print("Введите количество: ");
        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("Количество должно быть положительным числом.");
                return;
            }

            if (inventory.containsKey(itemName)) {
                System.out.println("Предмет уже существует в инвентаре. Хотите обновить его количество? (да/нет)");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("да")) {
                    inventory.put(itemName, inventory.get(itemName) + quantity);
                    System.out.println("Количество для \"" + itemName + "\" увеличено на " + quantity + ".");
                } else {
                    System.out.println("Предмет не был изменён.");
                }
            } else {
                inventory.put(itemName, quantity);
                System.out.println("Предмет \"" + itemName + "\" успешно добавлен.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: количество должно быть числом.");
        }
    }

    // Изменение количества предмета
    private static void updateItemQuantity() {
        System.out.print("Введите название предмета: ");
        String itemName = scanner.nextLine().trim();

        if (!inventory.containsKey(itemName)) {
            System.out.println("Предмет \"" + itemName + "\" не найден в инвентаре.");
            return;
        }

        System.out.print("Введите новое количество: ");
        try {
            int newQuantity = Integer.parseInt(scanner.nextLine().trim());
            if (newQuantity <= 0) {
                inventory.remove(itemName);
                System.out.println("Количество для \"" + itemName + "\" стало нулевым. Предмет удалён из инвентаря.");
            } else {
                inventory.put(itemName, newQuantity);
                System.out.println("Количество для \"" + itemName + "\" обновлено до " + newQuantity + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: количество должно быть числом.");
        }
    }

    // Удаление предмета
    private static void removeItem() {
        System.out.print("Введите название предмета для удаления: ");
        String itemName = scanner.nextLine().trim();

        if (inventory.remove(itemName) != null) {
            System.out.println("Предмет \"" + itemName + "\" успешно удалён.");
        } else {
            System.out.println("Предмет \"" + itemName + "\" не найден в инвентаре.");
        }
    }

    // Поиск предмета по названию
    private static void findItem() {
        System.out.print("Введите название предмета для поиска: ");
        String itemName = scanner.nextLine().trim();

        if (inventory.containsKey(itemName)) {
            int quantity = inventory.get(itemName);
            System.out.println("\"" + itemName + "\" – Количество: " + quantity);
        } else {
            System.out.println("Предмет \"" + itemName + "\" не найден в инвентаре.");
        }
    }

    // Показать весь инвентарь
    private static void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Инвентарь пуст.");
        } else {
            System.out.println("\nТекущий инвентарь:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " – " + entry.getValue());
            }
        }
    }
}