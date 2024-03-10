package com.yogeshseralia.blogappapis.exceptions

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class ResourceNotFoundException(
        private val resource: String,
        private val identifierName: String,
        private val identifierValue: Int) :
        RuntimeException(String.format("%s not found with %s : %l",
                resource,
                identifierName,
                identifierValue
        ))
