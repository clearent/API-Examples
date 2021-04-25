# API Examples

## Configuration
Add your test api key to src/config/config.php

#### If you want to push changes to your local webserver as you edit and test these files locally follow the steps below. 
Requires node/npm https://nodejs.org/en/download/

## From a terminal/cmd window
npm install

### When npm install is complete run: 
gulp default


* gulp will copy these project files to c:/wamp/www/wordpress/wp-content/plugins/clearent-payments/

* if you installed lamp/wamp in a non-default location then you can edit the gulpfile to meet your lamp/wamp installation

* start you local lamp/wamp server and go to (be sure to include port if running running on port other than 80): 
http://localhost/ClearentPHP/transaction/runsale.php
