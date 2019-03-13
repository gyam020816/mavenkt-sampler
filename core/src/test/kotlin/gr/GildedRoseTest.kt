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
}