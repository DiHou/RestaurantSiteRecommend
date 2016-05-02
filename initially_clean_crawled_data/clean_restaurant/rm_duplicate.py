

import csv

reader=csv.reader(open('items-all-copy.csv', 'r'), delimiter=',')
writer=csv.writer(open('items-all-revised.csv', 'w'), delimiter=',')

entries = set()

for row in reader:
   key = (row[2], row[6]) #use restaurant name and address as key to remove duplicated records

   if key not in entries:
      writer.writerow(row) # distinct data output
      entries.add(key)