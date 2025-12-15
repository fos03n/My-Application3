package com.example.myapplication3


import android.widget.RadioButton
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TopView(onTapButton: () -> Unit) {
    Column {
        Box(contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = R.drawable.top),
                contentDescription = "hamburger"
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Orange900)) {
                        append("Colbar's")
                    }
                    append(" burger")
                },
                modifier = Modifier.padding(top = 43.dp),
                color = Lime600,
                fontSize = 50.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, blurRadius = 10f, offset = Offset(10f, 10f)
                    )
                )
            )
        }
        Image(
            painter = painterResource(id = R.drawable.middle),
            contentDescription = "hamburger"
        )
        val scrollState = rememberScrollState()
        val menuItems = listOf(
            Pair("classicbeef", R.drawable.classicbeef),
            Pair("spicychichen",R.drawable.spicychicken),
            Pair("vegetarian", R.drawable.vegetarian),
            Pair("barbecue", R.drawable.barbecue),
            Pair("hotdog", R.drawable.hotdog),
            Pair("coffee", R.drawable.coffee),
            Pair("nugget", R.drawable.nugget)
        )
        Row(
            modifier = Modifier.horizontalScroll(scrollState)
        ) {
            menuItems.forEach { menuItems ->
                MenuView(modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                    id = menuItems.second,
                    title = menuItems.first,
                    onClick = {onTapButton()})
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun TopViewPreview() {
    TopView(onTapButton = {})
}

@Composable
fun MenuView(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    title: String = "",
    onClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
        .clickable { onClick(id) }
        .padding(10.dp)
        .background(
            brush = Brush.linearGradient(
                colors = listOf(BaseColor, Orange400),
                start = Offset(0f, 80.dp.px),
                end = Offset(0f, 150.dp.px)
            ), shape = RoundedCornerShape(20.dp)
        ), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                title, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = id), contentDescription = null
            )
        }
    }
}

@Stable
inline val Dp.px: Float @Composable get(){
    val density = LocalDensity.current
    return with(density) { this@px.toPx()}
}

@Preview(showBackground = true)
@Composable
fun MenuViewpreview(){
    MenuView(
        modifier = Modifier.width(150.dp).height(150.dp),
        id = R.drawable.classicbeef, title = "classicbeef", onClick = {}

    )
}

@Composable
fun RadioButtonWithText(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .selectable(selected = selected, onClick = onSelect),
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(
            selected = selected, onClick = null
        )
        Text(
            text = text, style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonWithTextPreview(){
    RadioButtonWithText(text = "ハンバーガー", selected = true, onSelect = {})
}

@Composable
fun MainDishSection(){
    var selectedDish by remember { mutableStateOf("ハンバーガー") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ){
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .selectableGroup()
        ) {
            Text("メインを選択", style = MaterialTheme.typography.titleLarge)
            RadioButtonWithText(
                text = "ハンバーガー", selected = selectedDish == "ハンバーガー"
            ) { selectedDish = "ハンバーガー"}
            RadioButtonWithText(
                text = "チーズバーガー",selected = selectedDish == "チーズバーガー"
            ) {selectedDish = "チーズバーガー" }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun MainDishSectionPreview(){
    MainDishSection()
}

@Composable
fun SideMenuSection(){
    var frenchFries by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("サイドメニュー", style = MaterialTheme.typography.titleLarge)
            Row(
                modifier = Modifier.toggleable(
                    value = frenchFries,
                    onValueChange = {frenchFries = it}
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = frenchFries,
                    onCheckedChange = null
                )
                Text(
                    "フレンチフライ", style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun SideMenuSectionPreview(){
    SideMenuSection()
}
