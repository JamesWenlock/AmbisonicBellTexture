BellTexture {
	var start, freq, gain, phase;
	var cutoff, lPFreq, family;
	var modAmt, pushAmt, rTTAmt, hilbertAmt, distance, distRate, distAmt, proxHP, rTTRates, pVals;
	var timeMul, noteRis, noteDec;
	var chorAmt, phaseAmt, devAmt, lPAmt;
	var pRatios, score, synth, synthSymb;
	var bellVals;
	var encoderKernel, encodeAmt, decoderKernel, decodeMix;
	var output;
	var max, min;
	var thisServer, sampleRate;

    *new {arg score, pRatios = [0.56, 0.92, 1.19, 1.7, 2.0, 2.74, 3.0, 3.76, 4.07], sampleRate;
		^super.new.init(score, pRatios, sampleRate);
    }

	init {arg thisScore, thisPRatios, thisSampleRate;
		sampleRate = thisSampleRate;
		max = thisPRatios.size;
		min = 1;
		thisServer = Server.default;  // hopefully this will actually be the server that is in use... for NRT, maybe not???
		decodeMix = 0;
		score = thisScore;
		pRatios = thisPRatios;
		family = pRatios[1] / pRatios[0];
		pVals = Array.fill(max, {arg i; i + min});
		synth = Dictionary.new;
		this.changeMod;
		this.changeSpace;
		this.changeKernels(
			FoaEncoderKernel.newDiffuse(sampleRate: sampleRate, score: score),
			FoaDecoderKernel.newUHJ(sampleRate: sampleRate, score: score)
		);
		this.selectSynth;
	}

	changeMod {arg chorus = 0, phase = 0, dev = 0, lp = 0;
		chorAmt = chorus;
		phaseAmt = phase;
		devAmt = dev;
		lPAmt= lp;
	}

	selectSynth {arg synthType = \AB;
		synthSymb = synthType;
	}

	selectFamily {arg pVals = [0, 0], ratio = 1.8461538461538, viewRatios = false;
		if (viewRatios,
			{
				"-----------".postln;
				"Ratios".postln;
				pRatios.do({arg ratio, i; ("" ++ (i + 1) ++ " | " ++ ratio).postln;});
				"".postln;
			}
		);
		if ((pVals[1] == 0) && (pVals[0] == 0),
			{family = ratio},
			{
				var checkPVals = {arg thesePVals;
					thesePVals.do({arg pVal, i;
						if (pVal < min,
							{pVal = min}
						);
						if (pVal > max,
							{pVal = max}
						);
						pVals[i] = pVal;
					})
				};

				checkPVals.value(pVals);

				family = pRatios[pVals[0]] / pRatios[pVals[1]];
				"Family".postln;
				[pRatios[pVals[0]], pRatios[pVals[1]]].postln;
				"-----------".postln;

			}
		)
	}


	randFamily {
		var ratios = [0, 0];
		{(ratios[0] == ratios[1]) || (ratios[0] < ratios[1])}.while ({
			ratios = {pRatios.choose}!2;
		});
	//	ratios.postln;
		family = ratios[0]/ratios[1];
	}

	changeKernels {arg encoder, decoder, mix = 0;
		encoderKernel = encoder;
		decoderKernel = decoder;
		encodeAmt = mix;
	}

	changeSpace {arg push = 0, hilbert = 0, rRates = [0, 0, 0], rAmt = 0, dist = 1, dRate = 0.3, dAmt = 0, hP = 40;
		pushAmt = push;
		hilbertAmt = hilbert;
		rTTAmt = rAmt;
		rTTRates = rRates;
		distance = dist;
		distRate = dRate;
		distAmt = dAmt;
		proxHP = hP;
	}

	generateSequence {arg start = 0, dur = 20, sFreq = 440, numNotes = 7, sGain = -10, sCutoff = 4000, sLPFreq = 4000;
		var starttime;

		freq = sFreq;
		gain = sGain;
		cutoff = sCutoff;
		lPFreq = sLPFreq;

		noteRis = 0.45;
		noteDec = 0.45;
		if ((numNotes > 1), {
		timeMul = dur / (numNotes * (1 - noteDec));
		starttime = timeMul - (noteDec * timeMul);
		}, {
			timeMul = dur;
			starttime = 0;
		});

		numNotes.do({arg i;
			this.playBell(
				nStart: start + (starttime * i),
				nDur: timeMul,
				nRis: noteRis,
				nDec: noteDec,
			);
		});

	}

	// Calculates A-Format params
	bellCalcs {arg thisCutoff, thisFreq, family, lPFreq;
	var freqVals;

	var phaseRange   = [0.1, 0.4];
	var chorusRange  = [0.1, 0.4];

	var devRateRange = [0.3, 0.8];

	var lPRateRange  = [0.3, 0.8];
	var lPModRange   = [0.8, 0.98];

	var calcPVals = {
			var thesePVals = [];
			{thesePVals.size < 4}.while ({
				var thisVal = pVals.choose;
				if ( (thesePVals.includes(thisVal)).not,
					{thesePVals = thesePVals.add(thisVal)}
				);
			});
			thesePVals;
	};

	var randPVals = calcPVals.value();


	freqVals = Array.fill(4,
		{arg i; var cm, carFreq, modFreq, dev, phaseRate, chorusRate, devMod, devRate, lPRate, lPMod, pVals;
			cm = CMRatio(family, randPVals[i]).returnCM;
			carFreq = thisFreq * cm[0];
			modFreq =  thisFreq * cm[1];
			dev = thisCutoff/thisFreq;
			dev = dev - cm[0];
			dev = dev / cm[1];
			dev = dev - 1;
			dev = dev * modFreq;
			dev = dev.abs;

			phaseRate = rrand(phaseRange[0], phaseRange[1]);
			chorusRate = rrand(chorusRange[0], chorusRange[1]);

			devMod = dev * 0.99;
			devRate = rrand(devRateRange[0], devRateRange[1]);

			lPMod  = lPFreq * rrand(lPModRange[0], lPModRange[1]);
			lPRate = rrand(lPRateRange[0], lPRateRange[1]);

			[carFreq, modFreq, dev, phaseRate, chorusRate, devRate, devMod, lPMod, lPRate];
	});
	bellVals = freqVals.flop;
	}

	// Plays fm bell
	playBell {arg nStart, nDur, nRis, nDec;
		"playing".postln;
		    freq.postln;
		    this.bellCalcs(cutoff, freq, family, lPFreq);
		    bellVals.postln;
			// Adds bell sound to score
			this.initSynths();
		    score.add(synth[synthSymb].note(starttime: nStart, duration: nDur)
				.dur_(nDur)
				.gain_(gain)
				.ris_(nRis)
				.dec_(nDec)
				.lPFreq_(lPFreq)
				.carFreq_(bellVals[0])
				.modFreq_(bellVals[1])
				.dev_(bellVals[2])
				.phaseRate_(bellVals[3])
				.chorusRate_(bellVals[4])
				.devRate_(bellVals[5])
				.devMod_(bellVals[6])
				.lPMod_(bellVals[7])
				.lPRate_(bellVals[8])
			);
		}

	// Initializes synths with given fields
	initSynths {
		synth.put(\AB, CtkSynthDef.new(\fmSpatSynthAB,
			{arg gain, ris, dec, dur, freq, lPFreq,

				// BELL CALCS //
				carFreq   = #[0, 0, 0, 0], modFreq    = #[0, 0, 0, 0],  dev     = #[0, 0, 0, 0],
			    phaseRate = #[0, 0, 0, 0], chorusRate = #[0, 0, 0, 0],  devRate = #[0, 0, 0, 0],
				devMod    = #[0, 0, 0, 0], lPMod      = #[0, 0, 0, 0],  lPRate  = #[0, 0, 0, 0];
				// --------- //

				// env vars
				var env = Dictionary.new;
				var vol;

				// osc vars
				var amp, car, mod, bellVals, chorMul, phaseMul, thisLPFreq, newLFMod, hilbert;  // added by JA

				// spatial vars
				var encoder = FoaEncoderMatrix.newAtoB;
				var rTTVals;
				var sig;
				var sigA, sigS;
				var bSig, dSig;
				var delayComp;

				// calculates delay compensation
				delayComp = ((encoderKernel.kernelSize-1)/2 + encoderKernel.kernelSize - thisServer.options.blockSize) / thisServer.sampleRate;

				// converts amps to db
				amp = gain.dbamp;

				// creates env
				env = EnvGen.kr(
					Env([0, 1, 1, 0], [ris, 1.0 - (ris + dec), dec], curve: [0, 0, 0]),
					levelScale: amp, timeScale: dur
				);


				// creates fm oscs
				mod = SinOsc.ar(
					modFreq + LFNoise2.kr(chorusRate, chorAmt),
					LFNoise2.kr(phaseRate, phaseAmt),
					dev + LFNoise2.kr(devRate, devMod * devAmt)
				);
				car = SinOsc.ar(carFreq + mod, 0, env);

				// adds LP Filter
				car = LPF.ar(car, lPFreq + LFNoise2.kr(lPRate, lPMod * lPAmt));

				// spatialial encoding
				 sigA = FoaEncode.ar(car, encoder);
				 sigS = FoaEncode.ar(sigA[0], encoderKernel);
				 sigA = DelayC.ar(sigA, delayComp, delayComp);
				//
				sig = (sigA * (1.0 - encodeAmt)) + (encodeAmt * sigS);

				// push
				sig = FoaPush.ar(sig, pushAmt);

				// phase rotation
				newLFMod = LFNoise2.kr([0.2]!4, 2pi);  // does expand!!
				hilbert = sig;
				// better... more SC
				hilbert.collectInPlace({arg item, i;
					item = (Hilbert.ar(item) * [newLFMod[i].cos, newLFMod[i].sin]).sum;
				});
				sig = (sig * (1.0 - hilbertAmt)) + (hilbertAmt * hilbert);

				// rotatation
				rTTVals  = LFNoise2.ar(rTTRates, rTTAmt);
				sig = FoaRTT.ar(sig, rTTVals[0], rTTVals[1], rTTVals[2]);

				// proximity
				sig = HPF.ar(sig, proxHP);
				sig = FoaProximity.ar(sig, distance + LFNoise2.kr(distRate, distance * distAmt));

				// decodes sig
				sig = FoaDecode.ar(sig, decoderKernel);

				Out.ar(0, sig);
			}
		));

	synth.put(\Omni, CtkSynthDef.new(\fmSpatSynthO,
			{arg gain, ris, dec, dur, freq, lPFreq,

				// BELL CALCS //
				carFreq   = #[0, 0, 0, 0], modFreq    = #[0, 0, 0, 0],  dev     = #[0, 0, 0, 0],
			    phaseRate = #[0, 0, 0, 0], chorusRate = #[0, 0, 0, 0],  devRate = #[0, 0, 0, 0],
				devMod    = #[0, 0, 0, 0], lPMod      = #[0, 0, 0, 0],  lPRate  = #[0, 0, 0, 0];
				// --------- //

				// env vars
				var env = Dictionary.new;
				var vol;

				// osc vars
				var amp, car, mod, bellVals, chorMul, phaseMul, thisLPFreq, newLFMod, hilbert;  // added by JA

				// spatial vars
				var encoder = FoaEncoderMatrix.newOmni;
				var rTTVals;
				var sig;
				var sigO, sigS;
				var bSig, dSig;
				var delayComp;

				// calculates delay compensation
				delayComp = ((encoderKernel.kernelSize-1)/2 + encoderKernel.kernelSize - thisServer.options.blockSize) / thisServer.sampleRate;

				// converts amps to db
				amp = gain.dbamp;

				// creates env
				env = EnvGen.kr(
					Env([0, 1, 1, 0], [ris, 1.0 - (ris + dec), dec], curve: [0, 0, 0]),
					levelScale: amp, timeScale: dur
				);

				// creates fm oscs
				mod = SinOsc.ar(
					modFreq + LFNoise2.kr(chorusRate, chorAmt),
					LFNoise2.kr(phaseRate, phaseAmt),
					dev + LFNoise2.kr(devRate, devMod * devAmt)
				);
				car = SinOsc.ar(carFreq + mod, 0, env);

				// adds LP Filter
				car = LPF.ar(car, lPFreq + LFNoise2.kr(lPRate, lPMod * lPAmt));

				// spatialial encoding
				 sigO = FoaEncode.ar(car[0], encoder);
				 sigS = FoaEncode.ar(car[0], encoderKernel);
				 sigO = DelayC.ar(sigO, delayComp, delayComp);
				sig = (sigO * (1.0 - encodeAmt)) + (encodeAmt * sigS);

				// push
				sig = FoaPush.ar(sig, pushAmt);

				// phase rotation
				newLFMod = LFNoise2.kr([0.2]!4, 2pi);  // does expand!!
				hilbert = sig;
				// better... more SC
				hilbert.collectInPlace({arg item, i;
					item = (Hilbert.ar(item) * [newLFMod[i].cos, newLFMod[i].sin]).sum;
				});
				sig = (sig * (1.0 - hilbertAmt)) + (hilbertAmt * hilbert);

				// rotatation
				rTTVals  = LFNoise2.ar(rTTRates, rTTAmt);
				sig = FoaRTT.ar(sig, rTTVals[0], rTTVals[1], rTTVals[2]);

				// proximity
				sig = HPF.ar(sig, proxHP);
				sig = FoaProximity.ar(sig, distance + LFNoise2.kr(distRate, distance * distAmt));

				// decodes sig
				sig = FoaDecode.ar(sig, decoderKernel);

				Out.ar(0, sig);
			}
		));

	}
}

/*

James Wenlock
DXARTS, University of Washington, 2016

*/
