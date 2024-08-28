import csv
import datetime
from datetime import datetime
from os import path

class ServerAttackDetector:
    stringPath = ""

    def __init__(self, pathString):
        self.stringPath = pathString

    def detect(self):
        num_lines = 0
        lines = "none found"

        with open(self.stringPath, "r") as csv_file:
            csv_reader = csv.reader(csv_file)

            next(csv_reader)
            
            prevDate = datetime.strptime("0001-01-01 00:00:00.000", "%Y-%m-%d %H:%M:%S.%f")
            prevProtocol = ""
            prevSuspicious = ""

            for line in csv_reader:
                num_lines = num_lines + 1
                
                if("UDP" in line[2]):
                    if("suspicious" in line[12]):
                        if(float(line[1]) < 1.0):
                            if("UDP" in prevProtocol):
                                if("suspicious" in prevSuspicious):
                                    currDate = datetime.strptime(line[0], "%Y-%m-%d %H:%M:%S.%f")
                                    diffTime = currDate - prevDate
                                    if(diffTime.total_seconds() < 1.0):
                                        lines = line[0] + "," + line[1] + "," + line[2] + "," + line[3] + "," + line[4] + "," + line[5] + "," + line[6] + "," + line[7] + "," + line[8] + "," + line[9] + "," + line[10] + "," + line[11] + "," + line[12] + "," + line[13] + "," + line[14] + "," + line[15]
                                        return (num_lines, lines)
                        prevDate = datetime.strptime(line[0], "%Y-%m-%d %H:%M:%S.%f")
                        prevProtocol = "UDP"
                        prevSuspicious = "suspicious"
        return (num_lines , lines)

