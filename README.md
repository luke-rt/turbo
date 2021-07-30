# Turbo
> NOTE: This is basically a Java rip off of [@dchen327](https://github.com/dchen327)'s fantastic [pingmote](https://github.com/dchen327/pingmote) project
> PLEASE check it out if you want a better and better maintained project

A cross-platform emoji manager that lets you insert emojis into any app you want!

## Installation

### Cross-Platform
On any system just downloading the .jar file in [releases]() and doing `java -jar turbo.jar /path/to/assets` should do the trick.

The assets directory should be whatever folder contains the "original" and "resized" directories. For reference check the assets folder in this project

## How it works
Turbo uses:
- jnativehook([org.jnativehook](https://github.com/kwhat/jnativehook)) for global hotkeys
- Other built in libraries like javax.swing for its GUI

## TODO
- .exe, .dmg, and .AppImage files in releases
- better resizing(higher quality resized images, transparency)
- config file
