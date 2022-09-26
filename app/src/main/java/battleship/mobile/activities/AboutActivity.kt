package battleship.mobile.activities

import AboutView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import battleship.mobile.ui.theme.BattleshipmobileTheme

data class Author(
    val id: Int,
    val name : String,
    val email : String,
)

data class AppInfo(
    val version : String,
    val authors : List<Author>
)

val appInfo = AppInfo(
    version = "0.0.1",
    authors = listOf(
        Author(48281, "Adolfo Morgado", "a48281@alunos.isel.pt"),
        Author(48355, "Rodrigo Correia", "a48355@alunos.isel.pt")
    )
)

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleshipmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AboutView(appInfo);
                }
            }
        }
    }
}

