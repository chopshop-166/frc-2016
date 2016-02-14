from __future__ import print_function
import cv2
import math
from pynetworktables import NetworkTable

GREEN = (0, 255, 0)
MAGENTA = (255, 0, 255)
CYAN = (0, 255, 255)
BLUE = (0, 0, 255)


def threshold_range(im, lo, hi):
    '''Returns a binary image if the values are between a certain value'''
    unused, t1 = cv2.threshold(im, lo, 255, type=cv2.THRESH_BINARY)
    unused, t2 = cv2.threshold(im, hi, 255, type=cv2.THRESH_BINARY_INV)
    return cv2.bitwise_and(t1, t2)


def findAngle(distance):
    ''' Formula found using experimentation with given camera '''
    return math.degrees(math.atan(6.5 / distance))


def findDistanceToTarget(width):
    ''' Formula found using experimentation with given camera '''
    return 44.139 * math.exp((-0.012) * width)


def putText(img, text, pos, color):
    '''Add some default values to cv2.putText'''
    cv2.putText(img, text, pos, cv2.FONT_HERSHEY_PLAIN, 1, color, thickness=1)


def processImage(img):
    ' Image processing '

    # Convert original color image to hsv image
    hsv = cv2.cvtColor(img, cv2.cv.CV_BGR2HSV)

    # Split hsv image into hue/saturation/value images
    h, s, v = cv2.split(hsv)

    # Isolate only the green in the image
    h = threshold_range(h, 40, 110)
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

    # Change binary to color
    image = cv2.cvtColor(morphed, cv2.cv.CV_GRAY2BGR)

    cv2.drawContours(image, simplecontours, -1, BLUE, thickness=2)

    for partCount, contour, in enumerate(simplecontours):
        # Determine x,y,w,h by drawing a rectangle on contour
        (xtemp, ytemp, wtemp, htemp) = cv2.boundingRect(contour)

        # Rough area of the particle
        atemp = wtemp * htemp

        # Keep in mind, x and y are the actual centers, but xtemp and ytemp is
        # the upper left corner
        xcenter = xtemp + (wtemp/2)
        ycenter = ytemp + (htemp/2)

        if atemp > 1000:
            distToTarget = findDistanceToTarget(wtemp)
            cv2.circle(image, (xcenter, ycenter), 2, GREEN, thickness=-1)
            putText(image, " (%d,%d)" % (xtemp, ytemp),
                    (xtemp + wtemp, ycenter), MAGENTA)
            putText(image, str(wtemp),
                    (xcenter, ytemp + htemp + 30), MAGENTA)
            putText(image, str(htemp), (xtemp - 30, ycenter), MAGENTA)
            putText(image, "Distance: %d" % distToTarget, (0, 16), CYAN)
            putText(image, "Angle: %d" % findAngle(distToTarget),
                    (0, 32), CYAN)

    cv2.imshow("Vision 166", image)


def main():
    '''Main loop'''

    # Connect to network tables on robot
    NetworkTable.SetIPAddress("10.1.66.2")
    NetworkTable.SetClientMode()
    NetworkTable.Initialize()

    # Connect specifically to vision NetworkTable
    visionDataTable = NetworkTable.GetTable("visionDataTable")

    # Define default values for Network Table Variables
    visionDataTable.PutBoolean("isHot", False)
    visionDataTable.PutNumber("skinnyOffset", 0.0)

    vc = cv2.VideoCapture()

    # connect to Axis Camera
    if not vc.open('http://10.1.66.11/mjpg/video.mjpg'):
        print("Could not connect to camera")
        exit(1)

    while cv2.waitKey(10) <= 0:
        success, img = vc.read()
        if not success:
            print("Failure")
            break
        processImage(img)

if __name__ == '__main__':
    main()
