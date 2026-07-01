package com.example.myapplication1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.ui.theme.MyApplication1Theme

@Composable
fun ProblemTypeDropdown(
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    val items = listOf(
        stringResource(R.string.no_water),
        stringResource(R.string.dirty_water),
        stringResource(R.string.sanitation_issue)
    )

    Column(modifier = modifier.fillMaxWidth()) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(if (expanded) Color.White else Color(0xFF3096A7))
                .border(3.dp, Color.Black, RectangleShape)
                .clickable { expanded = !expanded }
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selectedItem.ifEmpty { stringResource(R.string.problem_type_label) },
                    color = if (expanded) Color.Black else Color.White,
                    fontSize = 16.sp
                )
                Icon(
                    painter = painterResource(id = if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Black
                )
            }
        }

        if (expanded) {
            Column(modifier = Modifier.fillMaxWidth()) {
                items.forEach { item ->
                    val isSelected = item == selectedItem
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(if (isSelected) Color(0xFF81D4FA) else Color.White)
                            .border(2.dp, Color.Black, RectangleShape)
                            .clickable {
                                selectedItem = item
                                expanded = false
                                onItemSelected(item)
                            }
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item,
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProblemTypeDropdownPreview() {
    MyApplication1Theme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(300.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Closed State
            ProblemTypeDropdown()

            // Expanded State (MOCKED manually for preview as we can't easily force 'expanded' state in a single preview without multiple components)
            // But we can create another preview or a stateful preview.
            MockExpandedDropdown()
        }
    }
}

@Composable
fun MockExpandedDropdown() {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(Color.White)
                .border(3.dp, Color.Black, RectangleShape)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Problem Type",
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Black
                )
            }
        }
        
        // Item 1
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.White)
                .border(2.dp, Color.Black, RectangleShape)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No Water", fontSize = 14.sp)
        }
        // Item 2 (Selected)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFF81D4FA))
                .border(2.dp, Color.Black, RectangleShape)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Dirty Water", fontSize = 14.sp)
        }
        // Item 3
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.White)
                .border(2.dp, Color.Black, RectangleShape)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Sanitation Issue", fontSize = 14.sp)
        }
    }
}
