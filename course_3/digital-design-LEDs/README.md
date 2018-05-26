# digital-design-LED

Equipment:

* Artix-7 100TCSG324 FPGA, Nexys-4 DDR plate.

Nexys LED's stripe programming, stripe shows the following animation:
	
```
-000000000000000
--00000000000000
---0000000000000

...
...

-------------000
--------------00
---------------0
----------------
---------------0
--------------00
-------------000	

...
...

--00000000000000
-000000000000000
0000000000000000
```

There implemented 4 modes of frame change:

* SW = 0 - animation stops
* SW = 1 - new frame every second
* SW = 2 - new frame every 0.5 second
* SW = 3 - new frame every 0.2 second


