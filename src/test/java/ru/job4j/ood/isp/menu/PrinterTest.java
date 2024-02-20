package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class PrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private final String nextLine = System.lineSeparator();
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void byDefault() {
        System.setOut(stdOut);
    }

    @Test
    public void whenUsePrinterThenGetSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add(Menu.ROOT, "Сделать урок на jobj4", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add("Сделать урок на jobj4", "Создать меню", STUB_ACTION);
        menu.add("Сделать урок на jobj4", "Написать тесты", STUB_ACTION);
        menu.add("Написать тесты", "Написать тест на метод select()", STUB_ACTION);
        MenuPrinter printer = new Printer();
        printer.print(menu);
        String expected = "1.Сходить в магазин" + nextLine
                + "----1.1.Купить продукты" + nextLine
                + "--------1.1.1.Купить хлеб" + nextLine
                + "--------1.1.2.Купить молоко" + nextLine
                + "2.Покормить собаку" + nextLine
                + "3.Сделать урок на jobj4" + nextLine
                + "----3.1.Создать меню" + nextLine
                + "----3.2.Написать тесты" + nextLine
                + "--------3.2.1.Написать тест на метод select()";
        assertThat(expected).isEqualTo(outputStream.toString().trim());
    }
}