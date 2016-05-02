

import csv

reader=csv.reader(open('restaurants_all_trimmed.csv', 'r'), delimiter=',')
writer=csv.writer(open('items_all_trimmed_delimiter2', 'w'), delimiter='|')


headers = next(reader, None) #retrieve the header line
for row in reader:
	if row is not headers:
		writer.writerow(row)
		
with open('items_all_trimmed_delimiter2', 'r') as fr:
	with open('items_delimiter_nocomma2', 'w') as fw:
		for line in fr:
			line = line.replace(",", " ")
			fw.write(line)
