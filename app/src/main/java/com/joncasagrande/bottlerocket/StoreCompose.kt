package com.joncasagrande.bottlerocket

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.joncasagrande.bottlerocket.model.Store

@Composable
fun StoreCompose(store: Store) {
    Row {
        Text(store.name, fontSize = 16.sp, color )
    }
}


@Preview
@Composable
fun PreviewMessageCard() {
    StoreCompose(Store(1, "address", "city", "name", "2202", "logo", "939393", "MG", 0.0, 0.0))
}
