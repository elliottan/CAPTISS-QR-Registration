import pyqrcode, PIL, png, uuid

def qrcode(n, m):
    for i in range(n, m + 1):
        txt = uuid.uuid4().hex
        q = pyqrcode.create(txt)
        q.png(txt + ".png", scale=6)
        f.write("{}\n".format(txt))
    print("Done!")

if __name__ == '__main__':
    f = open("ids.txt", "w+")
    n = int(input("First num: "))
    m = int(input("Second num: "))
    print("Creating {} tags".format(m - n + 1))
    qrcode(n, m)
    f.close()
    input()