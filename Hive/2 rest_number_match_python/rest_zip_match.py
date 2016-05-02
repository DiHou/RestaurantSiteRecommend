import csv

reader1=csv.reader(open('rest_byzip', 'r'), delimiter=',')
reader2=csv.reader(open('rest_zip_crossjoin0', 'r'), delimiter=',')
writer=csv.writer(open('rest_zip_crossjoin', 'w'), delimiter=',')
	
for row1 in reader1:
	for row2 in reader2:
		if row1[0] == row2[0] and row1[1] == row2[1]:
			row2.append(row1[2])
			break
		else:
			row2.append("0")
			writer.writerow(row2)
	writer.writerow(row2)

for row_check in reader2:
	if len(row_check) != 3:
		row_check.append("0")
		writer.writerow(row_check)