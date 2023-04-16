package uz.osketchup.arts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.osketchup.arts.ui.theme.ArtsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtScreen()
                }
            }
        }
    }
}

fun isPreviousEnabled(currentIndex: Int): Boolean {
    return currentIndex > 0
}

fun isNextEnabled(currentIndex: Int, images: List<Int>): Boolean {
    return currentIndex < images.size - 1
}

@Composable
fun ArtScreen() {
    var currentIndex by remember { mutableStateOf(0) }
    val images = listOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.f,
        R.drawable.g,
    )
    val titles = mapOf(
        "Kite" to "Diane Arbus",
        "Stork" to "Gregory Colbert",
        "Swan" to "Tim Walker",
        "Quail" to "Martin Parr",
        "Sparrow" to "Alex Prager",
        "Dove" to "Sally Mann"
    ).toList()


    Column(
        modifier = Modifier.padding(18.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RectangleShape
            )
        ) {
            Image(
                painter = painterResource(id = images[currentIndex]),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Crop, modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(32.dp)
            )
        }

        Column(
            modifier = Modifier
                .shadow(
                    4.dp,
                    ambientColor = MaterialTheme.colorScheme.primary,
                    shape = RectangleShape
                )
                .padding(8.dp)
                .padding(12.dp),

            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = titles[currentIndex].first,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(150.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(color = Color.LightGray, offset = Offset(5.0f, 10.0f), 3f)
                ), color = MaterialTheme.colorScheme.primary, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.SemiBold
            )
            Text(
                text = titles[currentIndex].second,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(150.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            SwitchButton(
                modifier = Modifier.weight(1f),
                R.string.previous,
                isEnabled = isPreviousEnabled(currentIndex)
            ) {
                (currentIndex--).coerceAtLeast(0)


            }
            SwitchButton(
                modifier = Modifier.weight(1f),
                R.string.next,
                isEnabled = isNextEnabled(currentIndex, images)
            ) {
                (currentIndex++).coerceAtMost(images.size - 1)
            }
        }
    }


}

@Composable
fun SwitchButton(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick, modifier = modifier
            .fillMaxWidth()
            .padding(8.dp), enabled = isEnabled
    ) {
        Text(
            text = stringResource(id = label)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtsTheme {
        ArtScreen()
    }
}