from serverattackdetector import ServerAttackDetector

d = ServerAttackDetector("C:/Users/Sean Xia/Documents/CSE216HW4/hw4testfile.csv")  # to be replaced by actual path #CIDDS-001-external-week1.csv
a, b = d.detect()
print(a)  # to verify whether your code is, indeed, performing lazy evaluation
print(b)  # to verify whether your code is, indeed, able to detect the first
          # instance of a potential attack
          