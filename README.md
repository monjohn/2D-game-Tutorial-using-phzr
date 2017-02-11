# breakout-cljs

## Overview
This repo is an implementation of the [2D breakout game using Phaser](https://developer.mozilla.org/en-US/docs/Games/Tutorials/2D_breakout_game_Phaser)
in ClojureScript. I am comfortable with ClojureScript but have never delved into
game development and found this tutorial an easy introduction. Moreover, I also discovered [phzr](https://github.com/dparis/phzr), a ClojureScript wrapper for the Phaser HTML5 game framework, by [Dylan Paris](https://github.com/dparis). It hasn't been updated in a couple of years, but it seemed to work just fine. The advantage of using phzr is that you get all the benefits of cljs's interop facilities, but you also get to write idiomatic ClojureScript.

I followed the tutorial closely. I have created a tag for each step in the tutorial, so if you look above (and you are viewing this on github), and select the master branch, the dropdown will give you the option off viewing tags. Select step1 to view the code required after each step in the tutorial.

## Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL.

## License

Copyright © 2017

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
