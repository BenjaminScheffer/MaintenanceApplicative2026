package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int ZERO = 0;
    public static final int FIFTY = 50;
    public static final int ELEVEN = 11;
    public static final int SIX = 6;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            this.updateQuality(items[i]);
        }
    }

    public void updateQuality(Item item){
        switch (item.name){
            case AGED_BRIE :
                this.updateQualityAgedBrie(item);
                break;
            case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                this.updateQualityBackstage(item);
                break;
            case SULFURAS_HAND_OF_RAGNAROS :
                break;
            default:
                this.updateQualityDefaultItem(item);
        }
    }

    public void updateQualityAgedBrie(Item item){
        item.sellIn--;
        if(item.quality < FIFTY){
            item.quality++;
        }
        if(item.sellIn < ZERO && item.quality < FIFTY){
            item.quality++;
        }
    }

    public void updateQualityBackstage(Item item){
        item.sellIn--;
        if(item.sellIn < ZERO){
            item.quality = 0;
        }else{
            if(item.quality < FIFTY){
                item.quality++;
                if(item.sellIn < ELEVEN && item.quality < FIFTY){
                    item.quality++;
                }
                if(item.sellIn < SIX && item.quality < FIFTY){
                    item.quality++;
                }
            }
        }
    }

    public void updateQualityDefaultItem(Item item){
        if (item.quality > ZERO) {
            item.quality--;
        }
        item.sellIn--;
        if(item.sellIn<ZERO){
            if (item.quality > ZERO) {
                item.quality--;
            }
        }
    }

}
