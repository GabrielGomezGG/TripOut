package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TripDialog(
    name: String,
    photoUrl: String?,
    rating: Double? = null,
    userRating: Int? = null,
    address: String? = null,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
        ) {
            Column(
                Modifier.padding(8.dp),
            ) {
                TripItem(
                    name = name,
                    photoUrl = photoUrl,
                    rating = rating,
                    userRating = userRating,
                    address = address,
                    modifier = Modifier.fillMaxSize().weight(1f)
                )
                Button(
                    onClick = { onConfirm("$name, $address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(text = "Confirmar Viaje")
                }
            }

            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier
                    .align(Alignment.TopEnd).offset(x = 12.dp, y = (-12).dp)
            ) {
                Icon(
                    Icons.Outlined.Cancel,
                    contentDescription = Icons.Outlined.Cancel.toString(),
                    Modifier.size(40.dp)
                )
            }
        }
    }
}
