import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

class ToyStore {
    private List<Toy> toys = new ArrayList<>();

    public void addToy(int id, String name, int quantity, double weight) {
        Toy newToy = new Toy(id, name, quantity, weight);
        toys.add(newToy);
    }

    public void updateWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                return;
            }
        }
        System.out.println("Игрушка с ID " + toyId + " не найдена.");
    }

    public Toy drawToy() {
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double randomNumber = new Random().nextDouble() * totalWeight;

        double currentWeight = 0;
        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomNumber <= currentWeight) {
                return toy;
            }
        }

        // Вернуть null, если что-то пошло не так
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.addToy(1, "Мяч", 10, 20);
        toyStore.addToy(2, "Кукла", 5, 30);
        toyStore.addToy(3, "Машинка", 8, 50);

        toyStore.updateWeight(1, 25); // Обновить вес (частоту выпадения) мяча

        System.out.println("Розыгрыш игрушек:");
        for (int i = 0; i < 5; i++) {
            Toy drawnToy = toyStore.drawToy();
            if (drawnToy != null) {
                System.out.println("Выпала игрушка: " + drawnToy.getName());
            } else {
                System.out.println("Что-то пошло не так при розыгрыше.");
            }
        }
    }
}