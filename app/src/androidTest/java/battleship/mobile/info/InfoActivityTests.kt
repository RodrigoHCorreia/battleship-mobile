package battleship.mobile.info

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import battleship.mobile.info.ui.InfoScreenTag
import battleship.mobile.ui.NavigateBackTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoActivityTests {

    @get:Rule
    val testRule = createAndroidComposeRule<InfoActivity>()

    @Test
    fun info_screen_is_displayed() {
        testRule.onNodeWithTag(InfoScreenTag).assertExists()
    }

    @Test
    fun pressing_navigate_back_finishes_activity() {

        testRule.onNodeWithTag(InfoScreenTag).assertExists()
        testRule.onNodeWithTag(NavigateBackTag).assertExists()

        // Act
        testRule.onNodeWithTag(NavigateBackTag).performClick()
        testRule.waitForIdle()

        // Assert
        testRule.onNodeWithTag(InfoScreenTag).assertDoesNotExist()
        assert(testRule.activityRule.scenario.state == Lifecycle.State.DESTROYED)
    }

}