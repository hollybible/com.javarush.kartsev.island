package entities;


public class Herbivore extends Animal {

    public Herbivore(AnimalType type) {
        super(type);
    }

    @Override
    public void eat(Location location) {
        System.out.println(type.name() + " is grazing...");
        if (Math.random() < 0.9) { // 90% шанс на поедание растений
            location.consumePlants(this.getType().getFoodNeed());
            this.decreaseHunger(this.getType().getFoodNeed());
        }
    }

    @Override
    public void move(Island island) {
        System.out.println(type.name() + " is moving...");
        int x = (int) (Math.random() * island.getWidth());
        int y = (int) (Math.random() * island.getHeight());
        Location newLocation = island.getLocation(x, y);
        newLocation.addAnimal(this);
    }

    @Override
    public void reproduce(Location location) {
        System.out.println(type.name() + " is reproducing...");
        if (location.getAnimals().stream().anyMatch(a -> a.getType() == this.type)) {
            location.addAnimal(new Herbivore(this.type));
        }
    }

    @Override
    public void die() {
        System.out.println(type.name() + " has died.");
    }

    @Override
    public void checkIfStarved() {
        if (hunger >= 100) {
            this.die();
        }
    }
}
