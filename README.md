# Ambisonic FM Bell Texture

The Ambisonic FM Bell Texture is a tool for supercollider that can be used to generate a variety of  bell-like textures in ambisonics. It was created in collaboration with the University of Washington [Center for Digital Arts and Experimental Media](https://dxarts.washington.edu/) as a compositional instrument, and as example code to showcase a variety of ambisonic techniques.


### Prerequisites   
* the [Ambsionic Toolkit](http://www.ambisonictoolkit.net/download/supercollider/) with [kernels](http://www.ambisonictoolkit.net/download/kernels/)
* [SuperCollider 3.8.0](http://supercollider.github.io/download.html)
* [Composer's Toolkit](https://github.com/supercollider-quarks/Ctk) can be installed using Quarks gui inside super collider
    *  Here's some more information on quarks and how to use them --> [Quarks Help](http://doc.sccode.org/Guides/UsingQuarks.html)
    
### Installing

1. Be sure you have correctly installed the software specified in Prerequisites.
2. Open SuperCollider, go to file tab and select 'Open User Support Directory'
3. Make a folder called Extensions in this directory if it does not already exist
4. Put BellExtensions files and HelpSource folder into Extensions
5. Inside of SuperCollider open the language tab, be sure interpreeter is booted and select 'Recompile Class Library'
6. Once installed, Example File.scd can be used test install. 
    
## Getting Started

The FM Bell Synthesizer utilizes a series of advance spatial techniques transform interpolating clusters of fm spectral familes

Example File contains some basic examples of sounds that can be produced. To create sounds, uncomment one of the following fuctions and run code.

// playInitSound.value();

// playFinalSound.value();

// playSound.value();

Output will be generated on the desktop as a wave file called called testCTK

For information on individual methods and arguments the Bell Texture help file can be found in the Help browser

## Built With

* [SuperCollider 3.8.0](http://supercollider.github.io/download) - Audio synthesis platform 
* [Ambisonic Toolkit](http://www.ambisonictoolkit.net/) - Tools to convert and manipulate audio in 3D space
* [Composer's Toolkit](https://github.com/supercollider-quarks/Ctk) - Used to sequence and render audio

## Resources 

“ATK SuperCollider Documentation.” 2016. http://www.ambisonictoolkit.net/documentation/supercollider/

   Ambisonic toolkit documentation for SuperCollider

Dodge, Charles, and Jerse, Thomas A. Computer Music : Synthesis, Composition, and Performance. 2nd ed. New York :
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; London: Schirmer Books ; Prentice Hall International, 1997.
 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Excellent book on computer music and composition. See Section 5.1 for an in depth look at FM 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;theory and application. 
   
“Introduction - the Ambisonic Toolkit Workflow.” 2016. http://www.ambisonictoolkit.net/documentation/workflow/.

   Introduction and visual representation of ambisonic workflow. A good place to start if you've never worked with ambisonics before.  

Leonard, John. “Welcome to the Wonderful World of Ambisonics - a Primer” January 7, 2016. 
   http://www.asoundeffect.com/ambisonics-primer/.
   
   Article providing description, history, and potential applications of ambisonics.

Malham, David G., and Anthony Myatt. "3-D Sound Spatialization Using Ambisonic Techniques." Computer Music Journal 19, no. 4 (1995):   
   58-70.
   
   In depth article on ambisonic theory and spatial processing techniques

Schottstaedt, Bill. “An Introduction to FM.” https://ccrma.stanford.edu/software/snd/snd/fm.html.

   Introduction to FM Sythesis. Provides description on what FM synthesis is, methods to control and manipulate spectra, and examples of FM instruments

## Author

* **James Wenlock** - [JamesWenlock](https://github.com/JamesWenlock)

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* This project would not have been possible without the guidence, generosity and support  University of Washington [Center for Digital Arts and Experimental Media](https://dxarts.washington.edu/).
* I would like to personally thank
    * **Dr.Joseph Anderson** for taking me on this quater. Acting as my mentor this quater, he taught me everything I know now about ambisonics, as well as a bunch of other things! He's absolutely brilliant and I'm so grateful for all the time, flexibility, resources and expertise. This project would not have happened without him!
    * **Dan Peterson** for all the help! He's debugged my code, helped me develope new techniques, as well as be a fantastic teacher throughout my sonic studies. This project would not have happened without him!
    * **Dr.Juan Pampin** for meeting with me throughtout the year, creating an incredible for education, and helping me reach my rather ambitious goals. Thanks so much for the incredible resources and opportunities  
    * **Dr.Ewa Trębacz** for getting me started in the labs and helping me get the resources needed to execute this project. I appreciate the time and thought!
