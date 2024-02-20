package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectThenReturnSame() {
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
        assertThat(new Menu.MenuItemInfo("Сделать урок на jobj4",
                List.of("Создать меню", "Написать тесты"), STUB_ACTION, "3."))
                .isEqualTo(menu.select("Сделать урок на jobj4").get());
        assertThat(new Menu.MenuItemInfo("Создать меню",
                List.of(), STUB_ACTION, "3.1.")).
                isEqualTo(menu.select("Создать меню").get());
        assertThat(new Menu.MenuItemInfo("Написать тесты",
                List.of("Написать тест на метод select()"), STUB_ACTION, "3.2."))
                .isEqualTo(menu.select("Написать тесты").get());
        assertThat(new Menu.MenuItemInfo("Написать тест на метод select()",
                List.of(), STUB_ACTION, "3.2.1.")).
                isEqualTo(menu.select("Написать тест на метод select()").get());
    }
}