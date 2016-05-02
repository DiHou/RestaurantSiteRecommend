
import csv

reader=csv.reader(open('zip_demo_update.csv', 'r'), delimiter=',')
writer=csv.writer(open('zip_demo_update_delimiter.txt', 'w'), delimiter='|')


headers = next(reader, None) #retrieve the header line
for row in reader:
	if row is not headers:
		writer.writerow(row) #new delimiter output
		
with open('zip_demo_update_delimiter.csv', 'r') as fr:
	with open('zipdemo_delimiter_nocomma.csv', 'w') as fw:
		for line in fr:
			line = line.replace(",", "")
			line = line.replace("$", "")
			fw.write(line) #remove commas, $ signs output
