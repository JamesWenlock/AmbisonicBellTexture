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
(
var score;

// vars for NRT output
var	outputPath, headerFormat, sampleFormat, numOutputChannels;

var synth;

var start, dur, phase, freq, numNotes, gain, cut, lp, sampleRate;
var chorusMod, phaseMod, devMod, lpMod;
var push, hilbert, rRates, rAmt, dist, dRate, dModAmt, hP;
var calcVals;
var theseVals;
var encoderMix;
var playInitSound, playFinalSound, playSound;
var encoder, decoder;

//////////////////
// SCORE SET-UP
//////////////////

// set the NRT vars here...
outputPath = "~/Desktop/testCTK.wav"; // output file path
headerFormat = "WAV";                 // soundfile header format
sampleFormat = "int24";               // soundfile sample format
sampleRate = s.sampleRate;
numOutputChannels = 2;

// create a score
score = CtkScore.new;


// sequence vars
start      = 0;
dur        = 10;
phase      = 0;
freq       = 440/4;
numNotes   = 2;
gain       = -15;
cut        = 3000;
lp         = 2000;

// instance of BellTexture
~bell = BellTexture(score, sampleRate: sampleRate);

// Plays instance of 1 carrier running through omni encoder
playInitSound = {
// Creates bell texture
	~bell.selectSynth(\Omni);
	~bell.generateSequence(start, dur, freq, numNotes, gain, cut, lp);
};

// Plays instance of modulating A to B format signal with multiple modulating transforms
playFinalSound = {
	// Creates bell texture
	~bell = BellTexture(score, sampleRate: sampleRate);
	~bell.selectSynth(\AB);
	~bell.changeMod(0.5, pi, 1, 1);
	~bell.changeSpace(pi/4, 1, [0.3, 0.4, 0.35], 2pi, 2, 0.6, 0.9);
	~bell.generateSequence(start, dur, freq, numNotes, gain, cut, lp);

};

// Plays instance of raw A to B format signal
playSound = {
	// Creates bell texture
	~bell = BellTexture(score, sampleRate: sampleRate);
	~bell.selectSynth(\AB);
	~bell.changeKernels(
		FoaEncoderKernel.newDiffuse(sampleRate: sampleRate, score: score),
		FoaDecoderKernel.newUHJ(sampleRate: sampleRate, score: score),
		mix: 0);
	~bell.changeMod(0, 0, 0, 0);
	~bell.changeSpace(0, 0, [0, 0, 0], 0, 2, 0.3, 0);
	~bell.generateSequence(start, dur, freq, numNotes, gain, cut, lp);
};


//////////////////
// SCORE
//////////////////

// playInitSound.value();
// playFinalSound.value();
// playSound.value();

// Writes score to specified output path
score.write(
	outputPath.standardizePath,
	sampleRate: sampleRate,
	headerFormat: headerFormat,
	sampleFormat: sampleFormat,
	options: ServerOptions.new.numOutputBusChannels_(numOutputChannels)
);
)
