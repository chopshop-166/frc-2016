import cv2
import math

from pynetworktables import NetworkTable

NetworkTable.SetIPAddress("10.1.66.2")  # Connect to network tables on robot
NetworkTable.SetClientMode()
NetworkTable.Initialize()

# Connect specifically to vision NetworkTable
visionDataTable = NetworkTable.GetTable("visionDataTable")

# Define default values for Network Table Variables
visionDataTable.PutBoolean("isHot", False)
visionDataTable.PutNumber("skinnyOffset", 0.0)


def threshold_range(im, lo, hi):
    '''Returns a binary image if the values are between a certain value'''

    unused, t1 = cv2.threshold(im, lo, 255, type=cv2.THRESH_BINARY)
    unused, t2 = cv2.threshold(im, hi, 255, type=cv2.THRESH_BINARY_INV)
    return cv2.bitwise_and(t1, t2)


def findDistanceToTarget(width):
    # distance = (-15.26*math.log(width)+82.857)
    distance = (44.139 * math.exp((-0.012 * width)))
    return distance


def findAngle(distance):
    return math.degrees(math.atan(6.5 / distance))


def putText(img, text, pos, color):
    cv2.putText(img, text, pos, cv2.FONT_HERSHEY_PLAIN, 1, color, thickness=1)

vc = cv2.VideoCapture()


if not vc.open('http://10.1.66.11/mjpg/video.mjpg'):  # connect to Axis Camera
    # if not vc.open(0): #connect to Webcam
    print "Could not connect to camera"
    exit(1)
while cv2.waitKey(10) <= 0:
    # while i == 0:
    success, img = vc.read()
    if not success:
        print ("Failure")
        break

    # image processing
    # Convert original color image to hsv image
    hsv = cv2.cvtColor(img, cv2.cv.CV_BGR2HSV)

    # Split hsv image into hue/saturation/value images
    h, s, v = cv2.split(hsv)

    h = threshold_range(h, 40, 110)  # Isolate only the green in the image
    s = threshold_range(s, 90, 255)
    v = threshold_range(v, 20, 255)

    # Combine 3 thresholds into one final threshold image
    threshold = reduce(cv2.bitwise_and, (h, s, v))

    kernal = cv2.getStructuringElement(cv2.MORPH_RECT, (2, 2), anchor=(1, 1))

    # make threshold more solid
    morphed = cv2.morphologyEx(
        threshold, cv2.MORPH_CLOSE, kernal, iterations=5)

    contours, hierarchy = cv2.findContours(
        morphed.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_TC89_KCOS)
    simplecontours = [cv2.approxPolyDP(cnt, 5, True) for cnt in contours]

    color = cv2.cvtColor(morphed, cv2.cv.CV_GRAY2BGR)  # Change binary to color

    cv2.drawContours(color, simplecontours, -1, (0, 0, 255), thickness=2)

    x = []
    y = []
    w = []
    h = []
    a = []
    maxArea = 0
    maxAreaIndex = 0

    for partCount, contour, in enumerate(simplecontours):
        # Determine x,y,w,h by drawing a rectangle on contour
        (xtemp, ytemp, wtemp, htemp) = cv2.boundingRect(contour)
        atemp = wtemp * htemp

        # put x,y,w,h for each particle in an array
        x.append(xtemp + (wtemp / 2))
        y.append(ytemp + (htemp / 2))
        w.append(wtemp)
        h.append(htemp)
        a.append(atemp)

        if (atemp > maxArea):
            maxArea = atemp
            maxAreaIndex = partCount

        # Keep in mind, x and y are the actual centers, but xtemp and ytemp is
        # the upper left corner

        if(atemp > 1000):
            distToTarget = findDistanceToTarget(w[maxAreaIndex])
            cv2.circle(color, (xtemp + (wtemp / 2), ytemp + (htemp / 2)),
                       2, (0, 255, 0), thickness=-1)
            putText(color, " (%d,%d)" % (xtemp, ytemp),
                    (xtemp + wtemp, ytemp + (htemp / 2)), (255, 0, 255))
            putText(color, str(wtemp),
                    (xtemp + (wtemp / 2), ytemp + htemp + 30), (255, 0, 255))
            putText(color, str(htemp),
                    (xtemp - 30, ytemp + (htemp / 2)), (255, 0, 255))
            putText(color, "Distance To Target: %d" % distToTarget,
                    (0, 16), (0, 255, 255))
            putText(color, "Angle: %d" % distToTarget, (0, 32), (0, 255, 255))

    cv2.imshow("Cameras are awesome :D", color)
