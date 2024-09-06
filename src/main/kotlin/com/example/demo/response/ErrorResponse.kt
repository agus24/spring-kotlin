package com.example.demo.response

import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
class ErrorResponse(
    //General error message about nature of error
    private val message: String, //Specific errors in API request processing
    private val details: List<String>
) 