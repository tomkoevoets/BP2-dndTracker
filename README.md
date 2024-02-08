# BP2-dndTracker
<hr>
this D&amp;D tracker is a project made for school and all the Dungeon Masters out there.
<br/> 
the main function is to keep track of sessions, items, characters and encounters for a dungeon master. 

## Project Overview
<hr>
Welcome to the D&D Tracker, your indispensable companion for Dungeon Masters. This application ensures a smooth D&D gaming experience by allowing DMs to effortlessly manage sessions. Record session details and summaries, add monsters, NPCs, and items for quick reference. The Encounter Tracker simplifies on-the-fly management during encounters, displaying turn order and tracking statuses. Elevate your storytelling with this comprehensive tool, enhancing the immersive experience of Dungeons & Dragons for both the Dungeon Master and the players.
<br/>
<br/>
For Dungeon Masters (DMs), having immediate access to crucial information is vital. During a D&D game, DMs are continuously engaged in looking up information, which can lead to a slower pace that detracts from the fun of the game for both players and the DM. 
<br/>
Traditionally, DMs rely on D&D books or personal notes, but given the vast amount of data needed for a D&D campaign, having quick access is important.
<br/>
<br/>
The D&D Tracker addresses this issue head-on. It allows DMs to store all relevant information within the tracker, ensuring quick access to all the details they need.
<br/>
## Installation Instructions
<hr>

Follow the following steps, and you'll be ready to enhance your Dungeon Mastering experience with the D&D Tracker in no time!
<br/>
### Prerequisites
Make sure you have the following installed:

-  any working operating would suffice
-  an integrated development environment like Intellij
-  xampp

### Installation Steps

repository: https://github.com/tomkoevoets/BP2-dndTracker
1. Download the repository as a zip file
2. Extract the content of the zipfile
3. Open the project in your IDE (intellij)
4. Open xampp and click start at Apache and MySql
5. Click on the 'admin' button
6. Import the database file to the localhost phpMyAdmin
7. Run the application in intellij or open the [bp2-dnd-tracker.jar](bp2-dnd-tracker%2Fout%2Fartifacts%2Fbp2_dnd_tracker_jar%2Fbp2-dnd-tracker.jar)
8. The runable jar file can be found in [bp2-dnd-tracker](bp2-dnd-tracker) - [out](bp2-dnd-tracker%2Fout) - [artifacts](bp2-dnd-tracker%2Fout%2Fartifacts) - [bp2_dnd_tracker_jar](bp2-dnd-tracker%2Fout%2Fartifacts%2Fbp2_dnd_tracker_jar) - [bp2-dnd-tracker.jar](bp2-dnd-tracker%2Fout%2Fartifacts%2Fbp2_dnd_tracker_jar%2Fbp2-dnd-tracker.jar)
9. This jar file can be put on your desktop for quick acces to the application without having to run it threw an IDE

### Requirements
- Java Development Kit (JDK) installed, version 21.0.1 or higher. You can download it from Oracle's website.
<br/>
## Configuration
<hr>
 - there are no specific configurable options.
<br/>
 - at least java 8


## Usage Guide
<hr>
Once the tracker is started follow these steps to maximize your experience with the D&D Tracker:

### 1. Homescreen
read the welcome text on the homescreen to start your experience.

### 2. Sessions

The session screen displays all saved sessions. 

- To navigate to the session screen you can click on 'Sessions' on the navbar on the left side of the screen
- To view a session you can click on the session of choice and it will navigate to a screen which displays the session title, information and summary.
- On this screen there is a bin icon which will delete the session from the application and there is an update button which will navigate to the update screen of the currently chosen session.
- To delete the session you click on the bin icon and you will automatically be navigated to the session screen after. The session is deleted from the tracker
- To update the session after pressing the update button you simply make the desired changes and submit the updated session. You will automatically be navigated to the session screen after.
- to add a new session you can go to the bottom of the session screen where there is a button which says 'add session'. That button navigates you to the screen to add a new session. In this screen you fill in the required information and submit and the new session will show up on the session screen. 

### 3. Monsters

The monster screen displays all saved monsters. Monsters have to be linked to a session to use in the encounter tracker.

- To navigate to the monster screen you can click on 'Monsters' on the navbar on the left side of the screen
- To view a monster you can click on the monster of choice and it will navigate to a screen which displays the monster stats and information.
- On this screen there is a bin icon which will delete the monster from the application, there is a button to link a monster to a session and there is an update button which will navigate to the update screen of the currently chosen monster.
- To link a monster to a session you click the menu button and choose a session to link it to. You can link one monster to multiple sessions.
- To delete the monster you click on the bin icon and you will automatically be navigated to the monster screen after. The monster is deleted from the tracker.
- To update the monster after pressing the update button you simply make the desired changes and submit the updated monster. You will automatically be navigated to the monster screen after.
- to add a new monster you can go to the bottom of the monster screen where there is a button which says 'add monster'. That button navigates you to the screen to add a new monster. In this screen you fill in the required information and submit and the new monster will show up on the npc screen.

### 4. Npc's

The npc screen displays all saved npc's. Npc's have to be linked to a session to use in the encounter tracker.

- To navigate to the npc screen you can click on 'Npc's' on the navbar on the left side of the screen
- To view a npc you can click on the npc of choice and it will navigate to a screen which displays the npc stats and information.
- On this screen there is a bin icon which will delete the npc from the application, there is a button to link a npc to a session and there is an update button which will navigate to the update screen of the currently chosen npc.
- To link a npc to a session you click the menu button and choose a session to link it to. You can link one npc to multiple sessions.
- To delete the npc you click on the bin icon and you will automatically be navigated to the npc screen after. The npc is deleted from the tracker.
- To update the npc after pressing the update button you simply make the desired changes and submit the updated npc. You will automatically be navigated to the npc screen after.
- to add a new npc you can go to the bottom of the npc screen where there is a button which says 'add npc'. That button navigates you to the screen to add a new npc. In this screen you fill in the required information and submit and the new monster will show up on the npc screen.

### 5. Items

The item screen displays all saved items. 

- To navigate to the item screen you can click on 'Items' on the navbar on the left side of the screen
- To view a item you can click on the item of choice and it will navigate to a screen which displays the item information.
- On this screen there is a bin icon which will delete the item from the application and there is an update button which will navigate to the update screen of the currently chosen item.
- To delete the item you click on the bin icon and you will automatically be navigated to the item screen after. The item is deleted from the tracker
- To update the item after pressing the update button you simply make the desired changes and submit the updated item. You will automatically be navigated to the item screen after.
- to add a new item you can go to the bottom of the item screen where there is a button which says 'add item'. That button navigates you to the screen to add a new item. In this screen you fill in the required information and submit and the newitem will show up on the item screen.

### 6. Encounter

The encounter screen comes into play when the players get into an encounter and have to fight. The fighting in D&D is turn based. On this screen the DM can keep track of who is in the fight, what the fighting order, how much hp the players or characters have left and items can be added for quick acces.

- To navigate to the encounter screen you can click on 'Encounter' on the navbar on the left side of the screen
- to start you can choose a session using the menu button at the top of the screen. You can only choose characters in the tracker that are linked to the session you choose.
<br/>
<br/>
- In the initiative section you can add a character (monster or npc) by clicking on the 'add character' button. A new character section will pop up and you can add a character by clicking the menu button and choose a character that is linked to the previously choosen session.
- You can change the hp of the character using the HP bar. The HP bar will turn red when the character reaches 0HP.
- To see the character stats you can click on the character section after adding one.
- The character can be removed from the initiative section by clicking on the delete icon.
- You can also add a player. The player section works the same as the character section with the difference being that you add the player name to the text area.
- Once you have a couple character and players in the initiative section you can use the up/down buttons on the left to highlight and loop threw the sections so that you can always see whose turn it is within the fight.
<br/>
<br/>
- In the item section you can add an item using the menu button. this item does not have to be linked to a session.
- To see the item information you can click on the item section after adding one

## Roadmap
<hr>
Just like in the world of D&D the possebilities are endless to what can be added to this application to make it even better.

The future addations that i would like to see:

- Adding campaign as an entity. 
- Adding the possibility to give monsters, npc's and items a picture of choice.
- Adding cool widgets for map generating and picture generation.