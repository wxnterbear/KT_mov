package com.example.trivia


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.trivia.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class LogInTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LogIn::class.java)

    @Test
    fun logInTest() {
        val appCompatEditText = onView(
allOf(withId(R.id.Etext_correo),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
2),
isDisplayed()))
        appCompatEditText.perform(replaceText("nathy@gmail.com"), closeSoftKeyboard())
        
        val appCompatEditText2 = onView(
allOf(withId(R.id.Etext_correo), withText("nathy@gmail.com"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
2),
isDisplayed()))
        appCompatEditText2.perform(pressImeActionButton())
        
        val appCompatEditText3 = onView(
allOf(withId(R.id.Etxt_pass),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
3),
isDisplayed()))
        appCompatEditText3.perform(replaceText("1234"), closeSoftKeyboard())
        
        val appCompatEditText4 = onView(
allOf(withId(R.id.Etxt_pass), withText("1234"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
3),
isDisplayed()))
        appCompatEditText4.perform(pressImeActionButton())
        
        val materialButton = onView(
allOf(withId(R.id.btn_ingresar), withText("Ingresar"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
4),
isDisplayed()))
        materialButton.perform(click())
        
        val materialTextView = onView(
allOf(withId(R.id.txt_res4), withText("Kotlin"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
5),
isDisplayed()))
        materialTextView.perform(click())
        
        val materialTextView2 = onView(
allOf(withId(R.id.txt_res2), withText("Integrated Development Environment"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
3),
isDisplayed()))
        materialTextView2.perform(click())
        
        val materialTextView3 = onView(
allOf(withId(R.id.txt_res3), withText("Una plataforma de alojamiento de repositorios de código"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
4),
isDisplayed()))
        materialTextView3.perform(click())
        
        val materialTextView4 = onView(
allOf(withId(R.id.txt_res1), withText("Programación orientada a objetos"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
2),
isDisplayed()))
        materialTextView4.perform(click())
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
