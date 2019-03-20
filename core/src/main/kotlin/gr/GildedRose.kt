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
      SULFURAS("Sulfuras, Hand of Ragnaros")
   }

   internal fun updateQuality(item: Item) {
      when (item.name) {
         GildedRose.SpecialItem.AGED_BRIE.itemName -> {
            if (item.quality < 50) {
               item.quality = item.quality + 1
            }
            item.sellIn = item.sellIn - 1
            if (item.sellIn < 0) {
               if (item.quality < 50) {
                  item.quality = item.quality + 1
               }
            }
         }
         GildedRose.SpecialItem.BACKSTAGE.itemName -> {
            if (item.quality < 50) {
               item.quality = item.quality + 1

               if (item.sellIn < 11) {
                  if (item.quality < 50) {
                     item.quality = item.quality + 1
                  }
               }

               if (item.sellIn < 6) {
                  if (item.quality < 50) {
                     item.quality = item.quality + 1
                  }
               }
            }
            item.sellIn = item.sellIn - 1
            if (item.sellIn < 0) {
               item.quality = item.quality - item.quality
            }
         }
         GildedRose.SpecialItem.SULFURAS.itemName -> {}
         else -> {
            if (item.quality > 0) {
               item.quality = item.quality - 1
            }

            item.sellIn = item.sellIn - 1

            if (item.sellIn < 0) {
               if (item.quality > 0) {
                  item.quality = item.quality - 1
               }
            }
         }
      }
   }
}