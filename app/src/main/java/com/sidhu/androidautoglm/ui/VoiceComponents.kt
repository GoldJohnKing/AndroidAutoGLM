package com.sidhu.androidautoglm.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidhu.androidautoglm.R

@Composable
fun RecordingIndicator(soundLevel: Float) {
    // Infinite breathing animation to show activity even if sound level is low
    val infiniteTransition = rememberInfiniteTransition(label = "breathing")
    val breathingScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathingScale"
    )

    // Sound level based scaling (more responsive)
    // Map -60dB (silence) to -10dB (loud speech) to 0.0 - 1.0
    // Using a wider range to capture softer voices too
    val normalizedLevel = ((soundLevel + 60f) / 50f).coerceIn(0f, 1f)
    
    // Apply non-linear curve to make small changes more visible
    // Map 0..1 to 1.0..2.0 range
    val volumeScale = 1f + (normalizedLevel * normalizedLevel * 1.0f)
    
    val animatedVolumeScale by animateFloatAsState(
        targetValue = volumeScale,
        animationSpec = tween(durationMillis = 50), // Faster response
        label = "volumeScale"
    )
    
    // Combine breathing and volume
    val finalScale = maxOf(breathingScale, animatedVolumeScale)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .pointerInput(Unit) {}, // Block touches
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(200.dp) // Slightly larger container
                .background(Color.DarkGray.copy(alpha = 0.9f), shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(100.dp)
            ) {
                // Outer ripple/volume effect
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .graphicsLayer {
                            scaleX = finalScale
                            scaleY = finalScale
                            alpha = 0.4f
                        }
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                )
                
                // Inner breathing circle (always active)
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .graphicsLayer {
                            scaleX = breathingScale
                            scaleY = breathingScale
                            alpha = 0.6f
                        }
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                )
                
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.voice_listening),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun VoiceReviewOverlay(
    text: String,
    onTextChange: (String) -> Unit,
    onCancel: () -> Unit,
    onSend: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .pointerInput(Unit) {}, // Block clicks
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Text Bubble
            Surface(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                color = Color(0xFF95EC69), // WeChat Green
                shadowElevation = 8.dp
            ) {
                TextField(
                    value = text,
                    onValueChange = onTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Cancel Button
                IconButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = stringResource(R.string.voice_cancel),
                        tint = Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Send Button
                IconButton(
                    onClick = onSend,
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = stringResource(R.string.send_button),
                        tint = Color(0xFF95EC69), // WeChat Green
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
