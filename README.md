RMonitor Leaderboard
====================

RMonitor Leaderboard is a swing application designed for displaying race summary information from an rmonitor feed over
tcp, such as the one provided by the MyLaps Orbits software.

![Screenshot](/docs/screenshot.png)

Protocol information was taken from here: http://www.imsatiming.com/software/Protocols/AMB%20RMonitor%20Timing%20Protocol.pdf

It displays summary information and a sortable table of the current competitors in the race. In addition, it will
provide an estimate of time or laps remaining (based on time to go / laps to go received from the rmonitor feed.)

In addition to the swing application, this repository contains library packages for handling rmonitor feed data.

PROJECT TODOS
------------------------
1. Improve Settings;
2. Create a new Screen to Configure Remote Server informations;
	* This screen will test the connection and save this information;
3. Implement the code to send data information to Remote Server;
	* In this first version, will be something simple, to have a lightweight server;
4. Make a installer
	* Windows (InnoSetup)
	* Debian/Ubuntu
	* Others distros (zip file)

Building the Application
------------------------

Current revision: 1.3
 
// TODO

Library Packages
----------------

### com.zacharyfox.rmonitor.entities

This package contains the models for the race and competitors.

### com.zacharyfox.rmonitor.message

This package contains classes for each type of message provided in the protocol, and a factory for creating the objects
from an ascii string. Example usage below (returns a Heartbeat):

	import com.zacharyfox.rmonitor.message.*

	String line = "$F,14,\"00:12:45\",\"13:34:23\",\"00:09:47\",\"Green\"";
	RMonitorMessage message = Factory.getMessage(line);

### com.zacharyfox.rmonitor.utils

#### Duration

Duration takes time values as strings ("00:01:23.456"), integers (milliseconds), or floats (seconds) supplied by the
feed and stores them as milliseconds.

	import com.zacharyfox.rmonitor.utils.Duration

	Duration duration = new Duration("00:01:23.456");

#### Connection

Connection extends Socket and contains a BufferedLineReader.

	import com.zacharyfox.rmonitor.utils.Connection;

	String ip = "127.0.0.1";
	Integer port = 50000;
	Connection connection = new Connection(ip, port);

	while ((line = connection.readLine()) != null) {
		System.out.println(line);
	}


	RELEASES
	------------------------
	1.0:
	- Added pom.xml to the Project.

	1.1 (2017/06/08):
	- Added Screen and all structure to configure remote server IP and PORT;

	1.2 (2017/06/26):
	- Added a JTable Model parser to JSON object;
	- Removed some sysout from code;
	
	1.3 (2017/07/11):
	- Added ASYNC POST to send data to server.
	- Fixed problems with Settings (will need improve this feature in the future).
	
