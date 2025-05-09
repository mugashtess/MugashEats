package com.mugash.mugasheats.ui.screens.recipe

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mugash.mugasheats.model.Recipe
import com.mugash.mugasheats.navigation.ROUT_ADD_RECIPE
import com.mugash.mugasheats.navigation.ROUT_RECIPE_LIST
import com.mugash.mugasheats.viewmodel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRecipeScreen(recipeId: String?, navController: NavController, viewModel: RecipeViewModel) {
    val context = LocalContext.current
    val recipeList by viewModel.allRecipes.observeAsState(emptyList())

    // Fix the id type issue here
    val recipe = remember(recipeList) { recipeList.find { it.id == recipeId?.toIntOrNull() } }

    // State variables
    var name by remember { mutableStateOf(recipe?.title ?: "") }
    var ingredients by remember { mutableStateOf(recipe?.ingredients ?: "") }
    var steps by remember { mutableStateOf(recipe?.steps ?: "") }
    var prepTime by remember { mutableStateOf(recipe?.time ?: "") }
    var imageUri by remember { mutableStateOf(recipe?.imagePath ?: "") }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it.toString()
            Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Recipe") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Recipe List") },
                            onClick = {
                                navController.navigate(ROUT_RECIPE_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add Recipe") },
                            onClick = {
                                navController.navigate(ROUT_ADD_RECIPE)
                                showMenu = false
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (recipe != null) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Recipe Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = ingredients,
                    onValueChange = { ingredients = it },
                    label = { Text("Ingredients") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = steps,
                    onValueChange = { steps = it },
                    label = { Text("Preparation Steps") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = prepTime,
                    onValueChange = { prepTime = it },
                    label = { Text("Preparation Time") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = rememberAsyncImagePainter(model = Uri.parse(imageUri)),
                    contentDescription = "Recipe Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                )

                Button(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Change Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // FIXED update button
                Button(
                    onClick = {
                        if (name.isNotBlank() && ingredients.isNotBlank() && steps.isNotBlank() && prepTime.isNotBlank() && imageUri.isNotBlank()) {
                            viewModel.updateRecipe(
                                recipe.copy(
                                    title = name,
                                    ingredients = ingredients,
                                    steps = steps,
                                    time = prepTime,
                                    imagePath = imageUri
                                )
                            )
                            Toast.makeText(context, "Recipe Updated!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF6F6A72))
                ) {
                    Text("Update Recipe", color = Color.White)
                }
            } else {
                Text(text = "Recipe not found", color = MaterialTheme.colorScheme.error)
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    }
}
