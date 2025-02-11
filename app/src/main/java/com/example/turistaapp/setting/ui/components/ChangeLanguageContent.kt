package com.example.turistaapp.setting.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.example.turistaapp.setting.data.LanguageApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeLanguageContent(
    languages: List<LanguageApp>,
    codeLanguage: String,
    onClick: (String) -> Unit,
) {
    var selectedLanguage by remember {
        mutableStateOf("")
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    LazyRow {
        items(languages) {
            FilterChip(
                onClick = {
                    selectedLanguage = it.code
                    showDialog = true
                },
                label = {
                    Text(it.name)
                },
                selected = codeLanguage == it.code,
                leadingIcon = if (it.isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                        )
                    }
                } else {
                    null
                },
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            icon = {
                Icon(Icons.Default.Translate, contentDescription = Icons.Default.Translate.name)
            },
            title = {
                Text(text = stringResource(id = R.string.change_language))
            },
            text = {
                Text(text = stringResource(R.string.message_dialog_setting))
            },
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        onClick(selectedLanguage)
                    },
                ) {
                    Text(stringResource(R.string.accept))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    },
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
        )
    }
}
