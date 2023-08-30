package org.example;

class Notebok { // создаем объект Notebok
    private String model;
    private int ram;
    private int hdd;
    private String os;
    private String color;
    private double diag;
    private int price;

    public Notebok(String model, int ram, int hdd, String os, String color, double diag, int price) { // конструктор
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
        this.diag = diag;
        this.price = price;

    }

    // Геттеры для полей

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public double getDiag() {
        return diag;
    }

    @Override
    public String toString() {
        return String.format
                ("Производитель: %s \n " +
                        "объем оперативной памяти: %d Гб \n " +
                        "объем накопителя % d Гб \n " +
                        "ОС %s \n " +
                        "Диагональ %.1f \n " +
                        "Цена, руб.  %d \n" +
                        "Цвет корпуса %s\n", this.model, this.ram, this.hdd, this.os, this.diag, this.price, this.color);
    }
}

class App {
    public static void main(String[] args) {
        Set<Notebok> noteboks = new HashSet<>();
        // public Notebok(String model, int ram, int hdd, String os, String color, double diag, int price)
        noteboks.add(new Notebok("DNS", 8, 512, "Windows 10", "Черный", 15.6, 30000));
        noteboks.add(new Notebok("DNS", 4, 512, "Linux", "Серебряный", 15, 35000));
        noteboks.add(new Notebok("Lenovo", 8, 500, "Windows 11", "Черный", 15.6, 40000));
        noteboks.add(new Notebok("Acer", 4, 250, "Windows 11", "Черный", 11, 35000));
        noteboks.add(new Notebok("Honor", 16, 512, "Windows 11", "Черный", 16.1, 55000));
        noteboks.add(new Notebok("Apple", 32, 1024, "MacOS", "Черный", 13.3, 100000));
        noteboks.add(new Notebok("HP", 16, 1024, "Windows 11", "Белый", 15.6, 40000));
        noteboks.add(new Notebok("Xiomi", 16, 2048, "Linux", "Черный", 17, 70000));




        System.out.println("Введите цифру, соответствующую критерию фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("5 - Цена");
        System.out.println("6 - Диагональ экрана");

        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> filter = new HashMap<>();

        int selectUser = scanner.nextInt();
        filter.put(selectUser, null);

        if (selectUser == 1 || selectUser == 2 || selectUser == 5) {
            System.out.println("Введите минимальное значение параметра:");
            int minVolume = scanner.nextInt();
            filter.put(selectUser, minVolume);
        } else if (selectUser == 3 || selectUser == 4 ) {
            System.out.println("Введите значение:");
            String volume = scanner.next();
            filter.put(selectUser, volume);
        }
        else if (selectUser  == 6) {
            System.out.println("Введите значение:");
            double volume = scanner.nextDouble();
            filter.put(selectUser, volume);
        }

        filterAndPrint(noteboks, filter);
    }

    private static void filterAndPrint(Set<Notebok> notebooks, Map<Integer, Object> filter) {

        for (Notebok element : notebooks) {
            boolean match = true;

            for (Map.Entry<Integer, Object> entry : filter.entrySet()) {

                int criteriy = entry.getKey();
                Object entryValue = entry.getValue();

                switch (criteriy) {
                    case 1:
                        int volRam = (int) entryValue;
                        if (element.getRam() < volRam) {
                            match = false;
                        }
                        break;
                    case 2:
                        int volHdd = (int) entryValue;
                        if (element.getHdd() < volHdd) {
                            match = false;
                        }
                        break;
                    case 3:
                        String volOS = (String) entryValue;
                        if (!element.getOs().toLowerCase().contains(volOS.toLowerCase())) {

                            match = false;
                        }
                        break;
                    case 4:
                        String volColor = (String) entryValue;
                        if (!element.getColor().toLowerCase().equals(volColor.toLowerCase())) {
                            match = false;
                        }
                        break;
                    case 5:
                        int volPrice = (int) entryValue;
                        if (element.getPrice() < volPrice) {
                            match = false;
                        }
                        break;
                    case 6:
                        double volDiag = (double) entryValue;
                        if (element.getDiag() < volDiag) {
                            match = false;
                        }
                        break;

                }
            }

            if (match) {
                System.out.println(element);
            }
        }
    }
}