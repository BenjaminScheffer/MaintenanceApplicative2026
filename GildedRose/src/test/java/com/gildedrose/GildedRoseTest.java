package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void test1() {
        Item[] items = new Item[] { new Item("foo", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);

    }

    @Test
    void test2() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    @Test
    void test3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    @Test
    void test4() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void test5() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 52) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(52, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void test6() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(11, app.items[0].sellIn);
    }

    @Test
    void test7() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -12, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-13, app.items[0].sellIn);
    }

    @Test
    void test8() {
        Item[] items = new Item[] { new Item("AAA", -12, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(28, app.items[0].quality);
        assertEquals(-13, app.items[0].sellIn);
    }

    @Test
    void test9() {
        Item[] items = new Item[] { new Item("Aged Brie", -12, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(32, app.items[0].quality);
        assertEquals(-13, app.items[0].sellIn);
    }

    @Test
    void test10() {
        Item[] items = new Item[] { new Item("Aged Brie", -12, 550) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(550, app.items[0].quality);
        assertEquals(-13, app.items[0].sellIn);
    }

    @Test
    void test11() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -12, 550) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(550, app.items[0].quality);
        assertEquals(-12, app.items[0].sellIn);
    }

    @Test
    void test12() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 51) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(51, app.items[0].quality);
        assertEquals(11, app.items[0].sellIn);
    }

    @Test
    void test13() {
        Item[] items = new Item[] { new Item("aaaa", 12, 51) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(11, app.items[0].sellIn);
    }
    @Test
    void test14() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }
}
