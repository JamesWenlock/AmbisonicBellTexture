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
