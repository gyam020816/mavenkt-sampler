package gr;

object GildedRose {
   /**
    * @param args
    */
   @JvmStatic
   fun main(args: Array<String>) {

      System.out.println("OMGHAI!")

      val items = listOf(
              Item("+5 Dexterity Vest", 10, 20),
              Item("Aged Brie", 2, 0),
              Item("Elixir of the Mongoose", 5, 7),
              Item("Sulfuras, Hand of Ragnaros", 0, 80),
              Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
              Item("Conjured Mana Cake", 3, 6)
      )

      updateQuality(items)
   }

   fun updateQuality(items: List<Item>) {
      for (item in items) {
         updateQuality(item)
      }
   }

   enum class SpecialItem(val itemName: String) {
      AGED_BRIE("Aged Brie"),
      BACKSTAGE("Backstage passes to a TAFKAL80ETC concert"),
      SULFURAS("Sulfuras, Hand of Ragnaros"),
      CONJURED("Conjured")
   }

   internal fun updateQuality(item: Item) {
      when (item.name) {
         GildedRose.SpecialItem.AGED_BRIE.itemName -> {
            decreaseSellIn(item)

            increaseQuality(item)
            if (item.sellIn < 0) {
               increaseQuality(item)
            }
         }
         GildedRose.SpecialItem.BACKSTAGE.itemName -> {
            decreaseSellIn(item)

            increaseQuality(item)

            if (item.sellIn < 10) {
               increaseQuality(item)
            }

            if (item.sellIn < 5) {
               increaseQuality(item)
            }

            if (item.sellIn < 0) {
               item.quality = 0
            }
         }
         GildedRose.SpecialItem.CONJURED.itemName -> {
            decreaseSellIn(item)
            decreaseQuality(item, 2)
            if (item.sellIn < 0) {
               decreaseQuality(item, 2)
            }
         }
         GildedRose.SpecialItem.SULFURAS.itemName -> {
         }
         else -> {
            decreaseSellIn(item)

            decreaseQuality(item)

            if (item.sellIn < 0) {
               decreaseQuality(item)
            }
         }
      }
   }

   private fun decreaseSellIn(item: Item) {
      item.sellIn = item.sellIn - 1
   }

   private fun increaseQuality(item: Item) {
      if (item.quality < 50) {
         item.quality = item.quality + 1
      }
   }

   private fun decreaseQuality(item: Item, quality: Int = 1) {
      val result = item.quality - quality
      item.quality = (if (result >= 0) result else 0)
   }
}