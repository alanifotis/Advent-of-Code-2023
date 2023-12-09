link = 0
right = 0

numbers = { 1 : 'one', 
            2 : 'two',
            3 : 'three', 
            4 : 'four',
            5 : 'five',
            6 : 'six',
            7 : 'seven', 
            8 : 'eight',
            9 : 'nine'}

with open("input1") as file:
    for line in file.readlines():
        for links in line:
            try:
                link += (int(links) * 10)
                break
            except Exception as e:
                continue

        for ri in reversed(line):
            try:
                right += int(ri)
                break  # Stop after finding the first integer from the end
            except Exception as e:
                continue

total = right + link


print(**numbers)
    

print(total)
