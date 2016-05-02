

from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from NYCZip.items import ZipItem

class ZipSpider(BaseSpider):
    name = "zip"
    allowed_domains = ["city-data.com"]
    start_urls = ["http://www.city-data.com/zipmaps/New-York-New-York.html"]

    def parse(self, response):
        hxs = HtmlXPathSelector(response)
        titles = hxs.xpath("//div[@class='zip-codes']/p/a[@href]")
        items = []
        for titles in titles:
            item = ZipItem()
            item["areaZip"] = titles.select("text()").extract()
            items.append(item)
        return items