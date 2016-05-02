
from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from scrapy.contrib.spiders import CrawlSpider, Rule
from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor
import csv
from restaurants.items import RestaurantsItem

class MySpider(CrawlSpider):
	name = "restaurants"
	allowed_domains = ["yelp.com"]
	start_urls = []
	
	url_p1 = "http://www.yelp.com/search?find_desc=Restaurants&find_loc="
	url_p3 = "&start=00"
	zipfile_path = './zip.csv'
	with open(zipfile_path, 'rb') as f:
		reader = csv.reader(f)
		for row in reader:
			url_p2 = row[0]
			urls = url_p1 + url_p2 + url_p3
			start_urls.append(urls)
		
	rules = (
		Rule(SgmlLinkExtractor(allow=('start=',), restrict_xpaths=('//a[@class="page-option prev-next next"]',)), callback="parse_items", follow= True),
	)
	
		
	def parse_items(self, response):
		hxs = HtmlXPathSelector(response)
		levels = hxs.xpath("//div[@class='biz-listing-large']")
		items = []
		for levels in levels:
			item = RestaurantsItem()
			item["Name"] = levels.select(".//a[@class='biz-name']/span/text()").extract()
			item["Cuisine"] = levels.select(".//span[@class='category-str-list']/a/text()").extract()
			item["Price"] = levels.select(".//span[@class='business-attribute price-range']/text()").extract()
			item["Rating"] = levels.select(".//div[@class='rating-large']/i/@title").extract()
			item["Address"] = levels.select(".//div[@class='secondary-attributes']/address/text()[1]").extract()[0].strip()
			item["Neighborhood"] = levels.select(".//div[@class='secondary-attributes']/span/text()").extract()[0].strip()
			item["Zipcode"] = levels.select(".//div[@class='secondary-attributes']/address/text()[2]").extract()
			items.append(item)
		return items