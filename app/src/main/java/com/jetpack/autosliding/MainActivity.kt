package com.jetpack.autosliding

import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.jetpack.autosliding.model.natural
import com.jetpack.autosliding.ui.theme.AutoSlidingTheme
import com.jetpack.autosliding.ui.theme.Purple500
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoSlidingTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AutoSliding()
                }
            }
        }
    }
}


@ExperimentalPagerApi
@Composable
fun AutoSliding() {
    val pagerState = rememberPagerState(
        pageCount = natural.size,
        initialOffscreenLimit = 2
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Purple500),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Auto Sliding",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 40.dp, 0.dp, 40.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 15.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                val natural = natural[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(
                            id = when (page) {
                                    1 -> R.drawable.image_1
                                    2 -> R.drawable.image_2
                                    3 -> R.drawable.image_3
                                    4 -> R.drawable.image_4
                                    5 -> R.drawable.image_5
                                    else -> R.drawable.image_1
                                }
                        ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(15.dp)
                    ) {
                        Text(
                            text = natural.title,
                            style = MaterialTheme.typography.h5,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        val ratingBar = RatingBar(
                            LocalContext.current, null, R.attr.ratingBarStyleSmall
                        ).apply {
                            rating = natural.rating
                            progressDrawable.setColorFilter(
                                android.graphics.Color.parseColor("#FF0000"),
                                PorterDuff.Mode.SRC_ATOP
                            )
                        }
                        AndroidView(
                            factory = { ratingBar },
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )

                        Text(
                            text = natural.desc,
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )
                    }
                }
            }
        }
        
        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}
