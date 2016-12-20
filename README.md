# Ambisonic FM BELL SYNTHESIZER

The Ambisonic FM Bell Synthesizer is a tool for supercollider that can be used to generate a variety of  bell-like textures in ambisonics. It was created in collaboration with the UW Digital Arts Department as a compositional instrument, and as example code to showcase a variety of ambisonic techniques.


### Prerequisites   
* the [Ambsionic Toolkit](http://www.ambisonictoolkit.net/download/supercollider/) with [kernels](http://www.ambisonictoolkit.net/download/kernels/)
* [SuperCollider 3.8.0](http://supercollider.github.io/download.html)
* [Composer's Toolkit](https://github.com/supercollider-quarks/Ctk) can be installed using Quarks gui inside super collider
    *  Here's some more information on quarks and how to use them --> [Quarks Help](http://doc.sccode.org/Guides/UsingQuarks.html)
    
### Installing

1. Be sure you have correctly installed the software specified in Prerequisites.
2. Open SuperCollider, go to file tab and select 'Open User Support Directory'
3. Make a folder called Extensions in this directory if it does not already exist
4. Put BellExtensions files and HelpSource folder to Extensions
5. Inside of SuperCollider open the language tab, be sure interpreeter is booted and select 'Recompile Class Library'
6. Once installed, Example File.scd can be used test install. 
    
## Getting Started

The FM Bell Synthesizer utilizes a series of advance spatial techniques transform interpolating clusters of fm spectral familes

Example File contains some basic examples of sounds that can be produced. To create sounds, uncomment one of the following fuctions and run code.

// playInitSound.value();
// playFinalSound.value();
// playSound.value();

Output will be generated on the desktop as a wave file called called testCTK

For information on individual methods and arguments. A Bell Texture help file can be found in the Help browser

## Built With

* [SuperCollider 3.8.0](http://www.dropwizard.io/1.0.2/docs/) - Audio synthesis platform 
* [SuperCollider 3.8.0](http://www.dropwizard.io/1.0.2/docs/) - Audio synthesis platform 
* [SuperCollider 3.8.0](http://www.dropwizard.io/1.0.2/docs/) - Audio synthesis platform 
* [Ambisonic Toolkit](http://www.ambisonictoolkit.net/) - Tools to convert and manipulate audio in 3D space
* [Composer's Toolkit](https://github.com/supercollider-quarks/Ctk) - Used to sequence and render audio

## Resources 

* [Concise explanation of Ambisonics](http://www.asoundeffect.com/ambisonics-primer/)
* [Introduction to Ambisonic Workflow](http://www.ambisonictoolkit.net/documentation/workflow/)
* [ATK SuperCollider Documentation](http://www.ambisonictoolkit.net/documentation/supercollider/)
* [Video demonstation of BellTexture](https://www.youtube.com/playlist?list=PL2y-mQQ-qX1Vh9s9Oa7cxzyVMRNHIYBb2)
* [3D Sound Spatialization Using Ambisonic Techniques](http://www.samdrazin.com/classes/mmi505/3-d_sound_spatialization_using_ambisonic_techniques.pdf)
    * Article on ambisonic theory and spatial processing techniques
* [Basic overview of FM Synthesis](http://synthesizeracademy.com/fm-synthesis/)
    * A primer on FM synthesis
* [Dodge and Jerse](https://books.google.com/books?id=eY_BQgAACAAJ&hl=en)
    *Excellent book on computer music and composition. Section 5.1 contains an in depth look at FM theory and application

## Author

* **James Wenlock** - [JamesWenlock](https://github.com/JamesWenlock)

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* This project would not have been possible without the guidence, generosity and support University of Washington's Digital Arts deparment.
* I would like to personally thank
    * **Dr.Joseph Anderson** for taking me on this quater. Acting as my mentor this quater, he taught me everything I know now about ambisonics, as well as a bunch of other things! He's absolutely brilliant and I'm so grateful for all the time, flexibility, resources and expertise. This project would not have happened without him!
    * **Dan Peterson** for all the help! He's debugged my code, helped me develope new techniques, as well as be a fantastic teacher throughout my sonic studies. This project would not have happened without him!
    * **Dr.Juan Pampin** for meeting with me throughtout the year, creating an incredible for education, and helping me reach my rather ambitious goals. Thanks so much for the incredible resources and opportunities  
    * **Dr.Ewa Trębacz** for getting me started in the labs and helping me get the resources needed to execute this project. I appreciate the time and thought!
