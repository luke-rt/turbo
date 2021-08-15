# Turbo
> NOTE: This is basically a Java rip off of [@dchen327](https://github.com/dchen327)'s fantastic [pingmote](https://github.com/dchen327/pingmote) project
> PLEASE check it out if you want a better and better maintained project and not just a java practicing repository

A cross-platform emoji manager that lets you insert emojis into any app you want!

## Installation
### Cross-Platform
On any system just downloading the .jar file in [releases](https://github.com/ltgr/turbo/releases) and doing `java -jar turbo.jar /path/to/directory` should do the trick.

This gives turbo access to the directory containing the image source files as well as the turbo.conf file.
If you move the .turbo directory, be sure to update your running script to the new directory path.

## Usage
### 1. The config file
The config file stores all the settings that turbo.conf will follow. The default config file can be found [here](https://github.com/ltgr/turbo/blob/master/.turbo/turbo.conf). All settings are explained in the file so if you want to change anything, do so there.

### 2. Importing Images
Place any images you want resized in the "original" directory. Be sure that it has a unique name, otherwise turbo won't recognize it as a new image. Turbo fills the resized directory by itself so only change the images in the original directory.

### 3. Done!
Run the jar file and type the open hotkey(alt + w by default). This should show the window as well as any emojis in the resized directory. Click on any emoji to get started.

Please report any bugs to the [issues page](https://github.com/ltgr/turbo/issues).

## Dependencies
Turbo uses:
- jnativehook([org.jnativehook](https://github.com/kwhat/jnativehook)) for global hotkeys
- ini4j([org.ini4j](http://ini4j.sourceforge.net/)) for config file parsing
- imgscalr([org.imgscalr](https://github.com/rkalla/imgscalr)) for image resizing
- Other built in libraries like javax.swing for its GUI

## Contributing
### Building
Clone the repo and in the root level of the `turbo` directory, run
```sh
mvn package -f pom.xml
```
> NOTE: requires JDK 16 or above

If you have any suggestions, please open a feature request in issues or start a pull request

## TODO
- .exe, .dmg, and .AppImage files for releases
- error checking for config file, ie max values and int vs strings
- Better GUI

## License
[MIT License](https://github.com/ltgr/turbo/blob/master/LICENSE)
