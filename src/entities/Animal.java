package entities;

public abstract class Animal {
    double weight;                          //вес
    int maxSpeed;                           //(Макс кол-во клеток за раз)
    double maxSatiety;                      // максимальная сытость
    double actualSatiety;                   //(Фактическая сытость)

    public void eat(){                      //args-plant or animal? if there are food in the location
    }
    public void move(){                     // put the location
    }
    public void reproduce(){                // if we have pair in the location
    }
    public void die(){                      //if the animal is too hungry or be eaten
    }

}
