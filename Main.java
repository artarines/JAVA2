import java.util.ArrayList;
import java.util.Scanner;

// для зберігання адреси
class Address {
    String street;
    String building;
    String apartment;

    public Address(String street, String building, String apartment) {
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    //перевизначення методу класу Object для виведення
    @Override
    public String toString() {
        return "вул. " + street + ", буд. " + building + ", кв. " + apartment;
    }
}

// сутність "запис в журналі куратора"
class JournalEntry {
    String lastName;
    String firstName;
    String birthDate;
    String phone;
    Address address;

    public JournalEntry(String lastName, String firstName, String birthDate, String phone, Address address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    // імітація серіалізації в JSON за через toString() (з класу Object)
    @Override
    public String toString() {
        return "{\n" +
               "  \"Прізвище\": \"" + lastName + "\",\n" +
               "  \"Ім'я\": \"" + firstName + "\",\n" +
               "  \"Дата народження\": \"" + birthDate + "\",\n" +
               "  \"Телефон\": \"" + phone + "\",\n" +
               "  \"Адреса\": \"" + address.toString() + "\"\n" +
               "}";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<JournalEntry> journal = new ArrayList<>();

        System.out.println("Вітаємо в системі 'Журнал куратора'!");

        while (true) {
            System.out.println("\nОберіть дію:");
            System.out.println("1 - Додати новий запис");
            System.out.println("2 - Показати всі записи");
            System.out.println("3 - Вийти");
            System.out.print("Ваш вибір: ");
            
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("\n--- Введення даних студента ---");
                
                String lastName = inputWithValidation(scanner, "Введіть прізвище: ");
                String firstName = inputWithValidation(scanner, "Введіть ім'я: ");
                String birthDate = inputWithValidation(scanner, "Введіть дату народження (наприклад 01.01.2000): ");
                String phone = inputWithValidation(scanner, "Введіть номер телефону: ");
                
                System.out.println("--- Домашня адреса ---");
                String street = inputWithValidation(scanner, "Введіть вулицю: ");
                String building = inputWithValidation(scanner, "Введіть номер будинку: ");
                String apartment = inputWithValidation(scanner, "Введіть номер квартири: ");

                //створення об'єктів
                Address address = new Address(street, building, apartment);
                JournalEntry newEntry = new JournalEntry(lastName, firstName, birthDate, phone, address);
                
                // додавання до списку
                journal.add(newEntry);
                System.out.println("Запис успішно додано!");

            } else if (choice.equals("2")) {
                if (journal.isEmpty()) {
                    System.out.println("Журнал порожній. Додайте спочатку записи.");
                } else {
                    System.out.println("\nВсі записи в журналі");
                    for (int i = 0; i < journal.size(); i++) {
                        System.out.println("Запис #" + (i + 1) + ":");
                        System.out.println(journal.get(i).toString());
                    }
                }
            } else if (choice.equals("3")) {
                System.out.println("Робота завершена. До побачення!");
                break;
            } else {
                System.out.println("Неправильний вибір. Введіть 1, 2 або 3.");
            }
        }
        scanner.close();
    }

    // для валідації вводу (перевірка не порожності рядка)
    public static String inputWithValidation(Scanner scanner, String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("Помилка: Це поле не може бути порожнім. Спробуйте ще раз.");
            } else {
                break; // якщо дані введені правильно, вихід з циклу
            }
        }
        return input;
    }
}
