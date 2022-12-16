public class Cat {
    public String  name;
    public static String noise;

    public Cat(String name, String noise) {
        this.name = name;
        this.noise = noise;
    }

    public void play() {
        System.out.println(noise + " I'm " + name + " the cat!");
    }

    public static void anger() {
        noise = noise.toUpperCase();
    }

    public static void calm() {
        noise = noise.toLowerCase();
    }

    public static void main(String[] args) {
        Cat a = new Cat("Cream", "Meow!");
        Cat b = new Cat("Tubbs", "Nyan!");
        /**
         * 主要考察对静态变量的理解
         */
        a.play();
        b.play();
        Cat.anger();
        a.calm();
        a.play();
        b.play();
    }
}
