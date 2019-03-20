package gr

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test



internal class GildedRoseTest {
    @Test
    internal fun `it should update quality???`() {
        val items = listOf(
                Item("+5 Dexterity Vest", 10, 20),
                Item("Aged Brie", 2, 0),
                Item("Elixir of the Mongoose", 5, 7),
                Item("Sulfuras, Hand of Ragnaros", 0, 80),
                Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                Item("Conjured Mana Cake", 3, 6)
        )

        // Exercise
        GildedRose.updateQuality(items)

        // Verify
        assertThat(items).isEqualTo(listOf(
                Item(name = "+5 Dexterity Vest", sellIn = 9, quality = 19),
                Item(name = "Aged Brie", sellIn = 1, quality = 1),
                Item(name = "Elixir of the Mongoose", sellIn = 4, quality = 6),
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80),
                Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 14, quality = 21),
                Item(name = "Conjured Mana Cake", sellIn = 2, quality = 5)
        ))
    }

    @Test
    internal fun `it should degrade twice as fast when sell date has passed`() {
        val item = Item(
                name = "something",
                sellIn = 0,
                quality = 50
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "something",
                sellIn = -1,
                quality = 48
        ))
    }

    @Test
    internal fun `it should not degrade quality below zero`() {
        val item = Item(
                name = "something",
                sellIn = 10,
                quality = 0
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "something",
                sellIn = 9,
                quality = 0
        ))
    }

    @Test
    internal fun `it should increase the quality of aged brie`() {
        val item = Item(
                name = "Aged Brie",
                sellIn = 10,
                quality = 30
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Aged Brie",
                sellIn = 9,
                quality = 31
        ))
    }

    @Test
    internal fun `it should not increase the quality of aged brie above 50`() {
        val item = Item(
                name = "Aged Brie",
                sellIn = 10,
                quality = 50
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Aged Brie",
                sellIn = 9,
                quality = 50
        ))
    }

    @Test
    internal fun `when the quality is above 50, the quality decreases (?!)`() {
        val item = Item(
                name = "something",
                sellIn = 10,
                quality = 9999
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "something",
                sellIn = 9,
                quality = 9998
        ))
    }

    @Test
    internal fun `when the quality is above 50, the quality remains the same for Aged Brie (?!)`() {
        val item = Item(
                name = "Aged Brie",
                sellIn = 10,
                quality = 9999
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Aged Brie",
                sellIn = 9,
                quality = 9999
        ))
    }

    @Test
    internal fun `it should not change Sulfuras`() {
        val item = Item(
                name = "Sulfuras, Hand of Ragnaros",
                sellIn = 0,
                quality = 80
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Sulfuras, Hand of Ragnaros",
                sellIn = 0,
                quality = 80
        ))
    }

    @Test
    internal fun `it should increase the quality of Backstage passes by 1 when there are equal or more than 11 days`() {
        val item = Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = 11,
                quality = 30
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = 10,
                quality = 31
        ))
    }

    @Test
    internal fun `it should increase the quality of Backstage passes by 2 when there are equal or less than 10 days`() {
        val item = Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = 10,
                quality = 30
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = 9,
                quality = 32
        ))
    }

    @Test
    internal fun `it should increase the quality of Backstage passes by 3 when there are equal or less than 5 days`() {
        val item = Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = 5,
                quality = 30
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = 4,
                quality = 33
        ))
    }

    @Test
    internal fun `it should set the quality of Backstage passes to 0 when there are equal or less than 0 days`() {
        val item = Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = 0,
                quality = 30
        )

        // Exercise
        GildedRose.updateQuality(item)

        // Verify
        assertThat(item).isEqualTo(Item(
                name = "Backstage passes to a TAFKAL80ETC concert",
                sellIn = -1,
                quality = 0
        ))
    }

//    companion object {
//        @JvmStatic
//        fun fds() = listOf(
//                1 to 2
//        )
//    }

//    @ParameterizedTest
//    @MethodSource("fds")
//    fun isOdd_ShouldReturnTrueForOddNumbers(number: Pair<Int, Int>) {
//        assertThat(number).isEqualTo(-10000)
//    }
}
