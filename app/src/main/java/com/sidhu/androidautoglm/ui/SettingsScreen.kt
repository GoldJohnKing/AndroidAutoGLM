package com.sidhu.androidautoglm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sidhu.androidautoglm.R

// Updated by AI: Settings Screen with i18n support
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    apiKey: String,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit,
    onSave: (String) -> Unit,
    onBack: () -> Unit,
    onOpenDocumentation: () -> Unit
) {
    // If we have an existing key, start in "View Mode" (not editing), otherwise "Edit Mode"
    var isEditing by remember { mutableStateOf(apiKey.isEmpty()) }
    // The key being typed in Edit Mode
    var newKey by remember { mutableStateOf("") }
    
    // Visibility toggle only for the input field in Edit Mode
    var isInputVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!isEditing) {
                // View Mode: Show masked key + Edit button
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.api_key_label),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = stringResource(R.string.current_model, "autoglm-phone"),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = getMaskedKey(apiKey),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Button(
                            onClick = { isEditing = true },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text(stringResource(R.string.edit_api_key))
                        }
                    }
                }
            } else {
                // Edit Mode: Input field
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.api_key_label),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = stringResource(R.string.current_model, "autoglm-phone"),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        OutlinedTextField(
                            value = newKey,
                            onValueChange = { newKey = it },
                            label = { Text(stringResource(R.string.enter_api_key)) },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = if (isInputVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (isInputVisible)
                                    Icons.Filled.Visibility
                                else
                                    Icons.Filled.VisibilityOff

                                val description = if (isInputVisible) stringResource(R.string.hide_api_key) else stringResource(R.string.show_api_key)

                                IconButton(onClick = { isInputVisible = !isInputVisible }) {
                                    Icon(imageVector = image, contentDescription = description)
                                }
                            },
                            singleLine = true,
                            placeholder = { Text(stringResource(R.string.api_key_placeholder)) }
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = {
                                if (apiKey.isNotEmpty()) {
                                    isEditing = false
                                    newKey = ""
                                } else {
                                    onBack()
                                }
                            }) {
                                Text(stringResource(R.string.cancel))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { 
                                    if (newKey.isNotBlank()) {
                                        onSave(newKey)
                                        onBack() 
                                    }
                                },
                                enabled = newKey.isNotBlank()
                            ) {
                                Text(stringResource(R.string.save))
                            }
                        }
                    }
                }
            }

            // Language Switcher
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(R.string.language_label),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = currentLanguage == "zh",
                            onClick = { onLanguageChange("zh") }
                        )
                        Text(stringResource(R.string.language_chinese), modifier = Modifier.padding(start = 8.dp))
                        
                        Spacer(modifier = Modifier.width(24.dp))
                        
                        RadioButton(
                            selected = currentLanguage == "en",
                            onClick = { onLanguageChange("en") }
                        )
                        Text(stringResource(R.string.language_english), modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            // Documentation Link
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                onClick = onOpenDocumentation
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.view_documentation),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

private fun getMaskedKey(key: String): String {
    if (key.length <= 8) return "******"
    return "${key.take(4)}...${key.takeLast(4)}"
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        apiKey = "sk-...",
        currentLanguage = "en",
        onLanguageChange = {},
        onSave = {},
        onBack = {},
        onOpenDocumentation = {}
    )
}