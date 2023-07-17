| Class        | members        |
|--------------|----------------|
| ItemTypeEnum | String name    |
|              | String variant |
|              | double price   |

| Class | members           |
|-------|-------------------|
| Item  | ItemTypeEnum type |
|       | int count         |
|       |                   |

| Class  | members              | method                                    | output | story                                             |
|--------|----------------------|-------------------------------------------|--------|---------------------------------------------------|
| Basket | int default capacity | addItem(ItemTypeEnum itemType)            | void   | adds 1 item of a given type to the basket         |
|        | List<Item> basket    | addItem(ItemTypeEnum itemType, int count) | void   | adds a specified amount of items to the basket    |
|        | int capacity         | removeItem(ItemTypeEnum itemType)         | void   | removes 1 item from the basket                    |
|        | double totalCost     | getBasketSize()                           | int    | checks the number of items in the basket          |
|        |                      | checkCapacity()                           | int    | check how many more items can I add to the basket |


