package com.example.week2

import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.week2.ui.theme.Week2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    val paymentMethods = listOf("PayPal", "Direct")
    val (selected, setSelected) = remember { mutableStateOf("PayPal") }
    val (sliderVal, setSliderVal) = remember { mutableStateOf(0) }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (donated, setDonated) = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Donation. 1.5")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                ),
            )
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Welcome Homer",
                    fontSize = 30.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(35.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(Modifier.selectableGroup()) {
                        Text(
                            text = "Please Give Generously",
                            fontSize = 25.sp,
                            color = Color.Gray
                        )

                        paymentMethods.map {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = selected == it,
                                    onClick = { setSelected(it) },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color.Blue
                                    ),
                                )
                                Text(text = it)
                            }
                        }
                    }

                    AndroidView(factory = { context ->
                        NumberPicker(context).apply {
                            value = sliderVal
                            minValue = 0
                            maxValue = 1000
                            setOnValueChangedListener { _, _, newVal ->
                                setSliderVal(newVal); setAmount(
                                    newVal.toString()
                                )
                            }
                        }
                    })
                }

                Spacer(modifier = Modifier.height(35.dp))

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(),
                    progress = sliderVal.toFloat() / 1000f,
                    color = Color.Blue
                )

                Spacer(modifier = Modifier.height(35.dp))

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "Amount",
                            fontSize = 25.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)
                        )
                        TextField(
                            value = amount,
                            onValueChange = { setAmount(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Blue,
                                unfocusedIndicatorColor = Color.Black,
                                cursorColor = Color.Blue,
                                textColor = Color.Black,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(35.dp))

                    Row(verticalAlignment = Alignment.Bottom) {
                        ElevatedButton(
                            onClick = {
                                val amountInt = if (amount == "") 0 else amount.toInt()
                                setDonated(donated + amountInt)
                                setAmount("")
                            },
                            contentPadding = PaddingValues(10.dp),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.Black,
                            ),
                        ) {
                            Text(
                                text = "DONATE"
                            )
                        }
                        Spacer(modifier = Modifier.width(35.dp))

                        Text(
                            text = "Total so far: $$donated",
                            fontSize = 25.sp
                        )
                    }
                }
            }

        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week2Theme {
        Greeting()
    }
}