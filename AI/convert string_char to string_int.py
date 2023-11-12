
id = ""
for ky_tu in '00110BE':
    if not ky_tu.isdigit():
        ma_ascii = ord(ky_tu)
    else:
        ma_ascii = ky_tu  # Chuyển ky_tu thành số nguyên

    id = id + str(ma_ascii)
print(id)