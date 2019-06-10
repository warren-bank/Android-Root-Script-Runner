#### [Root Script Runner](https://github.com/warren-bank/Android-Root-Script-Runner)

Android app that runs shell scripts as the _root_ user on a __rooted__ Android device.

#### Use Cases:

1. interactive
   * uses a file picker to allow the user to select a shell script from the file system
   * executes the chosen script as the _root_ user
2. _Intent_ driven
   * reads the file path to a shell script in the __data URI__ of an _Intent_
   * executes the chosen script as the _root_ user
   * exits

#### Example:

* configuration to execute a specific shell script from a homescreen shortcut using [AnyCut](https://github.com/warren-bank/Android-libraries/tree/nujham/AnyCut):
  * _Make your own shortcut:_
    * __Action:__<br>`com.github.warren_bank.root_script_runner.ACTION_WIDGET`
    * __Data:__<br>`file:/sdcard/path/to/script/file.sh`
    * __Type:__<br>_[empty]_

#### Notes:

* minimum supported version of Android:
  * Android 4.0 Ice Cream Sandwich (API 14)

#### Credits:

* [launcher icon](https://github.com/odb/official-bash-logo/raw/61eff022f2dad3c7468f5deb4f06652d15f2c143/assets/Logos/Icons/PNG/256x256.png)
  * copied from the [Official GNU Bash Logo](https://github.com/odb/official-bash-logo) project repo
  * released under the [MIT License](https://github.com/odb/official-bash-logo/blob/61eff022f2dad3c7468f5deb4f06652d15f2c143/LICENSE)
* [MaterialFilePicker](https://github.com/nbsp-team/MaterialFilePicker)
  * great little library to browse the file system and select a file
    * used as a starting point, but [heavily modified](https://github.com/warren-bank/Android-libraries/tree/fork/nbsp-team/MaterialFilePicker/02_androidx)
* [Shell.java](https://gist.github.com/ricardojlrufino/61dbc1e9a8120862791e71287b17fef8/raw/adfbf58830886eceb79fb7dd93747f7c07e542b2/Shell.java)
  * authored by [Ricardo JL Rufino](https://github.com/ricardojlrufino)
  * a utility class containing static methods for running commands as root user
    * used as a starting point, but [heavily modified](https://github.com/warren-bank/Android-libraries/tree/ricardojlrufino/Shell)

#### Related Reading:

* "How To" article on XDA: [Execute Root Commands and Read Output](https://forum.xda-developers.com/showthread.php?t=2226664)
  * authored by [pedja1](https://forum.xda-developers.com/member.php?u=4303594)

#### Legal:

* copyright: [Warren Bank](https://github.com/warren-bank)
* license: [GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt)
