import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import battleship.mobile.ui.theme.BattleshipmobileTheme

class RankingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleshipmobileTheme {

            }
        }
    }
}


