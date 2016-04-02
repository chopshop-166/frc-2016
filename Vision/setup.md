# Setting up an environment for OpenCV

Instructions below are written with Windows in mind.
For any Linux development, the instructions should translate easily

For this setup, it's highly recommended to use Anaconda, which comes with many useful libraries built in, and takes care of compiling the right versions of some libraries.

1. Download and install [Anaconda](https://www.continuum.io/downloads)
  - Make sure the path to the python executable is in `%PATH%`
  - You can open a command prompt and type `python` to verify that it installed
2. Download [OpenCV](http://opencv.org/downloads.html)
  - The code has been tested using OpenCV 2.4.9 and 3.1.0
3. Run the OpenCV executable and extract the files to someplace accessible (which we'll call `%opencv%`)
4. Copy `%opencv%\build\python\2.7\x86\cv2.pyd` into `C:\Anaconda\Lib\site-packages`
5. Copy all DLL files from `%opencv%\build\x86\vc12` into `C:\Anaconda`

To verify that it installed properly, launch IDLE (or `python.exe`) and type:

```python
import cv2
print(cv2.__version__)
```
