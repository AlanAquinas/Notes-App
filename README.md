# Notes App

This practice task is aimed at creating a note-taking application. To accomplish this task, you will use the Room and Navigation libraries. Your knowledge of LiveData and ViewModels will also come in handy at runtime.

## Task Description
Let's imagine that we need to implement a note-taking app.

- The app should have the possibility to store notes in the database.
- It should be possible to view, add, edit, and delete notes.
- Notes should contain a name, a date (the date of creation or the last update time), and the note text itself.
- For the items of the list, display only a name and a date. By tapping on the item of the list, open the details screen with all information about a note, and show buttons to edit or delete a note on the details screen.
- After editing or deleting a note, update the list of notes on the main screen.
- In the edit mode, it is impossible to change the name, so only the note text can be changed.

## Complete the Task
Create an app that will allow managing notes locally. You need to take the following steps:

- Create a database to store notes (use Room for it).
- Create a screen with the list of notes. Add navigation to the details screen.
- Add the details screen. There should be an option to edit or delete a note on the screen.
- After editing or removing notes, redirect to the notes list screen.
- For navigation between screens, use the Navigation library.
- Use LiveData and ViewModels to handle data loading and send them to the fragments.
