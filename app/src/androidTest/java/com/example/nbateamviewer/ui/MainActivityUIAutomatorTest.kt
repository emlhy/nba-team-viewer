package com.example.nbateamviewer.ui

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.security.AccessController.getContext

@RunWith(AndroidJUnit4::class)
class MainActivityUIAutomatorTest {
    companion object{
        private val LAUNCH_TIMEOUT: Long = 5000
        private val PACKAGE_NAME = "com.example.nbateamviewer"
    }
    private lateinit var uiDevice: UiDevice

    @Before
    fun launchApp(){
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.pressHome()
        val launcherPackage = uiDevice.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        uiDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT)

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT)

        @Synchronized
        fun synchronizedMethod() {
            Thread.sleep(2000)
        }

    }

    @Test
    fun test_canSortList() {
        val nameTitle = uiDevice.findObject(UiSelector().resourceId("com.example.nbateamviewer:id/teamNameText"))
        nameTitle.click()

        val firstTeam = uiDevice.findObject(UiSelector().resourceId("com.example.nbateamviewer:id/teamName").index(0))
        assertTrue(firstTeam.text == "Washington Wizards")
    }

}