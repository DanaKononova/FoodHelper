package com.example.foodhelper.data.source

import com.example.foodhelper.data.db.instructions.EquipmentIngredientsEntity
import com.example.foodhelper.data.db.instructions.InstructionsDao
import com.example.foodhelper.data.db.instructions.InstructionsEntity
import com.example.foodhelper.data.db.nutrition.NutritionDao
import com.example.foodhelper.data.db.nutrition.NutritionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InstructionsDataBaseSource @Inject constructor(
    private val instructionsDao: InstructionsDao
) {
    suspend fun getAllInstructions() = withContext(Dispatchers.IO) {
        instructionsDao.getAllInstructions()
    }

    suspend fun getAllEquipmentIngredients() = withContext(Dispatchers.IO) {
        instructionsDao.getAllEquipmentIngredients()
    }

    suspend fun insertAllInstructions(instructions: InstructionsEntity) = withContext(Dispatchers.IO) {
        instructionsDao.insertAllInstructions(instructions)
    }

    suspend fun insertAllEquipmentIngredients(instructions: List<EquipmentIngredientsEntity>) = withContext(Dispatchers.IO) {
        instructionsDao.insertAllEquipmentIngredients(instructions)
    }

    suspend fun deleteInstructions(instructions: List<InstructionsEntity>) = withContext(Dispatchers.IO) {
        instructionsDao.deleteInstructions(instructions)
    }

    suspend fun deleteEquipmentIngredients(instructions: List<EquipmentIngredientsEntity>) = withContext(Dispatchers.IO) {
        instructionsDao.deleteEquipmentIngredients(instructions)
    }
}