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
numOutputChannels = 4;

// create a score
score = CtkScore.new;


// sequence vars
start      = 0;
dur        = 15;
phase      = 0;
freq       = 440/4;
numNotes   = 3;
gain       = -15;
cut        = 3000;
lp         = 2000;

playInitSound = {
	// synth set-up
	synth      = \fmSynthBasic;
	//synth      = \fmSynthSpatialB;
	//synth      = \fmSynthSpatialK;
	numOutputChannels = 2;
	encoder = FoaEncoderKernel.newDiffuse(sampleRate: sampleRate, score: score);
	decoder = FoaDecoderKernel.newUHJ(sampleRate: sampleRate, score: score);

	// mod vars
	chorusMod = 0.5;
	phaseMod  = 0.5;
	devMod    = 1;
	lpMod     = 1;

	// spatial vars
	encoderMix = 0.5;
	push    = pi/4;
	hilbert = 1;
	rRates  = [0.2, 0.3, 0.1];
	rAmt    = 2pi;
	dist    = 2;
	dRate   = 0.4;
	dModAmt    = 0.5;
	// Creates bell texture
	~bell = BellTexture(score, sampleRate: sampleRate);

	// Selects specified synth
	~bell.selectSynth(synth);
	//~bell.changeKernels(encoder,decoder);
	~bell.changeMod(chorusMod, phaseMod, devMod, lpMod);
	~bell.changeSpace(push, hilbert, rRates, rAmt, dist, dRate, dModAmt);
	~bell.generateSequence(start, dur, phase, freq, numNotes, gain, cut, lp);
};

playFinalSound = {
	// synth set-up
	//synth      = \fmSynthBasic;
	//synth      = \fmSynthSpatialB;
	synth      = \fmSynthSpatialK;
	numOutputChannels = 2;
	encoder = FoaEncoderKernel.newDiffuse(sampleRate: sampleRate, score: score);
	decoder = FoaDecoderKernel.newUHJ(sampleRate: sampleRate, score: score);

	// mod vars
	chorusMod = 0.5;
	phaseMod  = 0.5;
	devMod    = 1;
	lpMod     = 1;

	// spatial vars
	encoderMix = 0.5;
	push    = pi/4;
	hilbert = 1;
	rRates  = [0.2, 0.3, 0.1];
	rAmt    = 2pi;
	dist    = 4;
	dRate   = 0.2;
	dModAmt    = 0.5;
	// Creates bell texture
	~bell = BellTexture(score, sampleRate: sampleRate);

	// Selects specified synth
	~bell.selectSynth(synth);
	~bell.changeKernels(encoder,decoder);
	~bell.changeMod(chorusMod, phaseMod, devMod, lpMod);
	~bell.changeSpace(push, hilbert, rRates, rAmt, dist, dRate, dModAmt);
	~bell.generateSequence(start, dur, phase, freq, numNotes, gain, cut, lp);

};


playSound = {
	// synth set-up

	//synth    = \fmSynthBasic;
	//synth      = \fmSynthSpatialB;
	synth    = \fmSynthSpatialK;
	encoder = FoaEncoderKernel.newDiffuse(sampleRate: sampleRate, score: score);
	decoder = FoaDecoderKernel.newUHJ(sampleRate: sampleRate, score: score);
	encoderMix = 0;

	// mod vars
	chorusMod = 0;
	phaseMod  = 0;
	devMod    = 0;
	lpMod     = 0;

	// spatial vars
	encoderMix = 0;
	push    = 0;
	hilbert = 0;
	rRates  = [0, 0, 0];
	rAmt    = 0;
	dist    = 5;`
	dRate   = 0;
	dModAmt = 0;

	// Creates bell texture
	~bell = BellTexture(score, sampleRate: sampleRate);

	// Selects specified synth
	~bell.selectSynth(synth);
	~bell.changeKernels(encoder,decoder);
	~bell.changeMod(chorusMod, phaseMod, devMod, lpMod);
	~bell.changeSpace(push, hilbert, rRates, rAmt, dist, dRate, dModAmt);
	~bell.generateSequence(start, dur, phase, freq, numNotes, gain, cut, lp);

};

//////////////////
// SCORE
//////////////////

//playInitSound.value();
//playFinalSound.value();
playSound.value();



score.write(
	outputPath.standardizePath,
	sampleRate: sampleRate,
	headerFormat: headerFormat,
	sampleFormat: sampleFormat,
	options: ServerOptions.new.numOutputBusChannels_(numOutputChannels)
);
)
SFPlayer("~/Desktop/testCTK.wav".standardizePath).gui;

