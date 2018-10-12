# CEG4110Hw2

This is the homework 2 assignment for CEG 4110.


# Function
The application is intended to run a MVC (Model-View-Controller) and Command type design pattern.
The clock will be displayed in two different views. One view is in a digital format and the other 
view will be as an Analog. The application is also intended to be able to use multiple views 
on the same screen simultaneously. The application will also display the date. The user will be
capable of changing the times and date according to whichever buttons they click on
for incrementing/decrementing the field values. (i.e.: +1hour, would increment the hour by 1,
-1hour, would decrement the hour by 1 for each click, etc.) 

# Issues

During my time creating this application I have came across some issues. One of the issues Ihave 
came across was attempting to figure out what kind of viewgroup I would use for the UI
of the main screen. I decided to use a scrolling constraint view for the buttons while leaving
the clocks at the top of the screen. Another issue I came across was to figure out how to properly 
implement the command design pattern for the application. 
# MVC design
# Model:

In the application, there will be a "CustomTime" class that will be represented as tehmodel fo the
the program. The class will have the initialization of the clocks that will set the default
time based on the system clock from the mobile device that is in use. There will also be a Handler
and Runnable object that will have the seconds field increment after each initialization by a second. 
# View:

For this application, the view is represented as the main UI of the application. This is where
 the user will be able to view the clocks on the main UI screen and use the buttons to make the 
 changes that they desire. With each button that is clicked, whether it's for incrementing/decrementing
 the clock and/or date field(s), the display for the updated views will display on the main screen. 
 
# Controller: 

The controller for the application will be represented as the redo/undo of the program for the clock commands. 
With each update to the CustomTime class, the data will pass through into the controllers of the application. 
# Command Design: 
The command design will be used for each time the button is clicked and the actions are executed

# Screenshots: 
