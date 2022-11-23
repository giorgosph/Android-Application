# Android Application
![Gameplay Screenshot](https://github.com/giorgosph/Android-Application/blob/main/intro.png "Gameplay Screenshot")
### About
This project is developed in **Android Studio** using Java. The application focuses on the development and design of a single-player game. Thus, the idea, the characters, the code, and the design of the arena are originally implemented. The game has a leaderboard, and saves the state of the levels and the score according to the provided id, using **SharedPreferences**. This means that the scores are saved on your device, if you play the game on another device your score will not be visible. In the repository you can access the code for observation only, to actually run the application and play the game follow the **"Instructions"** section below. The code is provided in the **"code"** folder and it's fully commented. You can see the results in the **"demo.mp4"**, and you can read the detailed analysis of the project in the **"report.pdf"**.

### Instructions
**NOTE:** The process was only tested for android devices, you can try it for other devices but it may not work. Allow access at all times.

1. Download the **"application.zip"** from the repository and extract it.
2. Connect your phone to your PC and copy the **"app-debug.apk"** in your phone.
3. Use your phone to find the file from your phone's storage and run it.
4. Allow access to download the app.
5. The application should be downloaded called **"The Spartan"**.

### Game Manual
##### Home Page & Levels
To start the game you have to provide an id of at least 3 characters long. You can view your high score by entering your id and clicking on the "View My Score" button.
To compute a score you must successfully complete all three levels, and the total score will equal the sum of the score you achieved for each level individually. Winning a level will unlock the next one, and winning the last level will lock all of the levels except level 1.
##### Gameplay
On the left-hand side use the grey buttons to move left and right accordingly. On the right-hand side use the yellow button to attack and the blue to jump. To kill enemies you have to tap the yellow button just before you collide with them. When you pick a gun you can shoot enemies from a short distance.


