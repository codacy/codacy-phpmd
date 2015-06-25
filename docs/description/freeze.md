
Javascript allows you to extend native objects, which may sound appealing because your code will look better and simpler.
However, it can cause several problems, namely:

* In future versions, your method can accidentally override newly added built-ins
* If you are developing a library, your users may experience unexpected behaviour because your code is not contained
* It can lead to collisions and namespace conflicts.
      