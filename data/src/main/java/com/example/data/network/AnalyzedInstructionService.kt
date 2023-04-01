package com.example.data.network

import com.example.data.models.InstructionsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnalyzedInstructionService {
    @GET("{id}/analyzedInstructions")
    fun getInstruction(
        @Path("id") id: String,
        @Query("apiKey") token: String
    ): Call<List<InstructionsResponse>>
}