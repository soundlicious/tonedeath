package dev.pabloexposito.ui

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScoreComponent(modifier: Modifier = Modifier, score: Int) {
    var offset by remember {
        mutableFloatStateOf(0f)
    }
    val maxTranslation = 8.dp

    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(stringResource(id = R.string.practice_score), " ")
        }
        append("%03d".format(score))
    }
    Row(modifier = modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
        Image(
            modifier = Modifier
                .graphicsLayer {
                    translationY = offset * maxTranslation.toPx()
                },
            painter = painterResource(id = R.drawable.ic_coin), contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }

    LaunchedEffect(key1 = score) {
        if (score != 0) {
            val animationSpecs = tween<Float>(
                durationMillis = 200,
                easing = FastOutLinearInEasing
            )
            coroutineScope {
                launch {
                    animate(
                        initialValue = 0f,
                        targetValue = 0.3f,
                        animationSpec = animationSpecs
                    ) { value, _ ->
                        offset = value
                    }
                    animate(
                        initialValue = 0.3f,
                        targetValue = -1f,
                        animationSpec = animationSpecs
                    ) { value, _ ->
                        offset = value
                    }
                    animate(
                        initialValue = -1f,
                        targetValue = 0f,
                        animationSpec = animationSpecs
                    ) { value, _ ->
                        offset = value
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ScoreComponentPreview() {
    AppTheme {
        ScoreComponent(score = 1)
    }
}