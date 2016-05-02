

# -*- coding: utf-8 -*-


import scrapy


class RestaurantsItem(scrapy.Item):
    Name = scrapy.Field()
    Cuisine = scrapy.Field()
    Price = scrapy.Field()
    Rating = scrapy.Field()
    Address = scrapy.Field()
    Neighborhood = scrapy.Field()
    Zipcode = scrapy.Field()
    
