import cv2
import numpy
import math
import time
from networktables import *

NetworkTable.setIPAddress("roborio-166-frc.local")
NetworkTable.setClientMode()
NetworkTable.initialize()


visionDataTable = NetworkTable.getTable("VisionDataTable") #Connect specifically to vision NetworkTable

#while not visionDataTable.isConnected():
    #time.sleep(.1)

print("Connected to NetworkTables")

visionDataTable.putNumber("shooterAngle",45.0) #Define default values for Network Table Variables
visionDataTable.putNumber("xPosition",0.0)

def find_distance(x1,y1,x2,y2):
    root = math.sqrt(  ((x2 - x1) ** 2) + ((y2 - y1) ** 2)  )
    rootInt = int(root)
    return rootInt

def threshold_range(im, lo, hi):
    '''Returns a binary image if the values are between a certain value'''
    
    unused, t1 = cv2.threshold(im, lo, 255, type=cv2.THRESH_BINARY)
    unused, t2 = cv2.threshold(im, hi, 255, type=cv2.THRESH_BINARY_INV)
    return cv2.bitwise_and(t1, t2)
def findDistanceToTarget(width):
    #note that the width is multiplied by 2 because of resolution change on the image
    #this change allows the new resolution to fit with the correct model
    
    distance = (44.139 * math.exp((-0.012 * (2 * width))))
    return distance

def findAngle(distance):
    angle = (.1183*(distance **2 ) - (3.468 * distance) + 69.203)
    return angle;

vc = cv2.VideoCapture()

if not vc.open('http://10.1.66.11/mjpg/video.mjpg'): #connect to Axis Camera at 10.1.66.11
    print ("Could not connect to camera")
    exit(1)

print ("Connected to Camera")


while cv2.waitKey(10) <= 0:
    success, img = vc.read()
    if not success:
        print ("Failure")
        break

    #cv2.imshow("Source", img) #Show the Source Image for testing
    
    #image processing

    scale = 1.0  
    img = (img * scale).astype(numpy.uint8) #Changes the brightness of the image (0-1)

    hsv = cv2.cvtColor(img,cv2.cv.CV_BGR2HSV) # Convert original color image to hsv image

    h, s, v = cv2.split(hsv) #Split hsv image into hue/saturation/value images
   
    h = threshold_range(h, 50, 80)
    s = threshold_range(s, 150, 255)
    v = threshold_range(v, 20, 255)

    #h = threshold_range(h, 55, 69)
    #s = threshold_range(s, 200, 255)
    #v = threshold_range(v, 10, 50)

    threshold = cv2.bitwise_and(h, cv2.bitwise_and(s, v)) #Combine 3 thresholds into one final threshold image

    kernal = cv2.getStructuringElement(cv2.MORPH_RECT, (2,2), anchor=(1,1))

    morphed = cv2.morphologyEx(threshold, cv2.MORPH_CLOSE, kernal, iterations=5) #make threshold more solid

    contours, hierarchy = cv2.findContours(morphed.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_TC89_KCOS)
    simplecontours = [cv2.approxPolyDP (cnt, 5, True) for cnt in contours]

    color = cv2.cvtColor(morphed,cv2.cv.CV_GRAY2BGR) # Change binary to color

    #cv2.drawContours(color, simplecontours, -1, (0,0,255), thickness = 2)
    
    x = []
    y = []
    w = []
    h = []
    a = []
    maxArea = 0;
    maxAreaIndex = 0;

    for partCount, contour, in enumerate(simplecontours):
               (xtemp, ytemp, wtemp, htemp) = cv2.boundingRect(contour) #Determine x,y,w,h by drawing a rectangle on contour

               x.append(xtemp + (wtemp/2)) #put x,y,w,h for each particle in an array
               y.append(ytemp + (htemp/2))
               w.append(wtemp)
               h.append(htemp)
               a.append(wtemp * htemp)
               atemp = wtemp * htemp

               if (atemp > maxArea):
                   maxArea = atemp;
                   maxAreaIndex = partCount;
                
               #Keep in mind, x and y are the actual centers, but xtemp and ytemp is the upper left corner

               if(atemp > 750) and not((abs((float(h[maxAreaIndex]) / float(w[maxAreaIndex])) - .7) < .25) and (float(cv2.contourArea(simplecontours[maxAreaIndex]) / maxArea) < .45)):
                    cv2.putText(color, "BAD", (x[partCount],y[partCount]), cv2.FONT_HERSHEY_PLAIN, 1, (0,0,255), thickness = 1)
                   
    #cv2.putText(color, "Area Ratio: %.2f" %(float(cv2.contourArea(simplecontours[maxAreaIndex]) / maxArea)), (0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255),thickness = 1)

                   
    if((maxArea > 750) and (abs((float(h[maxAreaIndex]) / float(w[maxAreaIndex])) - .7) < .25) and (float(cv2.contourArea(simplecontours[maxAreaIndex]) / maxArea) < .45)): #We have a good target, display numbers and send aiming data to roboRIO
        #Draw Good Particle
        cv2.drawContours(color, simplecontours, maxAreaIndex, (0,255,0), thickness = 2)

        cv2.putText(color, "GOOD", (x[maxAreaIndex] - (w[maxAreaIndex] / 6),y[maxAreaIndex]), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,0), thickness = 1)

        #Print Data sent to networktables
        #cv2.putText(color, "Distance To Target: %.2f" %(findDistanceToTarget((w[maxAreaIndex]))), (0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255),thickness = 1)
        #cv2.putText(color, "Angle: %d" % (findAngle(findDistanceToTarget((w[maxAreaIndex])))), (0,32), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255),thickness = 1)


        #Display target Data
        cv2.circle(color,(x[maxAreaIndex],y[maxAreaIndex]),2,(255,0,0),thickness = 1)
        #tempstring = " (%d,%d)" % (x[maxAreaIndex], y[maxAreaIndex])
        #cv2.putText(color, tempstring, (x[maxAreaIndex] + w[maxAreaIndex],y[maxAreaIndex]), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)
        #cv2.putText(color, "%d" %(w[maxAreaIndex]), (x[maxAreaIndex],y[maxAreaIndex] + h[maxAreaIndex] + 30), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)
        #cv2.putText(color, "%d" %(h[maxAreaIndex]), (x[maxAreaIndex] - 30 ,y[maxAreaIndex]), cv2.FONT_HERSHEY_PLAIN, 1, (255,0,255), thickness = 1)
        #cv2.circle(color,(simplecontours[maxAreaIndex][0][0][0] ,simplecontours[maxAreaIndex][0][0][1]),4,(255,0,0),thickness = -1)
        
        #Write to NetworkTable
        visionDataTable.putBoolean("isLargeTargetFound", True)
        visionDataTable.putNumber("xPosition", x[maxAreaIndex])
        visionDataTable.putNumber("shooterAngle", findAngle(findDistanceToTarget((w[maxAreaIndex]))))
        visionDataTable.putNumber("distanceToTarget", findDistanceToTarget(w[maxAreaIndex]))
       
    else: #No good target, send safe default values to roboRIO
        cv2.putText(color, "No Good Target Found - DON'T FIRE!",(0,16), cv2.FONT_HERSHEY_PLAIN, 1, (0,255,255),thickness = 1)
        visionDataTable.putBoolean("isLargeTargetFound", True)
        visionDataTable.putNumber("xPosition", 143) #The "center" as described by the Java code. Will prevent robot from turning
        visionDataTable.putNumber("shooterAngle", 45) #Safe angle that is around the other angles we use
        visionDataTable.putNumber("distanceToTarget", 10)
        

    cv2.imshow("Cameras are awesome :D", color)

cv2.destroyAllWindows()
exit(0);
