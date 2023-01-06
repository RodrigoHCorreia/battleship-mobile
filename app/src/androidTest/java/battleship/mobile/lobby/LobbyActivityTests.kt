package battleship.mobile.lobby

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import battleship.mobile.info.ui.InfoScreenTag
import battleship.mobile.lobby.ui.LobbyScreenTag
import battleship.mobile.social.ui.SocialScreenTag
import battleship.mobile.ui.InfoButtonTag
import battleship.mobile.ui.OpenDrawerTag
import battleship.mobile.ui.SocialButtonTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LobbyActivityTests {

    @get:Rule
    val testRule = createAndroidComposeRule<LobbyActivity>()

    @Test
    fun lobby_screen_is_displayed() {
        testRule.onNodeWithTag(LobbyScreenTag).assertExists()
    }

    @Test
    fun pressing_drawer_button_opens_drawer() {

        // Act
        testRule.onNodeWithTag(OpenDrawerTag).performClick()
        testRule.waitForIdle()

        // Assert
        // TODO
    }

    @Test
    fun pressing_navigate_to_social_displays_social_activity() {

        // Act
        testRule.onNodeWithTag(SocialScreenTag).assertDoesNotExist()
        testRule.onNodeWithTag(OpenDrawerTag).performClick()
        testRule.waitForIdle()
        testRule.onNodeWithTag(SocialButtonTag).performClick()
        testRule.waitForIdle()

        // Assert
        testRule.onNodeWithTag(SocialScreenTag).assertExists()
    }

    @Test
    fun pressing_navigate_to_info_displays_info_activity() {

        // Act
        testRule.onNodeWithTag(InfoScreenTag).assertDoesNotExist()
        testRule.onNodeWithTag(OpenDrawerTag).performClick()
        testRule.waitForIdle()
        testRule.onNodeWithTag(InfoButtonTag).performClick()
        testRule.waitForIdle()

        // Assert
        testRule.onNodeWithTag(InfoScreenTag).assertExists()
    }

}