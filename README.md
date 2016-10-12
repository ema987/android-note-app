# NoteApp

This is a very simple project which shows how to use Dagger2 and interact with a REST repository using Retrofit.
It's not intended to have full functionality but to be taken as an example.
The project also shows the use of a custom view.

A little bit of explanation:

* The project was created using Android Studio and its Master/Detail flow example;
* I have then cleaned it from the things I didn't require (e.g. floating buttons);
* I then added the third party libraries I usually use.

The App has 2 views:
- list of all the notes of the user;
- details of a note.

Both the views have an header showing username, avatar, last login date.
In the details' view, the last login date is hidden (only purpose is to show how to use attributes on a custom view).

# Architecture
The project is based on MVP architecture realized without third party libraries (only Dagger for injection) and the repository pattern.

# Third party libraries
**Dagger2**  
Dagger is a fully static, compile-time dependency injection framework for both Java and Android.  
Link: https://google.github.io/dagger/

**Butter Knife**  
Field and method binding for Android views  
Link: https://jakewharton.github.io/butterknife/

**Retrofit**  
A type-safe HTTP client for Android and Java  
Link: https://square.github.io/retrofit/

**Retrofit mock response**  
Creates fake responses from JSON files  
Link: https://github.com/tientun/Android-Retrofit-Mock-Response

**Picasso**  
A powerful image downloading and caching library for Android  
Link: https://square.github.io/picasso/

**Guava**  
Guava: Google Core Libraries for Java  
Link: https://github.com/google/guava

**GreenDAO**  
greenDAO: Android ORM for your SQLite database  
Link: http://greenrobot.org/greendao/
