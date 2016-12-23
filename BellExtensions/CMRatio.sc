/* 
<Ambisonic Bell Texture - James Wenlock>
Center for Digital Arts and Experimental Media, University of Washington - https://dxarts.washington.edu/
   
   Copyright (C) <2016>  <James Wenlock>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
*/
CMRatio {
	var cm;
	var vals;

	*new {arg num, partial;
		^super.new.initCM(num, partial);
	}

	initCM {arg n, p;
		p.even.if({
			cm = [                        // p is even case
				(p)/2 * (1 + n) + 1,      // carrier
				(1 + n)                   // modulator
			]
		},{
			cm = [                        // p is odd case
				(p + 1)/2 * (1 + n) - 1,  // carrier
				(1 + n)                   // modulator
			]
		});
		cm.postln;
	}

	returnCM {
		^cm;
	}
}
