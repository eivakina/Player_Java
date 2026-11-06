package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void shouldWinPlayer1() {
        Game game = new Game();
        Player p1 = new Player(1, "Olya", 100);
        Player p2 = new Player(2, "Anya", 50);

        game.register(p1);
        game.register(p2);

        int expected = 1;
        int actual = game.round("Olya", "Anya");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWinPlayer2() {
        Game game = new Game();
        Player p1 = new Player(1, "Olya", 50);
        Player p2 = new Player(2, "Anya", 100);

        game.register(p1);
        game.register(p2);

        int expected = 2;
        int actual = game.round("Olya", "Anya");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeDraw() {
        Game game = new Game();
        Player p1 = new Player(1, "Olya", 100);
        Player p2 = new Player(2, "Anya", 100);

        game.register(p1);
        game.register(p2);

        int expected = 0;
        int actual = game.round("Olya", "Anya");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenPlayer1NotRegistered() {
        Game game = new Game();
        Player p2 = new Player(2, "Anya", 100);

        game.register(p2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Olya", "Anya");
        });
    }

    @Test
    public void shouldThrowExceptionWhenPlayer2NotRegistered() {
        Game game = new Game();
        Player p1 = new Player(1, "Olya", 100);

        game.register(p1);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Olya", "Anya");
        });
    }

    @Test
    public void shouldThrowExceptionWhenBothPlayersNotRegistered() {
        Game game = new Game();

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Olya", "Anya");
        });
    }

    @Test
    public void testPlayerEqualsAndHashCode() {
        Player p1 = new Player(1, "Olya", 100);
        Player p2 = new Player(1, "Olya", 100);
        Player pDifferentId = new Player(2, "Olya", 100);
        Player pDifferentName = new Player(1, "Anya", 100);
        Player pDifferentStrength = new Player(1, "Olya", 50);
        Player pWithNullName1 = new Player(1, null, 100);
        Player pWithNullName2 = new Player(1, null, 100);

        Assertions.assertEquals(p1, p1);
        Assertions.assertEquals(p1, p2);
        Assertions.assertNotEquals(p1, null);
        Assertions.assertNotEquals(p1, "Строка");

        Assertions.assertNotEquals(p1, pDifferentId);
        Assertions.assertNotEquals(p1, pDifferentName);
        Assertions.assertNotEquals(p1, pDifferentStrength);

        Assertions.assertEquals(pWithNullName1, pWithNullName2);
        Assertions.assertNotEquals(p1, pWithNullName1);
        Assertions.assertNotEquals(pWithNullName1, p1);

        Assertions.assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testPlayerGetters() {
        Player p = new Player(1, "Olya", 100);
        Assertions.assertEquals(1, p.getId());
        Assertions.assertEquals("Olya", p.getName());
        Assertions.assertEquals(100, p.getStrength());
    }
}
