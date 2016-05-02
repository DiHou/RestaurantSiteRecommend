

# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class ZipdemoItem(scrapy.Item):
    POP2013 = scrapy.Field()
    POP_DEN = scrapy.Field()
    COLI2013 = scrapy.Field() #cost of living index
    W_POP = scrapy.Field()
    B_POP = scrapy.Field()
    AI_POP = scrapy.Field()
    A_POP = scrapy.Field()
    NHOPI_POP = scrapy.Field()
    HL_POP = scrapy.Field()
    MEDIAN_AGE = scrapy.Field()
    MEDIAN_INCOME = scrapy.Field() #median household income
    MEDIAN_GROSSRENT = scrapy.Field()
    Zip = scrapy.Field()