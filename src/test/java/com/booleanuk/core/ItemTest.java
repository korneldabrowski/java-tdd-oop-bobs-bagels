package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    public void testSetGetType(){
        Item item = new Item(ItemTypeEnum.BGLE, 2);
        item.setType(ItemTypeEnum.BGLS);
        Assertions.assertEquals(ItemTypeEnum.BGLS, item.getType());
    }

    @Test
    public void testSetGetCount(){
        Item item = new Item(ItemTypeEnum.BGLE, 2);
        item.setCount(4);
        Assertions.assertEquals(4, item.getCount());
    }
}
