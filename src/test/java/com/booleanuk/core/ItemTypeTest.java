package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTypeTest {

    @Test
    public void testGetPrice(){
        Assertions.assertEquals(ItemTypeEnum.BGLS.getPrice(), 0.49);
    }
    @Test
    public void testGetName(){
        Assertions.assertEquals(ItemTypeEnum.BGLS.getName(), "Bagel");
    }

    @Test
    public void testGetVariant(){
        Assertions.assertEquals(ItemTypeEnum.BGLS.getVariant(), "Sesame");
    }

}
