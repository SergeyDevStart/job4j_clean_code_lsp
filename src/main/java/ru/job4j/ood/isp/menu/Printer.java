package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        StringBuilder indent = new StringBuilder("----");
        int prefix = 4;
        for (Menu.MenuItemInfo item : menu) {
            int currentSizePrefix = item.getNumber().length();
            if (currentSizePrefix == prefix) {
                System.out.printf("%s%s%s%n", indent, item.getNumber(), item.getName());
            } else if (currentSizePrefix > prefix) {
                prefix = currentSizePrefix;
                indent.append("----");
                System.out.printf("%s%s%s%n", indent, item.getNumber(), item.getName());
            } else {
                System.out.printf("%s%s%n", item.getNumber(), item.getName());
                indent.replace(0, indent.length(), "----");
                prefix = 4;
            }
        }
    }
}
