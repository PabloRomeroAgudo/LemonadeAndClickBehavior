package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var paso by remember { mutableStateOf(1) }
    var exprimir by remember { mutableStateOf(0) }

    when(paso) {
        1 -> Casos(
            imagen = R.drawable.lemon_tree,
            frase = R.string.select,
            descripcion = R.string.tree,
            onImageClick = {
                exprimir = (2..4).random()
                ++paso
            }
        )
        2 -> Casos(
            imagen = R.drawable.lemon_squeeze,
            frase = R.string.squeeze,
            descripcion = R.string.lemon,
            onImageClick = {
                exprimir--
                if (exprimir == 0) {
                ++paso
                }
            }
        )
        3 -> Casos(
            imagen = R.drawable.lemon_drink,
            frase = R.string.drink,
            descripcion = R.string.glass,
            onImageClick = {
                ++paso
            }
        )
        4 -> Casos(
            imagen = R.drawable.lemon_restart,
            frase = R.string.start,
            descripcion = R.string.empty,
            onImageClick = {
                paso = 1
            }
        )
    }
}

@Composable
fun Casos(
    imagen: Int,
    frase: Int,
    descripcion: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            modifier = modifier
                .height(80.dp)
        ) {
            Text(
                text = "Lemonade",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(249, 228, 75))
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = descripcion.toString(),
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(195, 236, 210))
                    .clickable{onImageClick.invoke()}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = frase),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonPreview() {
    LemonadeApp()
}