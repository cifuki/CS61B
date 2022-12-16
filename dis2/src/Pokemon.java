public class Pokemon {
    public String name;
    public int level;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static void main(String[] args) {
        Pokemon p = new Pokemon("Pikachu", 17);
        int level = 100;
        change(p, level);
        //最后输出的是什么？
        System.out.println("Name: " + p.name + ", Level: " + p.level);
    }

    // 入参和函数中的参数重名时，如何判断赋值呢？
    public static void change(Pokemon poke, int level) {
        poke.level = level;
        poke = new Pokemon("Gengar", 1);
        level = 50;
    }
}
