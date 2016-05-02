
from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from scrapy.contrib.spiders import CrawlSpider, Rule
from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor
import csv
from scrapy.http.request import Request
import urlparse
from ZipDemo.items import ZipdemoItem

class MySpider(BaseSpider):
	name = "demo"
	allowed_domains = ["city-data.com"]
	start_urls = ["http://www.city-data.com/zipmaps/New-York-New-York.html"]
	#items = []
	
	
	def parse(self, response):
		hxs = HtmlXPathSelector(response)
		links = hxs.xpath("//div[@class='zip data-block']")
		urls = []
		for links in links:
			yield Request(urlparse.urljoin("http://www.city-data.com/", links.select("./a[2]/@href").extract()[0]), callback = self.parse_links)
			
	
	def parse_links(self, response):
		hxs = HtmlXPathSelector(response)
		ziplinks = hxs.xpath("//body")
		item = ZipdemoItem()
		item["POP2013"] = ziplinks.select(".//div[@id='body']/text()[2]").extract()
		item["POP_DEN"] = ziplinks.select(".//table[@border='0']/tbody/tr/td/text()[2]").extract()
		item["COLI2013"] = ziplinks.select(".//div[@id='body']/text()[12]").extract()
		item["W_POP"] = ziplinks.select(".//div[@id='body']/div[11]/div/ul/li[3]/ul/li[1]/span/text()").extract()
		item["B_POP"] = ziplinks.select(".//div[@id='body']/div[11]/div/ul/li[3]/ul/li[2]/span/text()").extract()
		item["AI_POP"] = ziplinks.select(".//div[@id='body']/div[11]/div/ul/li[3]/ul/li[3]/span/text()").extract()
		item["A_POP"] = ziplinks.select(".//div[@id='body']/div[11]/div/ul/li[3]/ul/li[4]/span/text()").extract()
		item["NHOPI_POP"] = ziplinks.select(".//div[@id='body']/div[11]/div/ul/li[3]/ul/li[5]/span/text()").extract()
		item["HL_POP"] = ziplinks.select(".//div[@id='body']/div[11]/div/ul/li[3]/ul/li[8]/span/text()").extract()
		item["MEDIAN_AGE"] = ziplinks.select(".//div[@id='body']/div[19]/table/tbody/tr/td[1]/text()").extract()
		item["MEDIAN_INCOME"] = ziplinks.select(".//div[@id='body']/div[21]/table/tbody/tr[1]/td[2]/text()").extract()
		item["MEDIAN_GROSSRENT"] = ziplinks.select(".//div[@id='body']/p[4]/text()").extract()[1].strip()   	
		item["Zip"] = ziplinks.select(".//h1[@class='city']/span/text()").extract()[0].split(" ")[0]
		
		#items.append(item)
		return item
		
		