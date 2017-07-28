# ScrollViewAdjustViewPager

this project is a sample code to demonstrate an issue with using ScrollView (NestedScrollView) in ViewPager. 
the problem is similar to a wellknown bug [AndroidBug5497](https://issuetracker.google.com/issues/36911528). 

the issue occors when I use a ViewPager and inside the first fragment of the ViewPager I have a another fragment that is parenting a sub fragment with ScrollView in it. to make it more visual:
```
┌------------┐
|   1        | 1 is the ViewPager fragment
| ┌---------┐|
| | 2       || 2 is the fragment inside ViewPager fragment
| |┌-------┐||
| ||3      ||| 3 is the sub fragment containing the ScrollView with EditText form
| ||form   |||
| ||here   |||
| ||       |||
| |└-------┘||
| └---------┘|
└------------┘
```

I have tested both `AdjustPan` and `AdjustResize`

***with adjustPan:*** 

issue is that when the soft keybaord is open the view adjusts but the scroll view cannot scroll till the end of the view and some of the content remains behind the keyboard. this behaviour changes when I select the edit texts from bottom of the form. in that case, the fragment 1 (viewpager) and fragment 2(sub fragment) move up and allow the scroll view to be show even the last element.

![AdjustResize in Small Screen](/ART/AjustResizeSmallScreen.png)
![AdjustResize in Small Screen keyboard open](/ART/AdjusResizeSmallSCreenInput.png)

***with AdjustResize:*** 

issue is that in small screens with less height. the entier fragment 3 with scrollview falls behind the soft keybaord.

<img src="/ART/AdjustPan.png" alt="alt text" width="200">

I have attempted to use the wellknown Workaround on the web. for this issue. but hasn't been able to create a acceptable output using the workaround.
there is also a copy of the [AndroidBug5497Workaround](/app/src/main/java/com/bagherifaez/app/scrollviewadjustviewpager/AndroidBug5497Workaround.java) in the repository.
