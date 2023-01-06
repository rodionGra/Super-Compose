package com.supercompose.network.multiplebaseurl.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supercompose.network.multiplebaseurl.data.ApiType
import com.supercompose.network.multiplebaseurl.data.DeploymentType

@Composable
fun DynamicBaseUrlScreen(viewModel: DynamicBaseUrlViewModel) {
    val result = viewModel.resultFlow.collectAsState().value

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(modifier = Modifier.padding(8.dp), text = DESCRIPTION, fontSize = 12.sp)
        Button(onClick = {
            viewModel.login()
        }) {
            Text(text = "Login")
        }
        Button(onClick = {
            viewModel.getContents()
        }) {
            Text(text = "Contents")
        }
        Button(onClick = {
            viewModel.pay()
        }) {
            Text(text = "Pay")
        }
        viewModel.apiTypes.forEach { apiType ->
            Spinner(apiType, viewModel.deploymentTypes) { deploymentType ->
                viewModel.updateEnvironment(apiType, deploymentType)
            }
        }
        Text(text = "Result = $result")
    }
}

@Composable
fun Spinner(apiType: ApiType, items: List<DeploymentType>, onClick: (DeploymentType) -> Unit) {
    var selectedText by remember { mutableStateOf(items.firstOrNull()?.name ?: "List is empty") }
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(Modifier
            .padding(24.dp)
            .clickable {
                expanded = !expanded
            }
            .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = apiType.name, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Text(text = selectedText, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(
                expanded = expanded, onDismissRequest = { expanded = false }
            ) {
                items.forEach {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedText = it.name
                        val newDeploymentType = DeploymentType.values().find { type ->
                            type == it
                        }
                        newDeploymentType?.let(onClick)
                    }) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

private const val DESCRIPTION =
    """Think about having a lot of services on the backend and multiple base urls for our mobile client. How can we handle this? Overwrite the url? Absolutely no. Let’s dive too deep.

In this case, we use a different base url each environment type.

alpha → prefix: “alpha-”
beta → prefix: “beta-”
production → prefix: none
Also, we have multiple services.

auth operations → “auth.mydomain.com”
pay services → “pay.mydomain.com”
app content → “mydomain.com”
We don’t want to specify urls for each retrofit endpoint manually. So, I can use Custom Annotations to manage urls. It makes it easy.
"""
