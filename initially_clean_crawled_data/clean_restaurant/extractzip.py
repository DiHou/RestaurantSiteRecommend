

import csv

reader=csv.reader(open('restaurants_all_trimmed.csv', 'r'), delimiter=',')
writer=csv.writer(open('restaurant_delimiter.txt', 'w'), delimiter='|')

headers = next(reader, None) #retrieve the header line
for row in reader:
	if row is not headers:
		writer.writerow(row)


with open('restaurant_delimiter.txt', 'r') as fr:
	with open('restaurant_extractzip.txt', 'w') as fw:
		for line in fr:
			#split lines to extract zipcode column
			line = line.split("|")[0].strip() + "|"  + line.split("|")[1].strip() + "|" + line.split("|")[2].strip()+ "|" + line.split("|")[3].strip() + "|"+ line.split("|")[4].strip() + "|" + line.split("|")[6].strip() + "|" + line.split("|")[5].strip()[-15:-10]
			fw.write(line + "\n")

