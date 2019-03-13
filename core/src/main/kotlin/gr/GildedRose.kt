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

   fun updateQuality(items: List<Item>)
   {
      for ((i, item) in items.withIndex()) {
         if ((!"Aged Brie".equals(items.get(i).name)) && !"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).name))
         {
            if (items.get(i).quality > 0)
            {
               if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).name))
               {
                  items.get(i).quality = items.get(i).quality - 1
               }
            }
         }
         else
         {
            if (items.get(i).quality < 50)
            {
               items.get(i).quality = items.get(i).quality + 1

               if ("Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).name))
               {
                  if (items.get(i).sellIn < 11)
                  {
                     if (items.get(i).quality < 50)
                     {
                        items.get(i).quality = items.get(i).quality + 1
                     }
                  }

                  if (items.get(i).sellIn < 6)
                  {
                     if (items.get(i).quality < 50)
                     {
                        items.get(i).quality = items.get(i).quality + 1
                     }
                  }
               }
            }
         }

         if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).name))
         {
            items.get(i).sellIn = items.get(i).sellIn - 1
         }

         if (items.get(i).sellIn < 0)
         {
            if (!"Aged Brie".equals(items.get(i).name))
            {
               if (!"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).name))
               {
                  if (items.get(i).quality > 0)
                  {
                     if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).name))
                     {
                        items.get(i).quality = items.get(i).quality - 1
                     }
                  }
               }
               else
               {
                  items.get(i).quality = items.get(i).quality - items.get(i).quality
               }
            }
            else
            {
               if (items.get(i).quality < 50)
               {
                  items.get(i).quality = items.get(i).quality + 1
               }
            }
         }
      }
   }

}