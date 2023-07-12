package com.booleanuk.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Item {
    private ItemTypeEnum type;
    private int count;
}
