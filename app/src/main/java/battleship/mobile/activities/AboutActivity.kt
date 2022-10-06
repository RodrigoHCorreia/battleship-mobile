package battleship.mobile.activities

import AboutScreen
import AboutView
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import battleship.mobile.DependenciesContainer
import battleship.mobile.ServerInfoService
import battleship.mobile.data.appInfo
import battleship.mobile.ui.theme.BattleshipmobileTheme
class AboutActivity : ComponentActivity() {

    private val serverInfoService : ServerInfoService by lazy {
        (application as DependenciesContainer).serverInfoService;
    }

    @Suppress("UNCHECKED_CAST")
    private val vm by viewModels<DailyQuoteViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DailyQuoteViewModel(quoteService) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleshipmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AboutScreen()
                }
            }
        }
    }

}

