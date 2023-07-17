| Class        	| Fields                   	| Methods                                    	| Output 	|
|--------------	|--------------------------	|--------------------------------------------	|--------	|
| Basket       	| coffeeBagel: double      	| addItem(ItemTypeEnum itemType)             	| void   	|
|              	|                          	| addItem(ItemTypeEnum itemType, int number) 	| void   	|
|              	| List<Item> basket        	| removeItem(ItemTypeEnum itemType)          	| void   	|
|              	| capacity: int            	| checkCapacity()                            	| int    	|
|              	| totalCost: double        	| getTotalCostWithDiscount()                 	| double 	|
|              	|                          	| getBasketSize()                            	| int    	|
|              	| discountOnCoffee: double 	|                                            	|        	|
| Item         	| type: ItemTypeEnum       	| update()                                   	| void   	|
|              	| count: int               	|                                            	|        	|
|              	| price: double            	|                                            	|        	|
|              	| withoutDiscount: int     	|                                            	|        	|
| ItemTypeEnum 	| name: String             	|                                            	|        	|
|              	| variant: String          	|                                            	|        	|
|              	| price: double            	|                                            	|        	|
| Receipt      	|                          	| receiptWithDiscount(Basket basket)         	| String 	|
|              	|                          	| normalReceipt(Basket basket)               	| String 	|